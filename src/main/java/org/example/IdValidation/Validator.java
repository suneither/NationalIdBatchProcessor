package org.example.IdValidation;

import org.example.LithuanianNationalIdValidator;
import org.example.LtuNatIdModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

public class Validator {

    final int total_threads = 4;

    private List<String> unvalidatedIds;
    private List<LtuNatIdModel> validIds = Collections.synchronizedList(new ArrayList<>());
    private List<LtuNatIdModel> invalidIds = Collections.synchronizedList(new ArrayList<>());
    private int idsCount;
    private int badFormatIdsCount = 0;

    public Validator(List<String> ids){
        unvalidatedIds = ids;
        idsCount = unvalidatedIds.size();
    }

    public void validateIdsAsync() throws InterruptedException{
        ExecutorService executor = Executors.newFixedThreadPool(total_threads);

        for(String id : unvalidatedIds){
            executor.execute(new LitNatIdValidationTask(id));
        }

        System.out.println("Validating ids");

        executor.shutdown();
        while (!executor.awaitTermination(50, TimeUnit.MILLISECONDS)){}

        System.out.println("Ids validated");
    }

    public void validateIdsSync() {
        System.out.println("Validating ids");

        for (String id : unvalidatedIds){
            validateId(id);
        }

        System.out.println("Ids validated");
    }

    private void validateId(String id){
        try{
            LithuanianNationalIdValidator litNatIdVal = new LithuanianNationalIdValidator();

            long parsedId = Long.parseLong(id);
            boolean out = litNatIdVal.validateID(parsedId);
            if(out){
                validIds.add(litNatIdVal.getLtuNatIdModel());
            }else {
                invalidIds.add(litNatIdVal.getLtuNatIdModel());
            }
        }catch (NumberFormatException e){
            badFormatIdsCount++;
        }
    }

    public List<LtuNatIdModel> getValidIds() {
        return validIds;
    }

    public List<LtuNatIdModel> getInvalidIds() {
        return invalidIds;
    }

    public int getIdsCount() {
        return idsCount;
    }

    public int getBadFormatIdsCount() { return badFormatIdsCount; }

    private class LitNatIdValidationTask implements Runnable {
        public String id;

        public LitNatIdValidationTask(String id){
            this.id = id;
        }

        @Override
        public void run() {
            validateId(id);
        }
    }
}
