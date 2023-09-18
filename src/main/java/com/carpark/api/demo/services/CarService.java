package com.carpark.api.demo.services;

import com.carpark.api.demo.models.Car;
import com.carpark.api.demo.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CarService {

    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> findAll(){
        return carRepository.findAll();
    }

    public Car findById(int id){
        return carRepository.findById(id).orElse(null);
    }

    public Car findByNumberplate(String numberplate){
        return carRepository.findByNumberplate(numberplate).orElse(null);
    }
    @Transactional
    public void save(Car car){
        carRepository.save(car);
    }

    @Transactional
    public void delete(Car car){ carRepository.delete(car);}
}
