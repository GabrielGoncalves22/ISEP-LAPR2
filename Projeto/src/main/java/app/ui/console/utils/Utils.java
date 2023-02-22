package app.ui.console.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Paulo Maio pam@isep.ipp.pt
 */
public class Utils {

    static public String readLineFromConsole(String prompt) {
        try {
            System.out.println(prompt);

            InputStreamReader converter = new InputStreamReader(System.in);
            BufferedReader in = new BufferedReader(converter);

            return in.readLine();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    static public int readIntegerFromConsole(String prompt) {
        boolean validation;
        int value = 0;
        do {
            try {
                String input = readLineFromConsole(prompt);

                value = Integer.parseInt(input);
                validation = true;
            } catch (NumberFormatException ex) {
                validation = false;
                System.out.println("This field only supports numbers!");
            }
        } while (!validation);
        return value;
    }

    static public double readDoubleFromConsole(String prompt) {
        boolean validation;
        double value = 0;
        do {
            try {
                String input = readLineFromConsole(prompt);

                value = Double.parseDouble(input);
                validation = true;
            } catch (NumberFormatException ex) {
                validation = false;
                System.out.println("This field only supports numbers!");
            }
        } while (!validation);
        return value;
    }

    static public Date readDateFromConsole(String prompt) {
        do {
            try {
                String strDate = readLineFromConsole(prompt);

                SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");

                Date date = df.parse(strDate);

                return date;
            } catch (ParseException ex) {
                Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);
    }

    static public boolean confirm(String message) {
        String input;
        do {
            input = Utils.readLineFromConsole("\n" + message);
        } while (!input.equalsIgnoreCase("y") && !input.equalsIgnoreCase("n"));

        return input.equalsIgnoreCase("y");
    }

    static public Object showAndSelectOne(List list, String header) {
        showList(list, header);
        return selectsObject(list);
    }

    static public int showAndSelectIndex(List list, String header) {
        showList(list, header);
        return selectsIndex(list);
    }

    static public void showList(List list, String header) {
        System.out.println(header);

        int index = 0;
        for (Object o : list) {
            index++;

            System.out.println(index + ". " + o.toString());
        }
    }

    static public Object selectsObject(List list) {
        int value;

        System.out.println("");
        System.out.println("0 - Cancel");

        do {
            value = Utils.readIntegerFromConsole("Type your option: ");
        } while (value < 0 || value > list.size());

        if (value == 0) {
            return null;
        } else {
            return list.get(value - 1);
        }
    }

    static public int selectsIndex(List list) {
        int value;

        System.out.println("");
        System.out.println("0 - Cancel");

        do {
            value = Utils.readIntegerFromConsole("Type your option: ");
        } while (value < 0 || value > list.size());

        return value - 1;
    }

    /**
     * Method to limit a String with other String
     *
     * @param phrase   The String to be limited
     * @param maxChars The number of max characters the String can have
     * @param limiter  The String to put in the end of the phrase
     * @return The limited String
     */
    public static String stringLimiter(String phrase, int maxChars, String limiter) {
        if (phrase.length() > maxChars) {
            phrase = phrase.substring(0, maxChars);
            phrase += limiter;
        }

        return phrase;
    }

}
