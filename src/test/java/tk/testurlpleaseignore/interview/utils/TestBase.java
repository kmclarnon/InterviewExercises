package tk.testurlpleaseignore.interview.utils;

import java.util.List;

public class TestBase {
  protected int[] toIntArray(List<Integer> integers) {
    return integers.stream().mapToInt(Integer::intValue).toArray();
  }
}
