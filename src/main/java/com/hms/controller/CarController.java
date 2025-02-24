package com.hms.controller;

import com.hms.entity.Cars.*;
import com.hms.repository.CarRepository;
import com.hms.service.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/car")
public class CarController {


    private CarService carService;
    private CarRepository carRepository;

    public CarController(CarService carService, CarRepository carRepository) {
        this.carService = carService;
        this.carRepository = carRepository;
    }

    @PostMapping("/add-brand")
    public ResponseEntity<?> createBrand(
            @RequestBody Brand brand
            ){
        ResponseEntity<?> brandName = carService.createBrand(brand);
        return brandName;
    }

    @PostMapping("/add-model/{brandId}")
    public ResponseEntity<?> createModel(
            @RequestBody Model model,       // Model data (only 'name' here)
            @PathVariable Long brandId) {   // Brand ID from URL path

        // Create a new Brand object and set the provided brandId
        Brand brand = new Brand();
        brand.setId(brandId);  // Set the brandId from the URL

        // Call the service to create the model with the associated brand
        return carService.createModel(model);
    }

    @PostMapping("/add-fuel-type")
    public ResponseEntity<?>createFuelType(
            @RequestBody FuelType fueltype
            ){
        ResponseEntity<?> fuelType = carService.createFuelType(fueltype);
        return fuelType;
    }

    @PostMapping("/add-KM-Driven")
    public ResponseEntity<?>createKMDiven(
            @RequestBody KM_Driven kmDriven
            ){
        ResponseEntity<?> km = carService.createKMDriven(kmDriven);
        return km;
    }

    @PostMapping("/add-transmission")
    public ResponseEntity<?>createTransmission(
            @RequestBody Transmission transmission
            ){
        ResponseEntity<?> Type = carService.createTransmission(transmission);
        return Type;
    }

    @PostMapping("/add-year")
    public ResponseEntity<?>createYear(
            @RequestBody Year year
    ){
        ResponseEntity<?>Year = carService.createYear(year);
        return Year;
    }

    @GetMapping("/searchCar")
    public List<Car> searchCar(
            @RequestParam String param
    ){
       return carRepository.searchCar(param);
    }


}

