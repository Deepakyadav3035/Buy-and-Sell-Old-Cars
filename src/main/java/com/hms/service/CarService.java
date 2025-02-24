package com.hms.service;

import com.hms.entity.Cars.*;
import com.hms.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
    public class CarService {


    public CarService(BrandRepository brandRepository, FuelTypeRepository fuelTypeRepository, TransmissionRepository transmissionRepository, ModelRepository modelRepository, YearRepository yearRepository, KM_DrivenRepository kmDrivenRepository, CarRepository carRepository, CarRepository carRepository1) {
        this.brandRepository = brandRepository;
        this.fuelTypeRepository = fuelTypeRepository;
        this.transmissionRepository = transmissionRepository;
        this.modelRepository = modelRepository;
        this.yearRepository = yearRepository;
        this.kmDrivenRepository = kmDrivenRepository;
        this.carRepository = carRepository1;
    }

    private BrandRepository brandRepository;
        private FuelTypeRepository fuelTypeRepository;
        private ModelRepository modelRepository;
        private TransmissionRepository transmissionRepository;
        private YearRepository yearRepository;
        private KM_DrivenRepository kmDrivenRepository;
        private CarRepository carRepository;

        public ResponseEntity<?> createBrand(Brand brand) {
            Optional<Brand> byName = brandRepository.findByName(brand.getName());
            if(byName.isPresent()){
                return new ResponseEntity<>("Brand is already exist", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            Brand saveBrand = brandRepository.save(brand);
            return new ResponseEntity<>(saveBrand,HttpStatus.CREATED);
        }

    public ResponseEntity<?> createModel(Model model) {
        // Check if the Brand exists

        Optional<Model> byName = modelRepository.findByName(model.getName());
        if (byName.isPresent()) {
            return new ResponseEntity<>("Model already exists", HttpStatus.CONFLICT); // 409 Conflict is more appropriate
        }

        // Set the brand and save the model
        Model savedModel = modelRepository.save(model);

        // Return the saved model with HTTP status 201
        return new ResponseEntity<>(savedModel, HttpStatus.CREATED);
    }


    public ResponseEntity<?> createFuelType(FuelType fueltype) {
        Optional<FuelType> byType = fuelTypeRepository.findByFueltype(fueltype.getFueltype());
        if(byType.isPresent()){
            return new ResponseEntity<>("FuelType is already exist", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        FuelType fuelType = fuelTypeRepository.save(fueltype);
        return new ResponseEntity<>(fuelType,HttpStatus.CREATED);
    }


    public ResponseEntity<?> createKMDriven(KM_Driven kmDriven) {
        Optional<KM_Driven> byKmDriven = kmDrivenRepository.findByKms(kmDriven.getKms());
        if(byKmDriven.isPresent()){
            return new ResponseEntity<>("FuelType is already exist", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        KM_Driven saveKm = kmDrivenRepository.save(kmDriven);
        return new ResponseEntity<>(saveKm,HttpStatus.CREATED);
    }

    public ResponseEntity<?> createTransmission(Transmission transmission) {
        Optional<Transmission> byType = transmissionRepository.findByType(transmission.getType());
        if(byType.isPresent()){
            return new ResponseEntity<>("Transmission is already exist", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Transmission saveType = transmissionRepository.save(transmission);
        return new ResponseEntity<>(saveType,HttpStatus.CREATED);
        }


    public ResponseEntity<?> createYear(Year year) {
        Optional<Year> byYear = yearRepository.findByYear(year.getYear());
        if(byYear.isPresent()){
            return new ResponseEntity<>("Year is already exist", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Year saveYear = yearRepository.save(year);
        return new ResponseEntity<>(saveYear,HttpStatus.CREATED);
    }

}
