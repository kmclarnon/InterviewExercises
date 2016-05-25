package tk.testurlpleaseignore.interview.utils;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;

import tk.testurlpleaseignore.interview.utils.TestCaseGenerator;

public class TestCaseGeneratorTest {
  @Test
  public void itGeneratesRandomNumbersInRanges() {
    times(() -> {
      int val = TestCaseGenerator.getRandomIntBetween(5, 10);
      assertThat(val).isLessThanOrEqualTo(10);
      assertThat(val).isGreaterThanOrEqualTo(5);
    });
  }

  @Test
  public void itGeneratesSingleNumber() {
    times(() -> assertThat(TestCaseGenerator.getRandomIntBetween(5, 5)).isEqualTo(5));
  }

  @Test
  public void itGeneratesRandomListOfIntegers() {
    times(() -> {
      List<Integer> list1 = TestCaseGenerator.getRandomListOfIntegers(40);
      List<Integer> list2 = TestCaseGenerator.getRandomListOfIntegers(40);
      assertThat(list1).hasSize(40);
      assertThat(list2).hasSize(40);
      assertThat(list1).isNotEqualTo(list2);
    });
  }

  private void times(Runnable runnable) {
    times(10000, runnable);
  }

  private void times(int times, Runnable runnable) {
    for (int i = 0; i < times; ++i) {
      runnable.run();
    }
  }
}
