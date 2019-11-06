package com.javagda25.securitytemplate.service;

import com.javagda25.securitytemplate.model.CarRent;
import com.javagda25.securitytemplate.repository.CarRentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarRentService {

    @Autowired
    private CarRentRepository carRentRepository;

    public void saveCarRent(CarRent carRent) {
        carRentRepository.save(carRent);
    }

    public List<CarRent> getCarRentsList() {
        return carRentRepository.findAll();
    }

    public Page<CarRent> getPageCarRents(PageRequest of) {
        return carRentRepository.findAll(of);
    }

    public List<CarRent> getListCarRentsForPeriod(LocalDate dateStart, LocalDate dateEnd) {
        List<CarRent> carRentList = carRentRepository.findAll();
        List<CarRent> carListCarRentsForPeriod = carRentList.stream()
                .filter(carRent -> carRent.getCarReturn().getDateOfReturn().isAfter(dateStart) &&
                        carRent.getCarReturn().getDateOfReturn().isBefore(dateEnd))
                .collect(Collectors.toList());
        return carListCarRentsForPeriod;
    }

    public CarRent getCarRentById(Long idCarRent) {
        return carRentRepository.getOne(idCarRent);
    }
}
