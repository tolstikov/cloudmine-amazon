package com.cloudaware.cloudmine.amazon.ses;

import com.amazonaws.services.simpleemail.model.DescribeActiveReceiptRuleSetRequest;
import com.amazonaws.services.simpleemail.model.DescribeActiveReceiptRuleSetResult;
import com.amazonaws.services.simpleemail.model.DescribeConfigurationSetRequest;
import com.amazonaws.services.simpleemail.model.DescribeConfigurationSetResult;
import com.amazonaws.services.simpleemail.model.DescribeReceiptRuleSetRequest;
import com.amazonaws.services.simpleemail.model.DescribeReceiptRuleSetResult;
import com.amazonaws.services.simpleemail.model.GetAccountSendingEnabledRequest;
import com.amazonaws.services.simpleemail.model.GetAccountSendingEnabledResult;
import com.amazonaws.services.simpleemail.model.GetCustomVerificationEmailTemplateRequest;
import com.amazonaws.services.simpleemail.model.GetCustomVerificationEmailTemplateResult;
import com.amazonaws.services.simpleemail.model.GetIdentityDkimAttributesRequest;
import com.amazonaws.services.simpleemail.model.GetIdentityDkimAttributesResult;
import com.amazonaws.services.simpleemail.model.GetIdentityMailFromDomainAttributesRequest;
import com.amazonaws.services.simpleemail.model.GetIdentityMailFromDomainAttributesResult;
import com.amazonaws.services.simpleemail.model.GetIdentityNotificationAttributesRequest;
import com.amazonaws.services.simpleemail.model.GetIdentityNotificationAttributesResult;
import com.amazonaws.services.simpleemail.model.GetIdentityPoliciesRequest;
import com.amazonaws.services.simpleemail.model.GetIdentityPoliciesResult;
import com.amazonaws.services.simpleemail.model.GetIdentityVerificationAttributesRequest;
import com.amazonaws.services.simpleemail.model.GetIdentityVerificationAttributesResult;
import com.amazonaws.services.simpleemail.model.GetSendQuotaRequest;
import com.amazonaws.services.simpleemail.model.GetSendQuotaResult;
import com.amazonaws.services.simpleemail.model.GetSendStatisticsRequest;
import com.amazonaws.services.simpleemail.model.GetSendStatisticsResult;
import com.amazonaws.services.simpleemail.model.GetTemplateRequest;
import com.amazonaws.services.simpleemail.model.GetTemplateResult;
import com.amazonaws.services.simpleemail.model.ListConfigurationSetsRequest;
import com.amazonaws.services.simpleemail.model.ListConfigurationSetsResult;
import com.amazonaws.services.simpleemail.model.ListCustomVerificationEmailTemplatesRequest;
import com.amazonaws.services.simpleemail.model.ListCustomVerificationEmailTemplatesResult;
import com.amazonaws.services.simpleemail.model.ListIdentitiesRequest;
import com.amazonaws.services.simpleemail.model.ListIdentitiesResult;
import com.amazonaws.services.simpleemail.model.ListIdentityPoliciesRequest;
import com.amazonaws.services.simpleemail.model.ListIdentityPoliciesResult;
import com.amazonaws.services.simpleemail.model.ListReceiptFiltersRequest;
import com.amazonaws.services.simpleemail.model.ListReceiptFiltersResult;
import com.amazonaws.services.simpleemail.model.ListReceiptRuleSetsRequest;
import com.amazonaws.services.simpleemail.model.ListReceiptRuleSetsResult;
import com.amazonaws.services.simpleemail.model.ListTemplatesRequest;
import com.amazonaws.services.simpleemail.model.ListTemplatesResult;
import com.amazonaws.services.simpleemail.model.RawMessage;
import com.amazonaws.services.simpleemail.model.SendRawEmailRequest;
import com.amazonaws.services.simpleemail.model.SendRawEmailResult;
import com.cloudaware.cloudmine.amazon.AmazonUnparsedException;
import com.cloudaware.cloudmine.amazon.Constants;
import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.Nullable;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import org.apache.commons.codec.binary.Base64;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.BodyPart;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * User: urmuzov
 * Date: 03.23.17
 * Time: 16:28
 */
@Api(
        name = "ses",
        canonicalName = "Ses",
        title = "Amazon Simple Email Service (SES)",
        description = "Email Sending and Receiving",
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
public final class SesApi {

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "sendStatistics.get",
            path = "{region}/send-statistics"
    )
    public SendStatisticsResponse sendStatisticsGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region
    ) throws AmazonUnparsedException {
        return SesCaller.get(GetSendStatisticsRequest.class, SendStatisticsResponse.class, credentials, region).execute((client, request, response) -> {
            final GetSendStatisticsResult result = client.getSendStatistics(request);
            response.setSendDataPoints(result.getSendDataPoints());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.POST,
            name = "emails.send",
            path = "{region}/emails"
    )
    public SendResponse emailsSend(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            final SendRequest request
    ) throws AmazonUnparsedException {
        return SesCaller.get(SendRawEmailRequest.class, SendResponse.class, credentials, region).execute((client, r, response) -> {
            final Session session = Session.getInstance(new Properties(), null);
            final MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(request.getSource()));
            for (final String to : request.getToAddresses()) {
                mimeMessage.addRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(to));
            }

            if (request.getCcAddresses() != null) {
                for (final String cc : request.getCcAddresses()) {
                    mimeMessage.addRecipient(MimeMessage.RecipientType.CC, new InternetAddress(cc));
                }
            }

            mimeMessage.setSubject(request.getSubject());

            final MimeMultipart multipart = new MimeMultipart();
            final BodyPart part = new MimeBodyPart();
            String bodyMimeType = request.getBodyMediaType();
            if (Strings.isNullOrEmpty(bodyMimeType)) {
                bodyMimeType = "text/plain";
            }

            part.setContent(request.getBody(), bodyMimeType);
            multipart.addBodyPart(part);

            if (request.getAttachments() != null) {
                if (!Strings.isNullOrEmpty(request.getZipFileName())) {
                    final ByteArrayOutputStream out = new ByteArrayOutputStream();
                    final ZipOutputStream zip = new ZipOutputStream(out);
                    for (final EmailAttachment attachment : request.getAttachments()) {
                        final byte[] bytes = Base64.decodeBase64(attachment.getBase64Content().getBytes());
                        final ZipEntry zipEntry = new ZipEntry(attachment.getName());
                        zip.putNextEntry(zipEntry);
                        zip.write(bytes);
                        zip.closeEntry();
                    }

                    zip.close();
                    final DataSource dataSource = new ByteArrayDataSource(out.toByteArray(), "application/zip");
                    final BodyPart attachmentPart = new MimeBodyPart();
                    attachmentPart.setDataHandler(new DataHandler(dataSource));
                    attachmentPart.setFileName(request.getZipFileName());
                    multipart.addBodyPart(attachmentPart);
                } else {
                    for (final EmailAttachment attachment : request.getAttachments()) {
                        final DataSource dataSource = new ByteArrayDataSource(Base64.decodeBase64(attachment.getBase64Content().getBytes()), attachment.getMimeType());
                        final BodyPart attachmentPart = new MimeBodyPart();
                        attachmentPart.setDataHandler(new DataHandler(dataSource));
                        attachmentPart.setFileName(attachment.getName());
                        multipart.addBodyPart(attachmentPart);
                    }
                }
            }

            mimeMessage.setContent(multipart);

            // Create Raw message
            final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            mimeMessage.writeTo(outputStream);
            final RawMessage rawMessage = new RawMessage(ByteBuffer.wrap(outputStream.toByteArray()));
            // Send Mail
            final SendRawEmailResult result = client.sendRawEmail(r.withRawMessage(rawMessage));

            response.setMessageId(result.getMessageId());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "configurationSets.list",
            path = "{region}/configuration-sets"
    )
    public ConfigurationSetsResponse configurationSetsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return SesCaller.get(ListConfigurationSetsRequest.class, ConfigurationSetsResponse.class, credentials, region).execute((client, request, response) -> {
            final ListConfigurationSetsResult result = client.listConfigurationSets(request.withNextToken(page));
            response.setConfigurationSets(result.getConfigurationSets());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "configurationSets.get",
            path = "{region}/configuration-sets/{configurationSetName}"
    )
    public ConfigurationSetResponse configurationSetsGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("configurationSetName") final String configurationSetName
    ) throws AmazonUnparsedException {
        return SesCaller.get(DescribeConfigurationSetRequest.class, ConfigurationSetResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeConfigurationSetResult result = client.describeConfigurationSet(request.withConfigurationSetName(configurationSetName));
            response.setConfigurationSet(result.getConfigurationSet());
            response.setEventDestinations(result.getEventDestinations());
            response.setReputationOptions(result.getReputationOptions());
            response.setTrackingOptions(result.getTrackingOptions());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "customVerificationEmailTemplates.list",
            path = "{region}/custom-verification-email-templates"
    )
    public CustomVerificationEmailTemplatesResponse customVerificationEmailTemplatesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return SesCaller.get(ListCustomVerificationEmailTemplatesRequest.class, CustomVerificationEmailTemplatesResponse.class, credentials, region).execute((client, request, response) -> {
            final ListCustomVerificationEmailTemplatesResult result = client.listCustomVerificationEmailTemplates(request.withNextToken(page));
            response.setCustomVerificationEmailTemplates(result.getCustomVerificationEmailTemplates());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "customVerificationEmailTemplates.get",
            path = "{region}/custom-verification-email-templates/{templateName}"
    )
    public CustomVerificationEmailTemplateResponse customVerificationEmailTemplatesGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("templateName") final String templateName
    ) throws AmazonUnparsedException {
        return SesCaller.get(GetCustomVerificationEmailTemplateRequest.class, CustomVerificationEmailTemplateResponse.class, credentials, region).execute((client, request, response) -> {
            final GetCustomVerificationEmailTemplateResult result = client.getCustomVerificationEmailTemplate(request.withTemplateName(templateName));
            response.setFromEmailAddress(result.getFromEmailAddress());
            response.setFailureRedirectionUrl(result.getFailureRedirectionURL());
            response.setSuccessRedirectionUrl(result.getSuccessRedirectionURL());
            response.setTemplateContent(result.getTemplateContent());
            response.setTemplateName(result.getTemplateName());
            response.setTemplateSubject(result.getTemplateSubject());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "identities.list",
            path = "{region}/identities"
    )
    public IdentitiesResponse identitiesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("identityType") @Nullable final String identityType,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return SesCaller.get(ListIdentitiesRequest.class, IdentitiesResponse.class, credentials, region).execute((client, request, response) -> {
            final ListIdentitiesResult result = client.listIdentities(
                    request
                            .withIdentityType(identityType)
                            .withNextToken(page)
            );
            response.setIdentities(result.getIdentities());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "identities.policyNames.list",
            path = "{region}/identities/{identity}/policy-names"
    )
    public IdentityPolicyNamesResponse identitiesPolicyNamesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("identity") final String identity
    ) throws AmazonUnparsedException {
        return SesCaller.get(ListIdentityPoliciesRequest.class, IdentityPolicyNamesResponse.class, credentials, region).execute((client, request, response) -> {
            final ListIdentityPoliciesResult result = client.listIdentityPolicies(request.withIdentity(identity));
            response.setPolicyNames(result.getPolicyNames());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "identities.policies.list",
            path = "{region}/identities/{identity}/policies"
    )
    public IdentityPoliciesResponse identitiesPoliciesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("identity") final String identity,
            @Named("policyName") final List<String> policyNames
    ) throws AmazonUnparsedException {
        return SesCaller.get(GetIdentityPoliciesRequest.class, IdentityPoliciesResponse.class, credentials, region).execute((client, request, response) -> {
            final GetIdentityPoliciesResult result = client.getIdentityPolicies(
                    request
                            .withIdentity(identity)
                            .withPolicyNames(policyNames)
            );
            response.setPolicies(result.getPolicies());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "identities.dkimAttributes.list",
            path = "{region}/identities/dkim-attributes"
    )
    public IdentityDkimAttributesResponse identitiesDkimAttributesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("identity") final List<String> identities
    ) throws AmazonUnparsedException {
        return SesCaller.get(GetIdentityDkimAttributesRequest.class, IdentityDkimAttributesResponse.class, credentials, region).execute((client, request, response) -> {
            final GetIdentityDkimAttributesResult result = client.getIdentityDkimAttributes(request.withIdentities(identities));
            final List<IdentityDkimAttributes> identityDkimAttributesList = Lists.newArrayList();
            if (result.getDkimAttributes() != null) {
                for (final String key : result.getDkimAttributes().keySet()) {
                    final com.amazonaws.services.simpleemail.model.IdentityDkimAttributes identityDkimAttrs = result.getDkimAttributes().get(key);
                    final IdentityDkimAttributes identityDkimAttributes = new IdentityDkimAttributes();
                    identityDkimAttributes.setIdentityName(key);
                    identityDkimAttributes.setDkimEnabled(identityDkimAttrs.getDkimEnabled());
                    identityDkimAttributes.setDkimVerificationStatus(identityDkimAttrs.getDkimVerificationStatus());
                    identityDkimAttributes.setDkimTokens(identityDkimAttrs.getDkimTokens());
                    identityDkimAttributesList.add(identityDkimAttributes);
                }
            }
            response.setIdentityDkimAttributes(identityDkimAttributesList);
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "identities.mailFromDomainAttributes.list",
            path = "{region}/identities/mail-from-domain-attributes"
    )
    public IdentityMailFromDomainAttributesResponse identitiesMailFromDomainAttributesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("identity") final List<String> identities
    ) throws AmazonUnparsedException {
        return SesCaller.get(GetIdentityMailFromDomainAttributesRequest.class, IdentityMailFromDomainAttributesResponse.class, credentials, region).execute((client, request, response) -> {
            final GetIdentityMailFromDomainAttributesResult result = client.getIdentityMailFromDomainAttributes(request.withIdentities(identities));
            final List<IdentityMailFromDomainAttributes> identityMailFromDomainAttributesList = Lists.newArrayList();
            if (result.getMailFromDomainAttributes() != null) {
                for (final String key : result.getMailFromDomainAttributes().keySet()) {
                    final com.amazonaws.services.simpleemail.model.IdentityMailFromDomainAttributes identityMailFromDomainAttrs = result.getMailFromDomainAttributes().get(key);
                    final IdentityMailFromDomainAttributes identityMailFromDomainAttributes = new IdentityMailFromDomainAttributes();
                    identityMailFromDomainAttributes.setIdentityName(key);
                    identityMailFromDomainAttributes.setBehaviorOnMXFailure(identityMailFromDomainAttrs.getBehaviorOnMXFailure());
                    identityMailFromDomainAttributes.setMailFromDomain(identityMailFromDomainAttrs.getMailFromDomain());
                    identityMailFromDomainAttributes.setMailFromDomainStatus(identityMailFromDomainAttrs.getMailFromDomainStatus());
                    identityMailFromDomainAttributesList.add(identityMailFromDomainAttributes);
                }
            }
            response.setIdentityMailFromDomainAttributes(identityMailFromDomainAttributesList);
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "identities.notificationAttributes.list",
            path = "{region}/identities/notification-attributes"
    )
    public IdentityNotificationAttributesResponse identitiesNotificationAttributesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("identity") final List<String> identities
    ) throws AmazonUnparsedException {
        return SesCaller.get(GetIdentityNotificationAttributesRequest.class, IdentityNotificationAttributesResponse.class, credentials, region).execute((client, request, response) -> {
            final GetIdentityNotificationAttributesResult result = client.getIdentityNotificationAttributes(request.withIdentities(identities));
            final List<IdentityNotificationAttributes> identityNotificationAttributesList = Lists.newArrayList();
            if (result.getNotificationAttributes() != null) {
                for (final String key : result.getNotificationAttributes().keySet()) {
                    final com.amazonaws.services.simpleemail.model.IdentityNotificationAttributes identityNotificationAttrs = result.getNotificationAttributes().get(key);
                    final IdentityNotificationAttributes identityNotificationAttributes = new IdentityNotificationAttributes();
                    identityNotificationAttributes.setIdentityName(key);
                    identityNotificationAttributes.setBounceTopic(identityNotificationAttrs.getBounceTopic());
                    identityNotificationAttributes.setComplaintTopic(identityNotificationAttrs.getComplaintTopic());
                    identityNotificationAttributes.setDeliveryTopic(identityNotificationAttrs.getDeliveryTopic());
                    identityNotificationAttributes.setForwardingEnabled(identityNotificationAttrs.getForwardingEnabled());
                    identityNotificationAttributes.setHeadersInBounceNotificationsEnabled(identityNotificationAttrs.getHeadersInBounceNotificationsEnabled());
                    identityNotificationAttributes.setHeadersInComplaintNotificationsEnabled(identityNotificationAttrs.getHeadersInComplaintNotificationsEnabled());
                    identityNotificationAttributes.setHeadersInDeliveryNotificationsEnabled(identityNotificationAttrs.getHeadersInDeliveryNotificationsEnabled());
                    identityNotificationAttributesList.add(identityNotificationAttributes);
                }
            }
            response.setIdentityNotificationAttributes(identityNotificationAttributesList);
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "identities.verificationAttributes.list",
            path = "{region}/identities/verification-attributes"
    )
    public IdentityVerificationAttributesResponse identitiesVerificationAttributesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("identity") final List<String> identities
    ) throws AmazonUnparsedException {
        return SesCaller.get(GetIdentityVerificationAttributesRequest.class, IdentityVerificationAttributesResponse.class, credentials, region).execute((client, request, response) -> {
            final GetIdentityVerificationAttributesResult result = client.getIdentityVerificationAttributes(request.withIdentities(identities));
            final List<IdentityVerificationAttributes> identityVerificationAttributesList = Lists.newArrayList();
            if (result.getVerificationAttributes() != null) {
                for (final String key : result.getVerificationAttributes().keySet()) {
                    final com.amazonaws.services.simpleemail.model.IdentityVerificationAttributes identityVerificationAttrs = result.getVerificationAttributes().get(key);
                    final IdentityVerificationAttributes identityVerificationAttributes = new IdentityVerificationAttributes();
                    identityVerificationAttributes.setIdentityName(key);
                    identityVerificationAttributes.setVerificationStatus(identityVerificationAttrs.getVerificationStatus());
                    identityVerificationAttributes.setVerificationToken(identityVerificationAttrs.getVerificationToken());
                    identityVerificationAttributesList.add(identityVerificationAttributes);
                }
            }
            response.setIdentityVerificationAttributes(identityVerificationAttributesList);
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "receiptFilters.list",
            path = "{region}/receipt-filters"
    )
    public ReceiptFiltersResponse receiptFiltersList(
            @Named("credentials") final String credentials,
            @Named("region") final String region
    ) throws AmazonUnparsedException {
        return SesCaller.get(ListReceiptFiltersRequest.class, ReceiptFiltersResponse.class, credentials, region).execute((client, request, response) -> {
            final ListReceiptFiltersResult result = client.listReceiptFilters(request);
            response.setReceiptFilters(result.getFilters());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "receiptRuleSets.list",
            path = "{region}/receipt-rule-sets"
    )
    public ReceiptRuleSetsResponse receiptRuleSetsList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return SesCaller.get(ListReceiptRuleSetsRequest.class, ReceiptRuleSetsResponse.class, credentials, region).execute((client, request, response) -> {
            final ListReceiptRuleSetsResult result = client.listReceiptRuleSets(request.withNextToken(page));
            response.setReceiptRuleSets(result.getRuleSets());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "receiptRuleSets.get",
            path = "{region}/receipt-rule-sets/{ruleSetName}"
    )
    public ReceiptRuleSetResponse receiptRuleSetsGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("ruleSetName") final String ruleSetName
    ) throws AmazonUnparsedException {
        return SesCaller.get(DescribeReceiptRuleSetRequest.class, ReceiptRuleSetResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeReceiptRuleSetResult result = client.describeReceiptRuleSet(request.withRuleSetName(ruleSetName));
            response.setMetadata(result.getMetadata());
            response.setRules(result.getRules());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "templates.list",
            path = "{region}/templates"
    )
    public TemplatesResponse templatesList(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("page") @Nullable final String page
    ) throws AmazonUnparsedException {
        return SesCaller.get(ListTemplatesRequest.class, TemplatesResponse.class, credentials, region).execute((client, request, response) -> {
            final ListTemplatesResult result = client.listTemplates(request.withNextToken(page));
            response.setTemplates(result.getTemplatesMetadata());
            response.setNextPage(result.getNextToken());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "templates.get",
            path = "{region}/templates/{templateName}"
    )
    public TemplateResponse templatesGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region,
            @Named("templateName") final String templateName
    ) throws AmazonUnparsedException {
        return SesCaller.get(GetTemplateRequest.class, TemplateResponse.class, credentials, region).execute((client, request, response) -> {
            final GetTemplateResult result = client.getTemplate(request.withTemplateName(templateName));
            response.setTemplate(result.getTemplate());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "activeReceiptRuleSet.get",
            path = "{region}/active-receipt-rule-set"
    )
    public ActiveReceiptRuleSetResponse activeReceiptRuleSetGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region
    ) throws AmazonUnparsedException {
        return SesCaller.get(DescribeActiveReceiptRuleSetRequest.class, ActiveReceiptRuleSetResponse.class, credentials, region).execute((client, request, response) -> {
            final DescribeActiveReceiptRuleSetResult result = client.describeActiveReceiptRuleSet(request);
            response.setReceiptRuleSetMetadata(result.getMetadata());
            response.setReceiptRules(result.getRules());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "accountSendingEnabled.get",
            path = "{region}/account-sending-enabled"
    )
    public AccountSendingEnabledResponse accountSendingEnabledGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region
    ) throws AmazonUnparsedException {
        return SesCaller.get(GetAccountSendingEnabledRequest.class, AccountSendingEnabledResponse.class, credentials, region).execute((client, request, response) -> {
            final GetAccountSendingEnabledResult result = client.getAccountSendingEnabled(request);
            response.setEnabled(result.getEnabled());
        });
    }

    @ApiMethod(
            httpMethod = ApiMethod.HttpMethod.GET,
            name = "sendQuota.get",
            path = "{region}/send-quota"
    )
    public SendQuotaResponse sendQuotaGet(
            @Named("credentials") final String credentials,
            @Named("region") final String region
    ) throws AmazonUnparsedException {
        return SesCaller.get(GetSendQuotaRequest.class, SendQuotaResponse.class, credentials, region).execute((client, request, response) -> {
            final GetSendQuotaResult result = client.getSendQuota(request);
            response.setMax24HourSend(result.getMax24HourSend());
            response.setMaxSendRate(result.getMaxSendRate());
            response.setSentLast24Hours(result.getSentLast24Hours());
        });
    }
}
