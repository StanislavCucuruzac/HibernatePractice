package com.example.homework.controler;

import com.example.homework.Model.Car;
import com.example.homework.Model.Driver;
import com.example.homework.Model.Wheel;
import com.example.homework.repository.CarRepository;
import com.example.homework.repository.DriverRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/driver")
@AllArgsConstructor
public class DriverController {
    DriverRepository driverRepository;
    CarRepository carRepository;

    @GetMapping
    public List<Driver> getAll(){
        return driverRepository.getAllDrivers();
    }
    @PostMapping
    public Driver addDriver(@RequestBody Driver driver){
        return  driverRepository.addDriver(driver);
    }
    @GetMapping("/id/{id}")
    public Driver getById(@PathVariable int id){
        return driverRepository.getDriverById(id);
    }
    @DeleteMapping("/{id}")
    public void deleteDriver(@PathVariable int id){
         driverRepository.deleteDriver(id);
    }
    @GetMapping("/addCar")
    public Driver addCartoDriver(@RequestParam int carId, @RequestParam int driverId){
        Car car= carRepository.getCarById(carId);
        Driver driver = driverRepository.getDriverById(driverId);
        driver.addCarSet(car);
        return driverRepository.addDriver(driver);
    }

}
