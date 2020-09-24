package fitpay.engtest;

import org.springframework.beans.factory.annotation.Value;
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

    @Value("${bearerToken}")
    private String BEARER_TOKEN;

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
