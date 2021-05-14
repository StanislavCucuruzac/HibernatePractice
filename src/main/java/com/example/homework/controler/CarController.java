package com.example.homework.controler;

import com.example.homework.Model.Car;
import com.example.homework.Model.Driver;
import com.example.homework.Model.Wheel;
import com.example.homework.repository.CarRepository;
import com.example.homework.repository.DriverRepository;
import com.example.homework.repository.WheelReposiotry;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/car")
@AllArgsConstructor
public class CarController {
    CarRepository carRepository;
    WheelReposiotry wheelReposiotry;
    DriverRepository driverRepository;

    @GetMapping
    public ResponseEntity<List<Car>> getAllCars() {
        ResponseEntity<List<Car>> responseEntity= new ResponseEntity<List<Car>>(carRepository.getAllCars(), HttpStatus.OK);
        return responseEntity;
    }

    @PostMapping
    public Car createCar(@RequestBody Car car) {
        return carRepository.createCar(car);
    }

    @GetMapping("/id/{id}")
    public Car getById(@PathVariable int id) {
        return carRepository.getCarById(id);

    }
    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable int id){
        carRepository.deleteCarById(id);

    }

    @GetMapping("/addWheel")
    public Car addWheelToCar(@RequestParam int wheelId, @RequestParam int carId){
        Wheel wheel= wheelReposiotry.getById(wheelId);
        Car car= carRepository.getCarById(carId);
      //  wheel.setCar(car);
        car.addWheelToList(wheel);
        return carRepository.createCar(car);
    }
    @GetMapping("/addDriver")
    public Car addDriverToCar(@RequestParam int driverId, @RequestParam int carId){
        Driver driver= driverRepository.getDriverById(driverId);
        Car car= carRepository.getCarById(carId);
        car.addDriverSet(driver);
        return carRepository.createCar(car);
    }

}
