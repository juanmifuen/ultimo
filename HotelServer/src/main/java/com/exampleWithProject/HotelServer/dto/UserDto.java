package com.exampleWithProject.HotelServer.dto;

import com.exampleWithProject.HotelServer.enums.UserRole;
import lombok.Data;

@Data
public class UserDto {

    private Long id ;


    private String email;

    private  String name ;


    private UserRole userRole;
}
