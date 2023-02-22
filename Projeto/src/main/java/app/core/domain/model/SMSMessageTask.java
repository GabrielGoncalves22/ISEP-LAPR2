package app.core.domain.model;

import java.util.TimerTask;

/**
 * Represents the class related to the task of sending a message.
 */
public class SMSMessageTask extends TimerTask {
    /**
     * The phoneNumber.
     */
    private String phoneNumber;

    /**
     * The message to send.
     */
    private String message;

    /**
     * Builds an instance of SMSMessageTask receiving the phoneNumber and message.
     *
     * @param phoneNumber the phoneNumber.
     * @param message     the message to send.
     */
    public SMSMessageTask(String phoneNumber, String message) {
        this.phoneNumber = phoneNumber;
        this.message = message;
    }

    /**
     * SMSMessageTask class run method
     */
    @Override
    public void run() {
        new FileSMSMessage().sendSMSMessage(phoneNumber, message);
    }
}
