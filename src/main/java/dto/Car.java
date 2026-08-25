package dto;

import enums.Fuel;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Car {
    private String location;
    private String manufacture;
    private String model;
    private int year;
    private Fuel fuel;
    private int seats;
    private String carClass;
    private String carRegistrationNumber;
    private double price;
    private String about;
    private String uploadPhoto;
}
