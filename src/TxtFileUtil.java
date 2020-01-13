import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件读写工具类
 */
public class TxtFileUtil {

    /**
     * 按行读取文件，存入一个list
     * @param filePath
     * @return
     */
    public static List<String> readLineByLine(String filePath) {

        List<String> contents = new ArrayList<String>();

        InputStreamReader isr;
        BufferedReader br;

        try {
            isr = new InputStreamReader(new FileInputStream(filePath), "UTF-8");
            br = new BufferedReader(isr);

            String line;
            while ((line = br.readLine()) != null) {
                contents.add(line);
            }

            br.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return contents;
    }


    /**
     * 按行写文件
     * @param contents
     * @param filePath
     */
    public static void writeLineByLine(List<String> contents, String filePath) {

        BufferedWriter bw;
        try {
            bw = new BufferedWriter(new FileWriter(filePath,true));
            for (String text : contents) {
                bw.write(text);
                bw.write(CommonUtil.getLineSeparator());
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件拷贝 ： 输入流输出流对接
     * @param srcPath
     * @param detPath
     */
    public static void copyFile(String srcPath, String detPath) {

        try {
            FileInputStream fis = new FileInputStream(srcPath);
            FileOutputStream fos = new FileOutputStream(detPath);

            byte[] buffer = new byte[1024];
            while (fis.read(buffer) != -1) {
                fos.write(buffer);
            }

            fis.close();
            fos.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * 删除目录 ： 递归删除文件
     * @param dirPath
     */
    public static void deleteDirectory(String dirPath) {

        File dir = new File(dirPath);

        for (File file : dir.listFiles()) {
            if (file.isFile()) file.delete();
            else deleteDirectory(file.toString());
        }

        dir.delete();
    }
}
