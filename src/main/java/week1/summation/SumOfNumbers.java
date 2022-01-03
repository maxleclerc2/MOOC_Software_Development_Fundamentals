package week1.summation;

public class SumOfNumbers {
    public static void main(String[] args) {
        int sum = 0;
        int number = 1;
        while(number <= 100) {
            sum += number;
            number++;
        }
        System.out.println("Somme des nombres jusqu'à 100 : " + sum);

        sum = 0;
        for(int number2 = 1; number2 <= 100; number2 += 2) {
            sum += number2;
        }
        System.out.println("Somme des nombres impaires jusqu'à 100 : " + sum);
    }
}
