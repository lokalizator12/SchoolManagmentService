package by.personal.schoolmanagmentservice.exceptions;

import static by.personal.schoolmanagmentservice.utils.ApplicationConstants.IMAGE_NOT_FOUND;

public class ImageNotFoundException extends RuntimeException {
    public ImageNotFoundException() {
        super(IMAGE_NOT_FOUND);
    }
}
