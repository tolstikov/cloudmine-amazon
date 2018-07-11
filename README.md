[![Build Status](https://jenkins.cloudaware.com/buildStatus/icon?style=plastic&job=cloudmine-amazon%20deploy)](https://jenkins.cloudaware.com/job/cloudmine-amazon%20deploy)
# About

This project aims to solve following problems for AWS API Users:

* Minimize jar-hell you might have when adding aws-java-sdk library (especially useful if you already have any Google API libraries added to your project)
* Unified interface for request/response paging
* Exception categorization

The project consists of two parts:

1. [Cloud Endpoints](https://cloud.google.com/endpoints/) based API, that designed to run on [AppEngine Flexible Java](https://cloud.google.com/appengine/docs/flexible/java/) but can be deployed to any jetty or tomcat.
2. Client library generated by [Google APIs Client Generator](https://github.com/google/apis-client-generator)

# Usage

```java
import com.cloudaware.cloudmine.amazon.ec2.Ec2;
import com.cloudaware.cloudmine.amazon.ec2.model.InstanceAttribute;
import com.cloudaware.cloudmine.amazon.ec2.model.InstanceAttributeResponse;
import com.cloudaware.cloudmine.amazon.ec2.model.Volume;
import com.cloudaware.cloudmine.amazon.ec2.model.VolumesResponse;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.googleapis.services.json.AbstractGoogleJsonClientRequest;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.json.GenericJson;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.common.collect.Lists;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public final class Example {
    public static final String ROOT_URL = "https://cloudmine-amazon-dot-yourproject.appspot.com/_ah/api";
    public static final String API_KEY = "YOUR_API_KEY";
    private final Ec2 client;

    public Example() throws GeneralSecurityException, IOException {
        this.client = new Ec2.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JacksonFactory.getDefaultInstance(),
                new RequestInitializer()
        )
                .setApplicationName("Cloudaware")
                .setRootUrl(ROOT_URL)
                .build();
    }

    /**
     * Use this method if you know that there is not paging for this API call
     */
    public static <T extends AbstractGoogleJsonClientRequest<T2>, T2 extends GenericJson> T2 unwrapSingle(final T call) throws IOException {
        call.set("prettyPrint", false);
        call.set("key", API_KEY);
        final T2 response = call.execute();
        rethrowException(response);
        return response;
    }

    /**
     * Use this method if you know that there paging for this API call or you not sure
     */
    public static <T extends AbstractGoogleJsonClientRequest<T2>, T2 extends GenericJson> List<T2> unwrapList(final T call) throws IOException {
        call.set("prettyPrint", false);
        call.set("key", API_KEY);
        final List<T2> out = Lists.newArrayList();
        T2 response = call.execute();
        rethrowException(response);
        out.add(response);
        while (response.get("nextPage") != null) {
            response = call.set("page", response.get("nextPage")).execute();
            rethrowException(response);
            out.add(response);
        }
        return out;
    }

    /**
     * This method will re-throw exception caught by cloudmine-amazon and then serialized into "exception" property of 200 response
     */
    private static <T extends AbstractGoogleJsonClientRequest<T2>, T2 extends GenericJson> void rethrowException(final T2 response) {
        final Object exceptionObject = response.get("exception");
        if (exceptionObject != null) {
            if (exceptionObject instanceof GenericJson) {
                final GenericJson exception = (GenericJson) exceptionObject;
                final Object category = exception.get("category");
                final Object className = exception.get("className");
                final Object message = exception.get("message");
                final Object requestId = exception.get("requestId");
                final Object errorCode = exception.get("errorCode");
                final Object errorType = exception.get("errorType");
                final Object errorMessage = exception.get("errorMessage");
                final Object statusCode = exception.get("statusCode");
                final Object serviceName = exception.get("serviceName");
                throw new AmazonException(
                        String.valueOf(category),
                        String.valueOf(className),
                        String.valueOf(message),
                        String.valueOf(requestId),
                        String.valueOf(errorCode),
                        String.valueOf(errorType),
                        String.valueOf(errorMessage),
                        statusCode == null ? null : Integer.valueOf(String.valueOf(statusCode)),
                        String.valueOf(serviceName)
                );
            } else {
                throw new RuntimeException("response.exception is not GenericJson: " + exceptionObject);
            }
        }
    }

    /**
     * Use this method to serialize basic AWS credentials to cloudmine-amazon format
     */
    public static String basicCredentials(final String accessKey, final String secretKey) {
        return accessKey + "::" + secretKey;
    }

    /**
     * Use this method to serialize session credentials to cloudmine-amazon format
     */
    public static String sessionCredentials(final String accessKey, final String secretKey, final String sessionToken) {
        return accessKey + "::" + secretKey + "::" + sessionToken;
    }

    /**
     * Example 1: list of all EBS Volumes in a specific region
     */
    public List<Volume> listVolumes(final String credentials, final String region) throws IOException {
        final List<VolumesResponse> response = unwrapList(client.volumes().list(
                credentials,
                region
        ));
        final List<Volume> out = Lists.newArrayList();
        for (final VolumesResponse r : response) {
            if (r != null && r.getVolumes() != null) {
                out.addAll(r.getVolumes());
            }
        }
        return out;
    }

    /**
     * Example 2: Instance attribute
     */
    public InstanceAttribute getInstanceAttribute(final String credentials, final String region, final String instanceId, final String attributeName) throws IOException {
        final InstanceAttributeResponse response = unwrapSingle(client.instances().attributes().get(
                credentials,
                region,
                instanceId,
                attributeName
        ));
        if (response != null) {
            return response.getInstanceAttribute();
        }
        return null;
    }

    /**
     * Request Initializer to set greater timeout then default
     */
    public static class RequestInitializer implements HttpRequestInitializer {
        private static final int READ_TIMEOUT = 120000; //milliseconds

        @Override
        public void initialize(final HttpRequest request) throws IOException {
            request.setReadTimeout(READ_TIMEOUT);
        }
    }

    /**
     * Same POJO used by cloudmine-amazon to serialize exceptions from AWS
     */
    public static final class AmazonException extends RuntimeException {
        private final String category;
        private final String className;
        private final String requestId;
        private final String errorCode;
        private final String errorType;
        private final String errorMessage;
        private final Integer statusCode;
        private final String serviceName;

        public AmazonException(
                final String category,
                final String className,
                final String message,
                final String requestId,
                final String errorCode,
                final String errorType,
                final String errorMessage,
                final Integer statusCode,
                final String serviceName
        ) {
            super(message);
            this.category = category;
            this.className = className;
            this.requestId = requestId;
            this.errorCode = errorCode;
            this.errorType = errorType;
            this.errorMessage = errorMessage;
            this.statusCode = statusCode;
            this.serviceName = serviceName;
        }

        public String getCategory() {
            return category;
        }

        public String getClassName() {
            return className;
        }

        public String getRequestId() {
            return requestId;
        }

        public String getErrorCode() {
            return errorCode;
        }

        public String getErrorType() {
            return errorType;
        }

        public String getErrorMessage() {
            return errorMessage;
        }

        public Integer getStatusCode() {
            return statusCode;
        }

        public String getServiceName() {
            return serviceName;
        }
    }
}
```

# Build

`mvn clean package -Dappengine.app.id=yourproject -Dendpoints.service.version=2017-04-28r0`

Optionally you can override API name completely with `-Dendpoints.service.name=yourapi.endpoints.yourproject.cloud.goog`

# Dev Server

To start dev server:

`mvn clean package jetty:run-exploded -Dappengine.app.id=yourproject -Dendpoints.service.version=2017-04-28r0`

# New API Version Deployment

1. Build project - you will create/update `cloudmine-amazon.json` in root folder 
2. `gcloud service-management deploy cloudmine-amazon.json`
3. Receive new API Version and update property `endpoints.service.version`
4. `mvn clean package appengine:update -Dappengine.app.id=yourproject -Dendpoints.service.version=2017-05-02r0` (`2017-05-02r0` - is your new version)

# Client Library

Client library built using [apis-client-generator](https://github.com/google/apis-client-generator)
 
Each successful build will leave following files and directories:

* `cloudmine-amazon.json` - OpenAPI (Swagger) definition for Cloud Endpoints service management
* `disovery/*.discovery` - discovery documents for API Explorer and library generation

To build client library:

1. Build project
2. Execute `client/generate.sh && cd target/client && mvn versions:set -DnewVersion=2017-04-28r0`
3. `mvn package` or `mvn install`

# Changelog

* 1.0.49 - DynamoDB actualized
* 1.0.48 - Beanstalk actualized
* 1.0.47 - Amazon MQ added
* 1.0.46 - AWS Certificate Manager added
* 1.0.45 - Workspaces actualized
* 1.0.44 - S3 requests added
* 1.0.43 - IAM SSH Public Keys filter added
* 1.0.42 - IAM updated
* 1.0.41 - RDS actualized
* 1.0.40 - Route53 actualized
* 1.0.39 - Redshift actualized
* 1.0.38 - VPC actualized 
* 1.0.37 - Cost Explorer added
* 1.0.36 - S3 Server Side Encryption added
* 1.0.35 - GuardDuty added
* 1.0.34 - instanceState filter fro EMR ListInstances
* 1.0.33 - OpsWorks added
* 1.0.32 - SQS and CloudWatch Logs tags
* 1.0.31 - Tag modification refactoring
* 1.0.30 - Batch added
* 1.0.29 - CloudWatch Logs added
* 1.0.28 - API Gateway added
* 1.0.27 - Glue added
* 1.0.26 - StepFunctions added
* 1.0.25 - Athena added
* 1.0.24 - S3 Bucket ACLs added
* 1.0.23 - Lambda tags added
* 1.0.22 - ECR BatchGetImages (repositories.images.list) added
* 1.0.21 - ECR added
* 1.0.20 - CodePipeline added
* 1.0.19 - IoT added, DynamoDB updated
* 1.0.18 - DataPipeline and ElasticTranscoder added
* 1.0.17 - CodeDeploy added
* 1.0.16 - WAF & Shield added
* 1.0.15 - Kinesis added
* 1.0.14 - Service Catalog added
* 1.0.13 - AWS Config added
* 1.0.12 - EMR Instance Fleets added
* 1.0.11 - AutoScaling policies added
* 1.0.10 - CodeCommit added
* 1.0.9 - ELBv2 added
* 1.0.8 - CodeBuild added
* 1.0.7 - CodeStar added
* 1.0.6 - Elasticsearch Service added, EC2 Customer Gateways and NAT Gateways added
* 1.0.5 - Elastic File System added
* 1.0.4 - EC2 Systems Manager added
* 1.0.3 - Exceptions now return API action
* 1.0.2 - Added Redshift Parameter Groups and Security Groups
* 1.0.1 - Added `amazon.sts.callerIdentity.get`
* 1.0.0 - Initial release