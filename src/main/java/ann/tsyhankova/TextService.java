package ann.tsyhankova;

import ann.tsyhankova.TextUtils;

import java.util.ArrayList;
import java.util.List;

public class TextService {

    public String getAlignedText(String text, Integer symbolQuantity){
        try {
            List<String> wordList = TextUtils.getWordList(text);
            System.out.println(wordList);
            List<Integer> rawLineList = getFirstAndLastWordInLineList(wordList, symbolQuantity);
            System.out.println(rawLineList);
            List<String> alignedLineList = alignText(wordList, rawLineList, symbolQuantity);
            return String.join("\n", alignedLineList);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return "";
        }
    }

    private List<String> alignText(List<String> wordList, List<Integer> wordIndexList, Integer symbolQuantity){
        List<String> alignedLineList = new ArrayList<>();
        int i = 0;
        while((i+1) != wordIndexList.size()){
            String line = addSpacesToLine(wordList, wordIndexList.get(i), wordIndexList.get(i+1), symbolQuantity);
            alignedLineList.add(line);
            i++;
        }
        return alignedLineList;
    }
    private String addSpacesToLine(List<String> wordList, Integer firstWordIndex, Integer lastWordIndex, Integer symbolQuantity){
        int wordsNumber = lastWordIndex-firstWordIndex;
        int freeSymbols = symbolQuantity-countWordsLength(wordList, firstWordIndex, lastWordIndex);
        int spaceNumberPerGap = freeSymbols / (wordsNumber==1 ? 1 : wordsNumber-1);
        int freeSpacesInLine = freeSymbols - spaceNumberPerGap * (wordsNumber==1 ? 1 : wordsNumber-1);
        StringBuilder newLine = new StringBuilder();
        for (int j = firstWordIndex; j < lastWordIndex; j++) {
            newLine.append(wordList.get(j));
            if (j != lastWordIndex - 1) {
                newLine.append(" ".repeat(Math.max(0, spaceNumberPerGap)));
            }
            if (freeSpacesInLine > 0) {
                newLine.append(" ".repeat(Math.max(0, 1)));
                freeSpacesInLine--;
            }
        }
        return newLine.toString();
    }

    private Integer countWordsLength(List<String> wordList, Integer firstWordIndex, Integer lastWordIndex){
        int counter = 0;
        for (int i = firstWordIndex; i < lastWordIndex; i++) {
            counter += wordList.get(i).length();
        }
        return counter;
    }

    private List<Integer> getFirstAndLastWordInLineList(List<String> wordList, Integer symbolQuantity){
        List<Integer> wordIndexList = new ArrayList<>(List.of(0));
        int currentLineLength = 0;
        for (int i = 0; i < wordList.size(); i++) {
            if (fitsInLine(wordList.get(i), symbolQuantity, currentLineLength) && (wordList.size() - 1 != i)) {
                currentLineLength += wordList.get(i).length() + 1;
            } else {
                if (isItLastWordInText(i, wordList)){
                    wordIndexList.add(i+1);
                    currentLineLength = wordList.get(i+1).length()+1;
                }
                else {
                    wordIndexList.add(i);
                    currentLineLength = wordList.get(i).length()+1;
                }
            }
            System.out.println(wordList.get(i));
        }
        return wordIndexList;
    }

    private boolean fitsInLine(String word, Integer symbolQuantity, Integer currentLineLength) {
        return currentLineLength + word.length() + 1 < symbolQuantity;
    }

    private boolean isItLastWordInText(int index, List<String> wordList) {
        return index + 1 == wordList.size();
    }
//
//    private String buildLine()
//
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
//
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
//
//
//    private void addToAlignTextList(String line) {
//        this.resultText.add(line);
//    }
//
//
//
//    private void setLastWordIndex(int index) {
//        if (isItLastWordInText(index)) {
//            this.lastWordIndex = index + 1;
//        } else {
//            this.lastWordIndex = index;
//        }
//    }
}
//package ann.tsyhankova;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class TextService {
//
//    public String getAlignedText(String text, Integer symbolQuantity){
//        try {
//            List<String> wordList = TextUtils.getWordList(text);
//            List<String> preFormattedLines = getPreformattedLinesFromWordlist(wordList, symbolQuantity);
//            List<String> alignedLineList = addFreeSpacesToPreFormattedLines(preFormattedLines, symbolQuantity);
//            return String.join("\n", alignedLineList);
//        }catch (Exception e){
//            return "";
//        }
//    }
//
//    private List<String> getPreformattedLinesFromWordlist(List<String> wordList, Integer symbolQuantity){
//        //get empty lines for adding words
//        List<String> lines = prepareEmptyStringsListForAligning(wordList, symbolQuantity);
//        int lineCounter = 0;
//        for (String word : wordList) {
//            //try to put word with postfix single space into line
//            if((lines.get(lineCounter).length() + word.length() + 1) < symbolQuantity){
//                lines.set(
//                        lineCounter,
//                        lines.get(lineCounter) + word + ' '
//                );
//            }
//            //else chop last single space and increment line counter for future words
//            else{
//                lines.set(
//                        lineCounter,
//                        lines.get(lineCounter).substring(0, lines.get(lineCounter).length() - 1)
//                );
//                lineCounter++;
//                lines.set(
//                        lineCounter,
//                        lines.get(lineCounter) + word + ' '
//                );
//            }
//        }
//        return lines;
//    }
//
//    private List<String> addFreeSpacesToPreFormattedLines(List<String> lines, Integer symbolQuantity){
//        List<String> alignedLines = new ArrayList<>();
//        //take all words from line and calculate free spaces then add line with spaces to result list
//        for (String line : lines) {
//            List<String> wordsInLine = List.of(line.split(" "));
//            int wordsLength = wordsInLine.stream().mapToInt(String::length).sum();
//            //free symbols
//            int freeSymbolsInLine = symbolQuantity - wordsLength;
//            int spacesPerGapInLine;
//            int freeSpacesInLine = 0;
//            //spaces per gap = freeSymbols divide on gap number
//            //free spaces = freeSymbols minus used symbols on spaces
//            if(wordsInLine.size() != 1) {
//                spacesPerGapInLine = freeSymbolsInLine / (wordsInLine.size() - 1);
//                freeSpacesInLine = freeSymbolsInLine - ((wordsInLine.size() - 1) * spacesPerGapInLine);
//            }
//            else{
//                spacesPerGapInLine = freeSymbolsInLine;
//            }
//            //building aligned line by taking word adding spacesPerGap + free space if needed
//            int wordCounter = wordsInLine.size();
//            StringBuilder alignedLine = new StringBuilder();
//            do{
//                //if we have free spaces use single
//                int freeSpace = freeSpacesInLine != 0 ? 1 : 0;
//                //build word with spaces
//                String wordWithSpaces;
//                if(wordCounter > 1){
//                     wordWithSpaces = getWordWithSpaces(
//                        wordsInLine.get(wordsInLine.size() - wordCounter),
//                        (spacesPerGapInLine + freeSpace)
//                    );
//                }
//                else {
//                    wordWithSpaces = wordsInLine.get(wordsInLine.size() - wordCounter);
//                }
//                //check last word and make decision of building aligned line with last word
//                if((alignedLine.length() + wordWithSpaces.length()) < symbolQuantity) {
//                    alignedLine.append(wordWithSpaces);
//                } else {
//                    alignedLine.append(wordWithSpaces);
//                    alignedLines.add(alignedLine.toString());
//                    alignedLine = new StringBuilder();
//                }
//                //reduce freeSpaces if needed
//                if(freeSpacesInLine != 0){
//                    freeSpacesInLine--;
//                }
//                wordCounter--;
//            }while(wordCounter != 0);
//        }
//        return alignedLines;
//    }
//
//    private String getWordWithSpaces(String word, Integer spaceNumber) {
//        return word + " ".repeat(Math.max(0, spaceNumber));
//    }
//
//    private List<String> prepareEmptyStringsListForAligning(List<String> wordList, Integer symbolQuantity){
//        List<String> emptyList = new ArrayList<>();
//        //find number of empty strings by dividing word+' ' length on symbolQty
//        for (int i = 0; i <= wordList.stream().mapToInt(String::length).map(length -> length + 3).sum()/symbolQuantity; i++) {
//            emptyList.add("");
//        }
//        return emptyList;
//    }
//}
