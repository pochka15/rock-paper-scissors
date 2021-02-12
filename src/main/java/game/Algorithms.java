package game;

public class Algorithms {
    /**
     * An algorithm that checks whether an element at "index" belongs to the next half of elements.
     * If the pivot point + the half of elements exceeds the elementsAmount it's checked the indexes from
     * the beginning (circularly)
     *
     * @param index          int value. Constraints: 0 <= index < elementsAmount
     * @param pivot          int value representing the point after which it's taken the half of elements
     * @param elementsAmount the amount of elements which must be odd!
     * @return true if the index is sits in the next half of elements after the pivot index
     */
    public static boolean belongsToNextHalf(int index, int pivot, int elementsAmount) {
        assert elementsAmount % 2 == 1;
        int halfSize = (elementsAmount - 1) / 2;
        if (pivot < index && index < elementsAmount) {
            return index <= pivot + halfSize;
        } else {
            int rangeSizeFromStart = elementsAmount - pivot - 1;
            int rangeSizeTillTheEnd = halfSize - rangeSizeFromStart;
            return index <= rangeSizeTillTheEnd;
        }
    }
}
