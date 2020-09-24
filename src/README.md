# Engineering Test: Alex Schilling

## Overview
Hi All, I just wanted to give a little overview of the implementation and how to run the different aspects of this implementation.

### Bearer Token (optional)
If you haven't run the service before (or if it's been awhile), you probably need to update the bearer token. Go ahead and get a new one. (You can use this command):
```
curl -s --insecure -u CLIENT_ID:CLIENT_SECRET https://auth.qa.fitpay.ninja/oauth/token?grant_type=client_credentials

```
Now, update the `bearerToken` value in the `application.properties` located under `src/main/java/fitpay/engtest/config`

## Testing it
You can test the code by running `mvn clean test`

These Unit Tests try to cover all of the required (and edge cases) of the `/compositeUser` API call. All tests are self contained and do not require an outside connection (so they are as quick and lightweight as possible). The testing framework used is JUnit with some Mockito sprinkled in to mock server responses. 

## Running it
Simply run the app with `mvn spring-boot:run`

The API is built using Spring-boot. Most of the processing happens in the `UserController` class (receives and fulfills the request) and the `CompositeUserBuilder` class (requests, assigns and filters the credit card and device data). The processing steps are commented throughout the code and the methods are documented. If you have any questions, feel free to reach out. I'd be happy to clarify anything. Enjoy!
