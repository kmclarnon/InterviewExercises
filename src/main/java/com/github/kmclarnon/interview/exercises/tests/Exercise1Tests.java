package com.github.kmclarnon.interview.exercises.tests;

import com.github.kmclarnon.interview.exercises.common.AbstractTester;

public class Exercise1Tests extends AbstractTester {
  private static final String INPUT_FORMAT = "%s - %s";
  private static final String EXERCISE_1_INPUT = "/Exercise1Input1.txt";
  private static final String EXERCISE_1_OUTPUT_1 = "/Exercise1Output1.txt";

  @Override
  public void initialize() throws Exception {
    putInputOutput(String.format(INPUT_FORMAT, EXERCISE_1_INPUT, 1), EXERCISE_1_OUTPUT_1);
  }
}
