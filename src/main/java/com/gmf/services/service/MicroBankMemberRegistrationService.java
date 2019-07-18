package com.gmf.services.service;

import com.gmf.services.model.MicroBankMember;
import com.gmf.services.repository.MicroBankMemberDaoService;

import java.util.Random;

public class MicroBankMemberRegistrationService {

    public void addMicroBankMemberInService(MicroBankMember microBankMember){
        System.out.println("in microbank service");
        createPassword(microBankMember);
        MicroBankMemberDaoService daoService=new MicroBankMemberDaoService();
        daoService.createMicroBankMembers(microBankMember);
        System.out.println("service end");
    }

    public void createPassword(MicroBankMember microBankMember){
        double random= Math.random();
        String password=microBankMember.getName()+random;
        microBankMember.setPassword(password);
    }


}
