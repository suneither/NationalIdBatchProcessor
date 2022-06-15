package org.example.FileProcessing;

import org.example.LtuNatIdModel;

import java.io.*;
import java.util.List;

public class LtuNatIdFileWriter {

    public void writeIdsToFile(String filePath, List<LtuNatIdModel> ids) {

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));
            for(LtuNatIdModel id : ids){
                bw.write( id.getId() + " " + id.isValid());
                bw.newLine();
            }

            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
