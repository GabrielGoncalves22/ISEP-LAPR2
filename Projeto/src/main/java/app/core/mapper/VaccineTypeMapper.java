package app.core.mapper;

import app.core.domain.model.VaccineType;
import app.core.dto.VaccineTypeDTO;

/**
 * Represents the vaccine type mapper.
 *
 * @author Group 40
 */
public class VaccineTypeMapper {

    /**
     * Method to convert an VaccineType model into the VaccineType DTO.
     *
     * @param vt the vaccine type.
     * @return the vaccine type DTO.
     */
    public static VaccineTypeDTO toDTO(VaccineType vt) {
        String code = vt.getCode();
        String description = vt.getDescription();
        String technology = vt.getTechnology();

        return new VaccineTypeDTO(code, description, technology);
    }

    /**
     * Method to convert an VaccineType DTO into the VaccineType model.
     *
     * @param vtDTO the vaccine type DTO.
     * @return the vaccine type.
     */
    public static VaccineType toModel(VaccineTypeDTO vtDTO) {
        String code = vtDTO.getCode();
        String description = vtDTO.getDescription();
        String technology = vtDTO.getTechnology();

        return new VaccineType(code, description, technology);
    }
}
