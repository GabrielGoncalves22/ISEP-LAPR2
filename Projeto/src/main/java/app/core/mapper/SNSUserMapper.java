package app.core.mapper;

import app.core.dto.SNSUserDTO;
import app.core.domain.model.Date;
import app.core.domain.model.SNSUser;
import app.core.domain.shared.exception.InvalidSNSUserInListException;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible to transform a SNSUserDTO into SNSUser and vice versa
 *
 * @author Group 40
 */
public class SNSUserMapper {

    /**
     * Method to convert a list of SNS Users into list of SNS Users DTO with the necessary information.
     *
     * @param sUList The SNS User list.
     * @return A SNS User DTO list.
     */
    public static List<SNSUserDTO> toListDTO(List<SNSUser> sUList) {

        List<SNSUserDTO> sUListDto = new ArrayList<>();

        for (int i = 0; i < sUList.size(); i++) {
            SNSUser sU = sUList.get(i);
            SNSUserDTO sUDto = toDto(sU);
            sUListDto.add(sUDto);
        }

        return sUListDto;

    }

    /**
     * Function to transform a list with SNSUserDto registers into a list with SNSUser registers
     *
     * @param lSUDto List with SNSUserDTO registers
     * @return A list with SNSUser model objects registers
     */
    public static List<SNSUser> toModelList(List<SNSUserDTO> lSUDto) {

        List<SNSUser> lSU = new ArrayList<SNSUser>();
        int posNumber = 0;
        SNSUserDTO dto;

        while (posNumber < lSUDto.size()) {
            dto = lSUDto.get(posNumber);
            String name = dto.getName();
            String address = dto.getAddress();
            String sex = dto.getSex();
            String phoneNumber = dto.getPhoneNumber();
            Email email = dto.getEmail();
            Date birthDate = dto.getBirthdate();
            String snsNumber = dto.getSnsUserNumber();
            String citizenCardNumber = dto.getCitizenCardNumber();

            try {
                lSU.add(SNSUserMapper.create(name, address, sex, phoneNumber, email, birthDate, snsNumber, citizenCardNumber));
            } catch (IllegalArgumentException ex) {
                throw new InvalidSNSUserInListException(++posNumber, ex.getMessage());
            }

            posNumber++;

        }

        return lSU;

    }

    /**
     * Function responsible to instantiate a SNSUser object
     *
     * @param name              the name of the SNS User.
     * @param address           the address of the SNS User.
     * @param sex               the sex of SNS User.
     * @param phoneNumber       the phoneNumber of SNS User.
     * @param email             the email of the SNS User.
     * @param birthDate         the birth-date of the SNS User.
     * @param snsNumber         the SNS User number of SNS User.
     * @param citizenCardNumber the citizen card number of SNS User.
     * @return A SNSUser object with given data
     */
    private static SNSUser create(String name, String address, String sex, String phoneNumber, Email email, Date birthDate, String snsNumber, String citizenCardNumber) {
        return new SNSUser(name, address, sex, phoneNumber, email, birthDate, snsNumber, citizenCardNumber);
    }

    /**
     * Method to convert an SNSUser DTO into the SNSUser model.
     *
     * @param suDto the SNS user DTO.
     * @return the SNS user model.
     */
    public static SNSUser toModel(SNSUserDTO suDto) {
        String name = suDto.getName();
        String address = suDto.getAddress();
        String sex = suDto.getSex();
        String phoneNumber = suDto.getPhoneNumber();
        Email email = suDto.getEmail();
        Date birthDate = suDto.getBirthdate();
        String snsNumber = suDto.getSnsUserNumber();
        String citizenCardNumber = suDto.getCitizenCardNumber();

        return new SNSUser(name, address, sex, phoneNumber, email, birthDate, snsNumber, citizenCardNumber);
    }

    /**
     * Method to convert an SNSUser model into the SNSUser DTO.
     *
     * @param su the SNS user.
     * @return the SNS user DTO.
     */
    public static SNSUserDTO toDto(SNSUser su) {
        String name = su.getName();
        String address = su.getAddress();
        String sex = su.getSex();
        String phoneNumber = su.getPhoneNumber();
        Email email = su.getEmail();
        Date birthdate = su.getBirthdate();
        String snsUserNumber = su.getSnsUserNumber();
        String citizenCardNumber = su.getCitizenCardNumber();

        SNSUserDTO suDto = new SNSUserDTO(name, address, sex, phoneNumber, email, birthdate, snsUserNumber, citizenCardNumber);
        return suDto;
    }
}
