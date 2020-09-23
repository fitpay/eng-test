package fitpay.engtest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import fitpay.engtest.model.CompositeUser;
import fitpay.engtest.model.CreditCards;
import fitpay.engtest.model.Devices;

public class CompositeUserBuilder {

    private final String USER_URL = "https://api.qa.fitpay.ninja/users";

	public CompositeUserBuilder buildALL(CompositeUser cUser, String bearerToken) {

        this.setDevices(cUser, bearerToken);
        this.setCreditCards(cUser, bearerToken);

        return this;
	}

    private CompositeUserBuilder setCreditCards(CompositeUser cUser, String bearerToken) {

        //Construct GET request
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + bearerToken);
        final String uri = String.format("%s/%s/creditCards", USER_URL, cUser.getUserId());
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);
        
        System.out.println("URI: " + uri);
        System.out.println("request: " + requestEntity.toString());
        
        //Send off request
        ResponseEntity<CreditCards> response = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, CreditCards.class);
        System.out.println("response: " + response.getBody().toString());

        cUser.setCreditCards(response.getBody().getResults());

        return this;
    }

    /**
     * Retrieves Devices list from fitpay API and adds them to the {@link fitpay.engtest.model.Devices} list
     * @param cUser
     * @param bearerToken
     * @return the builder
     */
    private CompositeUserBuilder setDevices(CompositeUser cUser, String bearerToken) {

        //Construct GET request
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + bearerToken);
        final String uri = String.format("%s/%s/devices", USER_URL, cUser.getUserId());
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);
        
        System.out.println("URI: " + uri);
        System.out.println("request: " + requestEntity.toString());
        
        //Send off request
        ResponseEntity<Devices> response = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, Devices.class);
        System.out.println("response: " + response.getBody().toString());

        cUser.setDevices(response.getBody().getResults());

        return this;
    }


    // static CompositeUser build(CompositeUser cUser) {

    //     List<>
    // }
    
}
