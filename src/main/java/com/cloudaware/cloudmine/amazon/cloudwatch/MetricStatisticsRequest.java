package com.cloudaware.cloudmine.amazon.cloudwatch;

import com.amazonaws.services.cloudwatch.model.Dimension;

import java.util.Date;
import java.util.List;

/**
 * User: urmuzov
 * Date: 03.20.17
 * Time: 14:54
 */
public final class MetricStatisticsRequest {
    private String namespace;
    private String metric;
    private Date startDate;
    private Date endDate;
    private int period;
    private List<String> statistics;
    private List<Dimension> dimensions;

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(final String namespace) {
        this.namespace = namespace;
    }

    public String getMetric() {
        return metric;
    }

    public void setMetric(final String metric) {
        this.metric = metric;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(final Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(final Date endDate) {
        this.endDate = endDate;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(final int period) {
        this.period = period;
    }

    public List<String> getStatistics() {
        return statistics;
    }

    public void setStatistics(final List<String> statistics) {
        this.statistics = statistics;
    }

    public List<Dimension> getDimensions() {
        return dimensions;
    }

    public void setDimensions(final List<Dimension> dimensions) {
        this.dimensions = dimensions;
    }
}
