package com.syberry.school.exceptions;

import static com.syberry.school.utils.ApplicationConstants.IMAGE_NOT_FOUND;

public class ImageNotFoundException extends RuntimeException {
    public ImageNotFoundException() {
        super(IMAGE_NOT_FOUND);
    }
}
