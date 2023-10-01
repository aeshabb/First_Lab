package org.example;

import org.example.entity.Direction;
import org.example.entity.Division;
import org.example.entity.JsonObject;
import org.example.entity.Subject;
import org.example.runner.Runner;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Runner run = new Runner();
        run.runMethods();
    }
}