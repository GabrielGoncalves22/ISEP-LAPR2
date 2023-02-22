package mapper;

import app.core.domain.model.VaccineAdministration;
import app.core.dto.VaccineAdministrationDTO;
import app.core.mapper.VaccineAdministrationMapper;
import domain.shared.CommonConstants;
import org.junit.Assert;
import org.junit.Test;

/**
 * Represents the tests of the VaccineAdministrationMapper class.
 */
public class VaccineAdministrationMapperTests implements CommonConstants {

    /**
     * This test will test the function of convert an VaccineAdministration DTO into the VaccineAdministration model
     */
    @Test
    public void toModelTest() {
        VaccineAdministrationDTO vaDto = new VaccineAdministrationDTO(SNS_USER_DTO1, VACCINE_DTO1, DOSE_DTO1, ADMINISTRATION_DATE1, ADMINISTRATION_TIME1, LEAVING_DATE1, LEAVING_TIME2, VACCINATION_CENTER, "ABCD3-33");

        VaccineAdministration resultVaccineAdministration = VaccineAdministrationMapper.toModel(vaDto);
        VaccineAdministration expectedVaccineAdministration = new VaccineAdministration(SNS_USER1, VACCINE1, DOSE1, ADMINISTRATION_DATE1, ADMINISTRATION_TIME1, LEAVING_DATE1, LEAVING_TIME2, VACCINATION_CENTER, "ABCD3-33");

        Assert.assertNotEquals(expectedVaccineAdministration, resultVaccineAdministration);
    }
}
