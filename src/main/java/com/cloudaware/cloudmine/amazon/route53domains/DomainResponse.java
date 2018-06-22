package com.cloudaware.cloudmine.amazon.route53domains;

import com.amazonaws.services.route53domains.model.ContactDetail;
import com.amazonaws.services.route53domains.model.Nameserver;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.Date;
import java.util.List;

public final class DomainResponse extends AmazonResponse {

    private String abuseContactEmail;
    private String abuseContactPhone;
    private ContactDetail adminContact;
    private Boolean adminPrivacy;
    private Boolean autoRenew;
    private Date creationDate;
    private String dnsSec;
    private String domainName;
    private Date expirationDate;
    private List<Nameserver> nameservers;
    private ContactDetail registrantContact;
    private Boolean registrantPrivacy;
    private String registrarName;
    private String registrarUrl;
    private String registryDomainId;
    private String reseller;
    private List<String> statusList;
    private ContactDetail techContact;
    private Boolean techPrivacy;
    private Date updatedDate;
    private String whoIsServer;

    public String getAbuseContactEmail() {
        return abuseContactEmail;
    }

    public void setAbuseContactEmail(final String abuseContactEmail) {
        this.abuseContactEmail = abuseContactEmail;
    }

    public String getAbuseContactPhone() {
        return abuseContactPhone;
    }

    public void setAbuseContactPhone(final String abuseContactPhone) {
        this.abuseContactPhone = abuseContactPhone;
    }

    public ContactDetail getAdminContact() {
        return adminContact;
    }

    public void setAdminContact(final ContactDetail adminContact) {
        this.adminContact = adminContact;
    }

    public Boolean getAdminPrivacy() {
        return adminPrivacy;
    }

    public void setAdminPrivacy(final Boolean adminPrivacy) {
        this.adminPrivacy = adminPrivacy;
    }

    public Boolean getAutoRenew() {
        return autoRenew;
    }

    public void setAutoRenew(final Boolean autoRenew) {
        this.autoRenew = autoRenew;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(final Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getDnsSec() {
        return dnsSec;
    }

    public void setDnsSec(final String dnsSec) {
        this.dnsSec = dnsSec;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(final String domainName) {
        this.domainName = domainName;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(final Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public List<Nameserver> getNameservers() {
        return nameservers;
    }

    public void setNameservers(final List<Nameserver> nameservers) {
        this.nameservers = nameservers;
    }

    public ContactDetail getRegistrantContact() {
        return registrantContact;
    }

    public void setRegistrantContact(final ContactDetail registrantContact) {
        this.registrantContact = registrantContact;
    }

    public Boolean getRegistrantPrivacy() {
        return registrantPrivacy;
    }

    public void setRegistrantPrivacy(final Boolean registrantPrivacy) {
        this.registrantPrivacy = registrantPrivacy;
    }

    public String getRegistrarName() {
        return registrarName;
    }

    public void setRegistrarName(final String registrarName) {
        this.registrarName = registrarName;
    }

    public String getRegistrarUrl() {
        return registrarUrl;
    }

    public void setRegistrarUrl(final String registrarUrl) {
        this.registrarUrl = registrarUrl;
    }

    public String getRegistryDomainId() {
        return registryDomainId;
    }

    public void setRegistryDomainId(final String registryDomainId) {
        this.registryDomainId = registryDomainId;
    }

    public String getReseller() {
        return reseller;
    }

    public void setReseller(final String reseller) {
        this.reseller = reseller;
    }

    public List<String> getStatusList() {
        return statusList;
    }

    public void setStatusList(final List<String> statusList) {
        this.statusList = statusList;
    }

    public ContactDetail getTechContact() {
        return techContact;
    }

    public void setTechContact(final ContactDetail techContact) {
        this.techContact = techContact;
    }

    public Boolean getTechPrivacy() {
        return techPrivacy;
    }

    public void setTechPrivacy(final Boolean techPrivacy) {
        this.techPrivacy = techPrivacy;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(final Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getWhoIsServer() {
        return whoIsServer;
    }

    public void setWhoIsServer(final String whoIsServer) {
        this.whoIsServer = whoIsServer;
    }
}
