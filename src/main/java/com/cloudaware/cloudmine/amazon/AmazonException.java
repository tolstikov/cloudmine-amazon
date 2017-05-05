package com.cloudaware.cloudmine.amazon;

import com.amazonaws.AmazonServiceException;

import java.util.Map;

/**
 * User: urmuzov
 * Date: 03.17.17
 * Time: 16:27
 */
public final class AmazonException {
    private Category category;
    private String className;
    private String message;
    private String requestId;
    private String errorCode;
    private AmazonServiceException.ErrorType errorType;
    private String errorMessage;
    private int statusCode;
    private String serviceName;
    private Map<String, String> httpHeaders;

    public AmazonException(final Category category, final AmazonServiceException ex) {
        this.category = category;
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

    public AmazonException(final Category category, final String className, final String message) {
        this.category = category;
        this.className = className;
        this.message = message;
    }

    public Category getCategory() {
        return category;
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
         * Нет доступа до сервиса с этими ключами
         */
        NO_ACCESS,
        /**
         * Доступ есть, но сервис нас троттлит
         */
        THROTTLING,
        /**
         * Досту есть, но на этом аккаунте этот сервис не доступен
         */
        SERVICE_DISABLED,
        /**
         * Обращение к не существующему объекту, например удаленному
         */
        OBJECT_NOT_FOUND,
        /**
         * Временная ошибка на стороне AWS
         */
        TEMPORARY_ERROR,
        /**
         * Прочие не категоризированные ошибки
         */
        UNKNOWN,
    }
}
