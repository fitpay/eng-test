package fitpay.engtest;

import java.util.Iterator;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import fitpay.engtest.model.CompositeUser;
import fitpay.engtest.model.CreditCard;
import fitpay.engtest.model.CreditCards;
import fitpay.engtest.model.Device;
import fitpay.engtest.model.Devices;
import fitpay.engtest.state.CreditCardState;
import fitpay.engtest.state.DeviceState;

public class CompositeUserBuilder {

    private final String USER_URL = "https://api.qa.fitpay.ninja/users";

    public CompositeUserBuilder buildALL(CompositeUser cUser, String bearerToken) {

        this.setDevices(cUser, bearerToken);
        this.setCreditCards(cUser, bearerToken);

        return this;
    }

    /**
     * Retrieves Credit Cards list from fitpay API and add them to the
     * {@link fitpay.engtest.model.CreditCards} list
     *
     * @param cUser       specified user
     * @param bearerToken OAuth2 bearer token
     * @return the builder
     */
    private CompositeUserBuilder setCreditCards(CompositeUser cUser, String bearerToken) {

        // Construct GET request
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + bearerToken);
        final String uri = String.format("%s/%s/creditCards", USER_URL, cUser.getUserId());
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);

        // Send off request
        ResponseEntity<CreditCards> response = restTemplate.exchange(uri, HttpMethod.GET, requestEntity,
                CreditCards.class);
        System.out.println(String.format("Retrieved info from: %s", uri));

        List<CreditCard> creditCards = response.getBody().getResults();

        CreditCardState cCState = cUser.getState().getCreditCardState();
        // filter results if user has state
        if (cCState != null) {
            System.out.println(String.format("Filtering CreditCards with state: %s", cCState));
            filterCreditCards(creditCards, cCState);
        }

        cUser.setCreditCards(creditCards);

        return this;
    }

    /**
     * Retrieves Devices list from fitpay API and adds them to the
     * {@link fitpay.engtest.model.Devices} list
     * 
     * @param cUser       specified user
     * @param bearerToken OAuth2 bearer token
     * @return the builder
     */
    private CompositeUserBuilder setDevices(CompositeUser cUser, String bearerToken) {

        // Construct GET request
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + bearerToken);
        final String uri = String.format("%s/%s/devices", USER_URL, cUser.getUserId());
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);

        // Send off request
        ResponseEntity<Devices> response = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, Devices.class);
        System.out.println(String.format("Retrieved info from: %s", uri));

        // Filter devices if specified
        List<Device> devices = response.getBody().getResults();
        DeviceState dState = cUser.getState().getDeviceState();
        if (dState != null) {
            System.out.println(String.format("Filtering Devices with state: %s", dState));
            filterDevices(devices, dState);
        }

        cUser.setDevices(devices);

        return this;
    }

    /**
     * Removed Credit Cards that do not have specified state
     * 
     * @param creditCards list of credit cards
     * @param filter      state of the credit card
     */
    private void filterCreditCards(List<CreditCard> creditCards, CreditCardState filter) {

        Iterator<CreditCard> iter = creditCards.iterator();
        while (iter.hasNext()) {
            CreditCard cc = iter.next();
            if (cc.getState() != filter) {
                iter.remove();
            }
        }
    }

    /**
     * Remove Devices that do not have specified state
     * 
     * @param devices list of devices
     * @param filter  state of the devices
     */
    private void filterDevices(List<Device> devices, DeviceState filter) {

        Iterator<Device> iter = devices.iterator();
        while (iter.hasNext()) {
            Device dev = iter.next();
            if (dev.getState() != filter) {
                iter.remove();
            }
        }
    }

}
