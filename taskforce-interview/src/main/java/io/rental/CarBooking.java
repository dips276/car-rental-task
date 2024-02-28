package io.rental;

import io.utils.DatePeriod;

public class CarBooking {
    private final Renter renter;
    private final DatePeriod rentalPeriod;
    private boolean inService = false;

    public CarBooking(Renter renter, DatePeriod rentalPeriod){
        this.renter = renter;
        this.rentalPeriod = rentalPeriod;
    }

    public CarBooking(Renter renter, DatePeriod rentalPeriod, boolean inService){
        this.renter = renter;
        this.rentalPeriod = rentalPeriod;
        this.inService = inService;
    }

    public DatePeriod getRentalPeriod() {
        return rentalPeriod;
    }

    public Renter getRenter() {
        return renter;
    }

    public boolean isInService() {
        return inService;
    }

    public void setInService(boolean inService) {
        this.inService = inService;
    }

    @Override
    public String toString() {
        return "CarBooking{" +
                "renter=" + renter +
                ", rentalPeriod=" + rentalPeriod +
                ", inService=" + inService +
                '}';
    }
}
