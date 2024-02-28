package io.rental;

import io.utils.DatePeriod;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CarRentalTest {

    private static final Car CAR1 = new Car("VW", "Golf", "XX11 1UR", "B2", 90);
    private static final Car CAR2 = new Car("VW", "Passat", "XX12 2UR",  "C1", 110);
    private static final Car CAR3 = new Car("VW", "Polo", "XX13 3UR",  "A1", 65);
    private static final Car CAR4 = new Car("VW", "Polo", "XX14 4UR",  "A1", 70);

    private static final Renter RENTER1 = new Renter("Hydrogen", "Joe", "HYDRO010190JX8NM", LocalDate.of(1990, 1, 1));
    private static final Renter RENTER2 = new Renter("Calcium", "Sam", "CALCI010203SX8NM", LocalDate.of(2003, 2, 1));
    private static final Renter RENTER3 = new Renter("Neon", "Maisy", "NEONN010398MX8NM", LocalDate.of(1998, 3, 1));
    private static final Renter RENTER4 = new Renter("Carbon", "Greta", "CARBO010497GX8NM", LocalDate.of(1997, 4, 1));

    private static final Renter CARRCOM = new Renter("Rental-Company", "Car", "CARRENTALCOMP123", LocalDate.of(2000, 1, 1));

    @Test
    public void testListCarsAvailableToRentGivesMoreThanOneCar() {
        CarRentalCompany carRentalCompany = new CarRentalCompany();
        carRentalCompany.addCar(CAR1);
        carRentalCompany.addCar(CAR2);
        carRentalCompany.addCar(CAR3);
        carRentalCompany.addCar(CAR4);

        Criteria criteria = new Criteria();
        List<Car> carsAvailable = carRentalCompany.matchingCars(criteria);

        assertThat(carsAvailable.size()).isGreaterThan(1);
        System.out.println("Available cars:" + carsAvailable.size());
        System.out.println(carsAvailable);
    }

    @Test
    public void testListCarsAvailableToRentOnGivenDates(){
        CarRentalCompany carRentalCompany = new CarRentalCompany();
        carRentalCompany.addCar(CAR1);
        carRentalCompany.addCar(CAR2);
        carRentalCompany.addCar(CAR3);
        carRentalCompany.addCar(CAR4);

        // the matching criteria for a renter to rent a car should include a from date and to date
        Criteria criteria = new Criteria(new DatePeriod(LocalDate.now(), LocalDate.now().plusDays(2l)));
        List<Car> carsAvailable = carRentalCompany.matchingCars(criteria);

        assertThat(carsAvailable.size()).isGreaterThan(1);

        // one method returning a list of matching cars (with the filter having changed)
        criteria = new Criteria(new DatePeriod(LocalDate.now().plusDays(4l), LocalDate.now().plusDays(7l)));
        carsAvailable = carRentalCompany.matchingCars(criteria);
        assertThat(carsAvailable.size()).isGreaterThan(1);

        System.out.println("Available cars:" + carsAvailable.size());
        System.out.println(carsAvailable);
    }

    @Test
    public void testBookingAvailableCar(){
        CarRentalCompany carRentalCompany = new CarRentalCompany();
        carRentalCompany.addCar(CAR1);
        carRentalCompany.addCar(CAR2);
        carRentalCompany.addCar(CAR3);
        carRentalCompany.addCar(CAR4);

        Criteria criteria = new Criteria(new DatePeriod(LocalDate.now(), LocalDate.now().plusMonths(2l)));
        List<Car> carsAvailable = carRentalCompany.matchingCars(criteria);
        // pick random available car
        Collections.shuffle(carsAvailable);
        carRentalCompany.rentCar(RENTER1,carsAvailable.get(0),criteria.getRentalPeriod());

        List<Car> checkCarsAvailable = carRentalCompany.matchingCars(criteria);
        assertThat(!checkCarsAvailable.contains(carsAvailable.get(0)));

        Criteria criteria1 = new Criteria(new DatePeriod(LocalDate.now(), LocalDate.now().plusDays(3l)));
        List<Car> carsAvailable1 = carRentalCompany.matchingCars(criteria1);
        Collections.shuffle(carsAvailable1);
        carRentalCompany.rentCar(RENTER2, carsAvailable1.get(0),criteria1.getRentalPeriod());

        checkCarsAvailable = carRentalCompany.matchingCars(criteria);
        assertThat(!checkCarsAvailable.contains(carsAvailable1.get(0)));
    }

    @Test
    public void testPreparingCar(){
        CarRentalCompany carRentalCompany = new CarRentalCompany();
        carRentalCompany.addCar(CAR1);
        carRentalCompany.addCar(CAR2);
        carRentalCompany.addCar(CAR3);
        carRentalCompany.addCar(CAR4);

        carRentalCompany.rentCar(RENTER1, CAR1, new DatePeriod(LocalDate.now().plusDays(2l),
                LocalDate.now().plusDays(6l)));

        carRentalCompany.rentCar(RENTER2, CAR2, new DatePeriod(LocalDate.now().plusDays(1l),
                LocalDate.now().plusDays(3l)));

        assertThat(carRentalCompany.prepareCar().size()).isEqualTo(2);
        System.out.println("testPreparingCar:");
        System.out.println(carRentalCompany.getToBePreparedCars());
    }

    @Test
    public void testCarMaintenance(){
        CarRentalCompany carRentalCompany = new CarRentalCompany();
        carRentalCompany.addCar(CAR1);
        carRentalCompany.addCar(CAR2);
        carRentalCompany.addCar(CAR3);
        carRentalCompany.addCar(CAR4);

        carRentalCompany.rentCar(CARRCOM,CAR3,new DatePeriod(LocalDate.now(),LocalDate.now().plusWeeks(1l)),
                true);

        assertThat(carRentalCompany.getBookings().keys().nextElement().isInService()).isEqualTo(true);

        carRentalCompany.rentCar(RENTER2, CAR2, new DatePeriod(LocalDate.now().plusDays(1l),
                LocalDate.now().plusDays(3l)));

        assertThat(carRentalCompany.getBookings().keys().nextElement().isInService()).isEqualTo(false);
    }
}
