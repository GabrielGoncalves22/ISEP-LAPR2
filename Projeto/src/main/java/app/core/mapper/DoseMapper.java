package app.core.mapper;

import app.core.domain.model.Dose;
import app.core.dto.DoseDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the dose mapper.
 *
 * @author Group 40
 */
public class DoseMapper {

    /**
     * Method to convert a list of doses to a list of dose dto with the necessary information.
     *
     * @param listD the list dose.
     * @return the dose DTO.
     */
    public static List<DoseDTO> toListDTO(List<Dose> listD) {
        List<DoseDTO> listDDto = new ArrayList<>();

        for (int i = 0; i < listD.size(); i++) {
            Dose d = listD.get(i);

            DoseDTO vDto = toDTO(d);
            listDDto.add(vDto);
        }

        return listDDto;
    }

    /**
     * Method to convert an Dose model into the Dose DTO.
     *
     * @param d the dose.
     * @return the dose DTO.
     */
    public static DoseDTO toDTO(Dose d) {
        int doseNumber = d.getDoseNumber();
        int dosage = d.getDosage();
        int timeIntervalBetweenLastDose = d.getTimeIntervalBetweenLastDose();

        return new DoseDTO(doseNumber, dosage, timeIntervalBetweenLastDose);
    }

    /**
     * Method to convert an Dose DTO into the Dose model.
     *
     * @param dDto the dose DTO.
     * @return the dose model.
     */
    public static Dose toModel(DoseDTO dDto) {
        int doseNumber = dDto.getDoseNumber();
        int dosage = dDto.getDosage();
        int timeIntervalBetweenLastDose = dDto.getTimeIntervalBetweenLastDose();

        return new Dose(doseNumber, dosage, timeIntervalBetweenLastDose);
    }
}
