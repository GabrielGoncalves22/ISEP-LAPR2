package app.core.dto;

import app.core.domain.model.AgeGroup;
import app.core.domain.model.VaccineType;

import java.util.List;

/**
 * Represents the vaccine DTO.
 *
 * @author Group 40
 */
public class VaccineDTO {
    /**
     * The id of the vaccine DTO.
     */
    private int id;

    /**
     * The name of the vaccine DTO.
     */
    private String name;

    /**
     * The brand of the vaccine DTO.
     */
    private String brand;

    /**
     * The vaccine type of the vaccine DTO.
     */
    private VaccineTypeDTO vt;

    /**
     * The list of vaccine age group DTO.
     */
    private List<AgeGroup> agList;

    /**
     * Builds an instance of VaccineDTO receiving the id, name and brand.
     *
     * @param id     the id of the vaccine.
     * @param name   the name of the vaccine.
     * @param brand  the brand of the vaccine.
     * @param vt     the vaccine type of the vaccine.
     * @param agList the list of vaccine age group.
     */
    public VaccineDTO(int id, String name, String brand, VaccineTypeDTO vt, List<AgeGroup> agList) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.vt = vt;
        this.agList = agList;
    }

    /**
     * Function to return the id of the vaccine DTO.
     *
     * @return the id of the vaccine DTO.
     */
    public int getId() {
        return id;
    }

    /**
     * Function to return the name of the vaccine DTO.
     *
     * @return the name of the vaccine DTO.
     */
    public String getName() {
        return name;
    }

    /**
     * Function to return the brand of the vaccine DTO.
     *
     * @return the brand of the vaccine DTO.
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Function to return the vaccine type of the vaccine DTO.
     *
     * @return the vaccine type of the vaccine DTO.
     */
    public VaccineTypeDTO getVtDto() {
        return vt;
    }

    /**
     * Function to return the list of vaccine age group DTO.
     *
     * @return the list of vaccine age group DTO.
     */
    public List<AgeGroup> getAgList() {
        return agList;
    }

    /**
     * Returns the textual description of the vaccine DTO.
     *
     * @return characteristics of the vaccine DTO.
     */
    @Override
    public String toString() {
        return "Id: " + id + " - Name: " + name;
    }
}
