package com.syberry.school.validations;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@RequiredArgsConstructor
public class FileValidation implements ConstraintValidator<FileIsExists, MultipartFile> {
    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext constraintValidatorContext) {
        if (file.isEmpty()) {
            return false;
        }
        if (file.getOriginalFilename() == null || file.getOriginalFilename().contains("..")) {
            return false;
        }
        return false;
    }
}
