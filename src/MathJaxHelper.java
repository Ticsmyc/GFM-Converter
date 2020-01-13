public class MathJaxHelper {

    /**
     * 修改一段文本中的 本地文本公式 为 codecog地址
     * @param text
     * @return
     */
    public static String changeMathJaxToCodeCogs(String text) {

        StringBuilder sb = new StringBuilder();

        // 循环修改一段文本中的公式
        while (true) {
            //定位标识符首尾下标
            int startIdx, endIdx;
            if (isLineMath(text)) {
                startIdx = text.indexOf("$$");
                endIdx = text.indexOf("$$", startIdx + 1);
            } else if (isInLineMath(text)) {
                startIdx = text.indexOf("$");
                endIdx = text.indexOf("$", startIdx + 1);
            } else {
                //剩下的部分没有公式了，直接append
                sb.append(text);
                break;
            }
            //保留公式之前的内容
            String leftPartContent = text.substring(0, startIdx);

            while (startIdx < text.length() && text.charAt(startIdx) == '$') startIdx++;
            //提取内容
            String mathJaxContent = text.substring(startIdx, endIdx);
            //去掉空格
            mathJaxContent = mathJaxContent.replaceAll(" ", "");
            //拼接url （默认为行内公示）
            mathJaxContent = "<img src=\"https://latex.codecogs.com/gif.latex?" + mathJaxContent + "\"/>";

            if (isLineMath(text)) {
                //单独成行的公式
                mathJaxContent = "<div align=\"center\">" + mathJaxContent + "</div> <br>";
            }

            while (endIdx < text.length() && text.charAt(endIdx) == '$') endIdx++;
            sb.append(leftPartContent).append(mathJaxContent);
            text = text.substring(endIdx);
        }

        return sb.toString();
    }

    /**
     * 判断是否是单独成行的公式
     * @param text
     * @return
     */
    private static boolean isLineMath(String text) {
        return hasPairs(text, "$$");
    }

    /**
     * 判断是否是行内公式
     * @param text
     * @return
     */
    private static boolean isInLineMath(String text) {
        return hasPairs(text, "$");
    }

    /**
     * 公式标识符配对
     * @param text
     * @param str
     * @return
     */
    private static boolean hasPairs(String text, String str) {

        int idx = text.indexOf(str);
        if (idx == -1 || (idx != 0 && text.charAt(idx - 1) == '\\')) {
            return false;
        }
        idx = text.indexOf(str, idx + 3);
        return idx != -1;
    }
}
