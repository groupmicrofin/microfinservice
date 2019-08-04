package com.gmf.services.service;

public interface CollectionService {

    public void shareCollectionForAll(int groupMasterId);

    public double totalCashBalance(int groupMasterId);

    public void totalEarning(int groupMasterId);

    public void shareCollectionForIndividual(int groupMasterID, int id);


}
