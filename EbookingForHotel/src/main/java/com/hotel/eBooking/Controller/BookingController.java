package com.hotel.eBooking.Controller;

import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hotel.eBooking.Dao.RoomDAO;
import com.hotel.eBooking.Model.BookingDetailsModel;
import com.hotel.eBooking.Model.RoomModel;
import com.hotel.eBooking.RequestClass.BookingDetailsRequest;
import com.hotel.eBooking.Service.BookingDetailsService;

@CrossOrigin(origins = "*")
@RestController
public class BookingController {

	@Autowired
	BookingDetailsService bookingDetailsService;

	@Autowired
	RoomDAO roomDAO;

	@PostMapping("book")
	public Map<String, Object> bookRoom(@RequestBody BookingDetailsRequest request, HttpServletRequest httpRequest) {
		Map<String, Object> response = new LinkedHashMap<>();
		Enumeration<String> headerValues = httpRequest.getHeaderNames();
		String userId = null;
		if (headerValues.hasMoreElements()) {
			userId = httpRequest.getHeader("userId").toString();
		}
		BookingDetailsModel book = bookingDetailsService.saveBookingDetails(request, userId);
		if (book != null) {
			response.put("Success", "Room Booked Successfully");
		} else {
			response.put("Error", "Room Booked Already");
		}
		return response;
	}

	@GetMapping("countForTheDay")
	public Map<String, Object> countForTheDay() {
		Map<String, Object> response = new LinkedHashMap<>();
		List<RoomModel> rooms = roomDAO.getTotalRooms();
		int totalRooms = 0;
		for (int z = 0; z < rooms.size(); z++) {
			totalRooms++;
		}
		int dayCount = 0;
		int available = 0;
		List<BookingDetailsModel> count = bookingDetailsService.countForTheDay();
		List<RoomModel> list = roomDAO.getAvailableRooms();
		for (int i = 0; i < count.size(); i++) {
			dayCount++;
		}
		for (int j = 0; j < list.size(); j++) {
			available++;
		}
		response.put("CountOfTheDay", dayCount);
		response.put("Total Rooms Booked", totalRooms - available);
		response.put("Total Available Rooms", available);
		return response;
	}

	@PostMapping("checkIn")
	public Map<String, Object> checkIn(@RequestBody BookingDetailsRequest request) {
		Map<String, Object> response = new LinkedHashMap<>();
		bookingDetailsService.checkIn(request);
		response.put("Success", "Checked In");
		return response;
	}

	@PostMapping("checkOut")
	public Map<String, Object> checkOut(@RequestBody BookingDetailsRequest request) {
		Map<String, Object> response = new LinkedHashMap<>();
		bookingDetailsService.checkOut(request);
		response.put("Success", "Checked Out");
		return response;
	}
	
}
