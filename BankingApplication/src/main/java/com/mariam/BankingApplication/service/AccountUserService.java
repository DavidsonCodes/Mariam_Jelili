package com.mariam.BankingApplication.service;

import com.mariam.BankingApplication.model.AccountUser;
import com.mariam.BankingApplication.repository.AccountUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AccountUserService {


    private final AccountUserRepository accountUserRepository;

    public AccountUserService(AccountUserRepository accountUserRepository) {
        this.accountUserRepository = accountUserRepository;
    }

    public ResponseEntity<Iterable<AccountUser>> getAllUser(){
        return new ResponseEntity<>(accountUserRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<AccountUser> getUserById(int id){
        return new ResponseEntity<>(accountUserRepository.findById(id).get(), HttpStatus.OK);
    }

    public ResponseEntity<AccountUser> getByUsername(String name){
        return new ResponseEntity<>(accountUserRepository.findByUsername(name), HttpStatus.OK);
    }

    public ResponseEntity<AccountUser> addNewUser(AccountUser accountUser) {
        return new ResponseEntity<>(accountUserRepository.save(accountUser), HttpStatus.CREATED);
    }

        public ResponseEntity<AccountUser> updateUser(int id, AccountUser accountUser) {
        AccountUser user = accountUserRepository.findById(id).get();
        user.setFirstName(accountUser.getFirstName());
        user.setLastName(accountUser.getLastName());
        user.setMiddleName(accountUser.getMiddleName());
        user.setUsername(accountUser.getUsername());
        user.setPassword(accountUser.getPassword());
        user.setPhoneNumber(accountUser.getPhoneNumber());
        return new ResponseEntity<>(accountUserRepository.save(user), HttpStatus.ACCEPTED);
        }

        public ResponseEntity<AccountUser> deleteUser(int id){
            return new ResponseEntity<>(accountUserRepository.findById(id).get(), HttpStatus.OK);
        }

}
