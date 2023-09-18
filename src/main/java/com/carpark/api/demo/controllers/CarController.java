package com.carpark.api.demo.controllers;

import com.carpark.api.demo.exceptions.CarNotFoundException;
import com.carpark.api.demo.models.Car;
import com.carpark.api.demo.dto.CarDTO;
import com.carpark.api.demo.exceptions.InvalidCreditsException;
import com.carpark.api.demo.services.CarService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1")
public class CarController {

    private final CarService carService;
    private final ModelMapper modelMapper;

    @Autowired
    public CarController(CarService carService, ModelMapper modelMapper) {
        this.carService = carService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public List<CarDTO> findAll() {
        return carService.findAll().stream().map(this::convertToCarDTO).collect(Collectors.toList());
    }

    @GetMapping("/find_by_numberplate/{numberplate}")
    public CarDTO findByNumberplate(@PathVariable String numberplate) {
        Car car = carService.findByNumberplate(numberplate);
        if (car == null) {
            throw new CarNotFoundException("Car with this numberplate not found!");
        }
        return convertToCarDTO(car);
    }

    @GetMapping("/find_by_id/{id}")
    public CarDTO findByID(@PathVariable int id) {
        Car car = carService.findById(id);
        if (car == null) {
            throw new CarNotFoundException("Car with this id not found!");
        }
        return convertToCarDTO(car);
    }

    @GetMapping("/get_price/{id}")
    public int getPrice(@PathVariable int id){
        Car car = carService.findById(id);
        if (car == null){
            throw new CarNotFoundException("Car not found!");
        }
        return countPrice(car);
    }

    @GetMapping("/mileages")
    public List<Integer> getMileages() {
        return carService.findAll().stream().map(Car::getMileage).collect(Collectors.toList());
    }


    @PostMapping("/add")
    public ResponseEntity<String> createCar(@RequestBody @Valid CarDTO carDTO,
                                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidCreditsException("Invalid creating of car!");
        }
        carService.save(convertToCar(carDTO));
        return ResponseEntity.ok("Car created!");
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCar(@PathVariable int id) {
        Car car = carService.findById(id);
        if (car == null) {
            throw new CarNotFoundException("Car not found");
        }
        carService.delete(car);
        return ResponseEntity.ok("Car deleted!");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> updateCar(@PathVariable int id,
                                            @RequestBody @Valid CarDTO carDTO,
                                            BindingResult bindingResult){
        Car car = carService.findById(id);
        if (car == null){
            throw new CarNotFoundException("Car not found!");
        }
        else if (bindingResult.hasErrors()){
            throw new InvalidCreditsException("Invalid credits!");
        }
        carService.save(update(car, carDTO));
        return ResponseEntity.ok("Car updated!");
    }

    private Car update(Car car, CarDTO carDTO){
        car.setCarType(carDTO.getCarType());
        car.setNumberplate(carDTO.getNumberplate());
        car.setModel(carDTO.getModel());
        car.setMileage(carDTO.getMileage());
        car.setYearOfManufacture(carDTO.getYearOfManufacture());
        return car;
    }

    private int countPrice(Car car) {
        return (int) (Double.valueOf(car.getYearOfManufacture() / Double.valueOf(car.getMileage())) * 100);
    }
    private Car convertToCar(CarDTO carDTO) {
        return modelMapper.map(carDTO, Car.class);
    }

    private CarDTO convertToCarDTO(Car car){
        return modelMapper.map(car, CarDTO.class);
    }

}