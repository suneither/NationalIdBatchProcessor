package org.example.FileProcessing;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LtuNatIdFileReader {

    public List<String> readIdsFromFile(String filePath){

        List<String> ltuNatIds = new ArrayList<>();

        try {
            BufferedReader bf = new BufferedReader(new FileReader(filePath));

            String line;
            while((line = bf.readLine()) != null){
                ltuNatIds.add(line);
            }

        } catch (FileNotFoundException e) {
            System.out.println("File does not exist!");
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return ltuNatIds;
    }
}
