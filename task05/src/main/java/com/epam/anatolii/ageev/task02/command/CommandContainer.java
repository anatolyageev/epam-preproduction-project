package com.epam.anatolii.ageev.task02.command;

import com.epam.anatolii.ageev.task02.command.impl.FilterByExtentionCommand;
import com.epam.anatolii.ageev.task02.command.impl.FilterByFileNameCommand;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class CommandContainer implements Iterable<Command>{
    private List<Command> commands = new LinkedList<>();

    public CommandContainer(){
        commands.add(new FilterByFileNameCommand());
        commands.add(new FilterByExtentionCommand());
    }

    @Override
    public Iterator<Command> iterator() {
        return commands.iterator();
    }

}
