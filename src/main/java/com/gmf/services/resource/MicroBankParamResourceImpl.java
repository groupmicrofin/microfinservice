package com.gmf.services.resource;

import com.gmf.services.model.MicroBankParam;
import com.gmf.services.model.MicroFinResponse;
import com.gmf.services.service.MicroBankParamRegistrationServiceimpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MicroBankParamResourceImpl implements MicroBankParamResource {

    @Override
    @RequestMapping(value = "/bankparam" ,method = RequestMethod.POST , headers = "Accept=application/json")
    public ResponseEntity<?> addGroupParam(@RequestBody MicroBankParam microBankParam){
        System.out.println("in resource:"+microBankParam);

        MicroBankParamRegistrationServiceimpl service=new MicroBankParamRegistrationServiceimpl();
        service.addMicroBankParam(microBankParam);

        System.out.println("resource task complete");
        MicroFinResponse response = new MicroFinResponse("Success");
        ResponseEntity<MicroFinResponse> responseEntity=new ResponseEntity<>(response, HttpStatus.CREATED);
        return responseEntity;
    }
}
