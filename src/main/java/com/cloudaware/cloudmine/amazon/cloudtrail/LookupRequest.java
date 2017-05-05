package com.cloudaware.cloudmine.amazon.cloudtrail;

import java.util.Date;
import java.util.List;

public final class LookupRequest {
    private List<Attribute> lookupAttributes;
    private Date startTime;
    private Date endTime;

    public List<Attribute> getLookupAttributes() {
        return lookupAttributes;
    }

    public void setLookupAttributes(final List<Attribute> lookupAttributes) {
        this.lookupAttributes = lookupAttributes;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(final Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(final Date endTime) {
        this.endTime = endTime;
    }

    public static final class Attribute {
        private String attributeKey;
        private String attributeValue;

        public String getAttributeKey() {
            return attributeKey;
        }

        public void setAttributeKey(final String attributeKey) {
            this.attributeKey = attributeKey;
        }

        public String getAttributeValue() {
            return attributeValue;
        }

        public void setAttributeValue(final String attributeValue) {
            this.attributeValue = attributeValue;
        }
    }
}
