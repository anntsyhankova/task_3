package ann.tsyhankova;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextUtils {

    public static List<String> getWordList(String text) {
        try {
            ArrayList<String> resultList = new ArrayList<>();
            Arrays.asList(text.split("\\s+")).forEach(word -> {
                if (!"".equals(word)) {
                    resultList.add(word);
                }
            });
            return resultList;
        } catch (NullPointerException e) {
            return new ArrayList<String>();
        }
    }
}
