package app.core.domain.model;

import java.time.LocalDate;
import java.time.Period;
import java.io.Serializable;
import java.util.Calendar;

/**
 * Represents a date through the day, month and year.
 *
 * @author Group 40
 */
public class Date implements Comparable<Date>, Serializable {

    /**
     * The year of the date.
     */
    private int year;

    /**
     * The month of the date.
     */
    private Months month;

    /**
     * The day of the date.
     */
    private int day;


    /**
     * Represents the days of the week.
     */
    private enum DayWeek {

        /**
         * Days of the week.
         */
        SUNDAY {
            @Override
            public String toString() {
                return "Sunday";
            }
        },
        MONDAY {
            @Override
            public String toString() {
                return "Monday";
            }
        },
        TUESDAY {
            @Override
            public String toString() {
                return "Thuesday";
            }
        },
        WEDNESDAY {
            @Override
            public String toString() {
                return "Wednesday";
            }
        },
        THURSDAY {
            @Override
            public String toString() {
                return "Thursday";
            }
        },
        FRIDAY {
            @Override
            public String toString() {
                return "Friday";
            }
        },
        SATURDAY {
            @Override
            public String toString() {
                return "Saturday";
            }
        };

        /**
         * Returns the designation of the day of the week whose order is received by
         * parameter.
         *
         * @param weekdayorder the weekday order between zero and six,
         *                     including. The lowest order corresponds to the
         *                     Sunday.
         * @return the day of the week designation.
         */
        public static String designationDayOfWeek(int weekdayorder) {
            return DayWeek.values()[weekdayorder].toString();
        }
    }

    /**
     * Represents the months of the year.
     */
    private enum Months {

        /**
         * The months of the year.
         */
        JANUARY(31) {
            @Override
            public String toString() {
                return "January";
            }
        },
        FEBRUARY(28) {
            @Override
            public String toString() {
                return "February";
            }
        },
        MARCH(31) {
            @Override
            public String toString() {
                return "March";
            }
        },
        APRIL(30) {
            @Override
            public String toString() {
                return "April";
            }
        },
        MAY(31) {
            @Override
            public String toString() {
                return "May";
            }
        },
        JUNE(30) {
            @Override
            public String toString() {
                return "June";
            }
        },
        JULY(31) {
            @Override
            public String toString() {
                return "July";
            }
        },
        AUGUST(31) {
            @Override
            public String toString() {
                return "August";
            }
        },
        SEPTEMBER(30) {
            @Override
            public String toString() {
                return "September";
            }
        },
        OCTOBER(31) {
            @Override
            public String toString() {
                return "October";
            }
        },
        NOVEMBER(30) {
            @Override
            public String toString() {
                return "November";
            }
        },
        DECEMBER(31) {
            @Override
            public String toString() {
                return "December";
            }
        };

        /**
         * The number of days in a month.
         */
        private int numberOfDays;

        /**
         * Constructs a month with the number of days received per parameter.
         *
         * @param numberOfDays the number of days in the month.
         */
        Months(int numberOfDays) {
            this.numberOfDays = numberOfDays;
        }

        /**
         * Returns the number of days of the month of the year received by parameter.
         *
         * @param year the year of the month.
         * @return the number of days in the month of the year.
         */
        public int numberOfDays(int year) {
            if (ordinal() == 1 && Date.isLeapYear(year)) {
                return numberOfDays + 1;
            }
            return numberOfDays;
        }

        /**
         * Returns the month whose order is received by parameter.
         *
         * @param orderOfMonth the order of the month.
         * @return the month whose order is received by parameter.
         */
        public static Months getMonths(int orderOfMonth) {
            return Months.values()[orderOfMonth - 1];
        }

    }

    /**
     * Builds a Date instance receiving the year, month and day.
     *
     * @param year  the year of the date.
     * @param month the month of the date.
     * @param day   the day of the date.
     */
    public Date(int year, int month, int day) {
        this.year = year;
        this.month = Months.getMonths(month);
        this.day = day;
    }

    /**
     * Builds a Date instance receiving other date.
     *
     * @param otherDate the other date.
     */
    public Date(Date otherDate) {
        this.year = otherDate.year;
        this.month = otherDate.month;
        this.day = otherDate.day;
    }

    /**
     * Builds a new Date
     *
     * @param otherDate the other date.
     * @return the date
     */
    public static Date newDate(Date otherDate) {
        return new Date(otherDate.year, otherDate.month.ordinal() + 1, otherDate.day);
    }

    /**
     * Returns the textual description of the date in the format: dayOfWeek, year of month of day.
     *
     * @return date characteristics.
     */
    @Override
    public String toString() {
        return String.format("%s, %d of %s of %d", dayOfWeek(), day, month, year);
    }

    /**
     * Return the date in the format:%02d/%02d/%04d.
     *
     * @return caraterísticas da data.
     */
    public String toDayMonthYearString() {
        return String.format("%02d/%02d/%04d", day, month.ordinal() + 1, year);
    }

    /**
     * Compares the date with the received object.
     *
     * @param otherObject the object to compare with the date.
     * @return true if the received object represents a date equivalent to the
     * date. Otherwise, returns false.
     */
    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject == null || getClass() != otherObject.getClass()) {
            return false;
        }
        Date otherDate = (Date) otherObject;
        return year == otherDate.year && month.equals(otherDate.month)
                && day == otherDate.day;
    }

    /**
     * Compares the date with the other date received by parameter.
     *
     * @param otherDate the date to compare.
     * @return the value 0 if the received otherDate is equal to the date; the value -1 if
     * the otherDate is later than the date; the value 1 if the otherDate is
     * before the date.
     */
    @Override
    public int compareTo(Date otherDate) {
        return (otherDate.isMajor(this)) ? -1 : (isMajor(otherDate)) ? 1 : 0;
    }

    /**
     * Returns the day of the week for the date.
     *
     * @return weekday of date.
     */
    public String dayOfWeek() {
        int totalDays = accountDays();
        totalDays = totalDays % 7;

        return DayWeek.designationDayOfWeek(totalDays);
    }

    /**
     * Returns true if the date is greater than the date received by parameter. if
     * the date is less than or equal to the date received by parameter, returns false.
     *
     * @param otherDate to another date to compare the date to.
     * @return true if the date is greater than the date received by parameter,
     * otherwise returns false.
     */
    public boolean isMajor(Date otherDate) {
        int totalDays = accountDays();
        int totalDays1 = otherDate.accountDays();

        return totalDays > totalDays1;
    }

    /**
     * Returns true if the date is greater or equals than the date received by parameter. if
     * the date is less than or equal to the date received by parameter, returns false.
     *
     * @param otherDate to another date to compare the date to.
     * @return true if the date is greater or equals than the date received by parameter,
     * otherwise returns false.
     */
    public boolean isMajorOrEquals(Date otherDate) {
        int totalDays = accountDays();
        int totalDays1 = otherDate.accountDays();

        return totalDays >= totalDays1;
    }

    /**
     * Returns the difference in number of days between the date and the date received by
     * parameter.
     *
     * @param otherDate to another date to compare the date to calculate
     *                  the difference in the number of days.
     * @return difference in number of days between the date and the date received by
     * parameter.
     */
    public int difference(Date otherDate) {
        int totalDays = accountDays();
        int totalDays1 = otherDate.accountDays();

        return Math.abs(totalDays - totalDays1);
    }

    /**
     * Returns the difference in number of days between the date and the date received by
     * parameter with year, month and day.
     *
     * @param year  the year of the date to compare the date to calculate the
     *              difference in number of days.
     * @param month the month of the date to which the date is compared to calculate the
     *              difference in number of days.
     * @param day   the day of the date against which the date is compared to calculate the
     *              difference in number of days.
     * @return difference in number of days between the date and the date received by
     * parameter with year, month and day.
     */
    public int difference(int year, int month, int day) {
        int totalDays = accountDays();
        Date otherDate = new Date(year, month, day);
        int totalDays1 = otherDate.accountDays();

        return Math.abs(totalDays - totalDays1);
    }

    /**
     * Returns true if the year passed by parameter is a leap year. if the year
     * passed by parameter is not leap, returns false.
     *
     * @param year the year to validate.
     * @return true if the year passed by parameter is a leap year, otherwise
     * returns false.
     */
    public static boolean isLeapYear(int year) {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }

    /**
     * Returns the current system date.
     *
     * @return the current system date.
     */
    public static Date currentDate() {
        Calendar today = Calendar.getInstance();
        int year = today.get(Calendar.YEAR);
        int month = today.get(Calendar.MONTH) + 1;    // January is represented by 0.
        int day = today.get(Calendar.DAY_OF_MONTH);
        return new Date(year, month, day);
    }

    /**
     * Returns the number of days from 1/1/1 to date.
     *
     * @return number of days from 1/1/1 to date.
     */
    private int accountDays() {
        int totalDays = 0;

        for (int i = 1; i < year; i++) {
            totalDays += isLeapYear(i) ? 366 : 365;
        }
        for (int i = 1; i < month.ordinal() + 1; i++) {
            totalDays += Months.getMonths(i).numberOfDays(year);
        }
        totalDays += day;

        return totalDays;
    }

    /**
     * Modifies the year, the month, and the day in the date.
     *
     * @param year  the new year in the date.
     * @param month the new month in the date.
     * @param day   the new day in the date.
     */
    public final void setData(int year, int month, int day) {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Month " + month + " is invalid!!");
        }
        if (day < 1 || day > Months.getMonths(month).numberOfDays(year)) {
            throw new IllegalArgumentException("Day " + year + "/" + month + "/" + day + " is invalid!!");
        }
        this.year = year;
        this.month = Months.getMonths(month);
        this.day = day;
    }

    /**
     * This function validates the date in its format.
     *
     * @param stringDate holds a date in a String format.
     * @return boolean value.
     */
    public static boolean validateDateFormat(String stringDate) {
        boolean state = false;

        String[] stringArrayDate = stringDate.split("/");

        try {
            if (stringArrayDate.length == 3) {
                state = true;
            }
        } catch (RuntimeException ex) {
            System.out.println("Invalid date!");
        }
        return state;
    }

    /**
     * This function validates if the date is greater or equal to current date.
     *
     * @param stringDate holds a date in a String format.
     * @return boolean value.
     */
    public static boolean validateGreaterThanEqual(String stringDate) {
        boolean state = false;

        String[] stringArrayDate = stringDate.split("/");
        int[] integerArrayDate = {0, 0, 0};

        try {
            if (validateDateFormat(stringDate)) {
                for (int i = 0; i < stringArrayDate.length; i++) {
                    integerArrayDate[i] = Integer.parseInt(stringArrayDate[i]);
                }

                int day = integerArrayDate[0];
                int month = integerArrayDate[1];
                int year = integerArrayDate[2];

                Date date = new Date(year, month, day);

                if (date.isMajor(currentDate()) || date.equals(currentDate())) {
                    state = true;
                }
            }
        } catch (RuntimeException ex) {
            System.out.println("The date cannot be less than the current day!");
        }

        return state;
    }

    /**
     * This fuction validates the date in its meaning.
     *
     * @param stringDate holds a date in a String format.
     * @return boolean value.
     */
    public static boolean validateDateMeaning(String stringDate) {
        boolean state = false;

        String[] stringArrayDate = stringDate.split("/");
        int[] integerArrayDate = {0, 0, 0};

        try {
            if (validateDateFormat(stringDate)) {
                for (int i = 0; i < stringArrayDate.length; i++) {
                    integerArrayDate[i] = Integer.parseInt(stringArrayDate[i]);
                }

                int day = integerArrayDate[0];
                int month = integerArrayDate[1];
                int year = integerArrayDate[2];

                if (1 <= month && month <= 12) {
                    if (1 <= day && day <= Months.getMonths(month).numberOfDays(year)) {
                        if (year >= 1) {
                            state = true;
                        }
                    }
                }
            }

        } catch (RuntimeException ex) {
            System.out.println("Invalid date!");
        }

        return state;
    }

    /**
     * This function converts a date in String format to a date in Date format.
     *
     * @param stringDate holds a date in String format.
     * @return a date in Date format.
     */
    public static Date convertStringToDate(String stringDate) {
        String[] stringArrayDate = stringDate.split("/");
        int[] integerArrayDate = {0, 0, 0};

        Date date = null;

        if (validateDateFormat(stringDate)) {
            for (int i = 0; i < stringArrayDate.length; i++) {
                integerArrayDate[i] = Integer.parseInt(stringArrayDate[i]);
            }

            if (validateDateMeaning(stringDate)) {
                date = new Date(integerArrayDate[2], integerArrayDate[1], integerArrayDate[0]);
            }
        }
        return date;
    }

    /**
     * Function to calculate age by date of birth.
     *
     * @param birthdate the birthdate.
     * @return the age.
     */
    public static int currentAge(Date birthdate) {
        LocalDate currentDate = LocalDate.now();

        int year = birthdate.year;
        int month = birthdate.month.ordinal() + 1;
        int day = birthdate.day;
        LocalDate birthday = LocalDate.of(year, month, day);

        return Period.between(birthday, currentDate).getYears();
    }

    /**
     * Function to calculate years between two dates.
     *
     * @param dateOne the initial date.
     * @param dateTwo the second date.
     * @return the age or -1 if the dates are passed in an invalid state (dateOne higher than dateTwo).
     */
    public static int yearsBetweenTwoDates(Date dateOne, Date dateTwo) {

        if (dateOne.compareTo(dateTwo) > 0) {
            return -1;
        }

        LocalDate currentDate = LocalDate.now();

        int year = dateOne.year;
        int month = dateOne.month.ordinal() + 1;
        int day = dateOne.day;
        LocalDate firstDate = LocalDate.of(year, month, day);

        year = dateTwo.year;
        month = dateTwo.month.ordinal() + 1;
        day = dateTwo.day;
        LocalDate secondDate = LocalDate.of(year, month, day);

        return Period.between(firstDate, secondDate).getYears();
    }

    public void addOneDay() {
        Calendar date = Calendar.getInstance();
        date.set(year, month.ordinal(), day);

        date.add(Calendar.DATE, 1);

        year = date.get(Calendar.YEAR);
        month = Months.getMonths(date.get((Calendar.MONTH)) + 1);
        day = date.get(Calendar.DAY_OF_MONTH);
    }
}
