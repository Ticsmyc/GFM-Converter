public class CommonUtil {
    /**
     * System.lineSeparator() not support in jdk 1.6
     * 获取到系统换行符
     */
    public static String getLineSeparator() {
        return System.getProperty("line.separator");
    }


}
