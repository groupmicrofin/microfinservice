package com.gmf.services.service;

import com.gmf.services.model.MicroBankParam;
import com.gmf.services.repository.MicrobankParamDaoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MicroBankParamRegistrationServiceimpl implements MicroBankParamRegistrationService {

    @Autowired
    MicrobankParamDaoServiceImpl daoService;

    @Override
    public void addMicroBankParam(MicroBankParam microBankParam) {
        System.out.println("in service:" + microBankParam);
        daoService.create(microBankParam);
        System.out.println("service end;");
    }
}
