package app.core.mapper;

import app.core.domain.model.*;
import app.core.dto.VaccineAdministrationDTO;

/**
 * Represents the vaccine administration mapper.
 *
 * @author Group 40
 */
public class VaccineAdministrationMapper {

    /**
     * Method to convert an VaccineAdministration DTO into the VaccineAdministration model.
     *
     * @param avDTO the vaccine administration DTO.
     * @return the vaccine administration.
     */
    public static VaccineAdministration toModel(VaccineAdministrationDTO avDTO) {
        SNSUser snsUser = SNSUserMapper.toModel(avDTO.getSnsUserDTO());
        Vaccine vaccine = VaccineMapper.toModel(avDTO.getVaccineDTO());
        Dose dose = DoseMapper.toModel(avDTO.getDoseDTO());
        Date administrationDate = avDTO.getAdministrationDate();
        Time administrationTime = avDTO.getAdministrationTime();
        Date leavingDate = avDTO.getLeavingDate();
        Time leavingTime = avDTO.getLeavingTime();
        VaccinationCenter vaccinationCenter = avDTO.getVaccinationCenter();
        String lotNumber = avDTO.getLotNumber();

        return new VaccineAdministration(snsUser, vaccine, dose, administrationDate, administrationTime,
                leavingDate, leavingTime, vaccinationCenter, lotNumber);
    }
}

