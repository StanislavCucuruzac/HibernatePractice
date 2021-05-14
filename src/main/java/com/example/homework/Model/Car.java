package com.example.homework.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String name;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
            @JoinColumn(name = "fk_cars")
    List<Wheel> wheels=new ArrayList<>();
    @OneToOne(mappedBy = "car",fetch = FetchType.LAZY)
    Employee employee;

    public Car(int id, String name) {
        this.id= id;
        this.name= name;
    }

    public void addWheelToList(Wheel wheel){
        wheels.add(wheel);
    }
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "car_driver",
            joinColumns = @JoinColumn(name = "car_id"),
            inverseJoinColumns = @JoinColumn(name = "driver_id")
    )
    Set<Driver> driverSet= new LinkedHashSet<>();
    public void addDriverSet(Driver driver){
        driverSet.add(driver);
    }
}
