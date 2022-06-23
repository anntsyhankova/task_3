package ann.tsyhankova;

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
        } catch (Exception e){
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
        if (lastWordIndex-firstWordIndex == 1){
            newLine.append(wordList.get(wordList.size()-1));
            newLine.append(" ".repeat(Math.max(0, spaceNumberPerGap)));
        } else{
        for (int j = firstWordIndex; j < lastWordIndex; j++) {
            newLine.append(wordList.get(j));
            if (j != lastWordIndex - 1) {
                newLine.append(" ".repeat(Math.max(0, spaceNumberPerGap)));
            }
            if (freeSpacesInLine > 0) {
                newLine.append(" ".repeat(Math.max(0, 1)));
                freeSpacesInLine--;
            }
        }}
        System.out.println(newLine + " " + newLine.length());
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
                currentLineLength += wordList.get(i).length();
                if (currentLineLength + 1 < symbolQuantity){
                    currentLineLength+=1;
                }
            } else if ((wordList.size() - 1 == i)){
                if (wordList.get(i).length() + currentLineLength < symbolQuantity){
                    currentLineLength += wordList.get(i).length();
                } else {
                    currentLineLength = wordList.get(i).length();
                }
                wordIndexList.add(i+1);
                System.out.println(currentLineLength + " " + wordList.get(i).length());
            } else {
                wordIndexList.add(i);
                currentLineLength = wordList.get(i).length()+1;
            }
        }
        return wordIndexList;
    }

    private boolean fitsInLine(String word, Integer symbolQuantity, Integer currentLineLength) {
        return currentLineLength + word.length() <= symbolQuantity;
    }
}