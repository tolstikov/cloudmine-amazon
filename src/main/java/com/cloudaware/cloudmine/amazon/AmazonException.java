package com.cloudaware.cloudmine.amazon;

import com.amazonaws.AmazonServiceException;
import com.google.common.collect.ImmutableMap;

import java.util.Map;

/**
 * User: urmuzov
 * Date: 03.17.17
 * Time: 16:27
 */
public final class AmazonException {
    private final Category category;
    private final String action;
    private final String className;
    private final String message;
    private final String requestId;
    private final String errorCode;
    private final AmazonServiceException.ErrorType errorType;
    private final String errorMessage;
    private final int statusCode;
    private final String serviceName;
    private final Map<String, String> httpHeaders;

    public AmazonException(final Category category, final String action, final AmazonServiceException ex) {
        this.category = category;
        this.action = action;
        this.className = ex.getClass().getName();
        this.message = ex.getMessage();
        this.requestId = ex.getRequestId();
        this.errorCode = ex.getErrorCode();
        this.errorType = ex.getErrorType();
        this.errorMessage = ex.getErrorMessage();
        this.statusCode = ex.getStatusCode();
        this.serviceName = ex.getServiceName();
        this.httpHeaders = ex.getHttpHeaders();
    }

    public AmazonException(final Category category, final String action, final String className, final String message) {
        this.category = category;
        this.action = action;
        this.className = className;
        this.message = message;
        this.requestId = null;
        this.errorCode = null;
        this.errorType = AmazonServiceException.ErrorType.Unknown;
        this.errorMessage = null;
        this.statusCode = -1;
        this.serviceName = null;
        this.httpHeaders = ImmutableMap.of();
    }

    public Category getCategory() {
        return category;
    }

    public String getAction() {
        return action;
    }

    public String getClassName() {
        return className;
    }

    public String getMessage() {
        return message;
    }

    public String getRequestId() {
        return requestId;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public AmazonServiceException.ErrorType getErrorType() {
        return errorType;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getServiceName() {
        return serviceName;
    }

    public Map<String, String> getHttpHeaders() {
        return httpHeaders;
    }

    public enum Category {
        /**
         * Service responded that you have no access
         */
        NO_ACCESS,
        /**
         * We have access, but we've been throttled
         */
        THROTTLING,
        /**
         * Service is disabled for this account/region/etc
         */
        SERVICE_DISABLED,
        /**
         * Trying to get info for object that may be deleted
         */
        OBJECT_NOT_FOUND,
        /**
         * Temporary error of any kind on AWS side
         */
        TEMPORARY_ERROR,
        /**
         * Error related to network access
         */
        NETWORK_ERROR,
        /**
         * Прочие не категоризированные ошибки
         */
        UNKNOWN,
    }
}
