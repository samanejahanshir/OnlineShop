package service;

import exceptions.InvalidEmailExp;
import exceptions.InvalidInputExp;
import exceptions.InvalidNameExp;

import java.util.regex.Pattern;

public class CheckInputValidation {
    public static boolean checkName(String name) {
        if (name.length() < 2 && !Pattern.matches("[a-zA-Z]", name)) {
            throw new InvalidNameExp("format name not valid ! ");
        }
        return true;
    }

    public static boolean checkEmail(String email) {
        if (!email.contains(".") && !email.contains("@")) {
            throw new InvalidEmailExp("format email not valid");
        }
        return true;
    }
}
