package com.gmf.services.resource;

import com.gmf.services.service.CollectionServiceImpl;
import com.gmf.services.service.MicroBankMeetingServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class MicroBankMeetingResourceImpl implements MicroBankMeetingResource {

    //for start meeting
    @Override
    @RequestMapping(value = "/startmeeting", method = RequestMethod.POST, headers = "Accept=Application/json")
    public ResponseEntity<?> startMeeting(@RequestBody Object request) {
        System.out.println("meeting started");

        Map requestData = (Map) request;
        int groupMasterID = (Integer) ((Map) request).get("groupMasterID");

        MicroBankMeetingServiceImpl microBankMeetingServiceImpl = new MicroBankMeetingServiceImpl();
        microBankMeetingServiceImpl.startMeetingService(groupMasterID);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    //for share collection-for all
    @Override
    @RequestMapping(value = "/sharecollectionforall", method = RequestMethod.POST, headers = "Accept=Application/json")
    public ResponseEntity<?> shareCollectionForAll(@RequestBody Object request) {
        System.out.println("share collection for all started ");
        Map requestData = (Map) request;
        int groupMasterID = (Integer) ((Map) request).get("groupMasterID");
        System.out.println("groupMasterID taken");
        CollectionServiceImpl collectionServiceImpl = new CollectionServiceImpl();
        collectionServiceImpl.shareCollectionForAll(groupMasterID);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }


    //total cash balance
    @Override
    @RequestMapping(value = "/totalcashbalance", method = RequestMethod.POST, headers = "Accept=Application/json")
    public ResponseEntity<?> getTotalCashBalance(@RequestBody Object request) {
        System.out.println("getting total cash balance");
        Map requestdata = (Map) request;

        int groupMasterID = (Integer) ((Map) request).get("groupMasterID");
        System.out.println("groupMasterId taken ");
        CollectionServiceImpl collectionServiceImpl = new CollectionServiceImpl();
        collectionServiceImpl.totalCashBalance(groupMasterID);

        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }


    //for meeting end
    @Override
    @RequestMapping(value = "/endmeeting", method = RequestMethod.POST, headers = "Accept=Application/json")
    public ResponseEntity<?> meetingEnd(@RequestBody Object request) {
        System.out.println("meeting end process stared");
        Map requestdata = (Map) request;

        int groupMasterId = (Integer) ((Map) request).get("groupMasterId");
        String status = "A";//(String) ((Map) request).get("status");
        System.out.println("requried data taken for meeting end");

        MicroBankMeetingServiceImpl microBankMeetingServiceImpl = new MicroBankMeetingServiceImpl();
        microBankMeetingServiceImpl.endMeetingUpadateService(groupMasterId, status);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    //for getting total earning
    @Override
    @RequestMapping(value = "/totalearning", method = RequestMethod.POST, headers = "Accept=Application/json")
    public ResponseEntity<?> totalEarning(@RequestBody Object request) {
        System.out.println("calculating total earning");
        Map requestdata = (Map) request;

        int groupMasterId = (Integer) ((Map) request).get("groupMasterId");
        CollectionServiceImpl collectionServiceImpl = new CollectionServiceImpl();
        collectionServiceImpl.totalEaening(groupMasterId);

        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    //for Miscellenious Debit Event
    @Override
    @RequestMapping(value = "/miscelleniousDebitEvent", method = RequestMethod.POST, headers = "Accept=Application/json")
    public ResponseEntity<?> miscelleniousDebitEvent(@RequestBody Object request) {
        System.out.println("starting misc debit event");

        Map requestdata = (Map) request;
        System.out.println("request data taken");

        int groupMasterId = (Integer) ((Map) request).get("groupMasterId");
        int groupMemberId = (Integer) ((Map) request).get("groupMemberId");
        double expenseAmount = (Double) ((Map) request).get("expenseAmount");
        String description = (String) ((Map) request).get("description");
        System.out.println("Required data taken");
        //service class method
        MicroBankMeetingServiceImpl microBankMeetingServiceImpl = new MicroBankMeetingServiceImpl();
        microBankMeetingServiceImpl.miscelleniousdebitevent(groupMasterId, expenseAmount, description);

        return new ResponseEntity<String>("Success", HttpStatus.OK);

    }

    //for share collection - for individual
    @Override
    @RequestMapping(value = "/sharecollectionforIndividual", method = RequestMethod.POST, headers = "Accept=Application/json")
    public ResponseEntity<?> shareCollectionForIndividual(@RequestBody Object request) {
        System.out.println("share collection for individual started ");
        Map requestData = (Map) request;
        int groupMasterID = (Integer) ((Map) request).get("groupMasterID");
        int id = (Integer) ((Map) request).get("id");
        System.out.println("groupMasterID taken");
        CollectionServiceImpl collectionServiceImpl = new CollectionServiceImpl();
        collectionServiceImpl.shareCollectionForIndividual(groupMasterID, id);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }
}
