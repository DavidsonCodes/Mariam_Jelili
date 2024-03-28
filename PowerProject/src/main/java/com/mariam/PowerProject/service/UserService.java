package com.mariam.PowerProject.service;

import com.mariam.PowerProject.modal.PowerUser;
import com.mariam.PowerProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public ResponseEntity<List<PowerUser>> getAllUser(){
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    public  ResponseEntity<Optional<PowerUser>> getUserById(int id){
        return new ResponseEntity<>(userRepository.findById(id), HttpStatus.OK);
    }

    public ResponseEntity<PowerUser> createUser(PowerUser request){
        PowerUser powerUser = new PowerUser();
        powerUser.setFirstName(request.getFirstName());
        powerUser.setLastName(request.getLastName());
        powerUser.setAddress(request.getAddress());
        powerUser.setPhoneNumber(request.getPhoneNumber());
        powerUser.setCourse(request.getCourse());

        return new ResponseEntity<>(userRepository.save(powerUser), HttpStatus.CREATED);
    }

    public ResponseEntity<PowerUser> updateUser(int id,PowerUser request){
        PowerUser studentObject = userRepository.findById(id).get();
        studentObject.setFirstName(request.getFirstName());
        studentObject.setLastName(request.getLastName());
        studentObject.setAddress(request.getAddress());
        studentObject.setPhoneNumber(request.getPhoneNumber());
        studentObject.setCourse(request.getCourse());

        return new ResponseEntity<>(userRepository.save(studentObject), HttpStatus.OK);
    }

    public ResponseEntity<PowerUser> deleteUser(int id){
        PowerUser powerUser = userRepository.findById(id).get();
        userRepository.delete(powerUser);
        return new ResponseEntity<>(powerUser, HttpStatus.ACCEPTED);
    }

}
