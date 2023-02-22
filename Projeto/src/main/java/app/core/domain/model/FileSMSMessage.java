package app.core.domain.model;

import app.core.domain.shared.FileSMSMessageHandler;

import java.io.FileWriter;
import java.io.IOException;

/**
 * The type File sms message.
 */
public class FileSMSMessage implements FileSMSMessageHandler {

    /**
     * This function writes an SMS message with scheduled vaccine date, time, and vaccination center address.
     *
     * @param phoneNumber the phoneNumber.
     * @param message     the message to send.
     */
    public boolean sendSMSMessage(String phoneNumber, String message) {
        try {
            FileWriter myWriter = new FileWriter("src/messages/SMS.txt", true);
            myWriter.write("Phone Number: " + phoneNumber + "\nMessage: " + message + "\n\n");
            myWriter.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
