package app.core.dto;

/**
 * Represents the vaccine type DTO.
 *
 * @author Group 40
 */
public class VaccineTypeDTO {

    /**
     * The code of the vaccine type DTO.
     */
    private String code;

    /**
     * The description of the vaccine type DTO.
     */
    private String description;

    /**
     * The technology of the vaccine type DTO.
     */
    private String technology;

    /**
     * Builds an instance of VaccineTypeDTO receiving the code, description and technology.
     *
     * @param code        the code of the vaccine type DTO.
     * @param description the description of the vaccine type DTO.
     * @param technology  the technology of the vaccine type DTO.
     */
    public VaccineTypeDTO(String code, String description, String technology) {
        this.code = code;
        this.description = description;
        this.technology = technology;
    }

    /**
     * Function to return the code of the vaccine type DTO.
     *
     * @return the code of the vaccine type DTO.
     */
    public String getCode() {
        return code;
    }

    /**
     * Function to return the description of the vaccine type DTO.
     *
     * @return the code of the description of the vaccine type DTO.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Function to return the technology of the vaccine type DTO.
     *
     * @return the code of the technology of the vaccine type DTO.
     */
    public String getTechnology() {
        return technology;
    }
}
