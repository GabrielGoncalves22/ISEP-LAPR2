package domain.model;

import app.core.controller.LoadFileController;
import app.core.dto.SNSUserDTO;
import app.core.domain.model.Date;
import app.core.domain.shared.FileHandler;
import org.junit.Assert;
import org.junit.Test;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to test LoadFileController methods
 *
 * @author Group 40
 */
public class LoadFileControllerTests {

    /**
     * The controller
     */
    private LoadFileController controller = new LoadFileController();

    private String filePath = "src/files/teste.csv";

    @Test
    public void getDataFromFileTest() throws Exception {
        List<SNSUserDTO> lSUDto = new ArrayList<SNSUserDTO>();
        SNSUserDTO snsUserDTOExpected = new SNSUserDTO("Teste teste", "Santa Maria da Feira", "M", "919999999", new Email("teste@hotmail.com"), new Date(2001, 5, 20), "123456789", "123456789");
        lSUDto = controller.getDataFromFile(filePath);
        SNSUserDTO snsUserDTO = lSUDto.get(0);

        Assert.assertEquals(snsUserDTOExpected.getName(), snsUserDTO.getName());
        Assert.assertEquals(snsUserDTOExpected.getAddress(), snsUserDTO.getAddress());
        Assert.assertEquals(snsUserDTOExpected.getSex(), snsUserDTO.getSex());
        Assert.assertEquals(snsUserDTOExpected.getPhoneNumber(), snsUserDTO.getPhoneNumber());
        Assert.assertEquals(snsUserDTOExpected.getEmail().getEmail(), snsUserDTO.getEmail().getEmail());
        Assert.assertEquals(snsUserDTOExpected.getBirthdate().toString(), snsUserDTO.getBirthdate().toString());
        Assert.assertEquals(snsUserDTOExpected.getSnsUserNumber(), snsUserDTO.getSnsUserNumber());
        Assert.assertEquals(snsUserDTOExpected.getCitizenCardNumber(), snsUserDTO.getCitizenCardNumber());

    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void createFileHandlerTest1() throws Exception {
        FileHandler fHandler = controller.createFileHandler(filePath, "app.core.domain.model.FileHandlerType1");
    }

    /**
     *
     * @throws Exception
     */
    @Test (expected = Exception.class)
    public void createFileHandlerTest2() throws Exception {
        FileHandler fHandler = controller.createFileHandler(filePath, "abc");
    }

    /**
     *
     * @throws Exception
     */
    @Test (expected = Exception.class)
    public void createFileHandlerTest3() throws Exception {
        FileHandler fHandler = controller.createFileHandler("abc", "app.core.domain.model.FileHandlerType1");
    }

    /**
     *
     */
    @Test
    public void loadSNSUsersTest() {

    }

}
