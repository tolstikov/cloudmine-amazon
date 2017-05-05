package com.cloudaware.cloudmine.amazon.sns;

import com.amazonaws.services.sns.model.MessageAttributeValue;

import java.util.Map;

/**
 * User: urmuzov
 * Date: 03.24.17
 * Time: 01:11
 */
public final class PublishRequest {
    private String topicArn;
    private String targetArn;
    private String phoneNumber;
    private String message;
    private String subject;
    private String messageStructure;
    private Map<String, MessageAttributeValue> messageAttributes;

    public String getTargetArn() {
        return targetArn;
    }

    public void setTargetArn(final String targetArn) {
        this.targetArn = targetArn;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(final String subject) {
        this.subject = subject;
    }

    public String getMessageStructure() {
        return messageStructure;
    }

    public void setMessageStructure(final String messageStructure) {
        this.messageStructure = messageStructure;
    }

    public Map<String, MessageAttributeValue> getMessageAttributes() {
        return messageAttributes;
    }

    public void setMessageAttributes(final Map<String, MessageAttributeValue> messageAttributes) {
        this.messageAttributes = messageAttributes;
    }
}
