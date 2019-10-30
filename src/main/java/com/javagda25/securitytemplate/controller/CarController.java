package com.javagda25.securitytemplate.controller;

import com.javagda25.securitytemplate.model.Car;
import com.javagda25.securitytemplate.model.CarStatus;
import com.javagda25.securitytemplate.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/car/")
public class CarController {

    private CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/list")
    public String list(Principal principal,
                        Model model,
                       @RequestParam(name = "page", defaultValue = "0") int page,
                       @RequestParam(name = "size", defaultValue = "2") int size) {
//        principal.getName() nazwa zalogowanego uzytkownika
        Page<Car> carPage = carService.getPage(PageRequest.of(page, size));
        model.addAttribute("cars", carPage);
        return "car-list";
    }

    @GetMapping("/add")
    public String addCar(Model model, Car car) {
        car.setCarStatus(CarStatus.AVAILABLE);
        CarStatus[] statuses = CarStatus.values();

        model.addAttribute("statuses", statuses);
        model.addAttribute("car", car);
        return "car-add";
    }

    @PostMapping("/add")
    public String add(Car car) {
        carService.save(car);
        return "redirect:/car/list";
    }

    @GetMapping("/details")
    public String details(Model model, HttpServletRequest request, @RequestParam(name = "carId") Long carId) {
        Optional<Car> optionalCar = carService.getById(carId);
        if (optionalCar.isPresent()) {
            Car car = optionalCar.get();
//            CarStatus[] statuses = CarStatus.values();

            model.addAttribute("car", car);
//            model.addAttribute("statuses", statuses);
            model.addAttribute("referer", request.getHeader("referer"));
            return "car-details";
        }
        return "redirect:/car/list";
    }

    @GetMapping("/remove")
    public String remove(
            HttpServletRequest request,
            @RequestParam(name = "carId") Long carId) {
        String referer = request.getHeader("referer");
        carService.remove(carId);

        if (referer != null) {
            return "redirect:" + referer;
        }
        return "redirect:/car/list";
    }

    @GetMapping("/edit")
    public String editCar(Model model, @RequestParam(name = "carId") Long carId) {
        Optional<Car> carOptional = carService.getById(carId);
        CarStatus[] statuses = CarStatus.values();


        if (carOptional.isPresent()) {

            model.addAttribute("car", carOptional.get());
            model.addAttribute("statuses", statuses);

            return "car-add";
        }

        return "redirect:/car/list";
    }

}
