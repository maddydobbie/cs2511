/**
 *
 */
package unsw.venues;

import java.time.LocalDate;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
/**
 * Venue Hire System for COMP2511.
 *
 * A basic prototype to serve as the "back-end" of a venue hire system. Input
 * and output is in JSON format.
 *
 * @author Robert Clifton-Everest
 * @author z5112961
 */
public class VenueHireSystem {
	private List<Venue> venues;
	private List<Room> rooms;
	private List<Booking> bookings;

    /**
     * Constructs a venue hire system. Initially, the system contains no venues,
     * rooms, or bookings.
     */

    public VenueHireSystem() {
		this.venues = new ArrayList<Venue>();
		this.rooms = new ArrayList<Room>();
		this.bookings = new ArrayList<Booking>();
	}

	private void processCommand(JSONObject json) {
        switch (json.getString("command")) {

        case "room":
            String venue = json.getString("venue");
            String room = json.getString("room");
            String size = json.getString("size");
            addRoom(venue, room, size);
            break;

        case "request":
            String id = json.getString("id");
            LocalDate start = LocalDate.parse(json.getString("start"));
            LocalDate end = LocalDate.parse(json.getString("end"));
            int small = json.getInt("small");
            int medium = json.getInt("medium");
            int large = json.getInt("large");

            JSONObject result = request(id, start, end, small, medium, large);
            System.out.println(result.toString(2));
            break;

        case "cancel":
            String cancelId = json.getString("id");
            cancelBooking(cancelId);
            break;
        
        case "change":
            String changeId = json.getString("id");
            LocalDate changeStart = LocalDate.parse(json.getString("start"));
            LocalDate changeEnd = LocalDate.parse(json.getString("end"));
            int changeSmall = json.getInt("small");
            int changeMedium = json.getInt("medium");
            int changeLarge = json.getInt("large");
            
            requestChange(changeId, changeStart, changeEnd, changeSmall, changeMedium, changeLarge);
            break;
            
        case "list":
            String venueName = json.getString("venue");
            //System.out.println("Dont forget to uncomment list");
            listVenueInfo(venueName);
            break;
        }
    }

    private void addRoom(String venue, String room, String size) {
    	// Check if venue of that name exists already
    	int flag = 0;
        Venue newVenue = new Venue(venue);
    	for (int i = 0; i < venues.size(); i++) {
    		if (venues.get(i).getName().compareTo(venue) == 0) {
    			flag = 1;
    			newVenue = venues.get(i);
    		}
    	}
    	// If not, create venue and add to venue list 
    	if (flag == 0) {
    		newVenue = new Venue(venue);
    		venues.add(newVenue);
    	}
    	// Create room and add to list in venue, to room list, and to list of rooms in venues
    	Room newRoom = new Room(newVenue, room, size);
    	rooms.add(newRoom);
    	newVenue.addRoom(newRoom);
    }

    public JSONObject request(String id, LocalDate start, LocalDate end, int small, int medium, int large) {
    	JSONObject result = new JSONObject();
        int sBooked = 0;
        int mBooked = 0;
        int lBooked = 0;
        List<Room> bookedRooms = new ArrayList<Room>();
        
        // Loop through venues
        for (int i = 0; i < this.venues.size(); i++) {
        	// Loop through rooms
        	for (int j = 0; j < this.rooms.size(); j++) {
        		// Continue if the curr room isnt in curr venue
        		if (this.rooms.get(j).getVenue() != this.venues.get(i)) continue;
        		// If room size is small and number booked < number required
        		if (this.rooms.get(j).getSize().compareTo("small") == 0 && sBooked < small) {
        			if (!this.rooms.get(j).checkRoomsDates(start, end, this.bookings)) {
            			bookedRooms.add(this.rooms.get(j));
            			sBooked++;
            		}
        		}
        		// If room size is medium and number booked < number required
        		if (this.rooms.get(j).getSize().compareTo("medium") == 0 && mBooked < medium) {
        			if (!this.rooms.get(j).checkRoomsDates(start, end, this.bookings)) {
            			bookedRooms.add(this.rooms.get(j));
            			mBooked++;
            		}
        		}
        		// If room size is large and number booked < number required
        		if (this.rooms.get(j).getSize().compareTo("large") == 0 && lBooked < large) {
        			if (!this.rooms.get(j).checkRoomsDates(start, end, this.bookings)) {
            			bookedRooms.add(this.rooms.get(j));
            			lBooked++;
            		}
        		}
        	}
        	// If number of rooms booked is less than the required, set all counters to zero for next iteration
        	if (sBooked < small || mBooked < medium || lBooked < large) {
        		sBooked = 0;
        		mBooked = 0;
        		lBooked = 0;
        		bookedRooms.clear();
        	} else break;
        }
        // If the number of rooms booked are correct, set status and venue
        if (sBooked == small && mBooked == medium && lBooked == large) {
    		result.put("status", "success");
    		result.put("venue", bookedRooms.get(0).getVenue().getName());
    		// Create JSON array for rooms and add all rooms to it
    		JSONArray rooms = new JSONArray();
    		Booking newBooking = new Booking(id, bookedRooms.get(0).getVenue(), start, end);
    		for (int m = 0; m < bookedRooms.size(); m++) {
    			newBooking.addRoom(bookedRooms.get(m));
    			rooms.put(bookedRooms.get(m).getName());
    		}
    		// Add rooms array to result
    		this.bookings.add(newBooking);
    		result.put("rooms", rooms);
    	} else { //Else, set status = rejected
    		result.put("status", "rejected");
    	}
        return result;
    }
    
    public void requestChange(String id, LocalDate start, LocalDate end, int small, int medium, int large) {
    	for (int i = 0; i < this.bookings.size(); i++) {
    		if (this.bookings.get(i).getId().compareTo(id) == 0) {
    			// Pointer to current data
    			Booking curr = this.bookings.get(i);
    			// Saving the current start and end dates before setting to null 
    			// (setting to null frees rooms under OG booking)
    			LocalDate currStartDate = curr.getStartDate();
    			LocalDate currEndDate = curr.getEndDate();
    			curr.setStartDate(null);
    			curr.setEndDate(null);
    			// Request with new info
    			JSONObject r = request(id, start, end, small, medium, large);
    			// If rejected, put in place old date
    			// If successful, remove old booking, system out result
    			if (r.getString("status").compareTo("rejected") == 0) {
    				curr.setEndDate(currEndDate);
    				curr.setStartDate(currStartDate);
    			} else {
    				this.bookings.remove(curr);
    			}
    			System.out.println(r.toString(2));
    			return;
    		}
    	}
    }

    public void cancelBooking(String cancelId) {
    	// Find the booking with the correct id, and remove from bookings list
    	for (int i = 0; i < this.bookings.size(); i++) {
    		if (cancelId.compareTo(this.bookings.get(i).getId()) == 0) {
    			this.bookings.remove(i);
    		}
    	}
    }
    
    public void listVenueInfo(String venueName) {
    	for (int i = 0; i < this.venues.size(); i++) {
    		if (venues.get(i).getName().compareTo(venueName) == 0) {
    			JSONArray r = this.venues.get(i).listReservations(this.bookings);
    			System.out.println(r.toString(2));
    		}
    	}   
    }

    public static void main(String[] args) {
        VenueHireSystem system = new VenueHireSystem();

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (!line.trim().equals("")) {
                JSONObject command = new JSONObject(line);
                system.processCommand(command);
            }
        }
        sc.close();
    }

}
