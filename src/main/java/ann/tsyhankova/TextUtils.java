package ann.tsyhankova;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextUtils {

    public static List<String> getWordList(String text){
        try {
            return Arrays.asList(text.split("\\s+"));
        } catch (NullPointerException e){
            return new ArrayList<String>();
        }
    }
}
