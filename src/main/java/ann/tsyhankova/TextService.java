package ann.tsyhankova;

import java.util.ArrayList;
import java.util.List;

public class TextService {

    private final String[] text;
    private final int symbolQuantityPerLine;
    private List<String> resultText = new ArrayList<>();
    private int currentLineLength;
    private int lastWordIndex;
    private int firstWordIndex;

    public TextService(String text, int symbolQuantityPerLine) {
        this.text = TextUtils.getInstance(text, symbolQuantityPerLine).getSplittedText();
        this.symbolQuantityPerLine = symbolQuantityPerLine;
    }

    public List<String> getAlignText() {

        resetAllParams();
        for (int i = 0; i < this.text.length; i++) {
            if (fitsInLine(this.text[i]) && (this.text.length - 1 != i)) {
                setFirstWordIndex(i);
            } else {
                this.setLastWordIndex(i);
                this.currentLineLength -= this.wordInLineNumber();
                int spaceNumberPerGap = this.getFreeSymbolsInLine() / this.spaceInLineNumber();
                int freeSpacesInLine = this.getFreeSymbolsInLine() - spaceNumberPerGap * (this.spaceInLineNumber());
                this.addToAlignTextList(buildLine(spaceNumberPerGap, freeSpacesInLine));
                this.setParamsForFirstWordIndex(i, this.text[i].length() + 1);
            }
        }
        return this.resultText;
    }

    private boolean fitsInLine(String word) {
        if (this.currentLineLength + word.length() + 1 < this.symbolQuantityPerLine) {
            this.currentLineLength += word.length() + 1;
            return true;
        }
        return false;
    }

    private void resetAllParams() {
        this.lastWordIndex = 0;
        this.setParamsForFirstWordIndex(0, 0);
    }

    private void setParamsForFirstWordIndex(int firstWordIndex, int currentLineLength) {
        this.firstWordIndex = firstWordIndex;
        this.currentLineLength = currentLineLength;
    }

    private int wordInLineNumber() {
        return this.lastWordIndex - this.firstWordIndex;
    }

    private int spaceInLineNumber() {
        return this.wordInLineNumber() == 1 ? 1 : this.wordInLineNumber() - 1;
    }

    private int getFreeSymbolsInLine() {
        return this.symbolQuantityPerLine - this.currentLineLength;
    }

    private String buildLine(int spaceNumber, int freeSpaceNumber) {
        StringBuilder newLine = new StringBuilder();
        for (int j = this.firstWordIndex; j < this.lastWordIndex; j++) {
            newLine.append(this.text[j]);
            if (j != this.lastWordIndex - 1) {
                this.addSpaces(newLine, spaceNumber);
            }
            if (freeSpaceNumber > 0) {
                this.addSpaces(newLine, 1);
                freeSpaceNumber--;
            }
        }
        return newLine.toString();
    }

    private StringBuilder addSpaces(StringBuilder newLine, int spaceNumber) {
        for (int i = 0; i < spaceNumber; i++) {
            newLine.append(' ');
            this.currentLineLength++;
        }
        return newLine;
    }

    private void setFirstWordIndex(int index) {
        if (this.lastWordIndex != 0 && this.firstWordIndex == 0) {
            this.firstWordIndex = index;
        }
    }

    private void addToAlignTextList(String line) {
        this.resultText.add(line);
    }

    private boolean isItLastWordInText(int index) {
        return index + 1 == this.text.length;
    }

    private void setLastWordIndex(int index) {
        if (isItLastWordInText(index)) {
            this.lastWordIndex = index + 1;
        } else {
            this.lastWordIndex = index;
        }
    }
}
