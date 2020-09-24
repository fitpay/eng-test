package fitpay.engtest;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

import fitpay.engtest.model.CompositeUser;

public class TestUserController {

    @Test
    public void testGetCompositeUserSimpleSuccess(){
        
        MockUserController mockUC = new MockUserController();

        String userId = "testId";

        //Testing this method
        mockUC.getCompositeUser(userId, null, null);

        verify(mockUC.getCompositeUserBuilder(), times(1)).buildALL(any(CompositeUser.class), any(String.class));
    }
    
}
