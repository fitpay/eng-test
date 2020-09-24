package fitpay.engtest.builder;

import java.util.List;

import org.springframework.http.HttpEntity;

import fitpay.engtest.model.CreditCard;
import fitpay.engtest.model.Device;

/**
 * MMocked version of CompositeUserBuilder so that no connection is made to
 * outside server
 */
public class MockCompositeUserBuilder extends CompositeUserBuilder {

    List<CreditCard> ccList = null;
    List<Device> dList = null;

    @Override
    protected List<CreditCard> getCreditCardList(String uri, HttpEntity<String> requestEntity) {
        return ccList;
    }

    @Override
    protected List<Device> getDeviceList(String uri, HttpEntity<String> requestEntity) {
        return dList;
    }

    public void setCCList(List<CreditCard> ccList) {
        this.ccList = ccList;
    }

    public void setDList(List<Device> dList) {
        this.dList = dList;
    }

}
