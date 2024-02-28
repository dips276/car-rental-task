package io.rental;

import io.utils.DatePeriod;

import java.time.LocalDate;

public class Criteria {
    private final DatePeriod rentalPeriod;

    public Criteria(){
        this.rentalPeriod = new DatePeriod(LocalDate.now(), LocalDate.now().plusYears(1l));

    }

    public Criteria(DatePeriod rentalPeriod) {
        this.rentalPeriod = rentalPeriod;
    }

    public DatePeriod getRentalPeriod() {
        return rentalPeriod;
    }

    public boolean isEmpty(){
        if (this.rentalPeriod == null) {
            return true;
        }
        else {
            return false;
        }
    }
}
