package com.gmf.services.resource;

import com.gmf.services.service.LoanService;
import com.gmf.services.service.LoanServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class MicroBankLoanResource {

    @Autowired
    LoanService loanService = new LoanServiceImpl();


    //for loan disburesement
    @RequestMapping(value = "/loandisbursment", method = RequestMethod.POST, headers = "Accept=Application/json")
    public ResponseEntity<?> loanDisbursement(@RequestBody Object request) {
        System.out.println("loan disbursement started");
        Map requestdata = (Map) request;

        int groupMasterId = (Integer) ((Map) request).get("groupMasterId");
        System.out.println("groupMasterId:" + groupMasterId);
        int groupMemberId = (Integer) ((Map) request).get("groupMemberId");
        System.out.println("groupMemberId:" + groupMemberId);
        double amtLoanDisb = (Double) ((Map) request).get("amtLoanDisb");
        System.out.println("amtLoanDisb:" + amtLoanDisb);
        String gaurnterData = (String) ((Map) request).get("gaurnterData");
        System.out.println("gaurnterData:" + gaurnterData);


        System.out.println("gauranter data:" + gaurnterData);
        System.out.println("required data taken");
        //service method

        loanService.performLoanDisbursement(groupMasterId, amtLoanDisb, groupMemberId, gaurnterData);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    //for loan installment collection
    @RequestMapping(value = "/loaninstallmentcollection", method = RequestMethod.POST, headers = "Accept=Application/json")
    public ResponseEntity<?> loanInstallmentCollection(@RequestBody Object request) {
        System.out.println("loan installement collection started");
        Map requestData = (Map) request;

        int groupMasterId = (Integer) ((Map) request).get("groupMasterId");
        int groupMemberId = (Integer) ((Map) request).get("groupMemberId");
        double installmentAmount = (double) ((Map) request).get("installmentAmount");
        System.out.println("required data taken");

        loanService.perfomLoanInstallment(groupMasterId, groupMemberId, installmentAmount);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }
}
