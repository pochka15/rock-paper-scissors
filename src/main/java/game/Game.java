package game;

import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;

import static cryptography.CryptographyUtils.*;
import static formatting.StringWrappers.orderedList;

public class Game {
    private final String[] allMoves;

    public Game(String[] allMoves) {
        this.allMoves = allMoves;
    }

    private static Optional<Integer> readInt() {
        Scanner scanner = new Scanner(System.in);
        try {
            return Optional.of(Integer.parseInt(scanner.next()));
        } catch (Exception ignored) {
        }
        return Optional.empty();
    }

    private int readUserMove() {
        System.out.print("Available movements:\n"
                                 + orderedList(allMoves, 1)
                                 + "\nEnter your move: ");
        Optional<Integer> chosenMove = readInt();
        if (chosenMove.isPresent()) {
            final int val = chosenMove.get();
            if (val >= 1 && val <= allMoves.length)
                return val - 1;
        }
        System.out.println("You've entered an incorrect move, enter the correct movement number");
        return readUserMove();
    }


    private Optional<String> checkIfMovesAreCorrect() {
        final String expectationsMessage = "Expected to have:\n"
                + "movements amount > 1,\n"
                + "args amount is an odd number.";
        if (allMoves.length < 3)
            return Optional.of("Incorrect amount of movements were given!\n" + expectationsMessage);
        if (allMoves.length % 2 != 1)
            return Optional.of("You should give an odd number of movements\n" + expectationsMessage);
        return Optional.empty();
    }

    private String gameResult(int userMove, int opponentsMove) {
        if (userMove == opponentsMove) return "Draw";
        else if (Algorithms.belongsToNextHalf(userMove, opponentsMove, allMoves.length)) return "You won!";
        return "You've lost :(";
    }

    public void start() {
        checkIfMovesAreCorrect().ifPresentOrElse(
                s -> System.out.println(s + "\n---\nExiting..."),
                () -> {
                    final int opponentsMove = new Random().nextInt(allMoves.length);
                    final byte[] key = generateRandomKey();
                    final byte[] movesAsBytes = allMoves[opponentsMove].getBytes(StandardCharsets.UTF_8);
                    System.out.println("HMAC:\n" + byteArrToHex(hmacSha256(key, movesAsBytes)));
                    final int chosenMove = readUserMove();
                    System.out.println("Opponent's move: " + allMoves[opponentsMove] + "\n"
                                               + gameResult(chosenMove, opponentsMove) + "\n"
                                               + "HMAC key: " + byteArrToHex(key));
                });
    }
}
