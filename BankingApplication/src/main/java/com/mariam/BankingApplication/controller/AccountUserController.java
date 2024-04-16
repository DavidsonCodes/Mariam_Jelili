package com.mariam.BankingApplication.controller;

import com.mariam.BankingApplication.model.AccountUser;
import com.mariam.BankingApplication.model.AccountUserResource;
import com.mariam.BankingApplication.service.AccountUserService;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accountUser")
public class AccountUserController {

    private final AccountUserService accountUserService;

    public AccountUserController(AccountUserService accountUserService){this.accountUserService = accountUserService;}

    @GetMapping("/allUser")
    public ResponseEntity<Iterable<AccountUser>> getAllUser() {return accountUserService.getAllUser();}

    @GetMapping("/single/{id}")
    public ResponseEntity<AccountUser> getUserById(@PathVariable int id) {return accountUserService.getUserById(id);}

    @GetMapping("/search")
    public ResponseEntity<AccountUser> getByUsername(@RequestParam String name) {return accountUserService.getByUsername(name);}

    @PostMapping("/single")
    public ResponseEntity<AccountUser> addNewUser(@RequestBody AccountUser accountUser) {return accountUserService.addNewUser(accountUser);}

    @PutMapping("/single/{id}")
    public ResponseEntity<AccountUser> updateUser(@PathVariable int id, @RequestBody AccountUser accountUser) {return accountUserService.updateUser(id, accountUser);}

    @DeleteMapping("/single/{id}")
    public ResponseEntity<AccountUser> deleteUser(@PathVariable int id) {return accountUserService.deleteUser(id);}

    @GetMapping("/resource/{id}")
    public ResponseEntity<AccountUserResource> getMusicResource(@PathVariable int id){
        AccountUser account = accountUserService.getUserById(id).getBody();
        AccountUserResource accountUserResource = new AccountUserResource();
        accountUserResource.setAccountUser(account);
        Link selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AccountUserController.class).getUserById(id)).withSelfRel();
        Link delete = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AccountUserController.class).deleteUser(id)).withRel("delete");
        Link update = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AccountUserController.class).updateUser(id,account)).withRel("update");
        accountUserResource.add(selfLink, delete,update);
        return new ResponseEntity<>(accountUserResource, HttpStatus.OK);

    }
}

