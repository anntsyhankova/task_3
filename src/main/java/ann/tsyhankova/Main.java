package ann.tsyhankova;

import java.io.*;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        String text = FileService.getInstance(new File("").getAbsoluteFile() + "/src/main/resources/in.txt").getTextFromFile();
        String[] n = TextUtils.getInstance(text, 47).getSplittedText();
//        for (String s : n){
//            System.out.println(s);
//        }
        List<String> resultTextList = new TextService(text, 47).getAlignText();
        for(String s : resultTextList){
            System.out.println(s);
        }

//        FileService reader = new FileService();
//        String text = reader.getTextFromFile("C:/Users/htsyhankova/Desktop/task_1/src/main/java/ann/tsyhankova/src/in.txt");
//
//        Scanner input = new Scanner(System.in);
//
//        int number;
//        do {
//            System.out.println("Input number of symbols: ");
//            number = input.nextInt();
//        } while (number > 50 || number < 35);
//
//        List<String> lineList =  new TextAligner().alignText(text, number);
//        for (String string : lineList) {
//            System.out.println(string + " " + number);
//        }
    }
}
