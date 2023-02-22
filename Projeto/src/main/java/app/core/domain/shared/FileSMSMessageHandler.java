package app.core.domain.shared;

/**
 * Interface that represents the file handler to send SMS message.
 *
 * @author Group 40
 */
public interface FileSMSMessageHandler {

    /**
     * Create an SMS.txt file with the message passed as a parameter for simulating sending an SMS.
     *
     * @param phoneNumber the phoneNumber of SNS User.
     * @param message     the message to send.
     */
    boolean sendSMSMessage(String phoneNumber, String message);
}
