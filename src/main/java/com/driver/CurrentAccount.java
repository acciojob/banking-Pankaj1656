package com.driver;

import lombok.Getter;

import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.SubmissionPublisher;
@Getter
public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception

        super(name,balance,5000);
        if(balance<5000){
            throw new Exception("Insufficient Balance");
        }
        this.tradeLicenseId=tradeLicenseId;
    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception
        int n=tradeLicenseId.length();
        HashMap<Character,Integer> map=new HashMap<>();
        for(int i=0;i<n;i++){
            map.put(tradeLicenseId.charAt(i),map.getOrDefault(tradeLicenseId.charAt(i),0)+1);
        }
        if(Collections.max(map.values()) > n/2){
            throw new Exception("Valid License can not be generated");
        }
        else{
            int index1=0;
            int index2=1;
            char ans[]=new char[n];
            for(var entry : map.entrySet()){
                int freq= entry.getValue();
                int i=0;
                for(;i<freq && index1<ans.length;i++){
                    ans[index1]=entry.getKey();
                    index1=index1+2;
                }
                if(index1>ans.length){
                    for(;i<freq && index2<ans.length;i++){
                        ans[index2]= entry.getKey();
                        index2+=2;
                    }
                }
            }
            String s=String.valueOf(ans);
            this.tradeLicenseId=s;
        }

    }

}
