package app.core.controller;

import app.core.domain.model.Company;
import app.core.domain.model.Date;
import app.core.domain.model.SNSUser;
import app.core.domain.store.SNSUserStore;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.List;

/**
 * Represents the SNS User controller.
 *
 * @author Group 40
 */
public class RegisterSNSUserController {
    /**
     * The company.
     */
    private Company company;

    /**
     * The SNS User.
     */
    private SNSUser snsUser;

    /**
     * The SNS User store.
     */
    private SNSUserStore store;

    /**
     * Builds an instance of RegisterSNSUserController.
     */
    public RegisterSNSUserController() {
        this(App.getInstance().getCompany());
    }

    /**
     * Builds an instance of RegisterSNSUserController receiving the company.
     *
     * @param company the company.
     */
    public RegisterSNSUserController(Company company) {
        this.company = company;
        this.snsUser = null;
        this.store = this.company.getSNSUserStore();
    }

    /**
     * Function to return a new instance of SNSUser from the store.
     *
     * @param name              the name of the SNS User.
     * @param address           the address of the SNS User.
     * @param sex               the sex of SNS User.
     * @param phoneNumber       the phoneNumber of SNS User.
     * @param email             the email of the SNS User.
     * @param birthdate         the birth-date of the SNS User.
     * @param snsUserNumber     the SNS User number of SNS User.
     * @param citizenCardNumber the citizen card number of SNS User.
     * @return the SNS User.
     */
    public boolean createSNSUser(String name, String address, String sex, String phoneNumber, Email email,
                                 Date birthdate, String snsUserNumber, String citizenCardNumber) {
        this.snsUser = this.store.createSNSUser(name, address, sex, phoneNumber, email, birthdate, snsUserNumber, citizenCardNumber);
        return this.store.validateSNSUser(snsUser);
    }

    /**
     * Function to store a SNSUser object from the store.
     *
     * @return true if the object is successfully saved. Otherwise, returns false.
     */
    public boolean saveSNSUser() {
        this.store.loadSNSUsersListFromFile();
        if (this.store.saveSNSUser(snsUser)) {
            if (this.store.saveSNSUserListInFile()) {
                return true;
            } else {
                this.store.removeSNSUser(snsUser);
            }
        }
        return false;
    }

    /**
     * Returns the SNS User.
     *
     * @return the SNS User.
     */
    public SNSUser getSNSUser() {
        return snsUser;
    }
    
}
