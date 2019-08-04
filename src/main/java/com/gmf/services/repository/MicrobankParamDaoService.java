package com.gmf.services.repository;

import com.gmf.services.model.MicroBankParam;

public interface MicrobankParamDaoService {

    public void create(MicroBankParam microBankParam);

    public MicroBankParam findById(int groupMasterId);

    public float fetchLanInterestRate(int groupMasterId);




}
