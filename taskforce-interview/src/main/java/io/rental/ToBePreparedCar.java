package io.rental;

import java.time.LocalDate;
import java.util.Objects;

public class ToBePreparedCar {

    private final String make;
    private final String model;
    private final String registrationNumber;
    private final LocalDate rentalDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToBePreparedCar that = (ToBePreparedCar) o;
        return Objects.equals(make, that.make) && Objects.equals(model, that.model) && Objects.equals(registrationNumber, that.registrationNumber) && Objects.equals(rentalDate, that.rentalDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(make, model, registrationNumber, rentalDate);
    }

    public ToBePreparedCar(String make, String model, String registrationNumber, LocalDate rentalDate) {
        this.make = make;
        this.model = model;
        this.registrationNumber = registrationNumber;
        this.rentalDate = rentalDate;
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

    public LocalDate getRentalDate() {
        return rentalDate;
    }

    @Override
    public String toString() {
        return "ToBePreparedCar{" +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", rentalDate=" + rentalDate +
                '}';
    }

    static class ToBePreparedCarComparator implements java.util.Comparator<ToBePreparedCar> {
        @Override
        public int compare(ToBePreparedCar o1, ToBePreparedCar o2) {
            return o1.getRentalDate().compareTo(o2.getRentalDate());
        }
    }
}
