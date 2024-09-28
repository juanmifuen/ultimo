package com.exampleWithProject.HotelServer.services.auth;

import com.exampleWithProject.HotelServer.dto.SignupRequest;
import com.exampleWithProject.HotelServer.dto.UserDto;

public interface AuthService {

    UserDto createUser(SignupRequest signupRequest);
}
