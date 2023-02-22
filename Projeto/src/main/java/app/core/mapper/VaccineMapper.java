package app.core.mapper;

import app.core.domain.model.AgeGroup;
import app.core.domain.model.Vaccine;
import app.core.domain.model.VaccineType;
import app.core.dto.VaccineDTO;
import app.core.dto.VaccineTypeDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the vaccine mapper.
 *
 * @author Group 40
 */
public class VaccineMapper {

    /**
     * Method to convert a list of vaccines to a list of vaccine dto with the necessary information.
     *
     * @param listV the list vaccine.
     * @return the vaccine DTO.
     */
    public static List<VaccineDTO> toListDTO(List<Vaccine> listV) {
        List<VaccineDTO> listVDto = new ArrayList<>();

        for (int i = 0; i < listV.size(); i++) {
            Vaccine au = listV.get(i);

            VaccineDTO vDto = toDTO(au);
            listVDto.add(vDto);
        }

        return listVDto;
    }

    /**
     * Method to convert a Vaccine model into the Vaccine DTO.
     *
     * @param v the vaccine.
     * @return the vaccine DTO.
     */
    public static VaccineDTO toDTO(Vaccine v) {
        int id = v.getId();
        String name = v.getName();
        String brand = v.getBrand();
        VaccineTypeDTO vtDto = VaccineTypeMapper.toDTO(v.getVt());
        List<AgeGroup> agList = v.getAgList();

        return new VaccineDTO(id, name, brand, vtDto, agList);
    }

    /**
     * Method to convert an Vaccine DTO into the Vaccine model.
     *
     * @param vDTO the vaccine DTO.
     * @return the vaccine.
     */
    public static Vaccine toModel(VaccineDTO vDTO) {
        int id = vDTO.getId();
        String name = vDTO.getName();
        String brand = vDTO.getBrand();
        VaccineType vt = VaccineTypeMapper.toModel(vDTO.getVtDto());
        List<AgeGroup> agList = vDTO.getAgList();

        return new Vaccine(id, name, brand, vt, agList);
    }
}
