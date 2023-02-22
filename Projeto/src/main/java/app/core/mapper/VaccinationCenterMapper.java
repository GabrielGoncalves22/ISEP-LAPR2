package app.core.mapper;

import app.core.domain.model.*;
import app.core.dto.VaccinationCenterDTO;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the vaccination center mapper.
 *
 * @author Group 40
 */
public class VaccinationCenterMapper {

    /**
     * Method to convert the list vaccination centers to a list of vaccination center dto with the necessary information.
     *
     * @param listVC the list vaccination centers.
     * @return the vaccination center DTO.
     */
    public static List<VaccinationCenterDTO> toListDTO(List<VaccinationCenter> listVC) {
        List<VaccinationCenterDTO> listVCDto = new ArrayList<>();

        for (int i = 0; i < listVC.size(); i++) {
            VaccinationCenter vc = listVC.get(i);
            VaccinationCenterDTO vcDto = toDto(vc);
            listVCDto.add(vcDto);
        }

        return listVCDto;
    }

    /**
     * Method to convert a VaccinationCenter model into the VaccinationCenter DTO.
     *
     * @param vc the vaccination center.
     * @return the vaccination center DTO.
     */
    public static VaccinationCenterDTO toDto(VaccinationCenter vc) {
        int id = vc.getId();
        String name = vc.getName();
        int phoneNumber = vc.getPhoneNumber();
        Email email = vc.getEmail();

        return new VaccinationCenterDTO(id, name, phoneNumber, email);
    }

    /**
     * Method to convert an VaccinationCenter DTO into the VaccinationCenter model.
     *
     * @param vcDTO the vaccination center DTO.
     * @return the vaccination center.
     */
    public static VaccinationCenter toModel(VaccinationCenterDTO vcDTO) {
        int id = vcDTO.getId();
        String name = vcDTO.getName();
        String address = vcDTO.getAddress();
        int phoneNumber = vcDTO.getPhoneNumber();
        Email email = vcDTO.getEmail();
        int faxNumber = vcDTO.getFaxNumber();
        String website = vcDTO.getWebsite();
        Time openingHours = vcDTO.getOpeningHours();
        Time closingHours = vcDTO.getClosingHours();
        int slotDuration = vcDTO.getSlotDuration();
        int slotVaccineLimit = vcDTO.getSlotVaccineLimit();

        return new VaccinationCenter(id, name, address, phoneNumber, email, faxNumber, website, openingHours, closingHours, slotDuration, slotVaccineLimit);
    }
}
