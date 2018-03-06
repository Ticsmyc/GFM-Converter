public class CenterTagHelper {
    public static String changeCenterTag(String text) {
        boolean containsCenterTag = text.contains("<center>") && text.contains("</center>");
        boolean hasPic = text.contains("![]");
        if (!containsCenterTag) {
            return text;
        }

        if (containsCenterTag) {
            int startIdx = text.indexOf("<center>");
            int endIdx = text.indexOf("</center>");
            text = text.substring(startIdx + 8, endIdx);
        }

        if (hasPic) {
            int startIdx = text.indexOf("![]");
            int endIdx = text.indexOf(")", startIdx);
            String picPath = text.substring(startIdx + 4, endIdx);
            text = "<img src=\"" + picPath + "\"/>";
        }

        text = "<br><div align=\"center\"> " + text + " </div><br>";
        return text;
    }
}
