package unsw.venues;

import java.time.LocalDate;
import java.util.List;

/**
 * Room class for COMP2511 ass1
 *
 * @author z5112961
 */

public class Room {

	private Venue venue;
	private String name;
	private String size;
	
	public Room(Venue venue, String name, String size) {
		super();
		this.venue = venue;
		this.name = name;
		this.size = size;
	}
	
	public Venue getVenue() {
		return venue;
	}
	
	public String getName() {
		return name;
	}
	
	public String getSize() {
		return size;
	}
	
	public boolean checkRoomsDates(LocalDate start, LocalDate end, List<Booking> bookings) {
		for (int i = 0; i < bookings.size(); i++) {
			if (this.venue != bookings.get(i).getVenue()) continue;
			if (bookings.get(i).hasRoom(this)) {
				if (bookings.get(i).checkBookingsDates(start,end)) {
					return true;
				}
			}
		}
		return false;
		
		
	}
}
