package ann.tsyhankova;

import java.util.ArrayList;
import java.util.List;

public class TextUtils {

    private static TextUtils instance;
    private String[] text;
    private final int maxWordLength;

    private TextUtils(String text, int maxWordLength){
        this.text = text.split("\\s+");
        this.maxWordLength = maxWordLength;
    }

    public static TextUtils getInstance(String text, int maxWordLength){
        if (instance == null){
            instance = new TextUtils(text, maxWordLength);
        }
        return instance;
    }

    private String[] splitLongWords(int arraySize){
        String[] newArray = new String[arraySize];
        int counter = 0;
        int index = 0;
        boolean flagForWordWrap = false;
        while (counter < this.text.length){
            if (flagForWordWrap){
                newArray[index] = this.text[counter].substring(this.maxWordLength-1);
                flagForWordWrap = false;
                counter++;
            } else if (this.text[counter].length() >= this.maxWordLength) {
                newArray[index] = this.text[counter].substring(0, this.maxWordLength - 1) + '-';
                flagForWordWrap = true;
            } else {
                newArray[index] = this.text[counter];
                counter++;
            }
            index++;
        }
        return newArray;
    }

    private String[] checkWordList(){
        List<Integer> wordIndex = new ArrayList<>();
        for (int i = 0; i < this.text.length; i++) {
            if (this.text[i].length() >= this.maxWordLength){
                wordIndex.add(i);
            }
        }
        if (wordIndex.size() == 0) {
            return this.text;
        } else return splitLongWords(this.text.length+wordIndex.size());
    }

    public String[] getSplittedText(){
        return this.checkWordList();
    }
}
