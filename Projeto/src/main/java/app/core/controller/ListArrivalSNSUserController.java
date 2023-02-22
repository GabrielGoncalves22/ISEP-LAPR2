package app.core.controller;

import app.core.domain.model.ArrivalSNSUser;
import app.core.domain.model.Company;
import app.core.domain.model.VaccinationCenter;
import app.core.dto.ArrivalSNSUserDTO;
import app.core.mapper.ArrivalSNSUserMapper;
import app.core.domain.store.ArrivalSNSUserStore;
import app.core.domain.store.VaccinationCenterStore;

import java.util.List;

/**
 * Represents the SNS user arrival controller.
 *
 * @author Group 40
 */
public class ListArrivalSNSUserController {
    /**
     * The company.
     */
    private Company company;

    /**
     * The SNS user arrival store.
     */
    private ArrivalSNSUserStore auStore;

    /**
     * The vaccine center store.
     */
    private VaccinationCenterStore vcStore;

    /**
     * The vaccination center.
     */
    private VaccinationCenter vc;

    /**
     * Builds an instance of RegisterArrivalSNSUserController
     */
    public ListArrivalSNSUserController(int vcId) {
        this(App.getInstance().getCompany());
        this.vcStore = company.getVaccinationCenterStore();
        vc = vcStore.getVaccinationCenter(vcId);
    }

    /**
     * Builds an instance of RegisterArrivalSNSUserController
     */
    public ListArrivalSNSUserController() {
        this(App.getInstance().getCompany());
    }

    /**
     * Builds an instance of RegisterArrivalSNSUserController receiving the company.
     *
     * @param company The company.
     */
    public ListArrivalSNSUserController(Company company) {
        this.company = company;
    }

    /**
     * Function to return a copy of the arrival sns user list.
     *
     * @return A copy of the arrival sns user list
     */
    public List<ArrivalSNSUserDTO> getListArrivalSNSUser() {
        this.auStore = company.getArrivalSNSUserStore();
        List<ArrivalSNSUser> listArrivalSNSUserDto = auStore.getListArrivalSNSUser(vc);
        return ArrivalSNSUserMapper.toListDTO(listArrivalSNSUserDto);
    }
}