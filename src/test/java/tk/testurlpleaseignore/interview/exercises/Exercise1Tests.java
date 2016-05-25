package tk.testurlpleaseignore.interview.exercises;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.Test;

import com.google.common.collect.ImmutableList;

import tk.testurlpleaseignore.interview.utils.TestBase;
import tk.testurlpleaseignore.interview.utils.TestCaseGenerator;

public class Exercise1Tests extends TestBase {

  private static final List<Integer> seedList = ImmutableList.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
  private static final List<Integer> testSizeList = ImmutableList.of(15, 30, 50, 100);

  @Test
  public void itPassesRandomizedTests() {
    Exercise1 solution = new Exercise1();
    for (int testSize : testSizeList) {
      List<Integer> testData;
      Optional<Integer> mostCommonInteger;
      do {
        testData = TestCaseGenerator.getRandomizedListFrom(seedList, testSize);
        mostCommonInteger = getMostCommonInteger(testData);
      } while (!mostCommonInteger.isPresent());

      assertThat(solution.run(toIntArray(testData))).as("Checking most common value in %s", testData).isEqualTo(mostCommonInteger.get());
    }
  }

  private Optional<Integer> getMostCommonInteger(List<Integer> integers) {
    Map<Integer, Integer> counts = new HashMap<>();

    integers.stream()
        .forEach(i -> counts.put(i, counts.getOrDefault(i, 0) + 1));

    int result = counts.entrySet().stream()
        .max(Comparator.comparing(Map.Entry::getValue))
        .get()
        .getKey();

    boolean goodData = counts.entrySet().stream()
        .filter(e -> e.getValue().equals(counts.get(result)))
        .count() == 1;

    if (goodData) {
      return Optional.of(result);
    } else {
      return Optional.empty();
    }
  }
}
