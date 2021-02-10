import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.Scanner;


public class Main {
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
        final int halfSize = (elementsAmount - 1) / 2;
        if (pivot < index && index < elementsAmount) {
            return index <= pivot + halfSize;
        } else {
            final int rangeSizeFromStart = elementsAmount - pivot - 1;
            final int rangeSizeTillTheEnd = halfSize - rangeSizeFromStart;
            return index <= rangeSizeTillTheEnd;
        }
    }

    private static int readUserMove(String[] allMoves) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your move: ");
        int chosenMove = -1;
        String userInput;
        while (true) {
            System.out.print("Enter your move: ");
            try {
                userInput = scanner.next();
                chosenMove = Integer.parseInt(userInput);
            } catch (Exception ignored) {
            }
            if (chosenMove == -1 || chosenMove > allMoves.length) {
                System.out.println("You've entered an incorrect move, enter the correct movement number\n" +
                                           "Available movements:");
                int j = 1;
                for (String move : allMoves) System.out.println(j++ + ": " + move);
            } else {
                break;
            }
        }
        return chosenMove - 1;
    }

    public static void main(String[] allMoves) {
        final String expectationsMessage = "Expected to have:\nargs amount > 1,\n"
                + "args amount is an odd number.";
        if (allMoves.length < 3) {
            System.out.println("Incorrect amount of arguments were given!\n" + expectationsMessage + "\n---\nExiting...");
            return;
        }
        if (allMoves.length % 2 != 1) {
            System.out.println("You should give an odd number of arguments\n" + expectationsMessage + "\n---\nExiting...");
            return;
        }

        byte[] key = CryptographyUtils.generateRandomKey();
        int opponentsMove = new Random().nextInt(allMoves.length);

//        Print HMAC
        System.out.println("HMAC:\n" + CryptographyUtils.byteArrToHex(CryptographyUtils.hmacSha256(
                key, allMoves[opponentsMove].getBytes(StandardCharsets.UTF_8))));
//        Print available movements
        int i = 1;
        for (String move : allMoves) System.out.println(i++ + ": " + move);

        int chosenMove = readUserMove(allMoves);

//        Print the game result
        System.out.println("Opponent's move: " + allMoves[opponentsMove]);
        if (chosenMove == opponentsMove) {
            System.out.println("Draw");
        } else if (belongsToNextHalf(chosenMove, opponentsMove, allMoves.length)) {
            System.out.println("You won!");
        } else {
            System.out.println("You've lost :(");
        }
//        Print the key
        System.out.println("HMAC key: " + CryptographyUtils.byteArrToHex(key));
    }
}
