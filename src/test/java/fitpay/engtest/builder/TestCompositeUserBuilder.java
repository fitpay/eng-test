package fitpay.engtest.builder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import fitpay.engtest.model.CompositeUser;
import fitpay.engtest.model.CreditCard;
import fitpay.engtest.model.Device;
import fitpay.engtest.state.CreditCardState;
import fitpay.engtest.state.DeviceState;

public class TestCompositeUserBuilder {

    /**
     * Base test for creating CompositeUser
     */
    @Test
    public void testBuildAllBase() {

        String userId = "testId";
        String bt = "testToken";

        CompositeUser cu = new CompositeUser(userId, null, null);

        MockCompositeUserBuilder cub = new MockCompositeUserBuilder();

        // Testing this method
        cub.buildALL(cu, bt);

        assertEquals(userId, cu.getUserId());
        assertNotNull(cu.getId());
        assertNull(cu.getCreditCards());
        assertNull(cu.getDevices());

    }

    /**
     * Test population of Credit card list and Device list
     */
    @Test
    public void testBuildAllPop() {

        String userId = "testId";
        String bt = "testToken";

        // Generate list values
        int dLength = 2;
        int ccLength = 3;
        List<CreditCard> ccList = generateCCList(ccLength, CreditCardState.ACTIVE);
        List<Device> dList = generateDeviceList(dLength, DeviceState.INITIALIZED);
        CompositeUser cu = new CompositeUser(userId, null, null);

        MockCompositeUserBuilder cub = new MockCompositeUserBuilder();

        // Set returned list values
        cub.setCCList(ccList);
        cub.setDList(dList);

        // Testing this method
        cub.buildALL(cu, bt);

        assertEquals(userId, cu.getUserId());
        assertNotNull(cu.getId());
        assertEquals(ccLength, cu.getCreditCards().size());
        assertEquals(dLength, cu.getDevices().size());

    }

    /**
     * Test populated Devices and no Credit Cards
     */
    @Test
    public void testBuildAllPopCCNull() {

        String userId = "testId";
        String bt = "testToken";

        // Generate list values
        int dLength = 2;
        List<Device> dList = generateDeviceList(dLength, DeviceState.INITIALIZED);
        CompositeUser cu = new CompositeUser(userId, null, null);

        MockCompositeUserBuilder cub = new MockCompositeUserBuilder();

        // Set returned list values
        cub.setDList(dList);

        // Testing this method
        cub.buildALL(cu, bt);

        assertEquals(userId, cu.getUserId());
        assertNotNull(cu.getId());
        assertNull(cu.getCreditCards());
        assertEquals(dLength, cu.getDevices().size());

    }

    /**
     * Test populated credit cards and empty devices
     */
    @Test
    public void testBuildAllPopDNull() {

        String userId = "testId";
        String bt = "testToken";

        // Generate list values
        int ccLength = 3;
        List<CreditCard> ccList = generateCCList(ccLength, CreditCardState.ACTIVE);
        CompositeUser cu = new CompositeUser(userId, CreditCardState.ACTIVE.toString(), null);

        MockCompositeUserBuilder cub = new MockCompositeUserBuilder();

        // Set returned list values
        cub.setCCList(ccList);

        // Testing this method
        cub.buildALL(cu, bt);

        assertEquals(userId, cu.getUserId());
        assertNotNull(cu.getId());
        assertEquals(ccLength, cu.getCreditCards().size());
        assertNull(cu.getDevices());

    }

    /**
     * Filter out CC values. Devices are populated
     */
    @Test
    public void testBuildAllPopFilterCC() {

        String userId = "testId";
        String bt = "testToken";

        // Generate list values
        int dLength = 2;
        int ccLength = 3;
        List<CreditCard> ccList = generateCCList(ccLength, CreditCardState.ACTIVE);
        List<CreditCard> ccErrorList = generateCCList(ccLength, CreditCardState.ERROR);
        ccList.addAll(ccErrorList);

        List<Device> dList = generateDeviceList(dLength, DeviceState.FAILED_INITIALIZATION);
        List<Device> dInitList = generateDeviceList(dLength, DeviceState.INITIALIZED);
        dList.addAll(dInitList);

        CompositeUser cu = new CompositeUser(userId, CreditCardState.ACTIVE.toString(), null);

        MockCompositeUserBuilder cub = new MockCompositeUserBuilder();

        // Set returned list values
        cub.setCCList(ccList);
        cub.setDList(dList);

        // Testing this method
        cub.buildALL(cu, bt);

        assertEquals(userId, cu.getUserId());
        assertNotNull(cu.getId());
        assertEquals(ccLength, cu.getCreditCards().size());
        assertEquals(dLength * 2, cu.getDevices().size());

    }

    /**
     * Filter out Device values. Credit Cards are populated
     */
    @Test
    public void testBuildAllPopFilterD() {

        String userId = "testId";
        String bt = "testToken";

        // Generate list values
        int ccLength = 3;
        List<CreditCard> ccList = generateCCList(ccLength, CreditCardState.ACTIVE);
        List<CreditCard> ccErrorList = generateCCList(ccLength, CreditCardState.ERROR);
        ccList.addAll(ccErrorList);

        int dLength = 2;
        List<Device> dList = generateDeviceList(dLength + 3, DeviceState.FAILED_INITIALIZATION);
        List<Device> dInitList = generateDeviceList(dLength, DeviceState.INITIALIZED);
        dList.addAll(dInitList);

        CompositeUser cu = new CompositeUser(userId, null, DeviceState.INITIALIZED.toString());

        MockCompositeUserBuilder cub = new MockCompositeUserBuilder();

        // Set returned list values
        cub.setCCList(ccList);
        cub.setDList(dList);

        // Testing this method
        cub.buildALL(cu, bt);

        assertEquals(userId, cu.getUserId());
        assertNotNull(cu.getId());
        assertEquals(ccLength * 2, cu.getCreditCards().size());
        assertEquals(dLength, cu.getDevices().size());

    }

    /**
     * Filter out Both CC and Device values
     */
    @Test
    public void testBuildAllPopFilterBoth() {

        String userId = "testId";
        String bt = "testToken";

        // Generate list values
        int ccLength = 3;
        List<CreditCard> ccList = generateCCList(ccLength + 5, CreditCardState.ACTIVE);
        List<CreditCard> ccErrorList = generateCCList(ccLength, CreditCardState.ERROR);
        ccList.addAll(ccErrorList);

        int dLength = 2;
        List<Device> dList = generateDeviceList(dLength + 3, DeviceState.FAILED_INITIALIZATION);
        List<Device> dInitList = generateDeviceList(dLength, DeviceState.INITIALIZED);
        dList.addAll(dInitList);

        CompositeUser cu = new CompositeUser(userId, CreditCardState.ERROR.toString(),
                DeviceState.INITIALIZED.toString());

        MockCompositeUserBuilder cub = new MockCompositeUserBuilder();

        // Set returned list values
        cub.setCCList(ccList);
        cub.setDList(dList);

        // Testing this method
        cub.buildALL(cu, bt);

        assertEquals(userId, cu.getUserId());
        assertNotNull(cu.getId());
        assertEquals(ccLength, cu.getCreditCards().size());
        assertEquals(dLength, cu.getDevices().size());

    }

    /**
     * Filter out Both CC and Device values completely
     */
    @Test
    public void testBuildAllPopFilterBothCompletly() {

        String userId = "testId";
        String bt = "testToken";

        // Generate list values
        int ccLength = 3;
        List<CreditCard> ccList = generateCCList(ccLength, CreditCardState.ERROR);

        int dLength = 2;
        List<Device> dList = generateDeviceList(dLength, DeviceState.INITIALIZED);

        CompositeUser cu = new CompositeUser(userId, CreditCardState.ACTIVE.toString(),
                DeviceState.FAILED_INITILIZATION.toString());

        MockCompositeUserBuilder cub = new MockCompositeUserBuilder();

        // Set returned list values
        cub.setCCList(ccList);
        cub.setDList(dList);

        // Testing this method
        cub.buildALL(cu, bt);

        assertEquals(userId, cu.getUserId());
        assertNotNull(cu.getId());
        assertEquals(0, cu.getCreditCards().size());
        assertEquals(0, cu.getDevices().size());

    }

    /**
     * Helper method to generate credit card values
     * 
     * @param count the number of credit cards in the list
     * @param state the state of each credit card
     * @return the list
     */
    private List<CreditCard> generateCCList(int count, CreditCardState state) {

        List<CreditCard> ccList = new ArrayList<>();

        for (int i = 0; i < count; i++) {

            CreditCard cc = new CreditCard();
            cc.setState(state);
            cc.setCardType("sometype");
            cc.setCreditCardId("ccId" + i);
            cc.setExternalTokenReference("sometoken");
            cc.setTokenLastFour("last4");

            ccList.add(cc);
        }

        return ccList;
    }

    /**
     * Helper method to generate credit card values
     * 
     * @param count the number of credit cards in the list
     * @param state the state of each credit card
     * @return the list
     */
    private List<Device> generateDeviceList(int count, DeviceState state) {

        List<Device> dList = new ArrayList<>();

        for (int i = 0; i < count; i++) {

            Device d = new Device();
            d.setState(state);
            d.setDeviceName("someName");
            d.setDeviceIdentifier("dId" + i);
            d.setDeviceType("someType");
            d.setManufacturerName("someManufacturer");

            dList.add(d);
        }

        return dList;
    }

}
