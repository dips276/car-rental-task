package io.rental;

import io.utils.DatePeriod;
import io.utils.DatePeriodUtil;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class CarRentalCompany {
    private List<Car> cars = new ArrayList<>();

    private List<ToBePreparedCar> toBePreparedCars = new ArrayList<>();
    private ConcurrentHashMap<CarBooking, Car> bookings = new ConcurrentHashMap<>();

    public void addCar(Car car) {
        this.cars.add(car);
    }

    public List<ToBePreparedCar> getToBePreparedCars() {
        return toBePreparedCars;
    }

    public List<Car> matchingCars(Criteria criteria) {
        if (criteria.isEmpty()){
            System.out.println("No criteria provided, returning all cars");
            return new ArrayList<>(cars);
        }
        else {
            List<Car> matchingCars = new ArrayList<>(cars);
            DatePeriod cRentalPeriod = criteria.getRentalPeriod();
            for (Map.Entry<CarBooking, Car> bookings : this.bookings.entrySet()) {
                CarBooking cb = bookings.getKey();
                Car car = bookings.getValue();
                if (DatePeriodUtil.areOverlapping(cb.getRentalPeriod(), cRentalPeriod)) {
                    matchingCars.remove(car);
                }
            }
            return matchingCars;
        }

    }

    public void rentCar(Renter renter, Car car, DatePeriod rentalPeriod, Boolean inService) {
        //if this car is booked, then move booking to a different car within same group



        this.bookings.put(new CarBooking(renter, rentalPeriod, inService), car);
    }

    public void rentCar(Renter renter, Car car, DatePeriod rentalPeriod) {
        this.bookings.put(new CarBooking(renter, rentalPeriod), car);
    }

    public ConcurrentHashMap<CarBooking, Car> getBookings(){
        return this.bookings;
    }

    public void returnCar(Renter renter, Car car) {}

    public List<ToBePreparedCar> prepareCar(){
        DatePeriod thisWeek = new DatePeriod(LocalDate.now(), LocalDate.now().plusWeeks(1l));
        for (Map.Entry<CarBooking, Car> bookings : this.bookings.entrySet()) {
            CarBooking cb = bookings.getKey();
            Car car = bookings.getValue();
            if (DatePeriodUtil.isInPeriod(cb.getRentalPeriod().getStart(), thisWeek)){
                this.toBePreparedCars.add(new ToBePreparedCar(car.getMake(),car.getModel(),
                        car.getRegistrationNumber(),cb.getRentalPeriod().getStart()));
            }
        }
        Collections.sort(this.toBePreparedCars, new ToBePreparedCar.ToBePreparedCarComparator());
        return this.toBePreparedCars;
    }
}
