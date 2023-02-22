package app.core.domain.model;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Represents a time through hours, minutes and seconds.
 *
 * @author Group 40
 */
public class Time implements Comparable<Time>, Serializable {

    /**
     * The hours of time.
     */
    private int hours;

    /**
     * The minutes of time.
     */
    private int minutes;

    /**
     * The seconds of time.
     */
    private int seconds;

    /**
     * The seconds default value.
     */
    private final int SECONDS_DEFAULT = 0;

    /**
     * Builds a Time instance receiving the hours, minutes and seconds.
     *
     * @param hours   the hours.
     * @param minutes the minutes.
     * @param seconds the seconds.
     */
    public Time(int hours, int minutes, int seconds) {
        checkHours(hours);
        checkMinutes(minutes);
        checkSeconds(seconds);

        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    /**
     * Builds a Time instance receiving the hours and minutes.
     *
     * @param hours   the hours
     * @param minutes the minutes
     */
    public Time(int hours, int minutes) {
        checkHours(hours);
        checkMinutes(minutes);


        this.hours = hours;
        this.minutes = minutes;
        this.seconds = SECONDS_DEFAULT;
    }

    /**
     * Builds a Time instance receiving the hours and minutes.
     *
     * @param otherTime the outher time
     */
    public Time(Time otherTime) {
        this.hours = otherTime.hours;
        this.minutes = otherTime.minutes;
        this.seconds = otherTime.seconds;
    }

    /**
     * Returns the textual description of the time in the format: %02d:%02d:%02d AM/PM.
     *
     * @return time characteristics.
     */
    @Override
    public String toString() {
        return String.format("%02d:%02d %s",
                (hours == 12 || hours == 0) ? 12 : hours % 12,
                minutes, hours < 12 ? "AM" : "PM");
    }


    /**
     * Returns the time in the format: %02d%02d%02d.
     *
     * @return time characteristics.
     */
    public String toStringHHMMSS() {
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    /**
     * Returns the time in the format: hh:mm.
     *
     * @return time characteristics.
     */
    public String toStringHHMM() {
        return String.format("%2d:%02d", hours, minutes);
    }

    /**
     * Method for checking the rules of the hours.
     *
     * @param hours the hours of time.
     */
    private void checkHours(int hours) {
        if (hours < 0 || hours > 24) {

            throw new IllegalArgumentException("Value is only accepted if it is between 0 and 24.");
        }
    }

    /**
     * Method for checking the rules of the minutes.
     *
     * @param minutes the minutes of time.
     */
    private void checkMinutes(int minutes) {
        if (minutes < 0 || minutes > 60) {

            throw new IllegalArgumentException("Value is only accepted if it is between 0 and 60.");
        }
    }

    /**
     * Method for checking the rules of the seconds.
     *
     * @param seconds the seconds of time.
     */
    private void checkSeconds(int seconds) {
        if (seconds < 0 || seconds > 60) {

            throw new IllegalArgumentException("Value is only accepted if it is between 0 and 60.");
        }
    }

    /**
     * Returns the current system time.
     *
     * @return the current system time.
     */
    public static Time currentTime() {
        Calendar now = Calendar.getInstance();
        int hour = now.get(Calendar.HOUR_OF_DAY);
        int minute = now.get(Calendar.MINUTE);
        int second = now.get(Calendar.SECOND);

        return new Time(hour, minute, second);
    }

    /**
     * Returns true if the time is greater than the time received by parameter.
     * If the time is less than or equal to the time received by parameter, it returns
     * false.
     *
     * @param otherTime the other time against which the time is compared.
     * @return true if the time is greater than the time received by parameter,
     * otherwise returns false.
     */
    public boolean isBigger(Time otherTime) {
        return toSeconds() > otherTime.toSeconds();
    }


    /**
     * Returns true if the time is greater or equals than the time received by parameter.
     * If the time is less than to the time received by parameter, it returns
     * false.
     *
     * @param otherTime the other time against which the time is compared.
     * @return true if the time is greater or equals than the time received by parameter,
     * otherwise returns false.
     */
    public boolean isBiggerOrEquals(Time otherTime) {
        return toSeconds() >= otherTime.toSeconds();
    }

    /**
     * Increases time by one second.
     */
    public void tick() {
        seconds = ++seconds % 60;
        if (seconds == 0) {
            minutes = ++minutes % 60;
            if (minutes == 0) {
                hours = ++hours % 24;
            }
        }
    }

    /**
     * Increases time by minutes.
     *
     * @param minutes the minutes
     * @return the time.
     */
    public Time addMinutes(int minutes) {
        for (int i = 0; i < (minutes * 60); i++) {
            tick();
        }

        return new Time(this.hours, this.minutes, this.seconds);
    }

    /**
     * Returns the number of seconds corresponding to the time.
     *
     * @return number of seconds corresponding to time.
     */
    private int toSeconds() {
        return hours * 3600 + minutes * 60 + seconds;
    }

    /**
     * Returns the number of milliseconds corresponding to the time.
     *
     * @return number of milliseconds corresponding to time.
     */
    public long toMilliseconds() {
        return toSeconds() * 1000;
    }

    /**
     * Returns the number of minutes corresponding to the time.
     *
     * @return number of minutes corresponding to time.
     */
    private int toMinute() {
        return hours * 60 + minutes;
    }

    /**
     * Compares the time with the object received.
     *
     * @param otherObject the object to be compared with the time.
     * @return true if the object received represents another time equivalent to the time.
     * Otherwise, returns false.
     */
    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }

        if (otherObject == null || this.getClass() != otherObject.getClass()) {
            return false;
        }

        Time otherTime = (Time) otherObject;
        return (this.hours == otherTime.hours)
                && (this.minutes == otherTime.minutes)
                && (this.seconds == otherTime.seconds);
    }

    /**
     * Compares the time with the other time received by parameter.
     *
     * @param otherTime the time to be compared.
     * @return the value 0 if the otherTime received is equal to the time; the value -1
     * if the otherTime is later than the time; the value 1 if the otherTime
     * is before time.
     */
    @Override
    public int compareTo(Time otherTime) {
        return (otherTime.isBigger(this)) ? -1 : (isBigger(otherTime)) ? 1 : 0;
    }

    /**
     * Calculate time difference in minutes
     *
     * @param time1 the time to be calculate.
     * @param time2 the time to be calculate.
     * @return difference between the 2 times.
     */
    public static int calculateTimeDifferenceMinute(Time time1, Time time2) {
        int time1Minute = time1.toMinute();
        int time2Minute = time2.toMinute();
        int totalDifference = time2Minute - time1Minute;
        return totalDifference;
    }

    /**
     * Returns the number of milliseconds of given minutes
     *
     * @return the number of milliseconds.
     */
    public static int minutesToMilliseconds(int minutes) {
        return minutes * 60000;
    }

    /**
     * Returns the number of milliseconds in a day.
     *
     * @return the number of milliseconds in a day.
     */
    public static long oneDayMilliseconds() {
        return 24 * 60 * 60 * 1000;
    }
}
