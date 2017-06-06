package com.cloudaware.cloudmine.amazon;

import com.amazonaws.AmazonWebServiceRequest;

public class AmazonCaller<T, RqT extends AmazonWebServiceRequest, RsT extends AmazonResponse> {

    private final Class<RqT> requestClass;
    private final Class<RsT> responseClass;
    private final String endpointPrefix;
    private final AmazonClientCreate<T> clientCreate;

    public AmazonCaller(final Class<RqT> requestClass, final Class<RsT> responseClass, final String endpointPrefix, final AmazonClientCreate<T> clientCreate) {
        this.requestClass = requestClass;
        this.responseClass = responseClass;
        this.endpointPrefix = endpointPrefix;
        this.clientCreate = clientCreate;
    }

    private static String convertToAction(final Class<?> requestClass) {
        final String apiCallName;
        if (requestClass.getSimpleName().endsWith("Request")) {
            apiCallName = requestClass.getSimpleName().substring(0, requestClass.getSimpleName().length() - "Request".length());
        } else {
            apiCallName = "Unknown";
        }

        return apiCallName;
    }

    public final RsT execute(
            final AmazonCall<T, RqT, RsT> call
    ) throws AmazonUnparsedException, IllegalAccessException, InstantiationException {
        final RsT response = responseClass.newInstance();
        try (ClientWrapper<T> clientWrapper = clientCreate.create()) {
            call.call(clientWrapper.getClient(), requestClass.newInstance(), response);
        } catch (Throwable t) {
            response.setException(AmazonResponse.parse(t, endpointPrefix + ":" + convertToAction(requestClass)));
        }

        return response;
    }
}
