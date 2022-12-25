package com.javagda25.securitytemplate.controller;

import com.javagda25.securitytemplate.model.Car;
import com.javagda25.securitytemplate.repository.CarPhotoRepository;
import com.javagda25.securitytemplate.repository.CarRepository;
import com.javagda25.securitytemplate.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping(path = "/")
@AllArgsConstructor
public class IndexController {

    @Autowired
    private CarService carService;

    @Autowired
    private CarRepository carRepository;

    @GetMapping("/")
    public String index(Model model)
    {
//        Long index = Long.valueOf(1);
        Optional<Car> optionalCar = carRepository.findAllByName("Stinger");
        if (optionalCar.isPresent()) {
            Car car = optionalCar.get();
            model.addAttribute("atr_car", car);

            return "index";
        }
        return "index";
    }

    @GetMapping("/tylkodlakozakow")
    public String kozaki(){
        return "kozaki";
    }

    @GetMapping("/login")
    public String loginForm(){
        return "login-form";
    }

    @GetMapping("/faq")
    public String faq(){ return "about-us";}

    @GetMapping("/contact-form")
    public String contactFrom(){return "contact-form";}

    @GetMapping("/rental-conditions")
    public String rentalConditions(){return "rental-conditions";}
}
