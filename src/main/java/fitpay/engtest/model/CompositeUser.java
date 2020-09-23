package fitpay.engtest.model;

import java.util.List;

import fitpay.engtest.exception.CompositeUserException;
import fitpay.engtest.state.CreditCardState;
import fitpay.engtest.state.DeviceState;
import fitpay.engtest.state.State;

/**
 * Model for CompositeUser Response
 */
public class CompositeUser {

	// private long id;
	private String userId;
	private List<CreditCard> creditCards;
	private List<Device> devices;
	private State state;

	public CompositeUser(String userId, String creditCardState, String deviceState){

		System.out.println(String.format("userId: %s \n CCS: %s \n DS: %s", userId, creditCardState, deviceState));

		this.userId = userId;

		// if(!creditCardState.isEmpty())
		//May have to handle null states and invalid states here

		CreditCardState ccState = null;
		DeviceState dState = null;
		if(creditCardState != null){
			// try {
				ccState = CreditCardState.valueOf(creditCardState);
				
			// } catch(IllegalArgumentException iae) {
			// 	throw new CompositeUserException("Invalid creditCardState", iae);
			// }

		
		}
		if(deviceState != null) {
			dState = DeviceState.valueOf(deviceState);
		}
		this.state = new State(ccState, dState);

	}

	public CompositeUser(String userId) throws CompositeUserException{

		new CompositeUser(userId, null, null);
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
}
