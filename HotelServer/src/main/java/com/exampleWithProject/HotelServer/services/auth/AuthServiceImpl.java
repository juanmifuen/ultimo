package com.exampleWithProject.HotelServer.services.auth;

import com.exampleWithProject.HotelServer.dto.SignupRequest;
import com.exampleWithProject.HotelServer.dto.UserDto;
import com.exampleWithProject.HotelServer.entity.User;
import com.exampleWithProject.HotelServer.enums.UserRole;
import com.exampleWithProject.HotelServer.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;



    @PostConstruct
    public void createAnAdminAccount(){
        Optional<User> adminAccount = userRepository.findByUserRole(UserRole.ADMIN);
        if(adminAccount.isEmpty()){
            User user = new User();
            user.setEmail("admin@test.com");
            user.setName("Admin");
            user.setUserRole(UserRole.ADMIN);
            user.setPassword(new BCryptPasswordEncoder().encode("admin"));
            userRepository.save(user);
            System.out.println("Cuenta de administrador creada exitosamente");


        }else{
            System.out.println("La cuenta de dministrador ya existe");
        }
    }


    public UserDto  createUser(SignupRequest signupRequest) {
        if (userRepository.findFirtsByEmail(signupRequest.getEmail()).isPresent()){
            throw new EntityExistsException("Usuario ya presente con correo electrónico" + signupRequest.getEmail());
        }


        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setName(signupRequest.getName());
        user.setUserRole(UserRole.CUSTOMER);
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        User createdUser = userRepository.save(user);
        return createdUser.getUserDto();
    }
}
