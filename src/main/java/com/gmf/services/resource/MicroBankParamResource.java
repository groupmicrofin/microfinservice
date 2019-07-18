package com.gmf.services.resource;

import com.gmf.services.model.MicroBankParam;
import com.gmf.services.service.MicroBankParamRegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MicroBankParamResource {

    @RequestMapping(value = "/bankparam" ,method = RequestMethod.POST , headers = "Accept=application/json")
    public ResponseEntity<?> addGroupParam(@RequestBody MicroBankParam microBankParam){
        System.out.println("in resource:"+microBankParam);

        MicroBankParamRegistrationService service=new MicroBankParamRegistrationService();

        service.addMicroBankParam(microBankParam);
        System.out.println("resource task complete");
        ResponseEntity<String> responseEntity=new ResponseEntity<>("Success", HttpStatus.CREATED);
        return responseEntity;
    }
}
