package org.example.InputHandling;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

import static java.lang.System.out;

public class InputHandler {

    public boolean handleInput(String[] args){

        InputOptions options = new InputOptions();

        CmdLineParser parser = new CmdLineParser(options);

        if(args.length == 0){
            parser.printUsage(out);
        }

        try {
            parser.parseArgument(args);
        }catch (CmdLineException ex){
            ex.printStackTrace();
            return false;
        }

        return true;
    }
}
