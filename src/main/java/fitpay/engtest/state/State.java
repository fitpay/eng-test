package fitpay.engtest.state;

/**
 * Defines State of user's devices and credit cards
 */
public class State {

    DeviceState deviceState;
    CreditCardState creditCardState;

    //Overload the constructor for easier use

    /**
     * State of user
     * @param creditCardState state of credit card
     * @param deviceState state of device
     */
    public State(CreditCardState creditCardState, DeviceState deviceState) {
        this.deviceState = deviceState;
        this.creditCardState = creditCardState;
    }

    /**
     * State of user
     * @param deviceState state of device
     */
    public State(DeviceState deviceState) {
        new State(null, deviceState);
    }

    /**
     * State of user
     * @param creditCardState state of credit card
     */
    public State(CreditCardState creditCardState) {
        new State(creditCardState, null);
    }

    public DeviceState getDeviceState() {
        return deviceState;
    }

    public void setDeviceState(DeviceState deviceState) {
        this.deviceState = deviceState;
    }

    public CreditCardState getCreditCardState() {
        return creditCardState;
    }

    public void setCreditCardState(CreditCardState creditCardState) {
        this.creditCardState = creditCardState;
    }

    
}
