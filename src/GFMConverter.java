import java.util.ArrayList;
import java.util.List;

public class GFMConverter {
    public static void convert(String srcFilePath, String detFilePath) {
        List<String> contents = TxtFileHelper.readLineByLine(srcFilePath);
        List<String> newContents = new ArrayList<String>();
        boolean isCode = false;
        for (String text : contents) {
            text = SpecialsCharsHelper.Escape(text, isCode);
            if (text.contains("```")) {
                isCode = !isCode;
            } else if (!isCode) {
                text = MathJaxHelper.changeMathJaxToCodeCogs(text);
                text = CenterTagHelper.changeCenterTag(text);
                text = AsteriskCharsHelper.addSpace(text);
            }
            newContents.add(text);
        }
        newContents = TOCHelper.changeTOCToGeneratedCatalogue(newContents);
        for (String str : newContents) System.out.println(str);
        TxtFileHelper.writeLineByLine(newContents, detFilePath);
    }
}
