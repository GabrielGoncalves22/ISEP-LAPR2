package domain.store;

import app.core.dto.SNSUserDTO;
import app.core.domain.model.Date;
import app.core.domain.shared.exception.InvalidSNSUserInListException;
import app.core.domain.store.SNSUserStore;
import org.junit.Test;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to teste SNSUserStore methods
 *
 * @author Group 40
 */
public class SNSUserStoreTests {

    SNSUserStore store = new SNSUserStore();
    SNSUserDTO snsUserDTO1 = new SNSUserDTO("Test1", "Teste morada", "M", "912345678", new Email("teste@email.com"), new Date(1989, 2, 2), "123456789", "123456789");
    SNSUserDTO snsUserDTO2 = new SNSUserDTO("Test2", "Teste morada Dois", "F", "923456789", new Email("testeDois@email.com"), new Date(2002, 5, 5), "123456789", "123456789");
    SNSUserDTO snsUserDTO3 = new SNSUserDTO("Test3", "Teste morada Três", "M", "912312312", new Email("testeTres@email.com"), new Date(1999, 9, 1), "123456789", "123456789");
    SNSUserDTO invalidSnsUserDTO1 = new SNSUserDTO("", "Teste morada", "M", "913645555", new Email("teste@hotmail.com"), new Date(1989, 2, 2), "123456789", "123456789");
    SNSUserDTO invalidSnsUserDTO2 = new SNSUserDTO("Invalid Test 2", "", "M", "912222222", new Email("teste@email.com"), new Date(1989, 2, 2), "123456789", "123456789");
    List<SNSUserDTO> lSUDto = new ArrayList<SNSUserDTO>();

    /**
     * Test the method lSUDtoToModelList. The operation will be done successfully
     */
    @Test
    public void lSUDtoToModelListTest1() {
        lSUDto.add(snsUserDTO1);
        lSUDto.add(snsUserDTO2);
        lSUDto.add(snsUserDTO3);
        store.lSUDtoToModelList(lSUDto);
    }

    /**
     * Test the method lSUDtoToModelList. The operation will throw a InvalidSNSUserInListException exception
     */
    @Test (expected = InvalidSNSUserInListException.class)
    public void lSUDtoToModelListTest2() {
        lSUDto.add(invalidSnsUserDTO1);
        lSUDto.add(invalidSnsUserDTO2);
        store.lSUDtoToModelList(lSUDto);
    }

}
