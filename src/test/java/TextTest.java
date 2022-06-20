//import ann.tsyhankova.FileService;
//import ann.tsyhankova.TextService;
//import ann.tsyhankova.TextUtils;
//import org.testng.Assert;
//import org.testng.annotations.DataProvider;
//import org.testng.annotations.Test;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.List;
//import java.util.Scanner;
//
//public class TextTest {
//
//    @DataProvider
//    public Object[][] dataProvider() throws IOException {
//
//        int symbolNumber = 43;
//        String text = FileService.getInstance(new File("")
//                .getAbsoluteFile() + "/src/main/resources/in.txt")
//                .getTextFromFile();
//
//        TextUtils textUtils = TextUtils.getInstance(text, symbolNumber);
//        textUtils.getSplittedText();
//        String[] splittedTextList = textUtils.getSplittedText();
//
//        List<String> alignedTextList = new TextService(text, symbolNumber).getAlignText();
//        return new Object[][]{{splittedTextList, alignedTextList}};
//    }
//
//    @Test(dataProvider = "dataProvider")
//    public void checkForFirstWord(String[] text, List<String> alignedText){
//        Assert.assertEquals(alignedText.get(0).split(" ")[0], text[0]);
//    }
//
//    @Test(dataProvider = "dataProvider")
//    public void checkForLastWord(String[] text, List<String> alignedText){
//        String[] lastWord = alignedText.get(alignedText.size()-1).split(" ");
//        Assert.assertEquals(lastWord[lastWord.length - 1], text[text.length - 1]);
//    }
//
//    @Test(dataProvider = "dataProvider")
//    public void checkForEmptyFile(String[] text, List<String> alignedText){
//
//        Assert.assertNotEquals(text.length, 0);
//    }
//
//    @Test(dataProvider = "dataProvider")
//    public void checkForEmptyAlignedText(String[] text, List<String> alignedText){
//
//        Assert.assertNotEquals(alignedText.size(), 0);
//    }
//}