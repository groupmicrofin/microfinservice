package com.gmf.services.repository;

import com.gmf.services.model.MicroBankMember;

public interface MicroBankMemberDaoService {

    public void createMicroBankMembers(MicroBankMember microBankMember);

    public int getTotalActiveMember(int groupMasterId);

    public int getShareBalance(MicroBankMember microBankMember);

    public void addMeetingSharebalanceForAllMember(int shareFaceValue, int currentCalenderID, int groupMasteId);

    public int getTotalShareBalance(int groupMasterId, String memberStatus);

    public void addMeetingSharBalanceForIndividual(int shareFaceValue, int currentCalenderID, int groupMasteId, int id);

}
