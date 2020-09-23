package fitpay.engtest.model;

import fitpay.engtest.state.CreditCardState;

/**
 * Model defining Credit Card Json object structure
 */
public class CreditCard {

    private String creditCardId;
    private CreditCardState state;
    private String cardType;
    private String externalTokenReference;
    private String tokenLastFour;

    public String getCreditCardId() {
        return creditCardId;
    }

    public void setCreditCardId(String creditCardId) {
        this.creditCardId = creditCardId;
    }

    public CreditCardState getState() {
        return state;
    }

    public void setState(CreditCardState state) {
        this.state = state;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getExternalTokenReference() {
        return externalTokenReference;
    }

    public void setExternalTokenReference(String externalTokenReference) {
        this.externalTokenReference = externalTokenReference;
    }

    public String getTokenLastFour() {
        return tokenLastFour;
    }

    public void setTokenLastFour(String tokenLastFour) {
        this.tokenLastFour = tokenLastFour;
    }

    
}
