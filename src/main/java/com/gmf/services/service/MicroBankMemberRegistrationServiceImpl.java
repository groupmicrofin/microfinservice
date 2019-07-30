package com.gmf.services.service;

import com.gmf.services.model.MicroBankMember;
import com.gmf.services.repository.MicroBankMemberDaoServiceImpl;

public class MicroBankMemberRegistrationServiceImpl implements MicroBankMemberRegistrationService{

    @Override
    public void addMicroBankMemberInService(MicroBankMember microBankMember){
        System.out.println("in microbank service");
        createPassword(microBankMember);
        MicroBankMemberDaoServiceImpl daoService=new MicroBankMemberDaoServiceImpl();
        daoService.createMicroBankMembers(microBankMember);
        System.out.println("service end");
    }

    private void createPassword(MicroBankMember microBankMember){
        double random= Math.random();
        String password=microBankMember.getName()+random;
        microBankMember.setPassword(password);
    }


}
