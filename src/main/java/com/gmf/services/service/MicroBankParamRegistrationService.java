package com.gmf.services.service;

import com.gmf.services.model.MicroBankParam;
import com.gmf.services.model.MicroBnak;
import com.gmf.services.repository.MicroBankDaoService;
import com.gmf.services.repository.MicrobankParamDaoService;

import java.util.Random;

public class MicroBankParamRegistrationService {

   public void addMicroBankParam(MicroBankParam microBankParam){
       System.out.println("in service:"+microBankParam);
       MicrobankParamDaoService daoService=new MicrobankParamDaoService();
       daoService.create(microBankParam);
       System.out.println("service end;");
    }


}
