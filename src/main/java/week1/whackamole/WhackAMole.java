package week1.whackamole;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class WhackAMole {
    int score;
    int molesLeft;
    int attemptsLeft;
    char[][] moleGrid;

    WhackAMole(int numAttempts, int gridDimension) {
        score = 0;
        molesLeft = 0;
        attemptsLeft = numAttempts;
        moleGrid = new char[gridDimension * 2 - 1][gridDimension * 2 - 1];

        for (int i = 0; i < (gridDimension * 2 - 1); i++) {
            for (int j = 0; j < (gridDimension * 2 - 1); j++) {
                if (i % 2 == 1) {
                    if (j % 2 == 1) {
                        moleGrid[i][j] = '┼';
                    } else {
                        moleGrid[i][j] = '─';
                    }
                } else {
                    if (j % 2 == 1) {
                        moleGrid[i][j] = '│';
                    } else {
                        moleGrid[i][j] = '*';
                    }
                }
            }
        }

        for (int i = 0; i < gridDimension; i++) {
            boolean success;
            int randX = ThreadLocalRandom.current().nextInt(1, gridDimension);
            int randY = ThreadLocalRandom.current().nextInt(1, gridDimension);

            success = place(randX, randY);

            if (!success) {
                i--;
            }
        }
    }

    boolean place(int x, int y) {
        boolean success;
        int placeX = x * 2 - 2;
        int placeY = y * 2 - 2;
/*
        if (x != 0) {
            placeX = x * 2 - 2;
        }

        if (y != 0) {
            placeY = y * 2 - 2;
        }

 */

        if (moleGrid[placeX][placeY] == 'M') {
            success = false;
        } else {
            moleGrid[placeX][placeY] = 'M';
            molesLeft++;
            success = true;
        }

        return success;
    }

    void whack(int x, int y) {
        int whackX = x * 2 - 2;
        int whackY = y * 2 - 2;
/*
        if (x != 0) {
            whackX = x * 2 - 2;
        }

        if (y != 0) {
            whackY = y * 2 - 2;
        }

 */

        if (moleGrid[whackX][whackY] == 'M') {
            moleGrid[whackX][whackY] = 'W';
            score++;
            attemptsLeft--;
            molesLeft--;

            System.out.println("What a whack!");
        } else {
            attemptsLeft--;

            System.out.println("Nothing here... try again!");
        }
    }

    void printGridToUser() {
        int dim = moleGrid.length;

        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if (j == dim - 1) {
                    if (moleGrid[i][j] == 'M') {
                        System.out.println('*');
                    } else {
                        System.out.println(moleGrid[i][j]);
                    }
                } else {
                    if (moleGrid[i][j] == 'M') {
                        System.out.print('*');
                    } else {
                        System.out.print(moleGrid[i][j]);
                    }
                }
            }
        }
    }

    void printGrid() {
        int dim = moleGrid.length;

        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                if (j == dim - 1) {
                    System.out.println(moleGrid[i][j]);
                } else {
                    System.out.print(moleGrid[i][j]);
                }
            }
        }
    }

    public static void main(String[] args) {
        int ATTEMPTS = 50;
        int GRID_SIZE = 10;

        WhackAMole game = new WhackAMole(ATTEMPTS, GRID_SIZE);
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to WhackAMole!");
        System.out.println("You have " + ATTEMPTS + " attempts to take down " + GRID_SIZE + " moles.");
        System.out.println("Coordinates range from 1 to " + GRID_SIZE + ".");
        System.out.println("Type -1 -1 at any time to stop the game.\n");

        while(game.molesLeft != 0) {
            game.printGridToUser();
            System.out.println("Attempts left: " + game.attemptsLeft);
            System.out.println("Moles left: " + game.molesLeft);
            System.out.println("Where do you want to hit? Enter the coordinates as X Y : ");
            String userInput = scanner.nextLine();

            if (userInput.equals("-1 -1")) {
                break;
            } else {
                String[] split = userInput.split(" ");

                try {
                    game.whack(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
                } catch (NumberFormatException NFE) {
                    System.out.println("Seems like those aren't coordinates...");
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Oh no! You hit outside the board!");
                }
            }
        }

        game.printGrid();
        System.out.println("End of the game! You scored " + game.score + " points.");
    }
}
