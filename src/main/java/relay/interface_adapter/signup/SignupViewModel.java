package relay.interface_adapter.signup;

import relay.interface_adapter.State;
import relay.interface_adapter.ViewModel;

public class SignupViewModel implements ViewModel {
	private State state = new SignupState();

	public SignupViewModel() {
	}

	@Override
	public State getState() {
		return state;
	}

	@Override
	public void setState(State state) {
		this.state = state;
	}

}
