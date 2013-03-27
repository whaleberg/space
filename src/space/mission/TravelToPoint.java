package space.mission;

import space.Ship;
import vector.Point2d;

public class TravelToPoint implements Command {
	private final Point2d destination;

	public TravelToPoint(Point2d destination) {
		this.destination = destination;
	}

	@Override
	public int followOrders(Ship s, int seconds) {
		
	}

}
