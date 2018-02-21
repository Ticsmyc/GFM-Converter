import java.util.ArrayList;
import java.util.List;

public class GFMConverter {
    public static void convert(String srcFilePath, String detFilePath) {
        List<String> contents = FileHelper.readLineByLine(srcFilePath);
        contents = TOCHelper.changeTOCToGeneratedCatalogue(contents);
        List<String> newContents = new ArrayList<>();
        for (String text : contents) {
            text = MathJaxHelper.changeMathJaxToCodeCogs(text);
            text = SpecialsCharsHelper.Escape(text);
            newContents.add(text);
        }
        FileHelper.WriteLineByLine(newContents, detFilePath);
    }
}
