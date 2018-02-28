import java.io.File;
import java.util.List;

public class WizToGithub {
    public static void main(String[] args) throws Exception {
        List<File> noteFiles = DepositoryHelper.getNoteFiles();
        for (File file : noteFiles) {
            WizPicMover.movePics(file);
            GFMConverter.convert(file.toString(), file.toString());
        }
    }
}
