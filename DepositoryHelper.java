import java.io.File;
import java.util.*;

public class DepositoryHelper {
    private final static String DepositoryFolderPath = "D:\\Repo\\InterviewNotes\\notes";
    private static List<String> canUploadFiles = Arrays.asList("2016 校招真题题解.md", "HTTP.md", "Leetcode 题解.md", "Linux.md",
            "计算机操作系统.md", "计算机网络.md", "剑指 offer 题解.md", "设计模式.md", "算法.md", "面向对象思想.md",
            "JVM.md", "Java IO.md", "MySQL.md", "SQL 语法.md", "Java 容器.md", "重构.md", "代码可读性.md", "代码风格规范.md");

    public static List<File> getNoteFiles() throws Exception {
        List<File> allNoteFiles = getAllNoteFiles();
        List<File> noteFiles = filterNoteFiles(allNoteFiles);
        if (noteFiles.size() != canUploadFiles.size()) throw new Exception("笔记数量不正确！");
        return noteFiles;
    }

    private static List<File> getAllNoteFiles() {
        List<File> ret = new ArrayList<File>();
        File folder = new File(DepositoryFolderPath);
        for (File file : folder.listFiles()) {
            if (file.toString().equals(".") || file.toString().equals("..")) continue;
            if (file.isFile() && file.toString().contains(".md")) {
                ret.add(file);
            }
        }
        return ret;
    }

    private static List<File> filterNoteFiles(List<File> allNoteFiles) {
        List<File> textNoteFiles = new ArrayList<File>();
        for (File file : allNoteFiles) {
            boolean isTxtFile = file.getName().endsWith(".txt");
            if (isTxtFile) textNoteFiles.add(file);
            else file.delete();
        }

        List<File> ret = new ArrayList<File>();
        Set<String> canUploadFileSet = new HashSet<String>(canUploadFiles);
        for (File file : textNoteFiles) {
            String filePath = file.toString();
            File mdFile = new File(filePath.substring(0, filePath.length() - 4));
            file.renameTo(mdFile);
            if (canUploadFileSet.contains(mdFile.getName())) ret.add(mdFile);
            else mdFile.delete();
        }

        File folder = new File(DepositoryFolderPath);
        for (File file : folder.listFiles()) {
            if (file.toString().equals(".") || file.toString().equals("..")) continue;
            if (file.isFile() && file.getName().contains(".txt")) file.delete();
            else if (file.isDirectory() && !file.getName().equals("src")) TxtFileHelper.deleteDirectory(file.toString());
        }
        return ret;
    }
}
