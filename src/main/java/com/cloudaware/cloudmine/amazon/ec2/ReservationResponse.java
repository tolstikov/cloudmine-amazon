package com.cloudaware.cloudmine.amazon.ec2;

import com.amazonaws.services.ec2.model.Reservation;
import com.cloudaware.cloudmine.amazon.AmazonException;
import com.cloudaware.cloudmine.amazon.AmazonResponse;

public final class ReservationResponse extends AmazonResponse {

    private Reservation reservation;

    public ReservationResponse() {
    }

    public ReservationResponse(final AmazonException exception) {
        super(exception);
    }

    public ReservationResponse(final Reservation reservation) {
        this.reservation = reservation;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(final Reservation reservation) {
        this.reservation = reservation;
    }
}
