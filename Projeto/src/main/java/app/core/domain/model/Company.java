package app.core.domain.model;

import app.core.controller.App;
import app.core.domain.algorithms.SortDataAlgorithm;
import app.core.domain.store.*;

import pt.isep.lei.esoft.auth.AuthFacade;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Timer;

/**
 * @author Paulo Maio pam@isep.ipp.pt
 */
public class Company {

    private String designation;
    private Time autoReportTime;
    private AuthFacade authFacade;

    private VaccinationCenterStore vaccinationCenterStore = new VaccinationCenterStore();
    private VaccineTypeStore vaccineTypeStore = new VaccineTypeStore();
    private VaccineStore vaccineStore = new VaccineStore();
    private EmployeeStore employeeStore = new EmployeeStore();
    private EmployeeRoleStore employeeRoleStore = new EmployeeRoleStore();
    private SNSUserStore snsUserStore = new SNSUserStore();
    private ScheduleVaccineStore scheduleVaccineStore = new ScheduleVaccineStore();
    private ArrivalSNSUserStore arrivalSNSUserStore = new ArrivalSNSUserStore();
    private VaccineAdministrationStore vaccineAdministrationStore = new VaccineAdministrationStore();
    private UserStore userStore = new UserStore();
    private LegacySystemFileStore legacySystemFileStore = new LegacySystemFileStore();
    private SortDataAlgorithm sortDataAlgorithm;

    public Company(String designation) {
        checkDesignation(designation);

        this.designation = designation;
        this.authFacade = new AuthFacade();
    }

    public Company(String designation, Time autoReportTime) {
        checkDesignation(designation);
        checkAutoReportTime(autoReportTime);

        this.designation = designation;
        this.authFacade = new AuthFacade();
        this.autoReportTime = autoReportTime;

        //Record daily the total number of people vaccinated in each vaccination center
        fullVaccinatedInCsv();
    }

    private void checkDesignation(String designation) {
        if (StringUtils.isBlank(designation)) {
            throw new IllegalArgumentException("Designation cannot be blank.");
        }
    }

    private void checkAutoReportTime(Time autoReportTime) {
        if (autoReportTime == null) {
            throw new IllegalArgumentException("The auto report time must be valid");
        }
    }

    public String getDesignation() {
        return designation;
    }

    public AuthFacade getAuthFacade() {
        return authFacade;
    }

    public VaccinationCenterStore getVaccinationCenterStore() {
        return vaccinationCenterStore;
    }

    public VaccineTypeStore getVaccineTypeStore() {
        return vaccineTypeStore;
    }

    public VaccineStore getVaccineStore() {
        return vaccineStore;
    }

    public EmployeeStore getEmployeeStore() {
        return this.employeeStore;
    }

    public EmployeeRoleStore getEmployeeRolesStore() {
        return this.employeeRoleStore;
    }

    public SNSUserStore getSNSUserStore() {
        return snsUserStore;
    }

    public ScheduleVaccineStore getScheduleVaccineStore() {
        return scheduleVaccineStore;
    }

    public ArrivalSNSUserStore getArrivalSNSUserStore() {
        return arrivalSNSUserStore;
    }

    public VaccineAdministrationStore getVaccineAdministrationStore() {
        return vaccineAdministrationStore;
    }

    public UserStore getUserStore() {
        return userStore;
    }

    public void fullVaccinatedInCsv() {
        Timer timer = new Timer();
        timer.schedule(new ExportFullVaccinatedCSVTask(), this.autoReportTime.toMilliseconds(), Time.oneDayMilliseconds());
    }

    public LegacySystemFileStore getLegacySystemFileStore() {
        return legacySystemFileStore;
    }

    public SortDataAlgorithm getSortDataAlgorithm(String sortCriteria) throws ClassNotFoundException,
            NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        App app = App.getInstance();
        String sortingClass = app.getPropertySortingAlgorithm();
        // Getting class name to instantiate
        Class<?> oClass = Class.forName(sortingClass);
        Constructor<?> cons = oClass.getConstructor(String.class);
        return (SortDataAlgorithm) cons.newInstance(sortCriteria);
    }
}
