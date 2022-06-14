package org.example.InputHandling;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

import java.util.Arrays;

import static java.lang.System.out;

public class InputHandler {

    private static final String[] filterOptions = new String[] {"male", "female"};
    private static final String[] sortTypeOptions = new String[] {"asc", "desc"};


    public boolean handleInput(String[] args){
        InputOptions options = new InputOptions();
        CmdLineParser parser = new CmdLineParser(options);

        if(args.length == 0){
            parser.printUsage(out);
            return false;
        }

        try {
            parser.parseArgument(args);
        }catch (CmdLineException ex){
            out.println(ex.getMessage());
            return false;
        }

        if(InputOptions.filter != null){
            if(!Arrays.asList(filterOptions).contains(InputOptions.filter)){
                out.println(InputOptions.filter + " is not a valid filter");
                parser.printUsage(out);
                return false;
            }
        }

        if(InputOptions.sortType == null){
            InputOptions.sortType = "asc";
        }else {
            if(!Arrays.asList(sortTypeOptions).contains(InputOptions.sortType)){
                out.println(InputOptions.sortType + " is not a valid sort type");
                parser.printUsage(out);
                return false;
            }
        }

        return true;
    }
}
