public class CommonHelper {
    // System.lineSeparator() doesn't support jdk 1.6
    public static String getLineSeparator(){
        return System.getProperty("line.separator");
    }
}
