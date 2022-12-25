package com.javagda25.securitytemplate.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Base64;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCar;

    @NotNull
    private String name;

    @NotNull
    private String regNr;

    @NotNull
    private int price;

    @NotNull
    private int mileage;

    @NotNull
    private CarStatus carStatus;

    @NotNull
    private int feeForCleaning;

    @NotNull
    private int feeForFuel;

    @NotNull
    private int cancellationCost;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "car", fetch = FetchType.LAZY)
    private Set<Booking> bookings;

    @OneToOne
    private CarPhoto carPhoto;

    public String convertBinToStringImage(){
        if (carPhoto != null && carPhoto.getFoto().length > 0) {
            return Base64.getEncoder().encodeToString(carPhoto.getFoto());
        }
        return "";
    }
}
