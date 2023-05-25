package com.driver;

import lombok.Getter;

@Getter
public class StudentAccount extends BankAccount{

    private String  institutionName;

    public StudentAccount(String name, double balance, String  institutionName) {
        super(name,balance,0);
        //minimum balance is 0 by default
         this.institutionName=institutionName;
    }

}
