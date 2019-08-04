package com.gmf.services.service;

import com.gmf.services.model.MicroBnak;
import com.gmf.services.repository.MicroBankDaoService;
import com.gmf.services.repository.MicroBankDaoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class MicroBankRegistrationServiceImpl implements MicroBankRegistrationService {

    @Autowired
    MicroBankDaoService microBankDaoServiceImpl;

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
