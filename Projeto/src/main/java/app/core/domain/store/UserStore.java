package app.core.domain.store;

import app.core.controller.App;
import app.core.domain.shared.Constants;
import app.core.domain.shared.Helper;
import app.core.domain.shared.SerializeClasses;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the user's store.
 *
 * @author Group 40
 */
public class UserStore {

    /**
     * The list containing the users registered in the system.
     */
    private List<String> users;

    /**
     * The name of the file who has all the information to keep data persistence for users.
     */
    private final String USERS_FILE = "users.bin";


    /**
     * The separator used to separe different data about the user in a String.
     */
    private final String DATA_SEPARATOR = ";";

    /**
     * The constructor used to build a UserStore instance.
     */
    public UserStore() {
        this.users = this.loadUsers(this.USERS_FILE);
    }

    /**
     * Method used to get the list with all the users.
     *
     * @return
     */
    public List<String> getUsersList() {
        return this.users;
    }

    /**
     * Method to store a new user to the list with all the users.
     *
     * @param name            The user name.
     * @param email           The user email.
     * @param roleDesignation The user role designation.
     * @return The user password that is required to log in into the system.
     */
    public String addNewUser(String name, String email, String roleDesignation) {
        String password = Helper.generatePassword(Constants.DEFAULT_PASSWORD_LENGTH);
        String user = name + DATA_SEPARATOR + email + DATA_SEPARATOR + password + DATA_SEPARATOR + roleDesignation;
        this.users.add(user);
        this.createAuthentication(user);
        this.saveUsers(this.USERS_FILE);

        return password;
    }

    /**
     * Method to transform the user data stored in a String into a List.
     *
     * @param user The String with user information separated by a character.
     * @return A list containing all the user data.
     */
    public List<String> getUserDataInList(String user) {
        List<String> userData = new ArrayList<>();

        try {
            String[] userDataArray = user.split(DATA_SEPARATOR);
            userData.add(userDataArray[0]);
            userData.add(userDataArray[1]);
            userData.add(userDataArray[2]);
            userData.add(userDataArray[3]);
        } catch (IndexOutOfBoundsException ex) {
            //
        }

        return userData;

    }

    /**
     * Method to create the user authentication in the system.
     *
     * @param user The String with user information separated by a character.
     */
    private void createAuthentication(String user) {
        App app = App.getInstance();
        try {
            List<String> userData = this.getUserDataInList(user);
            app.getAuthFacade().addUserWithRole(userData.get(0), userData.get(1), userData.get(2), userData.get(3));
        } catch (IndexOutOfBoundsException ex) {
            //
        }
    }

    /**
     * Method to save all the users stored in the Users list into a file.
     *
     * @param fileName The name of the file where the data must be saved.
     */
    private void saveUsers(String fileName) {
        SerializeClasses.saveObjectIntoDisk(this.users, fileName);
    }

    /**
     * Method to load into memory the user's data stored in a file.
     *
     * @param fileName The name of the file where the data is saved.
     * @return A list with all the loaded user's data present in the file with the given name.
     */
    private List<String> loadUsers(String fileName) {
        return SerializeClasses.loadObjectIntoMemory(fileName);
    }

}
