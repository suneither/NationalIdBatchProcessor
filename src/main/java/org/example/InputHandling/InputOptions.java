package org.example.InputHandling;

import org.kohsuke.args4j.Option;

public class InputOptions {

    @Option(name = "-i", aliases = "--input", required = true, usage = "Sets a file: full path")
    public String path;

    @Option(name = "-f", aliases = "--filter", usage = "Sets a filter: male|female")
    public String filter;

    @Option(name = "-sbd", aliases = "--sort-by-birth-date", usage = "Sets a sort type: asc|desc")
    public String sortType;
}
