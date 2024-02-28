package io.rental;

import io.utils.DatePeriod;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Car {
    private final String make;
    private final String model;
    private final String registrationNumber;
    private String rentalGroup;
    private double costPerDay;

    public Car(String make, String model, String registrationNumber, String rentalGroup, double costPerDay) {
        this.make = make;
        this.model = model;
        this.registrationNumber = registrationNumber;
        this.rentalGroup = rentalGroup;
        this.costPerDay = costPerDay;
    }

    @Override
    public String toString() {
        return "Car{" +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", rentalGroup='" + rentalGroup + '\'' +
                ", costPerDay=" + costPerDay +
                '}';
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public String getRentalGroup() {
        return rentalGroup;
    }

    public void setRentalGroup(String rentalGroup) {
        this.rentalGroup = rentalGroup;
    }

    public double getCostPerDay() {
        return costPerDay;
    }

    public void setCostPerDay(double costPerDay) {
        this.costPerDay = costPerDay;
    }
}
