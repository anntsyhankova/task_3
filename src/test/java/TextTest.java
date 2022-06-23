import ann.tsyhankova.FileService;
import ann.tsyhankova.TextService;
import ann.tsyhankova.TextUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextTest {

    private String textFromTestFile;
    @BeforeClass
    public void readTestText(){
        textFromTestFile = "Шла Саша по шоссе и сосала сушку\n";
    }
    @DataProvider
    public Object[][] getTextFromFileDataProvider(){
        return new Object[][]{
                {"src/main/resources/test.txt", "Шла Саша по шоссе и сосала сушку\n"},
                {"wrongFilePath", ""},
                {null, ""}
        };
    }

    @Test(dataProvider = "getTextFromFileDataProvider")
    public void getTextFromFileUnitTest(String filePath, String expectedText){
        String actualTextFromFile = FileService.getTextFromFile(filePath);
        Assert.assertEquals(actualTextFromFile, expectedText);
    }

    @DataProvider
    public Object[][] splitTextFromFileDataProvider(){
        return new Object[][]{
                {textFromTestFile, Arrays.asList("Шла", "Саша", "по", "шоссе", "и", "сосала", "сушку")},
                {"Шла", Arrays.asList("Шла")},
                {null, new ArrayList<String>()}
        };
    }
    @Test(dataProvider = "splitTextFromFileDataProvider")
    public void splitTextFromFileUnitTest(String text, List<String> expectedWordList){
        List<String> actualWordList = TextUtils.getWordList(text);
        Assert.assertEquals(actualWordList, expectedWordList);
    }

    @DataProvider
    public Object[][] alignTextDataProvider(){
        return new Object[][]{
                {textFromTestFile, 45, "Шла    Саша   по   шоссе   и   сосала   сушку"},
                {textFromTestFile, 25, "Шла   Саша   по  шоссе  и\nсосала              сушку"},
                {textFromTestFile, 55, "Шла     Саша     по     шоссе     и     сосала    сушку"},
                {null, null, ""},
                {null, 40, ""},
                {"", 40, ""},
                {"one", 3, "one"},
                {"one", 10, "one       "},
                {" one", 3, "one"},
                {"one two", 3, "one\ntwo"},
                {textFromTestFile, null, ""},
                {"\none", 3, "one"},
                {"one \n", 3, "one"},
                {"one \none \n one \n", 3, "one\none\none"},
                {"\t \t \t \n \n", 10, ""},
                {"\t \t \t \n \n one", 10, "one       "}
        };
    }

    @Test(dataProvider = "alignTextDataProvider")
    public void alignTextUnitTest(String text, Integer symbolQuantity, String expectedAlignedText){
        String actualAlignedText = new TextService().getAlignedText(text, symbolQuantity);
        Assert.assertEquals(actualAlignedText, expectedAlignedText);
    }
}


