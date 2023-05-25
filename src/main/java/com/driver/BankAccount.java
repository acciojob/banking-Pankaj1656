package com.driver;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BankAccount {

    private String name;
    private double balance;
    private double minBalance;

    public BankAccount(String name, double balance, double minBalance) {
         this.name=name;
         this.balance=balance;
         this.minBalance=minBalance;
    }

    public String generateAccountNumber(int digits, int sum) throws Exception{
        //Each digit of an account number can lie between 0 and 9 (both inclusive)
        //Generate account number having given number of 'digits' such that the sum of digits is equal to 'sum'
        //If it is not possible, throw "Account Number can not be generated" exception
        if(digits*9 <sum){
            throw new Exception("Account Number can not be generated exception");
        }
        String accNo="";
        int rem=sum;
        while(digits>0 && rem >0){
            if(rem>9) {
                accNo += "9";
                rem = rem - 9;
            }
            else{
                accNo+=Integer.toString(rem);
                rem=0;
            }
            digits--;
        }
        while(digits>0){
            accNo+="0";
            digits--;
        }
        return accNo;
    }

    public void deposit(double amount) {
        //add amount to balance
        this.balance+=amount;

    }

    public void withdraw(double amount) throws Exception {
        // Remember to throw "Insufficient Balance" exception, if the remaining amount would be less than minimum balance
        if(amount<minBalance){
            throw new Exception("Insufficient Balance");
        }
        this.balance=this.balance-amount;
    }

}