package ann.tsyhankova;

public class Main {

    public static void main(String[] args){
        String text = FileService.getTextFromFile( "src/main/resources/in.txt");
        String textAligned = new TextService().getAlignedText(text, 35);
        System.out.println(textAligned);
    }
}
