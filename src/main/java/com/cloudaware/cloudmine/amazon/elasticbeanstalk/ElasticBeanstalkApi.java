package com.cloudaware.cloudmine.amazon.elasticbeanstalk;

import com.amazonaws.services.elasticbeanstalk.AWSElasticBeanstalk;
import com.amazonaws.services.elasticbeanstalk.model.ApplicationDescription;
import com.amazonaws.services.elasticbeanstalk.model.ConfigurationOptionDescription;
import com.amazonaws.services.elasticbeanstalk.model.ConfigurationSettingsDescription;
import com.amazonaws.services.elasticbeanstalk.model.DescribeApplicationVersionsRequest;
import com.amazonaws.services.elasticbeanstalk.model.DescribeApplicationVersionsResult;
import com.amazonaws.services.elasticbeanstalk.model.DescribeConfigurationOptionsRequest;
import com.amazonaws.services.elasticbeanstalk.model.DescribeConfigurationSettingsRequest;
import com.amazonaws.services.elasticbeanstalk.model.DescribeEnvironmentResourcesRequest;
import com.amazonaws.services.elasticbeanstalk.model.DescribeEventsRequest;
import com.amazonaws.services.elasticbeanstalk.model.DescribeEventsResult;
import com.amazonaws.services.elasticbeanstalk.model.EnvironmentDescription;
import com.amazonaws.services.elasticbeanstalk.model.EnvironmentResourceDescription;
import com.amazonaws.services.elasticbeanstalk.model.SolutionStackDescription;
import com.cloudaware.cloudmine.amazon.AmazonClientHelper;
import com.cloudaware.cloudmine.amazon.AmazonResponse;
import com.cloudaware.cloudmine.amazon.AmazonUnparsedException;
import com.cloudaware.cloudmine.amazon.ClientWrapper;
import com.cloudaware.cloudmine.amazon.Constants;
import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.Nullable;

import java.util.List;

/**
 * User: urmuzov
 * Date: 03.17.17
 * Time: 14:08
 */
@Api(
        name = "elasticbeanstalk",
        canonicalName = "ElasticBeanstalk",
        title = "AWS Elastic Beanstalk",
        description = "Run and Manage Web Apps",
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
public final class ElasticBeanstalkApi {
    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "applications.list",
            path = "{region}/applications"
    )
    public ApplicationsResponse applicationsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AWSElasticBeanstalk> clientWrapper = new AmazonClientHelper(credentials).getElasticBeanstalk(region)) {
            final List<ApplicationDescription> out = clientWrapper.getClient().describeApplications().getApplications();
            return new ApplicationsResponse(out);
        } catch (Throwable t) {
            return new ApplicationsResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "applicationVersions.list",
            path = "{region}/application-versions"
    )
    public ApplicationVersionsResponse applicationVersionsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AWSElasticBeanstalk> clientWrapper = new AmazonClientHelper(credentials).getElasticBeanstalk(region)) {
            final DescribeApplicationVersionsResult result = clientWrapper.getClient().describeApplicationVersions(
                    new DescribeApplicationVersionsRequest()
                            .withNextToken(page)
            );
            return new ApplicationVersionsResponse(result.getApplicationVersions(), result.getNextToken());
        } catch (Throwable t) {
            return new ApplicationVersionsResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "configurationOptions.list",
            path = "{region}/configuration-options"
    )
    public ConfigurationOptionsResponse configurationOptionsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AWSElasticBeanstalk> clientWrapper = new AmazonClientHelper(credentials).getElasticBeanstalk(region)) {
            final List<ConfigurationOptionDescription> out = clientWrapper.getClient().describeConfigurationOptions(new DescribeConfigurationOptionsRequest()).getOptions();
            return new ConfigurationOptionsResponse(out);
        } catch (Throwable t) {
            return new ConfigurationOptionsResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "applications.configurationSettings.get",
            path = "{region}/applications/APPLICATION_NAME/configuration-settings"
    )
    public ConfigurationSettingsResponse applicationsConfigurationSettingsGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("applicationName") final String applicationName,
            @Named("templateName") @Nullable final String templateName,
            @Named("environmentName") @Nullable final String environmentName
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AWSElasticBeanstalk> clientWrapper = new AmazonClientHelper(credentials).getElasticBeanstalk(region)) {
            final List<ConfigurationSettingsDescription> out = clientWrapper.getClient().describeConfigurationSettings(
                    new DescribeConfigurationSettingsRequest().withApplicationName(applicationName).withTemplateName(templateName).withEnvironmentName(environmentName)
            ).getConfigurationSettings();
            return new ConfigurationSettingsResponse(out);
        } catch (Throwable t) {
            return new ConfigurationSettingsResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "environments.list",
            path = "{region}/environments"
    )
    public EnvironmentsResponse environmentsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AWSElasticBeanstalk> clientWrapper = new AmazonClientHelper(credentials).getElasticBeanstalk(region)) {
            final List<EnvironmentDescription> out = clientWrapper.getClient().describeEnvironments().getEnvironments();
            return new EnvironmentsResponse(out);
        } catch (Throwable t) {
            return new EnvironmentsResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "environments.resources.list",
            path = "{region}/environment/{environmentId}/resources"
    )
    public EnvironmentResourcesResponse environmentsResourcesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("environmentId") final String environmentId
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AWSElasticBeanstalk> clientWrapper = new AmazonClientHelper(credentials).getElasticBeanstalk(region)) {
            final EnvironmentResourceDescription out = clientWrapper.getClient().describeEnvironmentResources(
                    new DescribeEnvironmentResourcesRequest()
                            .withEnvironmentId(environmentId)
            ).getEnvironmentResources();
            return new EnvironmentResourcesResponse(out);
        } catch (Throwable t) {
            return new EnvironmentResourcesResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "events.list",
            path = "{region}/events"
    )
    public EventsResponse eventsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AWSElasticBeanstalk> clientWrapper = new AmazonClientHelper(credentials).getElasticBeanstalk(region)) {
            final DescribeEventsResult result = clientWrapper.getClient().describeEvents(
                    new DescribeEventsRequest()
                            .withNextToken(page)
            );
            return new EventsResponse(result.getEvents(), result.getNextToken());
        } catch (Throwable t) {
            return new EventsResponse(AmazonResponse.parse(t));
        }
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "solutionStacks.list",
            path = "{region}/solution-stacks"
    )
    public SolutionStacksResponse solutionStacksList(
            @Named("credentials") final String credentials,
            @Named("region") final String region
    ) throws AmazonUnparsedException {
        try (ClientWrapper<AWSElasticBeanstalk> clientWrapper = new AmazonClientHelper(credentials).getElasticBeanstalk(region)) {
            final List<SolutionStackDescription> out = clientWrapper.getClient().listAvailableSolutionStacks().getSolutionStackDetails();
            return new SolutionStacksResponse(out);
        } catch (Throwable t) {
            return new SolutionStacksResponse(AmazonResponse.parse(t));
        }
    }
}
