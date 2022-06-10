package ann.tsyhankova;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        ReadFile reader = new ReadFile();
        String text = reader.getTextFromFile("C:/Users/htsyhankova/Desktop/task_1/src/main/java/ann/tsyhankova/src/in.txt");

        Scanner input = new Scanner(System.in);

        int number;
        do {
            System.out.println("Input number of symbols: ");
            number = input.nextInt();
        } while (number > 50 || number < 35);

        List<String> lineList =  new TextAligner().alignText(text, number);
        for (String string : lineList) {
            System.out.println(string + " " + number);
        }
    }
}
