import java.util.ArrayList;
import java.util.List;

// Generate GFM support TOC tag
public class TOCHelper {
    private final static String tocTag = "<!-- GFM-TOC -->";
    // catalogue level
    private static int level = 4;

    public static void setCatalogueLevel(int level) {
        TOCHelper.level = level;
    }

    public static List<String> changeTOCToGeneratedCatalogue(List<String> contents) {
        List<String> ret = new ArrayList<>();
        // If there already existed catalogue in document, then update this catalogue.
        boolean isInOldCatalogue = false;
        for (String text : contents) {
            if (text.contains(tocTag)) {
                isInOldCatalogue = !isInOldCatalogue;
            } else if (isInOldCatalogue) continue;
            else if (text.contains("[TOC]")) {
                ret.add(generateCatalogue(contents));
            } else {
                ret.add(text);
            }
        }
        return ret;
    }

    public static String generateCatalogue(List<String> contents) {
        List<String> titles = getTitles(contents);
        StringBuilder sb = new StringBuilder();
        sb.append(tocTag);
        sb.append(System.lineSeparator());
        for (String title : titles) {
            sb.append(formatTitle(title));
            sb.append(System.lineSeparator());
        }
        sb.append(tocTag);
        sb.append(System.lineSeparator());
        return sb.toString();
    }

    private static List<String> getTitles(List<String> contents) {
        List<String> titles = new ArrayList<>();
        boolean isCode = false;
        for (String line : contents) {
            if (line.contains("```")) {
                isCode = !isCode;
            } else if (line.startsWith("#") && !isCode && getLevelOfATitle(line) <= TOCHelper.level) {
                titles.add(line);
            }
        }
        return titles;
    }

    private static int getLevelOfATitle(String title) {
        int cnt = 0;
        int idx = title.indexOf("#");
        while (idx != -1) {
            cnt++;
            idx = title.indexOf("#", idx + 1);
        }
        return cnt;
    }

    private static String formatTitle(String title) {
        StringBuilder ret = new StringBuilder();
        int level = getLevelOfATitle(title);
        for (int i = 1; i < level; i++) {
            ret.append("    ");
        }
        ret.append("*");
        ret.append(" ");

        int contentIdx = title.lastIndexOf("#");
        contentIdx++;
        while (title.charAt(contentIdx) == ' ') contentIdx++;
        String content = title.substring(contentIdx);
        // replace ' ' to '-'
        String anchor = content.replaceAll(" ", "\\-");
        // remove spacial char
        anchor = anchor.replaceAll("\\.|：|（）|\\(|\\)|\\*|/|、", "");
        // uppercase to lowercase
        anchor = anchor.toLowerCase();

        ret.append("[").append(content).append("]");
        ret.append("(#").append(anchor).append(")");
        return ret.toString();
    }
}
