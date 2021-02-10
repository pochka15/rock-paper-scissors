import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class MainTest {
    @Test
    void belongsToNextHalf() {
        char[] letters = new char[]{'A', 'B', 'C', 'D', 'E'};
        List<List<Integer>> testCases = List.of(List.of(2, 1, 5),
                                          List.of(1, 0, 5),
                                          List.of(0, 4, 5),
                                          List.of(4, 3, 5),
                                          List.of(3, 2, 5),
                                          List.of(3, 1, 5),
                                          List.of(4, 2, 5),
                                          List.of(0, 3, 5),
                                          List.of(1, 4, 5),
                                          List.of(2, 0, 5));
        for (final List<Integer> testCase : testCases) {
            final Integer index = testCase.get(0);
            final Integer pivot = testCase.get(1);
            assertTrue(Main.belongsToNextHalf(index, pivot, testCase.get(2)),
                       () -> "Expected to have: " + letters[index] + " > " + letters[pivot] + "\n" +
                               "Indexes are: " + testCase);
        }
    }
}