package ann.tsyhankova;

import java.util.ArrayList;
import java.util.List;

public class TextAligner {

    private int lineLength = 0;
    private int index = 0;
    private int wordCounter = 0;
    private int firstWordIndex = 0;
    private int lastWordIndex = 0;
    private int spaceLength = 1;
    private int restSpace = 0;
    private List<String> alignText = new ArrayList<>();

    public List<String> alignText(String text, int symbolQuantity){

        StringBuilder line = new StringBuilder();
        do {
            if (isFitInLine(getWordList(text)[this.index], symbolQuantity)){
                this.setWordNumber();
                this.setAllParams(this.getWordList(text)[this.index]);
            } else {
                this.lineLength -= this.wordCounter;
                this.lastWordIndex = this.index;
                this.spaceLength = (symbolQuantity - this.lineLength) / (this.wordCounter - 1);
                this.restSpace = (symbolQuantity - this.lineLength) - (this.spaceLength * (this.wordCounter - 1));
                for (int i = this.firstWordIndex; i < this.lastWordIndex; i++) {
                    line.append(getWordList(text)[i]);
                    if (i != this.lastWordIndex - 1) {
                        for (int j = 0; j < this.spaceLength; j++) {
                            this.addSpace(line);
                            if (this.restSpace > 0) {
                                this.addRestSpace(line);
                            }
                        }
                    }
                }
                this.addToAlignText(line.toString());
                this.resetParams(line);
            }
        } while (getWordList(text).length != this.index);
        return this.alignText;
    }

    private boolean isFitInLine(String word, int symbolQuantity){
        return this.lineLength + word.length() < symbolQuantity;
    }

    private void setWordNumber(){
        if ((this.firstWordIndex == 0) && (this.lastWordIndex != 0)) {
            this.firstWordIndex = this.index;
        }
    }

    private void setAllParams(String word){
        this.lineLength += word.length()+1;
        this.index++;
        this.wordCounter++;
    }

    private void resetParams(StringBuilder line){
        line.setLength(0);
        this.lineLength = 0;
        this.wordCounter = 0;
        this.firstWordIndex = 0;
    }

    private void addRestSpace(StringBuilder line){
        this.restSpace--;
        addSpace(line);
    }

    private void addSpace(StringBuilder line){
        line.append(" ");
        this.lineLength++;
    }

    private String[] getWordList(String text){
//        return text.split("\\W+");
        return text.split(" ");
    }

    private void addToAlignText(String line){
        this.alignText.add(line);
    }
}
