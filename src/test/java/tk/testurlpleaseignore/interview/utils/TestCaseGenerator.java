package tk.testurlpleaseignore.interview.utils;

import java.util.List;
import java.util.Random;

import com.google.common.collect.ImmutableList;

public class TestCaseGenerator {
  private static final Random rnd = new Random();

  public static int getRandomIntBetween(int min, int max) {
    return rnd.nextInt((max - min) + 1) + min;
  }

  public static List<Integer> getRandomListOfIntegers(int size) {
    return getRandomListOfIntegers(size, size);
  }

  public static List<Integer> getRandomListOfIntegers(int minSize, int maxSize) {
    int size = getRandomIntBetween(minSize, maxSize);
    ImmutableList.Builder<Integer> result = ImmutableList.builder();
    for (int i = 0; i < size; ++i) {
      result.add(rnd.nextInt());
    }
    return result.build();
  }

  public static <T> List<T> getRandomizedListFrom(List<T> source, int size) {
    return getRandomizedListFrom(source, size, size);
  }

  public static <T> List<T> getRandomizedListFrom(List<T> source, int minSize, int maxSize) {
    int size = getRandomIntBetween(minSize, maxSize);
    ImmutableList.Builder<T> result = ImmutableList.builder();
    for (int i = 0; i < size; ++i) {
      result.add(source.get(rnd.nextInt(source.size())));
    }

    return result.build();
  }
}
