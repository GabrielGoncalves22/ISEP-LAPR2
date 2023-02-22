package app.core.mapper;

import app.core.domain.model.*;
import app.core.dto.ArrivalSNSUserDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the SNS user arrival mapper.
 *
 * @author Group 40
 */
public class ArrivalSNSUserMapper {

    /**
     * Method to convert an ArrivalSNSUser DTO into the ArrivalSNSUser model.
     *
     * @param auDTO the SNS user arrival DTO.
     * @return the SNS user arrival.
     */
    public static ArrivalSNSUser toModel(ArrivalSNSUserDTO auDTO) {
        SNSUser snsUser = auDTO.getSnsUser();
        Date arrivalDate = auDTO.getArrivalDate();
        Time arrivalTime = auDTO.getArrivalTime();
        VaccinationCenter vaccinationCenter = auDTO.getVaccinationCenter();

        return new ArrivalSNSUser(snsUser, arrivalDate, arrivalTime, vaccinationCenter);
    }

    /**
     * Method to convert a list of sns user arrivals to a list of sns user dto with the necessary information.
     *
     * @param listAU the list arrival sns user.
     * @return the list arrival sns user DTO.
     */
    public static List<ArrivalSNSUserDTO> toListDTO(List<ArrivalSNSUser> listAU) {
        List<ArrivalSNSUserDTO> listAUDto = new ArrayList<>();

        for (int i = 0; i < listAU.size(); i++) {
            ArrivalSNSUser au = listAU.get(i);

            ArrivalSNSUserDTO auDto = toDTO(au);
            listAUDto.add(auDto);
        }

        return listAUDto;
    }

    /**
     * Method to convert an ArrivalSNSUser model into the ArrivalSNSUser DTO.
     *
     * @param au the SNS user arrival.
     * @return the SNS user arrival DTO.
     */
    public static ArrivalSNSUserDTO toDTO(ArrivalSNSUser au) {
        String name = au.getSnsUser().getName();
        String sex = au.getSnsUser().getSex();
        Date birthdate = au.getSnsUser().getBirthdate();
        String phoneNumber = au.getSnsUser().getPhoneNumber();
        String snsUserNumber = au.getSnsUser().getSnsUserNumber();

        SNSUser su = new SNSUser(name, sex, phoneNumber, birthdate, snsUserNumber);
        return new ArrivalSNSUserDTO(su);
    }
}
