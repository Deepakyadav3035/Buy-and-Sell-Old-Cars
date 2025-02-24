package com.hms.controller;


import com.hms.entity.Cars.Car;
import com.hms.entity.Cars.CarImage;
import com.hms.repository.CarImageRepository;
import com.hms.repository.CarRepository;
import com.hms.service.BucketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/images")
public class ImageController {

    private BucketService bucketService;
    private CarRepository carRepository;
    private CarImageRepository carImageRepository;

    public ImageController(BucketService bucketService, CarRepository carRepository, CarImageRepository carImageRepository) {
        this.bucketService = bucketService;
        this.carRepository = carRepository;
        this.carImageRepository = carImageRepository;
    }

    //http://localhost:8080/api/v1/images/upload/file/{bucketName}/car/{carId}
    @PostMapping(path = "/upload/file/{bucketName}/car/{carId}")
    public ResponseEntity<CarImage> uploadCarPhotos(
            @RequestParam MultipartFile file,
            @PathVariable String bucketName,
            @PathVariable Long carId
    ) {
        String url = bucketService.uploadFile(file, bucketName);
//        ImageDto imageDto = new ImageDto();
//        imageDto.setImageUrl(url);
        Car car = carRepository.findById(carId).get();
        CarImage image = new CarImage();
        image.setUrl(url);
        image.setCar(car);
        CarImage savedImage = carImageRepository.save(image);
        return new ResponseEntity<>(savedImage, HttpStatus.OK);
    }


}