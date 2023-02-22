package controller;

import app.core.controller.ListArrivalSNSUserController;
import app.core.dto.ArrivalSNSUserDTO;
import app.core.domain.model.Company;
import domain.shared.CommonConstants;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the tests of the ListArrivalSNSUserControllerTests class.
 */
public class ListArrivalSNSUserControllerTests implements CommonConstants {
    Company company = new Company("DGS");
    ListArrivalSNSUserController controller = new ListArrivalSNSUserController();

    /**
     * This test will test the function of get SNS user arrival list.
     */
    @Test
    public void getListArrivalSNSUserTest() {
        company.getArrivalSNSUserStore().saveArrivalSNSUser(ARRIVAL_SNS_USER1);
        company.getArrivalSNSUserStore().saveArrivalSNSUser(ARRIVAL_SNS_USER1);

        List<ArrivalSNSUserDTO> expectedArivalSNSUserList = controller.getListArrivalSNSUser();

        ArrivalSNSUserDTO arrivalSNSUserDTO = new ArrivalSNSUserDTO(SNS_USER_SHORT1);
        ArrivalSNSUserDTO arrivalSNSUserDTO1 = new ArrivalSNSUserDTO(SNS_USER_SHORT2);

        List<ArrivalSNSUserDTO> resultArrivalSNSUserList = new ArrayList<>();
        resultArrivalSNSUserList.add(arrivalSNSUserDTO);
        resultArrivalSNSUserList.add(arrivalSNSUserDTO1);

        int n = 0;
        while (n < expectedArivalSNSUserList.size()) {
            boolean result = expectedArivalSNSUserList.get(n).equals(resultArrivalSNSUserList.get(n));
            Assert.assertTrue(result);
            n++;
        }
    }
}
