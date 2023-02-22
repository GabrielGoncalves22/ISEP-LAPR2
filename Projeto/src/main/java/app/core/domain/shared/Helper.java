package app.core.domain.shared;

import java.util.Random;
import java.util.regex.Pattern;

/**
 * This class has helper methods that can be used everywhere when they are needed.
 *
 * @author Group 40
 */
public class Helper {

    /**
     * Method to generate a random password.
     *
     * @param lengthPassword The length that the password should have.
     * @return The generated password.
     */
    public static String generatePassword(int lengthPassword) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        String password = "";
        Random random = new Random();

        while (!Pattern.compile("(?=.{" + lengthPassword + "}$)").matcher(password).find()
                || !Pattern.compile("[A-Z]{3}").matcher(password).find()
                || !Pattern.compile("[0-9]{2}").matcher(password).find()) {

            password = "";

            for (int i = 0; i < lengthPassword; i++) {
                int position = random.nextInt(characters.length());
                password += characters.charAt(position);
            }

        }

        return password;
    }
}
