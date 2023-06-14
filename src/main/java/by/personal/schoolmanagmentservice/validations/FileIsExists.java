package by.personal.schoolmanagmentservice.validations;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static by.personal.schoolmanagmentservice.utils.ApplicationConstants.FILE_NOT_FOUND;


@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FileValidation.class)
public @interface FileIsExists {

    String message() default FILE_NOT_FOUND;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
