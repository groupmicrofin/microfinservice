package com.gmf.services.service;

import com.gmf.services.model.MicroBankParam;
import com.gmf.services.repository.MicrobankParamDaoServiceImpl;

public class MicroBankParamRegistrationServiceimpl implements MicroBankParamRegistrationService {

   public void addMicroBankParam(MicroBankParam microBankParam){
       System.out.println("in service:"+microBankParam);
       MicrobankParamDaoServiceImpl daoService=new MicrobankParamDaoServiceImpl();
       daoService.create(microBankParam);
       System.out.println("service end;");
    }


}
