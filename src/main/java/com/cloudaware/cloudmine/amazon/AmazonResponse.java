package com.cloudaware.cloudmine.amazon;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.AmazonWebServiceResult;
import org.apache.http.conn.ConnectTimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.SocketTimeoutException;

/**
 * User: urmuzov
 * Date: 03.17.17
 * Time: 16:27
 */
public class AmazonResponse<T extends AmazonWebServiceResult> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AmazonResponse.class);
    private static final int HTTP_TEMPORARY_UNAVAILABLE = 503;
    private static final int HTTP_GATEWAY_TIMEOUT = 504;
    private AmazonException exception;
    private String nextPage;

    public AmazonResponse() {
    }

    public AmazonResponse(final String nextPage) {
        this.nextPage = nextPage;
    }

    public AmazonResponse(final AmazonException exception) {
        this.exception = exception;
    }

    public static AmazonException parse(final Throwable t, final String action) throws AmazonUnparsedException {
        LOGGER.info("Exception during AWS API Client call. {}: {}", t.getClass().getName(), t.getMessage());
        if (t instanceof AmazonServiceException) {
            final AmazonServiceException ex = (AmazonServiceException) t;
            final AmazonServiceException.ErrorType errorType = ex.getErrorType();
            final String errorCode = ex.getErrorCode() == null ? "" : ex.getErrorCode();
            final String errorMessage = ex.getErrorMessage() == null ? "" : ex.getErrorMessage();
            final int statusCode = ex.getStatusCode();
            /**
             * All errors that might be related to access permissions
             * SlowDown multiplier: 2, Category: NO_ACCESS
             */
            if ("AccessDeniedException".equals(errorCode)
                    || "AccessDenied".equals(errorCode)
                    || "AuthFailure".equals(errorCode)
                    || "UnauthorizedOperation".equals(errorCode)
                    || "AuthorizationError".equals(errorCode)
                    || "UnrecognizedClientException".equals(errorCode)
                    || "InsufficientPrivilegesException".equals(errorCode)
                    || "InvalidClientTokenId".equals(errorCode)
                    || "InvalidAccessKeyId".equals(errorCode)
                    || ("InvalidParameterValue".equals(errorCode) && errorMessage.startsWith("Access Denied")) // Strange exception from Beastalk which contains another exception in message
                    || ("InvalidParameterValue".equals(errorCode) && errorMessage.contains("is not authorized to perform")) // another Beanstalk exception
                    ) {
                return new AmazonException(AmazonException.Category.NO_ACCESS, action, ex);
            }
            /**
             * Throttling-related exceptions
             * SlowDown multiplier: 2, Category: THROTTLING
             */
            if ("Throttling".equals(errorCode)
                    || "ThrottlingException".equals(errorCode)
                    || "TooManyRequestsException".equals(errorCode)
                    || "RequestLimitExceeded".equals(errorCode)
                    || "LimitExceededException".equals(errorCode)
                    || "RequestThrottled".equals(errorCode)) {
                return new AmazonException(AmazonException.Category.THROTTLING, action, ex);
            }
            /**
             * Exceptions related to service unavailability
             * SlowDown multiplier: 2, Category: SERVICE_DISABLED
             */
            if ("SubscriptionRequiredException".equals(errorCode)
                    || "NotSignedUp".equals(errorCode)
                    || "OptInRequired".equals(errorCode)
//                  for things like this: com.amazonaws.services.elasticache.model.InvalidParameterValueException: Use of cache security groups is not permitted in this API version for your account.
//                  (Service: AmazonElastiCache; Status Code: 400; Error Code: InvalidParameterValue; Request ID: 73256403-8129-11e4-82b5-c9a953ea49b6)
                    || ("InvalidParameterValue".equals(errorCode) && errorMessage.contains("not permitted in this API version"))
//                  for things like: At this time, Reserved Instances are not available to purchase for customers contracting with Amazon Internet Services Private Limited (AISPL).
//                  We will notify you by email when they become available later this year.
//                  (Service: AmazonEC2; Status Code: 400; Error Code: UnsupportedOperation; Request ID: dad30187-e5f7-426b-9f0e-5f33865ec864)
                    || ("UnsupportedOperation".equals(errorCode) && errorMessage.contains("Amazon Internet Services Private Limited (AISPL)"))
//                  Starting August 1 2017, you won't be able to view or manage (except terminate) Elastic Beanstalk environments running legacy platforms.
//                  However, your existing resources (such as EC2 instances, auto-scaling groups etc.) and assets deployed to these resources, via these Elastic Beanstalk environments,
//                  will continue to function as expected, and you can continue to manage these resources using the respective service consoles.
//                  (Service: AWSElasticBeanstalk; Status Code: 400; Error Code: InvalidParameterValue; Request ID: ba27e3a6-891e-11e7-a9f0-f3be31bf2ff0)
                    || ("InvalidParameterValue".equals(errorCode) && errorMessage.startsWith("Starting August 1 2017, you won't be able to view or manage (except terminate) "
                    + "Elastic Beanstalk environments running legacy platforms"))
                    || "AWSOrganizationsNotInUseException".equals(errorCode)
                    || ("UnsupportedOperation".equals(errorCode) && errorMessage.contains("The operation is not supported in this region!"))
                    ) {
                return new AmazonException(AmazonException.Category.SERVICE_DISABLED, action, ex);
            }
            /**
             * Exception that signals that specific object not found in AWS
             * SlowDown multiplier: 2, Category: OBJECT_NOT_FOUND
             */
            if ("InvalidNetworkInterfaceID".equals(errorCode)
                    || "InvalidGatewayRequestException".equals(errorCode)
                    || "NoSuchEntity".equals(errorCode)
                    || "NoSuchHostedZone".equals(errorCode)
//                  for things like: Unable to find a snapshot matching the resource name: arn:aws:rds:us-east-1:617611493679:snapshot:rds:pappas-dev-int-drupal-db-2014-12-08-04-16
//                  (Service: AmazonRDS; Status Code: 400; Error Code: InvalidParameterValue; Request ID: a07b22a2-813d-11e4-88b6-eb9810c556b3)
                    || ("InvalidParameterValue".equals(errorCode) && errorMessage.contains("Unable to find"))
                    || ("InvalidParameterValue".equals(errorCode) && errorMessage.contains("No Environment found for EnvironmentName"))
                    || ("InvalidParameterValue".equals(errorCode) && errorMessage.contains("No Environment found for EnvironmentId"))
                    || ("InvalidRequestException".equals(errorCode) && errorMessage.contains("Cluster") && errorMessage.contains("was not found"))
                    || ("InvalidRequestException".equals(errorCode) && errorMessage.contains("Cluster") && errorMessage.contains("is not valid"))
                    || ("InvalidRequestException".equals(errorCode) && errorMessage.contains("Step") && errorMessage.contains("was not found"))
//                  for things like: com.amazonaws.services.sqs.model.QueueDoesNotExistException: The specified queue does not exist for this wsdl version.
//                      (Service: AmazonSQS; Status Code: 400; Error Code: AWS.SimpleQueueService.NonExistentQueue; Request ID: 6376254f-0423-59a2-8c97-68582d7add72)
                    || "AWS.SimpleQueueService.NonExistentQueue".equals(errorCode)
                    || "EntityDoesNotExistException".equals(errorCode)
                    || "RepositoryDoesNotExistException".equals(errorCode)
                    || "InvalidInstanceID.NotFound".equals(errorCode)
                    || "InvalidSnapshot.NotFound".equals(errorCode)
                    || "LoadBalancerNotFound".equals(errorCode)
                    || "DBInstanceNotFound".equals(errorCode)
                    || "CacheClusterNotFound".equals(errorCode)
                    || "FileSystemNotFound".equals(errorCode)
                    || "ResourceNotFoundException".equals(errorCode)
                    || "NotFound".equals(errorCode)
                    || "NoSuchBucket".equals(errorCode)
                    || "NoSuchDistribution".equals(errorCode)
                    || "NoSuchConfigRuleException".equals(errorCode)
                    || "TrailNotFoundException".equals(errorCode)
                    || "MountTargetNotFound".equals(errorCode)
                    || "ListenerNotFound".equals(errorCode)
                    || "ResourceNotFound".equals(errorCode)
                    || "TargetGroupNotFound".equals(errorCode)
                    || ("ValidationError".equals(errorCode) && errorMessage.startsWith("Stack with id") && errorMessage.contains("does not exist"))
                    || ("ValidationError".equals(errorCode) && errorMessage.startsWith("Group") && errorMessage.contains("not found"))
                    || "PipelineDeletedException".equals(errorCode)
                    || "PipelineNotFoundException".equals(errorCode)
                    || "ClientException".equals(errorCode) && "ds:DescribeEventTopics".equals(action) && errorMessage.contains("is in Deleting state")
                    || "ExecutionDoesNotExist".equals(errorCode)
                    || "DeploymentDoesNotExistException".equals(errorCode)
                    || "InvalidVpcID.NotFound".equals(errorCode)
                    ) {
                return new AmazonException(AmazonException.Category.OBJECT_NOT_FOUND, action, ex);
            }
            /**
             * SlowDown multiplier: 1, Category: TEMPORARY_ERROR
             */
            if ("ExpiredToken".equals(errorCode)
                    || "InternalFailure".equals(errorCode)
                    || "InternalError".equals(errorCode)
                    || "InternalServerError".equals(errorCode)
                    || "ServiceUnavailable".equals(errorCode)
                    || "ServerException".equals(errorCode)
                    || "503 Service Unavailable".equals(errorCode)
                    || "500 Internal Server Error".equals(errorCode)
                    || "ClientUnavailable".equals(errorCode)
                    || "DirectConnectServerException".equals(errorCode)
                    || "KMSInternalException".equals(errorCode)
                    || "HttpConnectionTimeoutException".equals(errorCode)
                    || (errorType == AmazonServiceException.ErrorType.Unknown && statusCode == HTTP_TEMPORARY_UNAVAILABLE)
                    //null (Service: AWSElasticBeanstalk; Status Code: 504; Error Code: 504 GATEWAY_TIMEOUT)
                    || (errorType == AmazonServiceException.ErrorType.Unknown && statusCode == HTTP_GATEWAY_TIMEOUT)) {
                return new AmazonException(AmazonException.Category.TEMPORARY_ERROR, action, ex);
            }
            LOGGER.error("Unable to categorize AmazonServiceException.", t);
            return new AmazonException(AmazonException.Category.UNKNOWN, action, ex);
        } else if (t instanceof AmazonClientException) {
            final AmazonClientException ex = (AmazonClientException) t;
            final String message = ex.getMessage();
            if (message != null && (message.contains("Read timed out") || message.contains("Connection reset"))) {
                return new AmazonException(AmazonException.Category.NETWORK_ERROR, action, t.getClass().getName(), t.getMessage());
            }
            if (ex.getCause() != null && ex.getCause() instanceof ConnectTimeoutException
                    && ex.getCause().getCause() != null && ex.getCause().getCause() instanceof SocketTimeoutException) {
                return new AmazonException(AmazonException.Category.NETWORK_ERROR, action, t.getClass().getName(), t.getMessage());
            }
            if (ex.getCause() != null && ex.getCause() instanceof org.apache.http.NoHttpResponseException) {
                return new AmazonException(AmazonException.Category.NETWORK_ERROR, action, t.getClass().getName(), t.getMessage());
            }
            if (ex.getCause() != null && ex.getCause() instanceof java.net.UnknownHostException) {
                return new AmazonException(AmazonException.Category.NETWORK_ERROR, action, t.getClass().getName(), t.getMessage());
            }
            LOGGER.error("Unable to categorize AmazonClientException.", t);
            return new AmazonException(AmazonException.Category.UNKNOWN, action, t.getClass().getName(), t.getMessage());
        }
        LOGGER.error("Unable to categorize exception.", t);
        throw new AmazonUnparsedException(t);
    }

    public final AmazonException getException() {
        return exception;
    }

    public final void setException(final AmazonException exception) {
        this.exception = exception;
    }

    public final String getNextPage() {
        return nextPage;
    }

    public final void setNextPage(final String nextPage) {
        this.nextPage = (nextPage != null && !nextPage.isEmpty()) ? nextPage : null;
    }
}
