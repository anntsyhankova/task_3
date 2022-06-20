package ann.tsyhankova;

import java.util.ArrayList;
import java.util.List;

public class TextService {

    public String getAlignedText(String text, int symbolQuantity){
        List<String> wordList = TextUtils.getWordList(text);
        List<String> preFormattedLines = getPreformattedLinesFromWordlist(wordList, symbolQuantity);
        List<String> alignedLineList = addFreeSpacesToPreFormattedLines(preFormattedLines, symbolQuantity);
        return String.join("\n", alignedLineList);
    }

    private List<String> getPreformattedLinesFromWordlist(List<String> wordList, int symbolQuantity){
        //get empty lines for adding words
        List<String> lines = prepareEmptyStringsListForAligning(wordList, symbolQuantity);
        int lineCounter = 0;
        for (String word : wordList) {
            //try to put word with postfix single space into line
            if((lines.get(lineCounter).length() + word.length() + 1) < symbolQuantity){
                lines.set(
                        lineCounter,
                        lines.get(lineCounter) + word + ' '
                );
            }
            //else chop last single space and increment line counter for future words
            else{
                lines.set(
                        lineCounter,
                        lines.get(lineCounter).substring(0, lines.get(lineCounter).length() - 1)
                );
                lineCounter++;
                lines.set(
                        lineCounter,
                        lines.get(lineCounter) + word + ' '
                );
            }
        }
        return lines;
    }

    private List<String> addFreeSpacesToPreFormattedLines(List<String> lines, int symbolQuantity){
        List<String> alignedLines = new ArrayList<>();
        //take all words from line and calculate free spaces then add line with spaces to result list
        for (String line : lines) {
            List<String> wordsInLine = List.of(line.split(" "));
            int wordsLength = wordsInLine.stream().mapToInt(String::length).sum();
            //free symbols
            int freeSymbolsInLine = symbolQuantity - wordsLength;
            int spacesPerGapInLine;
            int freeSpacesInLine = 0;
            //spaces per gap = freeSymbols divide on gap number
            //free spaces = freeSymbols minus used symbols on spaces
            if(wordsInLine.size() != 1) {
                spacesPerGapInLine = freeSymbolsInLine / (wordsInLine.size() - 1);
                freeSpacesInLine = freeSymbolsInLine - ((wordsInLine.size() - 1) * spacesPerGapInLine);
            }
            else{
                spacesPerGapInLine = freeSymbolsInLine;
            }
            //building aligned line by taking word adding spacesPerGap + free space if needed
            int wordCounter = wordsInLine.size();
            StringBuilder alignedLine = new StringBuilder();
            do{
                //if we have free spaces use single
                int freeSpace = freeSpacesInLine != 0 ? 1 : 0;
                //build word with spaces
                String wordWithSpaces;
                if(wordCounter > 1){
                     wordWithSpaces = getWordWithSpaces(
                        wordsInLine.get(wordsInLine.size() - wordCounter),
                        (spacesPerGapInLine + freeSpace)
                    );
                }
                else {
                    wordWithSpaces = wordsInLine.get(wordsInLine.size() - wordCounter);
                }
                //check last word and make decision of building aligned line with last word
                if((alignedLine.length() + wordWithSpaces.length()) < symbolQuantity) {
                    alignedLine.append(wordWithSpaces);
                } else {
                    alignedLine.append(wordWithSpaces);
                    alignedLines.add(alignedLine.toString());
                    alignedLine = new StringBuilder();
                }
                //reduce freeSpaces if needed
                if(freeSpacesInLine != 0){
                    freeSpacesInLine--;
                }
                wordCounter--;
            }while(wordCounter != 0);
        }
        return alignedLines;
    }

    private String getWordWithSpaces(String word, int spaceNumber) {
        return word + " ".repeat(Math.max(0, spaceNumber));
    }

    private List<String> prepareEmptyStringsListForAligning(List<String> wordList, int symbolQuantity){
        List<String> emptyList = new ArrayList<>();
        //find number of empty strings by dividing word+' ' length on symbolQty
        for (int i = 0; i < wordList.stream().mapToInt(String::length).map(length -> length + 2).sum()/symbolQuantity; i++) {
            emptyList.add("");
        }
        return emptyList;
    }
}

//    private final String[] text;
//    private final int symbolQuantityPerLine;
//    private List<String> resultText = new ArrayList<>();
//    private int currentLineLength;
//    private int lastWordIndex;
//    private int firstWordIndex;
//
//    public TextService(String text, int symbolQuantityPerLine) {
//        this.text = TextUtils.getInstance(text, symbolQuantityPerLine).getSplittedText();
//        this.symbolQuantityPerLine = symbolQuantityPerLine;
//    }
//
//    public List<String> getAlignText() {
//
//        resetAllParams();
//        for (int i = 0; i < this.text.length; i++) {
//            if (fitsInLine(this.text[i]) && (this.text.length - 1 != i)) {
//                setFirstWordIndex(i);
//            } else {
//                this.setLastWordIndex(i);
//                this.currentLineLength -= this.wordInLineNumber();
//                int spaceNumberPerGap = this.getFreeSymbolsInLine() / this.spaceInLineNumber();
//                int freeSpacesInLine = this.getFreeSymbolsInLine() - spaceNumberPerGap * (this.spaceInLineNumber());
//                this.addToAlignTextList(buildLine(spaceNumberPerGap, freeSpacesInLine));
//                this.setParamsForFirstWordIndex(i, this.text[i].length() + 1);
//            }
//        }
//        return this.resultText;
//    }
//
//    private boolean fitsInLine(String word) {
//        if (this.currentLineLength + word.length() + 1 < this.symbolQuantityPerLine) {
//            this.currentLineLength += word.length() + 1;
//            return true;
//        }
//        return false;
//    }
//
//    private void resetAllParams() {
//        this.lastWordIndex = 0;
//        this.setParamsForFirstWordIndex(0, 0);
//    }
//
//    private void setParamsForFirstWordIndex(int firstWordIndex, int currentLineLength) {
//        this.firstWordIndex = firstWordIndex;
//        this.currentLineLength = currentLineLength;
//    }
//
//    private int wordInLineNumber() {
//        return this.lastWordIndex - this.firstWordIndex;
//    }
//
//    private int spaceInLineNumber() {
//        return this.wordInLineNumber() == 1 ? 1 : this.wordInLineNumber() - 1;
//    }
//
//    private int getFreeSymbolsInLine() {
//        return this.symbolQuantityPerLine - this.currentLineLength;
//    }
//
//    private String buildLine(int spaceNumber, int freeSpaceNumber) {
//        StringBuilder newLine = new StringBuilder();
//        for (int j = this.firstWordIndex; j < this.lastWordIndex; j++) {
//            newLine.append(this.text[j]);
//            if (j != this.lastWordIndex - 1) {
//                this.addSpaces(newLine, spaceNumber);
//            }
//            if (freeSpaceNumber > 0) {
//                this.addSpaces(newLine, 1);
//                freeSpaceNumber--;
//            }
//        }
//        return newLine.toString();
//    }
//
//    private StringBuilder addSpaces(StringBuilder newLine, int spaceNumber) {
//        for (int i = 0; i < spaceNumber; i++) {
//            newLine.append(' ');
//            this.currentLineLength++;
//        }
//        return newLine;
//    }
//
//    private void setFirstWordIndex(int index) {
//        if (this.lastWordIndex != 0 && this.firstWordIndex == 0) {
//            this.firstWordIndex = index;
//        }
//    }
//
//    private void addToAlignTextList(String line) {
//        this.resultText.add(line);
//    }
//
//    private boolean isItLastWordInText(int index) {
//        return index + 1 == this.text.length;
//    }
//
//    private void setLastWordIndex(int index) {
//        if (isItLastWordInText(index)) {
//            this.lastWordIndex = index + 1;
//        } else {
//            this.lastWordIndex = index;
//        }
//    }
//}
