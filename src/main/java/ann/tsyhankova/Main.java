package ann.tsyhankova;

import java.io.*;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        String text = FileService.getTextFromFile(new File("").getAbsoluteFile() + "/src/main/resources/in.txt");
        List<String> wordList = TextUtils.getWordList(text);
        String textAligned = new TextService().getAlignedText(text, 45);
        System.out.println(textAligned);
//        for (String word : wordList){
//            System.out.println(word);
//        }
//        List<String> resultTextList = new TextService(text, 47).getAlignText();
//        for(String s : resultTextList){
//            System.out.println(s);
//        }
//        Scanner input = new Scanner(System.in);
//
//        int number;
//        do {
//            System.out.println("Input number of symbols: ");
//            number = input.nextInt();
//        } while (number > 50 || number < 35);

    }
}
