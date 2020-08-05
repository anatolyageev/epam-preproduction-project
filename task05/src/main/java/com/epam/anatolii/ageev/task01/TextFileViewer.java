package com.epam.anatolii.ageev.task01;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class TextFileViewer implements Iterable<String> {
    private Scanner viewer;

    public TextFileViewer(String fileName) {
        try {
            viewer = new Scanner(new File(fileName), StandardCharsets.UTF_8.toString());
        } catch (FileNotFoundException ex) {
            System.out.println("File does not exist.");
        }
    }

    @Override
    public Iterator<String> iterator() {
        return new TextFileIterator();
    }

    private class TextFileIterator implements Iterator<String> {

        @Override
        public boolean hasNext() {
            return viewer.hasNextLine();
        }

        @Override
        public String next() {
            if (hasNext()) {
                return viewer.nextLine();
            }
            viewer.close();
            throw new NoSuchElementException();
        }
    }
}
