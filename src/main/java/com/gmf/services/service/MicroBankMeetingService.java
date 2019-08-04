package com.gmf.services.service;

public interface MicroBankMeetingService {

    public void startMeetingService(int groupMasterId);

    public void endMeetingUpadateService(int groupMasterId, String status);

    public void miscelleniousdebitevent(int groupMasterId, double expenseAmount, String description);

}
