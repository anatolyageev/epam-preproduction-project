package com.epam.anatolii.ageev.task02.command;

import com.epam.anatolii.ageev.task02.command.impl.FilterByDateRangeCommand;
import com.epam.anatolii.ageev.task02.command.impl.FilterByExtentionCommand;
import com.epam.anatolii.ageev.task02.command.impl.FilterByFileNameCommand;
import com.epam.anatolii.ageev.task02.command.impl.FilterBySizeRangeCommand;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class CommandContainer implements Iterable<Command> {
    private List<Command> commands = new LinkedList<>();

    public CommandContainer() {
        commands.add(new FilterByFileNameCommand());
        commands.add(new FilterByExtentionCommand());
        commands.add(new FilterBySizeRangeCommand());
        commands.add(new FilterByDateRangeCommand());
    }

    @Override
    public Iterator<Command> iterator() {
        return commands.iterator();
    }

}
