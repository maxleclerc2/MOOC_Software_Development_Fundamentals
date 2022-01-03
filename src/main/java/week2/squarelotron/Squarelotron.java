package week2.squarelotron;

import java.util.Scanner;

public class Squarelotron {
    int[][] squarelotron;
    int size;

    public Squarelotron(int n) {
        this.size = n;
        this.squarelotron = new int[size][size];

        int fill = 1;
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                this.squarelotron[i][j] = fill;
                fill++;
            }
        }
    }

    public Squarelotron upsideDownFlip(int ring) {
        Squarelotron sq = new Squarelotron(this.size);

        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if (i == (ring - 1)) {
                    if (j >= (ring - 1) && j <= (size - ring)) {
                        sq.squarelotron[i][j] = this.squarelotron[size - ring][j];
                    }
                } else if(i == (size - ring)) {
                    if (j >= (ring - 1) && j <= (size - ring)) {
                        sq.squarelotron[i][j] = this.squarelotron[ring - 1][j];
                    }
                } else if (j == (ring - 1)) {
                    if (i >= (ring - 1) && i <= (size - ring)) {
                        sq.squarelotron[i][j] = this.squarelotron[Math.abs(i - size) - 1][j];
                    }
                } else if(j == (size - ring)) {
                    if (i >= (ring - 1) && i <= (size - ring)) {
                        sq.squarelotron[i][j] = this.squarelotron[Math.abs(i - size) - 1][j];
                    }
                } else {
                    sq.squarelotron[i][j] = this.squarelotron[i][j];
                }
            }
        }

        return sq;
    }

    public Squarelotron mainDiagonalFlip(int ring) {
        Squarelotron sq = new Squarelotron(this.size);

        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if (i == (ring - 1)) {
                    if (j > (ring - 1) && j <= (size - ring)) {
                        sq.squarelotron[i][j] = this.squarelotron[j][ring - 1];
                    }
                } else if(i == (size - ring)) {
                    if (j >= (ring - 1) && j < (size - ring)) {
                        sq.squarelotron[i][j] = this.squarelotron[j][size - ring];
                    }
                } else if (j == (ring - 1)) {
                    if (i > (ring - 1) && i <= (size - ring)) {
                        sq.squarelotron[i][j] = this.squarelotron[ring - 1][i];
                    }
                } else if(j == (size - ring)) {
                    if (i >= (ring - 1) && i < (size - ring)) {
                        sq.squarelotron[i][j] = this.squarelotron[size - ring][i];
                    }
                } else {
                    sq.squarelotron[i][j] = this.squarelotron[i][j];
                }
            }
        }

        return sq;
    }

    public void rotateRight(int numberOfTurns) {
        int [][] sqTMP = new int[size][size];

        for(int k = 0; k < numberOfTurns; k++) {
            for(int i = 0; i < size; i++) {
                System.arraycopy(squarelotron[i], 0, sqTMP[i], 0, size);
            }

            for(int i = 0; i < size; i++) {
                for(int j = 0; j < size; j++) {
                    this.squarelotron[i][j] = sqTMP[j][Math.abs(i - size) - 1];
                }
            }
        }
    }

    public void print(Squarelotron sq) {
        for(int i = 0; i < sq.size; i++) {
            for(int j = 0; j < sq.size; j++) {
                if (j == (sq.size - 1)) {
                    System.out.println(sq.squarelotron[i][j]);
                } else {
                    System.out.print(sq.squarelotron[i][j] + " ");
                }
            }
        }
    }

    public static void main(String[] args) {
        boolean newSq = true;
        boolean correct = false;
        int size = 0;
        Squarelotron sq = null;
        String userInput;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Squarelotron!");

        while (newSq) {
            do {
                System.out.println("What size do you want the squarelotron to be? ");
                userInput = scanner.nextLine();

                try {
                    size = Integer.parseInt(userInput);
                    sq = new Squarelotron(size);
                    correct = true;
                } catch (NumberFormatException e) {
                    System.out.println("That's not a number!");
                }
            } while (!correct);

            boolean again = true;

            do {
                System.out.println("What operation do you want to execute?");
                System.out.println("U for an upside down flip\nM for a main diagonal flip\nR for rotating the squarelotron\nQ to quit");
                userInput = scanner.nextLine().toUpperCase();

                switch (userInput) {
                    case "U":
                        System.out.println("Which ring would you like to flip? (between 1 and " + (size / 2 + 1) + ") :");
                        userInput = scanner.nextLine();

                        try {
                            int ring = Integer.parseInt(userInput);

                            if (ring >= 1 && ring <= (size / 2 + 1)) {
                                Squarelotron UDF;
                                UDF = sq.upsideDownFlip(ring);

                                sq.print(UDF);
                            } else {
                                System.out.println("Ring not available.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("That's not a number!");
                        }

                        break;
                    case "M":
                        System.out.println("Which ring would you like to flip? (between 1 and " + (size / 2 + 1) + ") :");
                        userInput = scanner.nextLine();

                        try {
                            int ring = Integer.parseInt(userInput);

                            if (ring >= 1 && ring <= (size / 2 + 1)) {
                                Squarelotron MDF;
                                MDF = sq.mainDiagonalFlip(ring);

                                sq.print(MDF);
                            } else {
                                System.out.println("Ring not available.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("That's not a number!");
                        }

                        break;
                    case "R":
                        System.out.println("How many turns would you like to make ? (1 turn = 90° counterclockwise) :");
                        userInput = scanner.nextLine();

                        try {
                            int turn = Integer.parseInt(userInput);

                            sq.rotateRight(turn);
                            sq.print(sq);
                        } catch (NumberFormatException e) {
                            System.out.println("That's not a number!");
                        }

                        break;
                    case "Q":
                        again = false;
                        break;
                    default:
                        System.out.println("That's not an operation -.-");
                        break;
                }
            } while (again);

            System.out.println("Do you want to make another squarelotron? (Y/N) ");
            userInput = scanner.nextLine().toUpperCase();

            if (!userInput.equals("Y") && !userInput.equals("YES")) {
                System.out.println("Bye bye!");
                newSq = false;
            }
        }
    }
}
