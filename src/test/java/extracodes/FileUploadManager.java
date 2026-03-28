package extracodes;

import org.openqa.selenium.WebElement;

public class FileUploadManager {

    private static FileUploadManager instance;
    private String uploadDir;

    private FileUploadManager() {
        uploadDir = System.getProperty("user.dir") + "/uploads/";
    }

    public static synchronized FileUploadManager getInstance() {
        if (instance == null) {
            instance = new FileUploadManager();
        }
        return instance;
    }

    public String getFilePath(String fileName) {
        return uploadDir + fileName;
    }

    public void upload(WebElement element, String fileName) {
        element.sendKeys(getFilePath(fileName));
    }
}
