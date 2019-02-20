package com.cloudaware.cloudmine.amazon.ec2;

import com.amazonaws.services.ec2.model.CapacityReservation;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

import java.util.List;

public final class CapacityReservationsResponse extends AmazonResponse {
    private List<CapacityReservation> capacityReservations;

    public List<CapacityReservation> getCapacityReservations() {
        return capacityReservations;
    }

    public void setCapacityReservations(final List<CapacityReservation> capacityReservations) {
        this.capacityReservations = capacityReservations;
    }
}
