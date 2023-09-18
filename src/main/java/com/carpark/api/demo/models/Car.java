package com.carpark.api.demo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "numberplate")
    @Size(max = 10)
    private String numberplate;

    @Column(name = "model")
    @Size(max = 20)
    private String model;

    @Column(name = "year_of_manufacture")
    @Min(value = 1)
    @Max(value = 2023)
    private int yearOfManufacture;

    @Enumerated(EnumType.STRING)
    @Column(name = "car_type")
    private CarType carType;

    @Column(name = "mileage")
    @Min(value = 0)
    private int mileage;

    public Car() {
    }

    public Car(int id, String numberplate, String model, int yearOfManufacture, CarType carType, int mileage) {
        this.id = id;
        this.numberplate = numberplate;
        this.model = model;
        this.yearOfManufacture = yearOfManufacture;
        this.carType = carType;
        this.mileage = mileage;
    }

    public Car(String numberplate, String model, int yearOfManufacture, int mileage) {
        this.numberplate = numberplate;
        this.model = model;
        this.yearOfManufacture = yearOfManufacture;
        this.mileage = mileage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumberplate() {
        return numberplate;
    }

    public void setNumberplate(String numberplate) {
        this.numberplate = numberplate;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(int yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }
}
