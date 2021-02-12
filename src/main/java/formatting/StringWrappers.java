package formatting;

public class StringWrappers {
    public static String orderedList(String[] elements, int startingIndex) {
        StringBuilder sb = new StringBuilder();
        int i = startingIndex;
        for (String element : elements) sb.append(i++).append(": ").append(element).append('\n');
        return sb.toString();
    }
}
