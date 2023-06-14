package by.personal.schoolmanagmentservice.utils;

import java.text.SimpleDateFormat;

public class ApplicationConstants {

    public static final String[] WHITELIST = {
            "/home",
            "/",
            "/registration",
            "/by/personal/schoolmanagmentservice/resources/**",
            "/static/**",
            "/css/**",
            "/js/**",
            "/images/**",
            "/icon/**"
    };

    public static final String[] ADMIN_ACCESS_LIST = {
            "/admin/**",
            "/admin",
            "/image/delete/**",
            "/news/delete**",
            "/students/delete**"
    };

    public static final String[] USERS_ACCESS_LIST = {
            "/image/**",
            "/image/",
            "/users/avatar**",
            "/users/teachers"
    };
    public final static String IMAGE_NOT_FOUND = "Image is not found";
    public final static String FILE_NOT_FOUND = "File is not found";
    public static final SimpleDateFormat DATE_FORMAT_ENTITY = new SimpleDateFormat("dd-MM-yyyy- HH:mm");
    public static String UPLOAD_DIRECTORY_IMAGES = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\uploadedImages\\";
    public static String UPLOAD_DIRECTORY_GALLERY = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\uploadedImages\\gallery\\";

    public static String UPLOAD_DIRECTORY_AVATAR = System.getProperty("user.dir") + "src/main/resources/static/images/avatars";

    public static String UPLOAD_DIRECTORY_PREVIEW = System.getProperty("user.dir") + "/src/main/resources/static/uploadedImages/previewImages";
    public static String LOG_CREAT_POST_SUCCESSFUL = "Creating and saving to database a new post news with header:{} is finished successful";
}
