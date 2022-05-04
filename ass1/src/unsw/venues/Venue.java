package unsw.venues;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Venue class for COMP2511 ass1
 *
 * @author z5112961
 */

public class Venue {

	private List<Room> rooms;
	private String name;
	
	public Venue(String name) {
		this.rooms = new ArrayList<Room>();
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void addRoom(Room room) {
		this.rooms.add(room);
	}
	
	public JSONArray listReservations(List<Booking> bookings) {
		JSONArray result = new JSONArray();
		
		for (int i = 0; i < this.rooms.size(); i++) {
			JSONObject room = new JSONObject();
			room.put("room", this.rooms.get(i).getName());
			JSONArray bookingArray = new JSONArray();
			for (int j = 0; j < bookings.size(); j++) {
				if (bookings.get(j).hasRoom(this.rooms.get(i))) {
					JSONObject bookingObj = new JSONObject();
					bookingObj.put("start", bookings.get(j).getStartDate());
					bookingObj.put("end", bookings.get(j).getEndDate());
					bookingObj.put("id", bookings.get(j).getId());
					bookingArray.put(bookingObj);
				}
			}
			room.put("reservations", bookingArray);
			result.put(room);
		}
		return result;
	}

	/*
	 * public JSONArray listReservations(List<Booking> bookings) {
		JSONArray result = new JSONArray();
		// Loop through rooms
		for (int i = 0; i < this.rooms.size(); i++) {
			// Create room object
			JSONObject room = new JSONObject();
			room.put("room", this.rooms.get(i).getName());
			JSONArray bookingArray = new JSONArray();
			List<JSONObject> sortedList = new ArrayList<JSONObject>();
			for (int j = 0; j < bookings.size(); j++) {
				if (bookings.get(j).hasRoom(this.rooms.get(i))) {
					JSONObject bookingObj = new JSONObject();
					bookingObj.put("start", bookings.get(j).getStartDate());
					bookingObj.put("end", bookings.get(j).getEndDate());
					bookingObj.put("id", bookings.get(j).getId());
					
					if (sortedList.size() == 0) sortedList.add(bookingObj);
					else {
						for (int k = 0; k < sortedList.size(); k++) {
							LocalDate newB = bookings.get(j).getStartDate();
							System.out.println(sortedList.get(k).toString());
							LocalDate curr = LocalDate.parse(sortedList.get(k).getString("start"));
							//LocalDate next = LocalDate.parse(sortedList.get(k+1).getString("start"));
							if (newB.compareTo(curr) < 0) {
								sortedList.add(k,bookingObj);
								break;
							}
						}
					}
				}
			}
			// Sorted List --> Booking Array
			for (int m = 0; m < sortedList.size(); m++) bookingArray.put(sortedList.get(m));
			// Put booking array into room object
			room.put("reservations", bookingArray);
			result.put(room);
		}
		
		//result = orderByDate(result);
		
		return result;
	}
	 * */
	
}
