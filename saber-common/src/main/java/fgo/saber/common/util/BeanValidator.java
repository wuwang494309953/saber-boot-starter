package fgo.saber.common.util;

import fgo.saber.common.exception.ParamException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.*;

public class BeanValidator {

    private static ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

    public static <T> Map<String, String> validate(T t, Class... groups) {
        Validator validator = validatorFactory.getValidator();
        Set validateResult = validator.validate(t, groups);
        if (validateResult.isEmpty()) {
            return Collections.emptyMap();
        } else {
            LinkedHashMap errors = new LinkedHashMap<>();
            Iterator iterator = validateResult.iterator();
            while (iterator.hasNext()) {
                ConstraintViolation violation = (ConstraintViolation)iterator.next();
                errors.put(violation.getPropertyPath().toString(), violation.getMessage());
            }
            return errors;
        }
    }

    public static Map<String, String> validateList(Collection<?> collection, Class... groups) {
        Iterator iterator = collection.iterator();
        Map errors;

        do {
            if (!iterator.hasNext()) {
                return Collections.emptyMap();
            }
            Object object = iterator.next();
            errors = validate(object, groups);
        } while (errors.isEmpty());

        return errors;
    }

    public static Map<String, String> validateObject(Object first, Class... groups) {
        if (groups != null && first instanceof Collection && groups.length > 0) {
            return validateList((Collection) first, groups);
        } else {
            return validate(first, groups);
        }
    }

    public static void check(Object param) throws ParamException {
        Map<String, String> map = BeanValidator.validateObject(param);
        if (!map.isEmpty()) {
            throw new ParamException(mapToStr(map));
        }
    }

    private static String mapToStr(Map<String, String> map) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String val : map.values()) {
            stringBuilder.append(val).append(",");
        }

        return stringBuilder.toString().substring(0, stringBuilder.lastIndexOf(","));
    }

}
