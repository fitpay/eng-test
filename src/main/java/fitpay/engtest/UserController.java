package fitpay.engtest;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.util.JSONPObject;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientAutoConfiguration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import fitpay.engtest.exception.CompositeUserException;
import fitpay.engtest.model.CompositeUser;
import fitpay.engtest.model.Devices;


@RestController
public class UserController {

    static final String BEARER_TOKEN = "eyJhbGciOiJSUzI1NiJ9.eyJqdGkiOiI1NGQ0ZTc2My1mNGYxLTQyYWQtOWQ0NS04Mjg2MTcwZDBmYjkiLCJzdWIiOiJWbjlMMUVLYyIsImF1dGhvcml0aWVzIjpbInVzZXIucmVhZCIsInVzZXIud3JpdGUiLCJvcmdhbml6YXRpb25zLkVOR1RFU1QiLCJjcmVkZW50aWFscy5yZWFkIiwiZGV2aWNlcy5yZWFkIiwidXNlcnMucmVhZCIsImNyZWRpdENhcmRzLnJlYWQiLCJ1c2Vycy53cml0ZSJdLCJzY29wZSI6WyJ1c2VyLnJlYWQiLCJ1c2VyLndyaXRlIiwib3JnYW5pemF0aW9ucy5FTkdURVNUIiwiY3JlZGVudGlhbHMucmVhZCIsImRldmljZXMucmVhZCIsInVzZXJzLnJlYWQiLCJjcmVkaXRDYXJkcy5yZWFkIiwidXNlcnMud3JpdGUiXSwiY2xpZW50X2lkIjoiVm45TDFFS2MiLCJjaWQiOiJWbjlMMUVLYyIsImF6cCI6IlZuOUwxRUtjIiwiZ3JhbnRfdHlwZSI6ImNsaWVudF9jcmVkZW50aWFscyIsInJldl9zaWciOiJlYTlmYzI2YyIsImlhdCI6MTYwMDg3NjI0OSwiZXhwIjoxNjAwOTE5NDQ5LCJpc3MiOiJodHRwczovL2F1dGgucWEuZml0cGF5Lm5pbmphL29hdXRoL3Rva2VuIiwiemlkIjoidWFhIiwiYXVkIjpbIlZuOUwxRUtjIiwidXNlciIsIm9yZ2FuaXphdGlvbnMiLCJjcmVkZW50aWFscyIsImRldmljZXMiLCJ1c2VycyIsImNyZWRpdENhcmRzIl19.bIS98lyRm6ntk-Hgifaph1FwHSKkCEzK23SGxk8AjfrblM3hyGwGhDck3mC4rsrZm3Z50xL2nTIAwRvUN0Sl8zCNpBZFkaUDWE_uRasAsVyQ0ScLQFVmz_pzlR6FlgK9_NtRv-ZNRCh5vG6QYaEJT31cIdFZoqUuttGTpufujF-YLcs6l5f14V12Gt5ZUbPSdH5H2H7_ur9iPCN4Md7LhPvGdhaN6R0yBOpkKIRWJcc4KaId1u7baLtmf9HGauLpJDThtB9HZpPM9LAmXhyhLA_pPWhTxK8x2hVfUU9pmh2ep44ZVG9keidFgmHEscTZSVPoT_N-AFTGLh_9Q0LG4Q";

    @RequestMapping("/")
    String hello() {
        return "Hello World!";
    }

    /**
     * Get the Composite User based on the defined parameters
     * 
     * @param userId          (Path Variable) the id of the user
     * @param creditCardState (optional param) the state of the credit card
     * @param deviceState     (optional param) the state of the device
     * @return the composite user response in JSON format
     */
    @GetMapping("/compositeUsers/{userId}")
    public ResponseEntity<CompositeUser> compositeUser(@PathVariable String userId,
            @RequestParam(required = false) String creditCardState,
            @RequestParam(required = false) String deviceState) {

        System.out.println("Got a ping");
        // Generate body of CompositeUser
        CompositeUser userResponse = null;
        // try{
        userResponse = new CompositeUser(userId, creditCardState, deviceState);

        CompositeUserBuilder cuBuilder = new CompositeUserBuilder();
        cuBuilder.buildALL(userResponse, BEARER_TOKEN);

        // } catch(CompositeUserException cue){
        //     // throw cue;
        // }
        // Might want to break this out into a CompositeUserBuilder (could be overkill)
        ResponseEntity<CompositeUser> resp = ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "application/json")
                .body(userResponse);

        return resp;
    }



    //TODO: Using this to get a list of the devices. Will be able to process this and get ids. Similar steps will be taken for the credit cards. Should probably add this to a builder to construct the actual CompositeUser object
    @GetMapping("/devices/{userId}")
    public Devices getDevices(@PathVariable String userId){

        //Construct GET request
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + BEARER_TOKEN);
        final String uri = String.format("https://api.qa.fitpay.ninja/users/%s/devices", userId);
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);
        
        //Send off request
        ResponseEntity<Devices> response = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, Devices.class);
        
        System.out.println("URI: " + uri);
        System.out.println("request: " + requestEntity.toString());
        System.out.println("response: " + response.getBody().toString());

        return response.getBody();

    }
}
