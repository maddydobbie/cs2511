package unsw.venues;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Booking class for COMP2511 ass1
 *
 * @author z5112961
 */

public class Booking {
	
	private String id;
	private Venue venue;
	private List<Room> rooms;
	private LocalDate startDate;
	private LocalDate endDate;
	
	public Booking(String id, Venue venue, LocalDate startDate, LocalDate endDate) {
		super();
		this.id = id;
		this.venue = venue;
		this.rooms = new ArrayList<Room>();
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public String getId() {
		return this.id;
	}
	
	public Venue getVenue() {
		return this.venue;
	}
	
	public List<Room> getRooms() {
		return this.rooms;
	}
	
	public void addRoom(Room room) {
		this.rooms.add(room);
	}
	
	public LocalDate getStartDate() {
		return this.startDate;
	}
	
	public LocalDate getEndDate() {
		return this.endDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
	public boolean hasRoom(Room room) {
		for (int i = 0; i < this.rooms.size(); i++) {
			if (this.rooms.get(i) == room) return true;
		}
		return false;
	}
	
	public boolean checkBookingsDates(LocalDate start, LocalDate end) {
		if ((this.endDate == null) && (this.startDate == null)) return false;
		else if ((this.endDate.compareTo(start) > 0) && (this.startDate.compareTo(end) > 0)) return false;
		else if (this.endDate.compareTo(start) < 0) return false;
		else return true;
	}

	@Override
	public String toString() {
		return "Booking [id=" + id + ", venue=" + venue.getName() + ", rooms=" + rooms + ", startDate=" + startDate + ", endDate="
				+ endDate + "]";
	}
	
}