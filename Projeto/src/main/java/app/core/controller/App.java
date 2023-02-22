package app.core.controller;

import app.core.domain.model.Company;
import app.core.domain.model.Employee;
import app.core.domain.model.Time;
import app.core.domain.shared.Constants;
import pt.isep.lei.esoft.auth.AuthFacade;
import pt.isep.lei.esoft.auth.UserSession;
import pt.isep.lei.esoft.auth.domain.model.Email;
import pt.isep.lei.esoft.auth.domain.model.Password;
import pt.isep.lei.esoft.auth.domain.model.User;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * @author Paulo Maio pam@isep.ipp.pt
 */
public class App {

    private Company company;
    private AuthFacade authFacade;

    private App() {
        this.company = new Company(getPropertyCompanyDesignation(), getPropertyAutoReportTime());
        this.authFacade = this.company.getAuthFacade();
        bootstrap();
    }

    public Company getCompany() {
        return this.company;
    }

    public AuthFacade getAuthFacade() {
        return authFacade;
    }

    public UserSession getCurrentUserSession() {
        return this.authFacade.getCurrentUserSession();
    }

    public boolean doLogin(String email, String pwd) {
        return this.authFacade.doLogin(email, pwd).isLoggedIn();
    }

    public void doLogout() {
        this.authFacade.doLogout();
    }

    private Properties getProperties() {
        Properties props = new Properties();

        // Add default properties and values
        props.setProperty(Constants.PARAMS_COMPANY_DESIGNATION, "DGS/SNS");

        props.setProperty(Constants.PARAMS_RECOVERY_PERIOD, "30");
        props.setProperty(Constants.PARAMS_AUTO_REPORT_TIME, "10:30");

        // Adds the sorting algorithm to be used
        props.setProperty(Constants.PARAMS_SORTING_ALGORITHM, "app.core.domain.algorithms.QuickSortAlgorithm");

        // Read configured values
        try {
            InputStream in = new FileInputStream(Constants.PARAMS_FILENAME);
            props.load(in);
            in.close();
        } catch (IOException ex) {

        }
        return props;
    }

    public String getPropertyCompanyDesignation() {
        Properties props = getProperties();
        return props.getProperty(Constants.PARAMS_COMPANY_DESIGNATION);
    }

    public String getPropertyRecoveryPeriod() {
        Properties props = getProperties();
        return props.getProperty(Constants.PARAMS_RECOVERY_PERIOD);
    }

    public Time getPropertyAutoReportTime() {
        Properties props = getProperties();

        String time = props.getProperty(Constants.PARAMS_AUTO_REPORT_TIME);
        String[] timeSplit = time.split(":");

        if (timeSplit.length == 2) {

            try {
                int hours = Integer.parseInt(timeSplit[0]);
                int minutes = Integer.parseInt(timeSplit[1]);
                return new Time(hours, minutes);
            } catch (NumberFormatException e) {
                return null;
            }

        } else {
            return null;
        }
    }

    public String getPropertySortingAlgorithm() {
        Properties props = getProperties();
        return props.getProperty(Constants.PARAMS_SORTING_ALGORITHM);
    }

    private void bootstrap() {
        this.authFacade.addUserRole(Constants.ROLE_ADMIN, Constants.ROLE_ADMIN);
        this.authFacade.addUserRole(Constants.ROLE_RECEPTIONIST, Constants.ROLE_RECEPTIONIST);
        this.authFacade.addUserRole(Constants.ROLE_NURSE, Constants.ROLE_NURSE);
        this.authFacade.addUserRole(Constants.ROLE_CENTER_COORDINATOR, Constants.ROLE_CENTER_COORDINATOR);

        this.authFacade.addUserWithRole("Main Administrator", "admin@lei.sem2.pt", "123456", Constants.ROLE_ADMIN);
        this.authFacade.addUserWithRole("Main Receptionist", "receptionist@lei.sem2.pt", "123456", Constants.ROLE_RECEPTIONIST);
        this.authFacade.addUserWithRole("Main Nurse", "nurse@lei.sem2.pt", "123456", Constants.ROLE_NURSE);

        this.bootstrapAllSavedUsers();
    }

    public void bootstrapAllSavedUsers() {
        List<String> users = this.company.getUserStore().getUsersList();
        if (users.size() != 0) {
            for (String user : users) {
                List<String> userData = this.company.getUserStore().getUserDataInList(user);
                this.authFacade.addUserWithRole(userData.get(0), userData.get(1), userData.get(2), userData.get(3));
                Employee employee = this.company.getEmployeeStore().findEmployeeByEmail(userData.get(1));
                if (employee != null) {
                    employee.setUser(new User(new Email(userData.get(1)), new Password(userData.get(2)), userData.get(0)));
                }
            }
        }
    }

    // Extracted from https://www.javaworld.com/article/2073352/core-java/core-java-simply-singleton.html?page=2
    private static App singleton = null;

    public static App getInstance() {
        if (singleton == null) {
            synchronized (App.class) {
                singleton = new App();
            }
        }
        return singleton;
    }

}
