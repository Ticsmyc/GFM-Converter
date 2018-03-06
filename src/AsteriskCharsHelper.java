public class AsteriskCharsHelper {

    public static String addSpace(String text) {
        int indexStart = text.indexOf("**");
        if (indexStart == -1) return text;
        int indexEnd = text.indexOf("**", indexStart + 3);
        if (indexEnd == -1) return text;
        StringBuilder ret = new StringBuilder();
        if (indexStart != 0) {
            ret.append(text.substring(0, indexStart));
            ret.append(" ");
        }
        ret.append(text.substring(indexStart, indexEnd + 2));
        if (indexEnd != text.length() - 1) {
            ret.append(" ");
            ret.append(text.substring(indexEnd + 2));
        }
        return ret.toString();
    }
}
