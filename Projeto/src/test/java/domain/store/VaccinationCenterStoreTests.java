package domain.store;

import app.core.domain.model.Time;
import app.core.domain.model.VaccinationCenter;
import app.core.domain.store.VaccinationCenterStore;
import org.junit.Assert;
import org.junit.Test;
import pt.isep.lei.esoft.auth.domain.model.Email;

/**
 * Represents the tests of the VaccinationCenterStore class.
 */
public class VaccinationCenterStoreTests {
    private static VaccinationCenterStore vaccinationCenterStore = new VaccinationCenterStore();

    Time openingHours = new Time(9, 0);
    Time closingHours = new Time(18, 0);

    VaccinationCenter vaccinationCenter1 = vaccinationCenterStore.createVaccinationCenter("Ramalde Lda", "R. Eng. Ferreira Dias 874, 4100-246 Porto", 918273645, new Email("jumpjump@ramaldejump.pt"), 123456789, "https://www.jumpers.pt/pt/", openingHours, closingHours, 14, 3);
    VaccinationCenter vaccinationCenter2 = vaccinationCenterStore.createVaccinationCenter("Francos Lda", "R. Eng. Carlos 133, 4100-441 Porto", 918274445, new Email("isep@lei.pt"), 123333789, "https://www.isep.pt/pt/", openingHours, closingHours, 9, 5);
    VaccinationCenter vaccinationCenter3 = vaccinationCenterStore.createVaccinationCenter("Viso Lda", "Av. Soltida 133, 4130-441 Porto", 915573645, new Email("lei@isep.pt"), 123455589, "https://www.lei.pt/pt/", openingHours, closingHours, 4, 6);
    VaccinationCenter vaccinationCenter4 = vaccinationCenterStore.createVaccinationCenter("SeteBicas Lda", "Av. Antonia 133, 4130-441 Porto", 915533645, new Email("lei@isep.pt"), 123111589, "https://www.lsadasd.pt/pt/", openingHours, closingHours, 4, 6);
    VaccinationCenter vaccinationCenter5 = vaccinationCenterStore.createVaccinationCenter("Srh Hora Lda", "Av. Facbrica 133, 4130-441 Porto", 915533645, new Email("lei@isep.pt"), 123333589, "https://www.l1e121fasf.pt/pt/", openingHours, closingHours, 4, 6);

    /**
     * This test will test the function of creating a vaccination center.
     */
    @Test
    public void testValidateVaccineCenter() {
        boolean test1 = vaccinationCenterStore.validateVaccinationCenter(null);
        boolean test2 = vaccinationCenterStore.validateVaccinationCenter(vaccinationCenter4);
        boolean test3 = vaccinationCenterStore.validateVaccinationCenter(vaccinationCenter5);

        Assert.assertFalse(test1);
        Assert.assertTrue(test2);
        Assert.assertTrue(test3);
    }

    /**
     * This test will test the function of validating a vaccination center.
     */
    @Test
    public void testCreateVaccineCenter() {

        VaccinationCenter expectedResult1 = new VaccinationCenter("Ramalde Lda", "R. Eng. Ferreira Dias 874, 4100-246 Porto", 918273645, new Email("jumpjump@ramaldejump.pt"), 123456789, "https://www.jumpers.pt/pt/", openingHours, closingHours, 14, 3);
        VaccinationCenter expectedResult2 = new VaccinationCenter("Francos Lda", "R. Eng. Carlos 133, 4100-441 Porto", 918274445, new Email("isep@lei.pt"), 123333789, "https://www.isep.pt/pt/", openingHours, closingHours, 9, 5);
        VaccinationCenter expectedResult3 = new VaccinationCenter("Viso Lda", "Av. Soltida 133, 4130-441 Porto", 915573645, new Email("lei@isep.pt"), 123455589, "https://www.lei.pt/pt/", openingHours, closingHours, 4, 6);

        Assert.assertEquals(expectedResult1, vaccinationCenter1);
        Assert.assertEquals(expectedResult2, vaccinationCenter2);
        Assert.assertEquals(expectedResult3, vaccinationCenter3);
    }

    /**
     * This test will test the function of saving a vaccination center.
     */
    @Test
    public void testSaveVaccineCenter() {
        boolean test1 = vaccinationCenterStore.saveVaccinationCenter(null);
        boolean test2 = vaccinationCenterStore.saveVaccinationCenter(vaccinationCenter1);
        boolean test3 = vaccinationCenterStore.saveVaccinationCenter(vaccinationCenter1);

        Assert.assertFalse(test1);
        Assert.assertTrue(test2);
        Assert.assertFalse(test3);
    }
}
