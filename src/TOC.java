import java.util.ArrayList;
import java.util.List;

//生成一个！ 可以在github上跳转的目录
public class TOC {

    private final static String tocTagBefore = "[TOC]";
    private final static String tocTagAfter = "<!-- GFM-TOC -->";
    // catalogue level
    private static int level = 4;


    public static void setCatalogueLevel(int level) {
        TOC.level = level;
    }


    public static List<String> changeTOCToGeneratedCatalogue(List<String> contents) {

        List<String> ret = new ArrayList<String>();

        // 如果原文档有目录了，就在它的基础上改变.
        boolean isInOldCatalogue = false;

        // 确保要生成一个目录
        boolean hasGenerated = false;

        for (String text : contents) {
            if (isInOldCatalogue) {
                //当前位置是原来的目录
                if (text.contains(tocTagAfter)) {
                    ret.add(generateCatalogue(contents));
                    hasGenerated = true;
                    isInOldCatalogue = false;
                }
                continue;
            }
            if (hasGenerated) ret.add(text);
            else if (text.contains(tocTagAfter)) {
                isInOldCatalogue = true;
            } else if (text.contains(tocTagBefore)) {
                ret.add(generateCatalogue(contents));
                hasGenerated = true;
            } else ret.add(text);
        }

        return ret;
    }

    /**
     * 生成目录
     * @param contents
     * @return
     */
    public static String generateCatalogue(List<String> contents) {

        List<String> titles = getTitles(contents);

        StringBuilder sb = new StringBuilder();

        sb.append(tocTagAfter).append(CommonUtil.getLineSeparator());

        for (String title : titles) {
            sb.append(formatTitle(title)).append(CommonUtil.getLineSeparator());
        }

        sb.append(tocTagAfter).append(CommonUtil.getLineSeparator());

        return sb.toString();
    }

    /**
     * 寻找标题，返回一个list
     * @param contents
     * @return
     */
    private static List<String> getTitles(List<String> contents) {

        List<String> titles = new ArrayList<String>();

        boolean isCode = false;

        for (String line : contents) {
            if (line.contains("```")) {
                //代码片段不处理
                isCode = !isCode;
            } else if (line.startsWith("#") && !isCode && getLevelOfATitle(line) <= TOC.level) {
                titles.add(line);
            }
        }

        return titles;
    }

    /**
     * 获取标题级别：  几个#
     * @param title
     * @return
     */
    private static int getLevelOfATitle(String title) {

        int cnt = 0;

        int idx = title.indexOf("#");
        while (idx != -1) {
            cnt++;
            idx = title.indexOf("#", idx + 1);
        }

        return cnt;
    }

    /**
     * 标题样式 ： 空格*n + * + 空格*1 + [#
     * @param title
     * @return
     */
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
        while (title.charAt(contentIdx) == ' ') {
            contentIdx++;
        }

        String content = title.substring(contentIdx);
        //content：标题名
        content = content.trim();

        //anchor ： 锚点名
        //"空格"换成 "-"
        String anchor = content.replaceAll(" ", "\\-");

        // 去掉括号等特殊字符
        anchor = anchor.replaceAll("[.：（）()*/、:+，]", "");

        // 转小写
        anchor = anchor.toLowerCase();

        ret.append("[").append(content).append("]");
        ret.append("(#").append(anchor).append(")");

        return ret.toString();
    }
}
