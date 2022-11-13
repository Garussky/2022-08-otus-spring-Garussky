package ru.otus.spring.garussky.service;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class IOServiceImpl implements IOService {

    private final PrintStream output;
    private final PrintStream errorOutput;
    private final Scanner input;

    public IOServiceImpl(PrintStream output, PrintStream errorOutput, InputStream input) {
        this.output = output;
        this.errorOutput = errorOutput;
        this.input = new Scanner(input);
    }

    @Override
    public void print(String message) {
        output.print(message + "\n");
    }

    @Override
    public void printDelimiter() {
        print("==========================");
    }

    @Override
    public void printError(String message) {
        errorOutput.print(message);
    }

    @Override
    public String readAnswer() {
        return input.nextLine();
    }

    @Override
    public int readAnswerIndex() {
        try {
            return Integer.parseInt(readAnswer());
        } catch (NumberFormatException nfe) {
            printError("Input is not valid, it should be a number.");
            return readAnswerIndex();
        }
    }
}
