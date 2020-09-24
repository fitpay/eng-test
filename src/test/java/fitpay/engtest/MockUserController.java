package fitpay.engtest;

import static org.mockito.Mockito.mock;

import fitpay.engtest.builder.CompositeUserBuilder;

public class MockUserController extends UserController {


    CompositeUserBuilder cuBuilder;

    @Override
    protected CompositeUserBuilder getCompositeUserBuilder(){

        cuBuilder = (cuBuilder == null) ? mock(CompositeUserBuilder.class) : cuBuilder;
        return cuBuilder;
    }
    
}
