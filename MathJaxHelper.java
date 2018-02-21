public class MathJaxHelper {
    public static String changeMathJaxToCodeCogs(String text) {
        if (!hasMathJax(text)) return text;
        int startIdx = text.indexOf("$$");
        int endIdx = text.indexOf("$$", startIdx + 1);
        String leftPartContent = text.substring(0, startIdx);
        String rightPartContent = text.substring(endIdx + 2);
        String mathJaxContent = text.substring(startIdx, endIdx + 1);
        mathJaxContent = mathJaxContent.replaceAll(" ", "");
        mathJaxContent = "![](http://latex.codecogs.com/gif.latex?\\\\\\\\" + mathJaxContent + ")";
        return leftPartContent + mathJaxContent + rightPartContent;
    }

    private static boolean hasMathJax(String text) {
        int idx = text.indexOf("$$");
        if (idx == -1) return false;
        idx = text.indexOf("$$", idx + 1);
        return idx != -1;
    }
}
