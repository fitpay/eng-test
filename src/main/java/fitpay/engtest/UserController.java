package fitpay.engtest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fitpay.engtest.builder.CompositeUserBuilder;
import fitpay.engtest.model.CompositeUser;

@RestController
public class UserController {

    static final String BEARER_TOKEN = "eyJhbGciOiJSUzI1NiJ9.eyJqdGkiOiI1NGQ0ZTc2My1mNGYxLTQyYWQtOWQ0NS04Mjg2MTcwZDBmYjkiLCJzdWIiOiJWbjlMMUVLYyIsImF1dGhvcml0aWVzIjpbInVzZXIucmVhZCIsInVzZXIud3JpdGUiLCJvcmdhbml6YXRpb25zLkVOR1RFU1QiLCJjcmVkZW50aWFscy5yZWFkIiwiZGV2aWNlcy5yZWFkIiwidXNlcnMucmVhZCIsImNyZWRpdENhcmRzLnJlYWQiLCJ1c2Vycy53cml0ZSJdLCJzY29wZSI6WyJ1c2VyLnJlYWQiLCJ1c2VyLndyaXRlIiwib3JnYW5pemF0aW9ucy5FTkdURVNUIiwiY3JlZGVudGlhbHMucmVhZCIsImRldmljZXMucmVhZCIsInVzZXJzLnJlYWQiLCJjcmVkaXRDYXJkcy5yZWFkIiwidXNlcnMud3JpdGUiXSwiY2xpZW50X2lkIjoiVm45TDFFS2MiLCJjaWQiOiJWbjlMMUVLYyIsImF6cCI6IlZuOUwxRUtjIiwiZ3JhbnRfdHlwZSI6ImNsaWVudF9jcmVkZW50aWFscyIsInJldl9zaWciOiJlYTlmYzI2YyIsImlhdCI6MTYwMDg3NjI0OSwiZXhwIjoxNjAwOTE5NDQ5LCJpc3MiOiJodHRwczovL2F1dGgucWEuZml0cGF5Lm5pbmphL29hdXRoL3Rva2VuIiwiemlkIjoidWFhIiwiYXVkIjpbIlZuOUwxRUtjIiwidXNlciIsIm9yZ2FuaXphdGlvbnMiLCJjcmVkZW50aWFscyIsImRldmljZXMiLCJ1c2VycyIsImNyZWRpdENhcmRzIl19.bIS98lyRm6ntk-Hgifaph1FwHSKkCEzK23SGxk8AjfrblM3hyGwGhDck3mC4rsrZm3Z50xL2nTIAwRvUN0Sl8zCNpBZFkaUDWE_uRasAsVyQ0ScLQFVmz_pzlR6FlgK9_NtRv-ZNRCh5vG6QYaEJT31cIdFZoqUuttGTpufujF-YLcs6l5f14V12Gt5ZUbPSdH5H2H7_ur9iPCN4Md7LhPvGdhaN6R0yBOpkKIRWJcc4KaId1u7baLtmf9HGauLpJDThtB9HZpPM9LAmXhyhLA_pPWhTxK8x2hVfUU9pmh2ep44ZVG9keidFgmHEscTZSVPoT_N-AFTGLh_9Q0LG4Q";

    /**
     * Get the Composite User based on the defined parameters
     * 
     * @param userId          (Path Variable) the id of the user
     * @param creditCardState (optional param) the state of the credit card
     * @param deviceState     (optional param) the state of the device
     * @return the composite user response in JSON format
     */
    @GetMapping("/compositeUsers/{userId}")
    public ResponseEntity<CompositeUser> getCompositeUser(@PathVariable String userId,
            @RequestParam(required = false) String creditCardState,
            @RequestParam(required = false) String deviceState) {

        // Generate body of CompositeUser
        CompositeUser userResponse = null;
        userResponse = new CompositeUser(userId, creditCardState, deviceState);

        // Build response object
        CompositeUserBuilder cuBuilder = getCompositeUserBuilder();
        cuBuilder.buildALL(userResponse, BEARER_TOKEN);

        ResponseEntity<CompositeUser> resp = ResponseEntity.ok().header(HttpHeaders.CONTENT_TYPE, "application/json")
                .body(userResponse);

        System.out.println(String.format("Sending response: %s", userResponse.getId()));

        return resp;
    }

    /**
     * Created for testing purposes so that Class could be mocked
     * @return a new CompositeUserBuilder
     */
    protected CompositeUserBuilder getCompositeUserBuilder(){
        return new CompositeUserBuilder();
    }

}
