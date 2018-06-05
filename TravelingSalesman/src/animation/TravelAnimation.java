package animation;

import java.util.List;

import visual.TravelDisplay;
import visual.Salesman;

public class TravelAnimation extends Animation {

	private double stepMovement;
	private TravelDisplay travelDisplay;
	
	public TravelAnimation(TravelDisplay display, double desiredFps, double stepMovement) {
		super(display, desiredFps);
		travelDisplay = display;
		this.stepMovement = stepMovement;
	}

	@Override
	protected void animationStep() {
		List<Salesman> salesmen = travelDisplay.getSalesmen();
		boolean salesmanBusy = false;
		for (Salesman salesman : salesmen) {
			salesmanBusy = salesman.move(stepMovement) ? true : salesmanBusy;
		}
		running = salesmanBusy;
	}

}
