package com.cloudaware.cloudmine.amazon.elastictranscoder;

import com.amazonaws.services.elastictranscoder.model.ListJobsByPipelineRequest;
import com.amazonaws.services.elastictranscoder.model.ListJobsByPipelineResult;
import com.amazonaws.services.elastictranscoder.model.ListPipelinesRequest;
import com.amazonaws.services.elastictranscoder.model.ListPipelinesResult;
import com.amazonaws.services.elastictranscoder.model.ListPresetsRequest;
import com.amazonaws.services.elastictranscoder.model.ListPresetsResult;
import com.cloudaware.cloudmine.amazon.AmazonUnparsedException;
import com.cloudaware.cloudmine.amazon.Constants;
import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.Nullable;

@Api(
        name = "elastictranscoder",
        canonicalName = "Elastic Transcoder",
        title = "AWS Elastic Transcoder",
        description = "Easy-to-Use Scalable Media Transcoding",
        namespace = @ApiNamespace(
                ownerDomain = "cloudaware.com",
                ownerName = "CloudAware",
                packagePath = "cloudmine/amazon"
        ),
        version = "v1",
        scopes = {Constants.EMAIL_SCOPE},
        clientIds = {Constants.WEB_CLIENT_ID, Constants.API_EXPLORER_CLIENT_ID},
        apiKeyRequired = AnnotationBoolean.TRUE
)
public final class ElasticTranscoderApi {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "pipelines.list",
            path = "{region}/pipelines"
    )
    public PipelinesResponse pipelinesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return ElasticTranscoderCaller.get(ListPipelinesRequest.class, PipelinesResponse.class, credentials, region).execute((client, request, response) -> {
            final ListPipelinesResult result = client.listPipelines(request.withPageToken(page));
            response.setPipelines(result.getPipelines());
            response.setNextPage(result.getNextPageToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "pipelines.jobs.list",
            path = "{region}/pipelines/{pipelineId}/jobs"
    )
    public JobsResponse pipelineJobsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page,
            @Named("pipelineId") final String pipelineId
    ) throws AmazonUnparsedException {
        return ElasticTranscoderCaller.get(ListJobsByPipelineRequest.class, JobsResponse.class, credentials, region).execute((client, request, response) -> {
            final ListJobsByPipelineResult result = client.listJobsByPipeline(request.withPageToken(page).withPipelineId(pipelineId));
            response.setJobs(result.getJobs());
            response.setNextPage(result.getNextPageToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "presets.list",
            path = "{region}/presets"
    )
    public PresetsResponse presetsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return ElasticTranscoderCaller.get(ListPresetsRequest.class, PresetsResponse.class, credentials, region).execute((client, request, response) -> {
            final ListPresetsResult result = client.listPresets(request.withPageToken(page));
            response.setPresets(result.getPresets());
            response.setNextPage(result.getNextPageToken());
        });
    }

}
