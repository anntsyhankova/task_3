package ann.tsyhankova;

import java.util.Scanner;

public class InputService {

    public static int getSymbolQuantity(){
        Scanner input = new Scanner(System.in);

        int number;
        do {
            System.out.println("Input number of symbols: ");
            number = input.nextInt();
        } while (number > 50 || number < 35);

        return number;
    }
}
