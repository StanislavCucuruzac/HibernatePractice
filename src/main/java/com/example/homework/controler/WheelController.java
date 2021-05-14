package com.example.homework.controler;

import com.example.homework.Model.Car;
import com.example.homework.Model.Wheel;
import com.example.homework.repository.WheelReposiotry;
import lombok.AllArgsConstructor;
import org.hibernate.SessionFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wheel")
@AllArgsConstructor
public class WheelController {
     WheelReposiotry wheelReposiotry;
//    @PostMapping
//    public Wheel post(@RequestBody Wheel wheel){
//        return wheel;
//    }
    @PostMapping
    public Wheel createWheel(@RequestBody Wheel wheel) {
        return wheelReposiotry.createWheel(wheel);
    }


    @GetMapping
    public List<Wheel> getAllWheel(){
        return wheelReposiotry.getAllWhell();
    }
    @GetMapping("/id/{id}")
    public Wheel getById(@PathVariable int id){
        return wheelReposiotry.getById(id);
    }
}
