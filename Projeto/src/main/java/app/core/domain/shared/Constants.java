package app.core.domain.shared;

/**
 * @author Paulo Maio pam@isep.ipp.pt
 */
public interface Constants {
    String ROLE_ADMIN = "ADMINISTRATOR";
    String ROLE_RECEPTIONIST = "RECEPTIONIST";
    String ROLE_NURSE = "NURSE";
    String ROLE_CENTER_COORDINATOR = "CENTER COORDINATOR";

    int DEFAULT_PASSWORD_LENGTH = 7;

    String PARAMS_FILENAME = "config.properties";
    String PARAMS_COMPANY_DESIGNATION = "Company.Designation";
    String PARAMS_RECOVERY_PERIOD = "Company.Recovery.Period";
    String PARAMS_AUTO_REPORT_TIME = "Company.Auto.Report.Time";
    String PARAMS_SORTING_ALGORITHM = "Company.SortDataAlgorithm.Class";
}
