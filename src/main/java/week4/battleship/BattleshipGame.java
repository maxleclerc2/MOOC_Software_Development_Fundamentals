package week4.battleship;

import java.util.Scanner;

public class BattleshipGame {
    Ocean ocean;
    Scanner scanner;
    String userInput;
    boolean playing;

    public BattleshipGame() {
        initGame();

        System.out.println("Welcome to Battleship Game!\n");
        helpMessage();

        userInput = scanner.nextLine();
        if (!userInput.isBlank()) {
            System.out.println("Ok you wrote something but that not a problem, let's play!");
        }

        while (playing) {
            playGame();
        }

        System.out.println("\n\nBye Bye!");
    }

    void initGame() {
        ocean = new Ocean();
        scanner = new Scanner(System.in);

        ocean.placeAllShipsRandomly();
        playing = true;
    }

    void playGame() {
        ocean.print();
        System.out.println("Shots fired: " + ocean.getShotsFired() +
                " ; Hits: " + ocean.getHitCount() + " ; Ships sunk: " + ocean.getShipsSunk());

        System.out.print("Enter the coordinates to shoot at: ");
        userInput = scanner.nextLine();

        if (userInput.contains("HELP")) {
            helpMessage();
            userInput = scanner.nextLine();
            return;
        } else if (userInput.contains("QUIT")) {
            System.out.println("Final score :");
            System.out.println("Shots fired: " + ocean.getShotsFired() +
                    " ; Hits: " + ocean.getHitCount() + " ; Ships sunk: " + ocean.getShipsSunk());
            playing = false;
            return;
        }

        try {
            String[] coordSTR = userInput.split(";");

            for (int i = 0; i < 5; i ++) {
                String[] coordINT = coordSTR[i].split(",");

                if (!ocean.shootAt(Integer.parseInt(coordINT[0]), Integer.parseInt(coordINT[1])))
                    System.out.println("Miss!");
                else System.out.println("Hit!");
            }
        } catch (Exception e) {
            System.out.println("\nInvalid coordinates!");
        }


        endGame();
    }

    void endGame() {
        if (ocean.isGameOver()) {
            ocean.print();
            System.out.println("Final score :");
            System.out.println("Shots fired: " + ocean.getShotsFired() +
                    " ; Hits: " + ocean.getHitCount() + " ; Ships sunk: " + ocean.getShipsSunk());

            System.out.print("Do you want to play again ? [Y/N] ");
            userInput = scanner.nextLine();

            if (userInput.toUpperCase().contains("Y")) {
                ocean = new Ocean();
                ocean.placeAllShipsRandomly();
            } else playing = false;
        }
    }

    void helpMessage() {
        System.out.println("Your objective is to sink 13 ships with as few shots as possible:");
        System.out.println("1 Battleship (8-square)");
        System.out.println("1 Battlecruiser (7-square)");
        System.out.println("2 Cruisers (6-square)");
        System.out.println("2 Light Cruisers (5-square)");
        System.out.println("3 Destroyers (4-square)");
        System.out.println("4 Submarines (3-square)\n");
        System.out.println("You can shoot 5 times each turns. To do so,");
        System.out.println("write the 5 positions you want to shoot at at the same time.");
        System.out.println("You must use this format, where X is the row and Y the column:");
        System.out.println("X, Y; X, Y; X, Y; X, Y; X, Y");
        System.out.println("For example, you can type:");
        System.out.println("1, 2; 3, 4; 5, 6; 7, 8; 9, 10\n");
        System.out.println("Type HELP at any time to display those instructions.");
        System.out.println("Type QUIT to end the game.\n");
        System.out.println("Press enter when you are ready!");
    }

    public static void main(String[] args) {
        new BattleshipGame();
    }
}
