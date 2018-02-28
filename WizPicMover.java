import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WizPicMover {
    private final static String WizPicFolderPath = "D:\\文档\\My Knowledge";
    private final static String DepositoryPicFolderPath = "D:\\Repo\\InterviewNotes\\pics";
    private final static String GitHubPicFolderPath = "https://github.com/CyC2018/InterviewNotes/blob/master/pics/";
    private final static String PicString = "![](index_files/";
    private static Map<String, String> picToPath = new HashMap<String, String>();

    static {
        picToPath = getPicToPathMapInWizPicFolder();
    }

    public static void movePics(File file) {
        List<String> contents = TxtFileHelper.readLineByLine(file.toString());
        List<String> newContents = new ArrayList<String>();
        for (String line : contents) {
            if (line.contains(PicString)) {
                String picName = getPicName(line);
                String path = picToPath.get(picName);
                if (path != null) {
                    TxtFileHelper.copyFile(path, DepositoryPicFolderPath + "\\" + picName);
                }
                newContents.add(replacePicPathToGithub(line));
            } else {
                newContents.add(line);
            }
        }
        TxtFileHelper.writeLineByLine(newContents,file.toString());
    }

    private static String getPicName(String line) {
        assert line.contains(PicString);
        int idx = line.indexOf(PicString);
        return line.substring(idx + 16, line.indexOf(")"));
    }


    private static String replacePicPathToGithub(String line) {
        return line.replace("index_files", GitHubPicFolderPath);
    }

    private static Map<String, String> getPicToPathMapInWizPicFolder() {
        File folder = new File(WizPicFolderPath);
        return getPicToPathMapInWizPicFolder(folder);
    }

    private static Map<String, String> getPicToPathMapInWizPicFolder(File curFolder) {
        Map<String, String> picToPath = new HashMap<String, String>();
        for (File file : curFolder.listFiles()) {
            if (file.toString().equals(".") || file.toString().equals("..")) continue;
            if (file.isDirectory()) picToPath.putAll(getPicToPathMapInWizPicFolder(file));
            if (file.isFile() && isPic(file.toString())) picToPath.put(file.getName(), file.toString());
        }
        return picToPath;
    }

    private static boolean isPic(String filePath) {
        return filePath.contains(".bmp") || filePath.contains("gif") || filePath.contains(".jpg") || filePath.contains(".png");
    }
}
