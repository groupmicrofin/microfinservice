package com.gmf.services.service;

import com.gmf.services.model.MicroBankMember;
import com.gmf.services.repository.MicroBankMemberDaoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MicroBankMemberRegistrationServiceImpl implements MicroBankMemberRegistrationService {

    @Autowired
    MicroBankMemberDaoServiceImpl daoService;

    @Override
    public void addMicroBankMemberInService(MicroBankMember microBankMember) {
        System.out.println("in microbank service");
        createPassword(microBankMember);

        daoService.createMicroBankMembers(microBankMember);
        System.out.println("service end");
    }

    private void createPassword(MicroBankMember microBankMember) {
        double random = Math.random();
        String password = microBankMember.getName() + random;
        microBankMember.setPassword(password);
    }

}
