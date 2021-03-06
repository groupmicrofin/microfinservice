package com.gmf.services.resource;


import com.gmf.services.model.MicroBnak;
import com.gmf.services.service.MicroBankRegistrationService;
import com.gmf.services.service.MicroBankRegistrationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class MicroBankResource {

    @Autowired
    MicroBankRegistrationService bankRegistrationService = new MicroBankRegistrationServiceImpl();

    @RequestMapping(value = "/bankreg", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<?> performmicrobankregistrations(@RequestBody MicroBnak microBnak) {
        MicroBnak microBnak1 = bankRegistrationService.performMicroBankRegistration(microBnak);
        if (microBnak1 == null) {
            return new ResponseEntity<String>("not able to create group", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<MicroBnak>(microBnak1, HttpStatus.OK);
    }
}
