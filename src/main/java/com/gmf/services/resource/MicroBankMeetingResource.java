package com.gmf.services.resource;

import com.gmf.services.service.MicroBankMeetingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class MicroBankMeetingResource {
    @RequestMapping(value = "/startmeeting", method = RequestMethod.POST, headers = "Accept=Application/json")
    public ResponseEntity<?> startMeeting(@RequestBody Object request) {
        System.out.println("meeting started");
        MicroBankMeetingService microBankMeetingService = new MicroBankMeetingService();
        Map requestData = (Map) request;
        int groupMasterID = (Integer) ((Map) request).get("groupMasterID");
        microBankMeetingService.startMeetingService(groupMasterID);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }
}
