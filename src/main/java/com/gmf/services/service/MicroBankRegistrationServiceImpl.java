package com.gmf.services.service;

import com.gmf.services.model.MicroBnak;
import com.gmf.services.repository.MicroBankDaoService;
import com.gmf.services.repository.MicroBankDaoServiceImpl;

import java.util.Random;

public class MicroBankRegistrationServiceImpl implements MicroBankRegistrationService{

    MicroBankDaoService microBankDaoServiceImpl = new MicroBankDaoServiceImpl();

    @Override
    public MicroBnak performMicroBankRegistration(MicroBnak microBnak) {
        System.out.println("call group registration start");
        System.out.println("Microbank Data" + microBnak);
        String loginID = createLogInId(microBnak.getName());
        microBnak.setLoginId(loginID);
        //dao service method call
        MicroBnak microBnak1 = microBankDaoServiceImpl.addMicroBank(microBnak);
        boolean result = microBankDaoServiceImpl.addMicroBankBalance(microBnak1.getId());
        System.out.println("call group registration end" + result);
        return microBnak1;

    }

    private String createLogInId(String name) {
        int random = new Random().nextInt(100);
        return name + random;
    }
}
