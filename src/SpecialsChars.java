public class SpecialsChars {

    /**
     * 特殊字符替换
     * @param text
     * @param isCode
     * @return
     */
    public static String Escape(String text, boolean isCode) {

        if (!isCode) {
            //匹配 ：  【~前面不带\】   替换：【给每个~前面加上 \】
            text = text.replaceAll("([^\\\\])~", "$1\\\\~");
        }

        // System.out.println(" ".equals(" "));  // false, one is 160，one is 32
        //替换160空格
        //160 这个空格是是由页面上的 &nbsp; 产生的空格，全称为“non-breaking space”  与ASCII为32的空格并不兼容
        text = text.replaceAll(" ", " ");

        return text;
    }
}
