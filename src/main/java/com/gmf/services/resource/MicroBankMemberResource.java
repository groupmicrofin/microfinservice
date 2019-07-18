package com.gmf.services.resource;

import com.gmf.services.model.MicroBankMember;
import com.gmf.services.service.MicroBankMemberRegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MicroBankMemberResource {

    @RequestMapping(value = "/bankmember", method = RequestMethod.POST, headers = "Accept=Application/json")
    public ResponseEntity<?> addMember(@RequestBody MicroBankMember microBankMember) {
        System.out.println("in microbankMember " + microBankMember);
        MicroBankMemberRegistrationService memberRegistrationService = new MicroBankMemberRegistrationService();
        memberRegistrationService.addMicroBankMemberInService(microBankMember);
        System.out.println("Resource task complete");
        ResponseEntity<String> responseEntity = new ResponseEntity<>("success", HttpStatus.CREATED);
        return responseEntity;

    }
}
