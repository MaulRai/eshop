package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;

import java.util.List;
import java.util.UUID;

public interface CarService {
    public Car create(Car car);
    public List<Car> findAll();
    Car findById(String id);
    public void update(String carId, Car car);
    public void deleteCarById(String id);
}
