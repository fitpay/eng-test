package fitpay.engtest.model;

import java.util.List;
import java.util.UUID;

import fitpay.engtest.state.CreditCardState;
import fitpay.engtest.state.DeviceState;
import fitpay.engtest.state.State;

/**
 * Model for CompositeUser Response
 */
public class CompositeUser {

	private String id = UUID.randomUUID().toString();
	private String userId;
	private List<CreditCard> creditCards;
	private List<Device> devices;
	private State state;

	public CompositeUser(String userId, String creditCardState, String deviceState) {

		this.userId = userId;

		// Convert state string values to enums
		CreditCardState ccState = null;
		DeviceState dState = null;
		if (creditCardState != null) {
			ccState = CreditCardState.valueOf(creditCardState);
		}
		if (deviceState != null) {
			dState = DeviceState.valueOf(deviceState);
		}
		this.state = new State(ccState, dState);
	}

	public String getUserId() {
		return userId;
	}

	public State getState() {
		return state;
	}

	public List<CreditCard> getCreditCards() {
		return creditCards;
	}

	public void setCreditCards(List<CreditCard> creditCards) {
		this.creditCards = creditCards;
	}

	public List<Device> getDevices() {
		return devices;
	}

	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}

	public String getId() {
		return id;
	}

}
