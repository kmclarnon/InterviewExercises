package com.github.kmclarnon.interview.exercises.common;

import static org.assertj.core.api.Assertions.fail;

import java.io.BufferedReader;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import org.assertj.core.api.Assertions;

public abstract class AbstractTester {
  private Map<String, String> inputOutputMap = new HashMap<>();

  public abstract void initialize() throws Exception;

  public void dryRun(Exercise exercise) {
    run(exercise, true);
  }

  public void test(Exercise exercise) {
    run(exercise, false);
  }


  protected void putInputOutput(String input, String outputFile) {
    inputOutputMap.put(input, outputFile);
  }

  private void run(Exercise exercise, boolean dryRun) {
    try {
      // delegate to subclass
      initialize();

      if (dryRun) {
        // run the exercise through its input
        inputOutputMap.entrySet()
            .forEach(e -> dryRun(exercise, e.getKey()));
      } else {
        // run the actual tests
        inputOutputMap.entrySet()
            .forEach(e -> test(exercise, e.getKey(), e.getValue()));
      }

    } catch (Exception e) {
      throw new RuntimeException("Failed to initialize test of " + exercise.getClass().getSimpleName());
    } finally {
      // ensure we do this so that the program isn't in a bad state
      resetSystemOut();
    }
  }

  private void dryRun(Exercise exercise, String input) {
    System.out.println(String.format("Running %s with input: %s", exercise.getClass().getSimpleName(), input));
    exercise.run(input);
  }

  private void test(Exercise exercise, String input, String expectedOutputFile) {
    try {
      // rewrite streams to hook tests
      PipedInputStream pipedInputStream = new PipedInputStream();
      PipedOutputStream pipedOutputStream = new PipedOutputStream();
      pipedInputStream.connect(pipedOutputStream);
      System.setOut(new PrintStream(pipedOutputStream));

      // run the test
      exercise.run(input);

      // close program output stream
      pipedOutputStream.close();

      // reset system out so we can log messages to the user
      resetSystemOut();

      // inspect the test output
      try (BufferedReader exerciseReader = new BufferedReader(new InputStreamReader(pipedInputStream));
           BufferedReader expectedReader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(expectedOutputFile)))) {
        String actualOutput;
        String expectedOutput;
        int lineCounter = 1;
        while (true) {
          actualOutput = exerciseReader.readLine();
          expectedOutput = expectedReader.readLine();
          if (actualOutput == null && expectedOutput == null) {
            System.out.println(String.format("Exercise: %s processed input: %s correctly", exercise.getClass().getSimpleName(), input));
            return; // test success
          } else if (actualOutput == null) {
            fail(String.format("Expected %s, but got null on line %d", expectedOutput, lineCounter));
          } else if (expectedOutput == null) {
            fail(String.format("Expected end of output, but got %s on line %d", actualOutput, lineCounter));
          } else if (!expectedOutput.trim().equals(actualOutput.trim())) {
            fail(String.format("Expected %s but got %s on line %d", expectedOutput.trim(), actualOutput.trim(), lineCounter));
          }

          lineCounter++;
        }
      }
    } catch (Exception e) {
      Assertions.fail(String.format("%s threw an exception processing input %s", exercise.getClass().getSimpleName(), input), e);
    }
  }

  protected void resetSystemOut() {
    System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
  }
}
