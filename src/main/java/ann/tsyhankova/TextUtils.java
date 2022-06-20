package ann.tsyhankova;

import java.util.Arrays;
import java.util.List;

public class TextUtils {

    public static List<String> getWordList(String text){
        return Arrays.asList(text.split("\\s+"));
    }
}
