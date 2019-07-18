package com.gmf.services.service;

import com.gmf.services.model.MicroBnak;
import com.gmf.services.repository.MicroBankDaoService;

import java.util.Random;

public class MicroBankRegistrationService {

    MicroBankDaoService microBankDaoService=new MicroBankDaoService();

    public  MicroBnak performMicroBankRegistration(MicroBnak microBnak){
        System.out.println("call group registration start");
        System.out.println("Microbank Data"+ microBnak);
        String loginID=createLogInId(microBnak.getName());
        microBnak.setLoginId(loginID);
        MicroBnak microBnak1=microBankDaoService.addMicroBank(microBnak);
        boolean result=microBankDaoService.addMicroBankBalance(microBnak1.getId());
        System.out.println("call group registration end"+result);
        return microBnak1;

}
    public String createLogInId(String name){
        int random=new Random().nextInt(100);
        return name+random;
    }
}
