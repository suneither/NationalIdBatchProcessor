package org.example;

import org.example.FileProcessing.LtuNatIdFileReader;
import org.example.FileProcessing.LtuNatIdFileWriter;
import org.example.Filtering.CustomFilters;
import org.example.IdValidation.Validator;
import org.example.InputHandling.InputHandler;
import org.example.InputHandling.InputOptions;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        InputHandler inputHandler = new InputHandler();
        if(!inputHandler.handleInput(args))
            System.exit(0);

        LtuNatIdFileReader ltuNatIdFileReader = new LtuNatIdFileReader();
        List<String> ids = ltuNatIdFileReader.readIdsFromFile(InputOptions.path);
        if(ids == null)
            System.exit(0);

        long startTime = System.currentTimeMillis();

        Validator idValidator = new Validator(ids);
        idValidator.validateIdsAsync();

        System.out.println("Time elapsed: " + (System.currentTimeMillis() - startTime));

        List<LtuNatIdModel> validIds = idValidator.getValidIds();
        List<LtuNatIdModel> invalidIds = idValidator.getInvalidIds();

        if(InputOptions.filter != null){
            Gender gender = null;
            if(InputOptions.filter.equals("male")) gender = Gender.MALE;
            else if(InputOptions.filter.equals("female")) gender = Gender.FEMALE;

            validIds = CustomFilters.filterByGender(validIds, gender);
            invalidIds = CustomFilters.filterByGender(invalidIds, gender);
        }

        if(InputOptions.sortType.equals("asc")){
            validIds.sort(Comparator.comparing(LtuNatIdModel::getBirthDate, Comparator.nullsFirst(Comparator.naturalOrder())));
            invalidIds.sort(Comparator.comparing(LtuNatIdModel::getBirthDate, Comparator.nullsFirst(Comparator.naturalOrder())));

        }else if(InputOptions.sortType.equals("desc")){
            validIds.sort(Comparator.comparing(LtuNatIdModel::getBirthDate, Comparator.nullsFirst(Comparator.reverseOrder())));
            invalidIds.sort(Comparator.comparing(LtuNatIdModel::getBirthDate, Comparator.nullsFirst(Comparator.reverseOrder())));
        }

        LtuNatIdFileWriter ltuNatIdFileWriter = new LtuNatIdFileWriter();
        ltuNatIdFileWriter.writeIdsToFile("valid.txt", validIds);
        ltuNatIdFileWriter.writeIdsToFile("invalid.txt", invalidIds);

        System.out.println("Valid id's: " + validIds.size() + " out of " + idValidator.getIdsCount());
        System.out.println("Invalid id's: " + invalidIds.size() + " out of " + idValidator.getIdsCount());
        System.out.println("Invalid given format: " + idValidator.getBadFormatIdsCount() + " out of " + idValidator.getIdsCount());
    }
}
