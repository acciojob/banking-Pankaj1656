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
//        int n=tradeLicenseId.length();
//        HashMap<Character,Integer> map=new HashMap<>();
//        for(int i=0;i<n;i++){
//            map.put(tradeLicenseId.charAt(i),map.getOrDefault(tradeLicenseId.charAt(i),0)+1);
//        }
//        if(Collections.max(map.values()) > n/2){
//            throw new Exception("Valid License can not be generated");
//        }
//        else{
//            int index1=0;
//            int index2=1;
//            char ans[]=new char[n];
//            for(var entry : map.entrySet()){
//                int freq= entry.getValue();
//                int i=0;
//                for(;i<freq && index1<ans.length;i++){
//                    ans[index1]=entry.getKey();
//                    index1=index1+2;
//                }
//                if(index1>ans.length){
//                    for(;i<freq && index2<ans.length;i++){
//                        ans[index2]= entry.getKey();
//                        index2+=2;
//                    }
//                }
//            }
//            String s=String.valueOf(ans);
//            this.tradeLicenseId=s;
//        }
        if(!isNumberValid(tradeLicenseId)){
            String rearrangedId = arrangeString(tradeLicenseId);
            if(rearrangedId == ""){
                throw new Exception("Valid License can not be generated");
            }else{
                this.tradeLicenseId = rearrangedId;
            }
        }
    }


    public boolean isNumberValid(String licenseId){
        for(int i=0; i<licenseId.length()-1; i++){
            if(licenseId.charAt(i) == licenseId.charAt(i+1)){
                return false;
            }
        }
        return true;
    }


    public String arrangeString(String s){
        int n = s.length();

        int[]count = new int[26];
        for(int i=0;i<26;i++){
            count[i] = 0;
        }
        for(char ch: s.toCharArray()){
            count[(int)ch - (int)'A']++;
        }

        char ch_max = getCountChar(count);
        int maxCount = count[(int)ch_max - (int)'A'];

        if(maxCount > (n+1)/2){
            return "";
        }

//        int index = 0;
//        char[]res = new char[n];
//        for(index=0;index<n;index=index+2){
//            if(count[maxCount]>0){
//                res[index] = ch_max;
//                count[maxCount]--;
//            }else{
//                break;
//            }
//        }
//
//        for(int i=0;i<26;i++){
//            char ch = (char)('A' + i);
//            while(count[i] > 0){
//                if(index>n){
//                    index = 1;
//                }
//                res[index] = ch;
//                index = index + 2;
//                count[i]--;
//            }
//        }
//        String ans = valueOf(res);
//        return ans;

        String res = "";
        for (int i = 0; i < n; i++) {
            res += ' ';
        }

        int ind = 0;
        while (maxCount > 0) {
            res = res.substring(0, ind) + ch_max
                    + res.substring(ind + 1);
            ind = ind + 2;
            maxCount--;
        }
        count[(int) ch_max - (int) 'A'] = 0;
        for (int i = 0; i < 26; i++) {
            while (count[i] > 0) {
                ind = (ind >= n) ? 1 : ind;
                res = res.substring(0, ind)
                        + (char) ((int) 'A' + i)
                        + res.substring(ind + 1);
                ind += 2;
                count[i]--;
            }
        }
        return res;
    }


    public char getCountChar(int[] count){
        int max = 0;
        char ch = 0;
        for(int i=0;i<26;i++){
            if(count[i]>max){
                max = count[i];
                ch = (char)((int)'A' + i);
            }
        }
        return ch;
    }



}
