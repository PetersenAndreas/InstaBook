package Database;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.Part;

public class PictureDB {

    private static final String UPLOAD_DIRECTORY = "pictures";
    private static final String[] ALLOWED_FILE_TYPES = new String[]{"image/png", "image/jpeg"};
    private static final String[] ALLOWED_FILE_NAMES = new String[]{"png", "jpg"};
    private CloudinaryDB database;

    public PictureDB(CloudinaryDB database) {
        this.database = database;
    }

    public ArrayList<String> uploadPost(List<Part> requestParts) {

        ArrayList<String> result_list = new ArrayList();
        try {
            //Make a folder for the picture to be stored while working with upload, if it's not already made.
            File uploadFolder = new File(database.getWorkingDirectory() + File.separator + UPLOAD_DIRECTORY);
            if (!uploadFolder.exists()) {
                uploadFolder.mkdir();
            }

            String pictureURL = null;

            for (Part part : requestParts) {
                //Check each part to see if it is a part that could contain a file
                if (part != null && part.getSize() > 0) {
                    String fileName = part.getSubmittedFileName();
                    String fileContentType = part.getContentType();

                    if (isFileTypeAllowed(fileContentType)) {

                        //Make a local file of the picture
                        String filePlacement = database.getWorkingDirectory() + File.separator + UPLOAD_DIRECTORY + File.separator + fileName;
                        part.write(filePlacement);
                        File file = new File(filePlacement);

                        //Upload the picture, and get the URL for the picture back
                        Cloudinary cloud = database.getCloudinaryConnection();
                        Map uploadResult = cloud.uploader().upload(file, ObjectUtils.emptyMap());
                        pictureURL = (String) uploadResult.get("url");
                        result_list.add(pictureURL);

                        file.delete();
                    }
                }
            }

            return result_list;

        } catch (IOException ex) {
            String allowedFileContentTypes = constructAllowedFileContentTypesString();
            throw new IllegalArgumentException("Picturefile could not be copied into the system. "
                    + "Please make sure the file is of any of the following types: " + allowedFileContentTypes);
        }

    }

    private boolean isFileTypeAllowed(String fileContentType) {
        if (fileContentType != null) {
            for (String allowedFileType : ALLOWED_FILE_TYPES) {
                if (fileContentType.equalsIgnoreCase(allowedFileType)) {
                    return true;
                }
            }
        }
        return false;
    }


    private String constructAllowedFileContentTypesString() {
        String result = "";
        boolean firstLine = true;
        for (String allowedFileType : ALLOWED_FILE_NAMES) {
            if (firstLine) {
                result += allowedFileType;
                firstLine = false;
            } else {
                result += ", " + allowedFileType;
            }
        }
        return result;
    }
}