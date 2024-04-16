package com.mariam.BankingApplication.model;

import org.springframework.hateoas.RepresentationModel;

public class AccountUserResource extends RepresentationModel<AccountUserResource> {
    public AccountUser accountUser;

    public AccountUser getAccountUser() {
        return accountUser;
    }

    public void setAccountUser(AccountUser accountUser) {
        this.accountUser = accountUser;
    }
}
