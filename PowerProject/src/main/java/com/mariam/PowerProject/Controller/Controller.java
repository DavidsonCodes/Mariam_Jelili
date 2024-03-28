package com.mariam.PowerProject.Controller;

import com.mariam.PowerProject.modal.PowerUser;
import com.mariam.PowerProject.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class Controller {

    private UserService userService;

    public Controller(UserService studentService) {
        this.userService = studentService;
    }
    @GetMapping("/user")
    public ResponseEntity<List<PowerUser>> getAllUser(){
        return userService.getAllUser();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Optional<PowerUser>> getByUserId(@PathVariable int id){
        return userService.getUserById(id);
    }

    @PostMapping("/user")
    public ResponseEntity<PowerUser> createNewUser(@RequestBody PowerUser request){
        return userService.createUser(request);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<PowerUser> updateUser(@PathVariable int id, @RequestBody PowerUser request){
        return userService.updateUser(id, request);
    }

    @DeleteMapping("/user")
    public  ResponseEntity<PowerUser> deleteUser(@RequestParam int id){
        return userService.deleteUser(id);
    }

}
