package domain.shared;

import app.core.domain.model.*;
import app.core.domain.shared.VaccineTypeTechnology;
import app.core.dto.*;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents some common and useful constants for the various tests.
 */
public interface CommonConstants {
    Time OPENING_HOURS = new Time(9, 0);
    Time CLOSING_HOURS = new Time(18, 0);
    VaccinationCenter VACCINATION_CENTER = new VaccinationCenter("Ramalde Lda", "R. Eng. Ferreira Dias 874, 4100-246 Porto", 123456789, new Email("jumpjump@ramaldejump.pt"), 123456789, "https://www.jumpers.pt/pt/", OPENING_HOURS, CLOSING_HOURS, 5, 10);

    SNSUser SNS_USER1 = new SNSUser("André Silva", "Rua da Fruta Nº30", "Male", "967654321",
            new Email("Silva@Gmail.com"), new Date(2008, 12, 12), "187178348", "165044578");

    SNSUser SNS_USER2 = new SNSUser("Carlos Pereira", "Rua do Melão Nº23", "Male", "912345651",
            new Email("Pereira@gmail.com"), new Date(1980, 4, 1), "150315910", "125529392");

    SNSUser SNS_USER3 = new SNSUser("Maria Santos", "Rua do Morango Nº12", "Female", "912315651",
            new Email("Santos@gmail.com"), new Date(1999, 4, 13), "102488959", "175492263");

    SNSUser SNS_USER_SHORT1 = new SNSUser("André Silva", "Male",
            "967654321", new Date(2008, 12, 12), "187178348");

    SNSUser SNS_USER_SHORT2 = new SNSUser("Carlos Pereira", "Male",
            "912345651", new Date(1980, 4, 1), "150315910");

    ArrivalSNSUser ARRIVAL_SNS_USER1 = new ArrivalSNSUser(SNS_USER1, new Date(2022, 12, 12),
            new Time(10, 25, 10), VACCINATION_CENTER);
    ArrivalSNSUser ARRIVAL_SNS_USER2 = new ArrivalSNSUser(SNS_USER2, new Date(2022, 12, 12),
            new Time(12, 30, 15), VACCINATION_CENTER);
    ArrivalSNSUser ARRIVAL_SNS_USER3 = new ArrivalSNSUser(SNS_USER3, new Date(2022, 12, 12),
            new Time(14, 45, 20), VACCINATION_CENTER);

    VaccineType VACCINE_TYPE = new VaccineType("ABCDE", "Covid-19", VaccineTypeTechnology.VIRAL_VECTOR.toString());

    ScheduleVaccine SCHEDULE_VACCINE1 = new ScheduleVaccine(SNS_USER1, VACCINATION_CENTER, Date.currentDate(), Time.currentTime(), VACCINE_TYPE);

    Time OPENING_HOURS_1 = new Time(8, 0, 0);
    Time CLOSING_HOURS_1 = new Time(18, 0, 0);
    Time OPENING_HOURS_2 = new Time(9, 30, 0);
    Time CLOSING_HOURS_2 = new Time(20, 0, 0);
    Email EMAIL_1 = new Email("AAA@gmail.com");
    Email EMAIL_2 = new Email("BBB@gmail.com");
    Email EMAIL_3 = new Email("CCC@gmail.com");
    Email EMAIL_4 = new Email("DDD@gmail.com");
    Date BIRTH_DATE_1 = new Date(2003, 5, 20);
    Date BIRTH_DATE_2 = new Date(2000, 7, 16);
    SNSUser SNS_USER_1 = new SNSUser("AAAAAAAA", "Ali", "male", "987654321", EMAIL_1, BIRTH_DATE_1, "123456789", "111222333");
    SNSUser SNS_USER_2 = new SNSUser("BBBBBBBBB", "Acolá", "female", "999888777", EMAIL_2, BIRTH_DATE_2, "123789456", "444555666");
    VaccinationCenter VACCINATION_CENTER_1 = new VaccinationCenter("CCCCCCCCC", "não sei :(", 666555666, EMAIL_3, 678542465, "www.CCC.com", OPENING_HOURS_1, CLOSING_HOURS_1, 20, 50);
    VaccinationCenter VACCINATION_CENTER_2 = new VaccinationCenter("DDDDDDDD", "continuo a não seber", 676454554, EMAIL_4, 876595434, "www.DDDD.com", OPENING_HOURS_2, CLOSING_HOURS_2, 15, 30);
    Date DATE_1 = new Date(2022, 10, 13);
    Date DATE_2 = new Date(2022, 7, 23);
    Time TIME_1 = new Time(10, 30, 0);
    Time TIME_2 = new Time(15, 0, 0);
    Time TIME_3 = new Time(23, 0, 0);
    VaccineType VACCINE_TYPE_1 = new VaccineType("12345", "very good", "da boa");
    VaccineType VACCINE_TYPE_2 = new VaccineType("09876", "not so very good", "já fostes");
    ScheduleVaccine SCHEDULE_VACCINE_1 = new ScheduleVaccine(SNS_USER_1, VACCINATION_CENTER_1, DATE_1, TIME_1, VACCINE_TYPE_1);
    ScheduleVaccine SCHEDULE_VACCINE_2 = new ScheduleVaccine(SNS_USER_2, VACCINATION_CENTER_2, DATE_2, TIME_2, VACCINE_TYPE_2);
    ScheduleVaccine SCHEDULE_VACCINE_3 = new ScheduleVaccine(SNS_USER_2, VACCINATION_CENTER_2, DATE_2, TIME_2, VACCINE_TYPE_2);

    List<AgeGroup> AGLIST = new ArrayList<>();
    Vaccine VACCINE1 = new Vaccine(1, "Pfizer", "Pfizer, Inc.", VACCINE_TYPE, AGLIST);

    Dose DOSE1 = new Dose(1, 30, 0);

    Date ADMINISTRATION_DATE1 = new Date(2022, 6, 15);
    Date LEAVING_DATE1 = new Date(2022, 6, 15);

    Time ADMINISTRATION_TIME1 = new Time(15, 10, 20);
    Time LEAVING_TIME1 = new Time(15, 0, 20);

    Time ADMINISTRATION_TIME2 = new Time(15, 10, 20);
    Time LEAVING_TIME2 = new Time(15, 40, 20);

    SNSUserDTO SNS_USER_DTO1 = new SNSUserDTO("André Silva", "Rua da Fruta Nº30","Male", "967654321",
            new Email("Silva@Gmail.com"), new Date(2008, 12, 12), "187178348", "165044578");

    VaccineTypeDTO VACCINE_TYPE_DTO1 = new VaccineTypeDTO("ABCDE", "Covid-19", VaccineTypeTechnology.VIRAL_VECTOR.toString());

    VaccineDTO VACCINE_DTO1 = new VaccineDTO(1, "Pfizer", "Pfizer, Inc.", VACCINE_TYPE_DTO1, AGLIST);

    DoseDTO DOSE_DTO1 = new DoseDTO(1, 30, 0);

    VaccinationCenterDTO VACCINATION_CENTER_DTO1 = new VaccinationCenterDTO(1, "Ramalde Lda", "R. Eng. Ferreira Dias 874, 4100-246 Porto", 123456789, new Email("jumpjump@ramaldejump.pt"), 123456789, "https://www.jumpers.pt/pt/", OPENING_HOURS, CLOSING_HOURS, 5, 10);
}
