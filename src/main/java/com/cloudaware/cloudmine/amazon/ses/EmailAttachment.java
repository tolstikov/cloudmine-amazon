package com.cloudaware.cloudmine.amazon.ses;

public final class EmailAttachment {

    private String name;
    private String mimeType;
    private String base64Content;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(final String mimeType) {
        this.mimeType = mimeType;
    }

    public String getBase64Content() {
        return base64Content;
    }

    public void setBase64Content(final String base64Content) {
        this.base64Content = base64Content;
    }
}
