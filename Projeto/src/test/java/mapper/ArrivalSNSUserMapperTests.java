package mapper;

import app.core.dto.ArrivalSNSUserDTO;
import app.core.mapper.ArrivalSNSUserMapper;
import app.core.domain.model.ArrivalSNSUser;
import app.core.domain.model.Date;
import app.core.domain.model.Time;
import domain.shared.CommonConstants;
import org.junit.Assert;
import org.junit.Test;

/**
 * Represents the tests of the ArrivalSNSUserMapper class.
 */
public class ArrivalSNSUserMapperTests implements CommonConstants {

    /**
     * This test will test the function of convert ArrivalSNSUserDTO in ArrivalSNSUser model.
     */
    @Test
    public void testToModel() {
        ArrivalSNSUser arrivalSNSUser1 = ArrivalSNSUserMapper.toModel(new ArrivalSNSUserDTO(SNS_USER1, new Date(2022, 12, 12),
                new Time(10, 25, 10), VACCINATION_CENTER));
        ArrivalSNSUser arrivalSNSUser2 = ArrivalSNSUserMapper.toModel(new ArrivalSNSUserDTO(SNS_USER2, new Date(2022, 12, 12),
                new Time(12, 30, 15), VACCINATION_CENTER));
        ArrivalSNSUser arrivalSNSUser3 = ArrivalSNSUserMapper.toModel(new ArrivalSNSUserDTO(SNS_USER3, new Date(2022, 12, 12),
                new Time(14, 45, 20), VACCINATION_CENTER));

        ArrivalSNSUser expectedResult1 = new ArrivalSNSUser(SNS_USER1, new Date(2022, 12, 12),
                new Time(10, 25, 10), VACCINATION_CENTER);
        ArrivalSNSUser expectedResult2 = new ArrivalSNSUser(SNS_USER2, new Date(2022, 12, 12),
                new Time(12, 30, 15), VACCINATION_CENTER);
        ArrivalSNSUser expectedResult3 = new ArrivalSNSUser(SNS_USER3, new Date(2022, 12, 12),
                new Time(14, 45, 20), VACCINATION_CENTER);

        Assert.assertEquals(expectedResult1, arrivalSNSUser1);
        Assert.assertEquals(expectedResult2, arrivalSNSUser2);
        Assert.assertEquals(expectedResult3, arrivalSNSUser3);
    }
}
