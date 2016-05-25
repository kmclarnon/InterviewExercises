package tk.testurlpleaseignore.interview.exercises;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;

import com.google.common.collect.ImmutableList;

import tk.testurlpleaseignore.interview.exercises.utils.Pair;
import tk.testurlpleaseignore.interview.utils.TestBase;

public class Exercies2Tests extends TestBase {

  // Find pairs in an integer array whose sum is equal to 10 (bonus: do it in linear time)

  @Test
  @SuppressWarnings("unchecked")
  public void itFindsAllPairsThatSumTo10() {
    Exercise2 exercise2 = new Exercise2();
    List<Integer> test1 = ImmutableList.of(1, 2, 8, 9, 7, 5);
    assertThat(exercise2.run(toIntArray(test1))).containsOnly(Pair.of(1, 9), Pair.of(2, 8));
    List<Integer> test2 = ImmutableList.of(1, 2, 3, 4, 5, 6, 7, 8, 9);
    assertThat(exercise2.run(toIntArray(test2))).containsOnly(Pair.of(1, 9), Pair.of(2, 8), Pair.of(3, 7), Pair.of(4, 6));
  }

  @Test
  public void itFindsAllPairsThatSumTo10InLinearTime() {

  }
}
