package io.rental;

import java.time.LocalDate;
import java.util.Objects;

public class Renter {
    private final String lastName;
    private final String firstName;
    private final String drivingLicenseNumber;
    private final LocalDate dateOfBirth;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Renter renter = (Renter) o;
        return Objects.equals(lastName, renter.lastName) && Objects.equals(firstName, renter.firstName) && Objects.equals(drivingLicenseNumber, renter.drivingLicenseNumber) && Objects.equals(dateOfBirth, renter.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lastName, firstName, drivingLicenseNumber, dateOfBirth);
    }

    @Override
    public String toString() {
        return "Renter{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", drivingLicenseNumber='" + drivingLicenseNumber + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }

    public Renter(String lastName, String firstName, String drivingLicenseNumber, LocalDate dateOfBirth) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.drivingLicenseNumber = drivingLicenseNumber;
        this.dateOfBirth = dateOfBirth;
    }
}
