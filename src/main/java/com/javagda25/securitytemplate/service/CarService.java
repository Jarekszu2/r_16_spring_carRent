package com.javagda25.securitytemplate.service;

import com.javagda25.securitytemplate.model.Car;
import com.javagda25.securitytemplate.model.CarStatus;
import com.javagda25.securitytemplate.repository.GruntRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private GruntRepository gruntRepository;

    @Autowired
    public CarService(GruntRepository gruntRepository) {
        this.gruntRepository = gruntRepository;
    }

    public List<Car> getAll() {
        return gruntRepository.findAll();
    }

    public List<Car> getCarsByStatus(CarStatus status) {
        return gruntRepository.findAllByCarStatus(status);
    }

    public void save(Car car) {
        gruntRepository.save(car);
    }

    public Optional<Car> getById(Long carId) {
        return gruntRepository.findById(carId);
    }

    public void remove(Long carId) {
        gruntRepository.deleteById(carId);
    }
//    public Page<Author> getPage(PageRequest of) {
//        return authorRepository.findAll(of);
//    }

    public Page<Car> getPage(PageRequest of) {
        return gruntRepository.findAll(of);
    }
}
