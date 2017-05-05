package com.cloudaware.cloudmine.amazon.ec2;

import com.amazonaws.services.ec2.model.BlockDeviceMapping;
import com.amazonaws.services.ec2.model.IamInstanceProfileSpecification;
import com.amazonaws.services.ec2.model.InstanceIpv6Address;
import com.amazonaws.services.ec2.model.InstanceNetworkInterfaceSpecification;
import com.amazonaws.services.ec2.model.Placement;

import java.util.List;

public final class InstancesRequest {
    private String imageId;
    private Integer minCount;
    private Integer maxCount;
    private String keyName;
    private List<String> securityGroups;
    private List<String> securityGroupIds;
    private String userData;
    private String instanceType;
    private Placement placement;
    private String kernelId;
    private String ramdiskId;
    private List<BlockDeviceMapping> blockDeviceMappings;
    private Boolean monitoring;
    private String subnetId;
    private Boolean disableApiTermination;
    private String instanceInitiatedShutdownBehavior;
    private String privateIpAddress;
    private List<InstanceIpv6Address> ipv6Addresses;
    private Integer ipv6AddressCount;
    private String clientToken;
    private String additionalInfo;
    private List<InstanceNetworkInterfaceSpecification> networkInterfaces;
    private IamInstanceProfileSpecification iamInstanceProfile;
    private Boolean ebsOptimized;

    public String getImageId() {
        return imageId;
    }

    public void setImageId(final String imageId) {
        this.imageId = imageId;
    }

    public Integer getMinCount() {
        return minCount;
    }

    public void setMinCount(final Integer minCount) {
        this.minCount = minCount;
    }

    public Integer getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(final Integer maxCount) {
        this.maxCount = maxCount;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(final String keyName) {
        this.keyName = keyName;
    }

    public List<String> getSecurityGroups() {
        return securityGroups;
    }

    public void setSecurityGroups(final List<String> securityGroups) {
        this.securityGroups = securityGroups;
    }

    public List<String> getSecurityGroupIds() {
        return securityGroupIds;
    }

    public void setSecurityGroupIds(final List<String> securityGroupIds) {
        this.securityGroupIds = securityGroupIds;
    }

    public String getUserData() {
        return userData;
    }

    public void setUserData(final String userData) {
        this.userData = userData;
    }

    public String getInstanceType() {
        return instanceType;
    }

    public void setInstanceType(final String instanceType) {
        this.instanceType = instanceType;
    }

    public Placement getPlacement() {
        return placement;
    }

    public void setPlacement(final Placement placement) {
        this.placement = placement;
    }

    public String getKernelId() {
        return kernelId;
    }

    public void setKernelId(final String kernelId) {
        this.kernelId = kernelId;
    }

    public String getRamdiskId() {
        return ramdiskId;
    }

    public void setRamdiskId(final String ramdiskId) {
        this.ramdiskId = ramdiskId;
    }

    public List<BlockDeviceMapping> getBlockDeviceMappings() {
        return blockDeviceMappings;
    }

    public void setBlockDeviceMappings(final List<BlockDeviceMapping> blockDeviceMappings) {
        this.blockDeviceMappings = blockDeviceMappings;
    }

    public Boolean getMonitoring() {
        return monitoring;
    }

    public void setMonitoring(final Boolean monitoring) {
        this.monitoring = monitoring;
    }

    public String getSubnetId() {
        return subnetId;
    }

    public void setSubnetId(final String subnetId) {
        this.subnetId = subnetId;
    }

    public Boolean getDisableApiTermination() {
        return disableApiTermination;
    }

    public void setDisableApiTermination(final Boolean disableApiTermination) {
        this.disableApiTermination = disableApiTermination;
    }

    public String getInstanceInitiatedShutdownBehavior() {
        return instanceInitiatedShutdownBehavior;
    }

    public void setInstanceInitiatedShutdownBehavior(final String instanceInitiatedShutdownBehavior) {
        this.instanceInitiatedShutdownBehavior = instanceInitiatedShutdownBehavior;
    }

    public String getPrivateIpAddress() {
        return privateIpAddress;
    }

    public void setPrivateIpAddress(final String privateIpAddress) {
        this.privateIpAddress = privateIpAddress;
    }

    public List<InstanceIpv6Address> getIpv6Addresses() {
        return ipv6Addresses;
    }

    public void setIpv6Addresses(final List<InstanceIpv6Address> ipv6Addresses) {
        this.ipv6Addresses = ipv6Addresses;
    }

    public Integer getIpv6AddressCount() {
        return ipv6AddressCount;
    }

    public void setIpv6AddressCount(final Integer ipv6AddressCount) {
        this.ipv6AddressCount = ipv6AddressCount;
    }

    public String getClientToken() {
        return clientToken;
    }

    public void setClientToken(final String clientToken) {
        this.clientToken = clientToken;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(final String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public List<InstanceNetworkInterfaceSpecification> getNetworkInterfaces() {
        return networkInterfaces;
    }

    public void setNetworkInterfaces(final List<InstanceNetworkInterfaceSpecification> networkInterfaces) {
        this.networkInterfaces = networkInterfaces;
    }

    public IamInstanceProfileSpecification getIamInstanceProfile() {
        return iamInstanceProfile;
    }

    public void setIamInstanceProfile(final IamInstanceProfileSpecification iamInstanceProfile) {
        this.iamInstanceProfile = iamInstanceProfile;
    }

    public Boolean getEbsOptimized() {
        return ebsOptimized;
    }

    public void setEbsOptimized(final Boolean ebsOptimized) {
        this.ebsOptimized = ebsOptimized;
    }
}
