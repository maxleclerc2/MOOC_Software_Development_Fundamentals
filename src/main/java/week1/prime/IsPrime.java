package week1.prime;

import java.util.*;


public class IsPrime {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a positive integer : ");
        int userInput = scanner.nextInt();

        System.out.println("The user entered : " + userInput);

        int potentialFactor = 2;
        while (userInput % potentialFactor != 0) {
            potentialFactor++;
        }
        if (potentialFactor == userInput) {
            System.out.println("The number is prime.");
        } else {
            System.out.println("The number is not prime, factor is " + potentialFactor);
        }
    }
}
