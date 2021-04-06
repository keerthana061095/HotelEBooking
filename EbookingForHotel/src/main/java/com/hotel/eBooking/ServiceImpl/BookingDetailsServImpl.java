package com.hotel.eBooking.ServiceImpl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.commons.text.CharacterPredicates;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.eBooking.Dao.BookingDetailsDAO;
import com.hotel.eBooking.Dao.RoomDAO;
import com.hotel.eBooking.Model.BookingDetailsModel;
import com.hotel.eBooking.Model.RoomModel;
import com.hotel.eBooking.RequestClass.BookingDetailsRequest;
import com.hotel.eBooking.Service.BookingDetailsService;

@Service
public class BookingDetailsServImpl implements BookingDetailsService {

	@Autowired
	BookingDetailsDAO bookingDetailsDAO;

	@Autowired
	RoomDAO roomDAO;

	@Override
	public BookingDetailsModel saveBookingDetails(BookingDetailsRequest request, String userId) {
		BookingDetailsModel book = new BookingDetailsModel();
		RandomStringGenerator randomStringGenerator = new RandomStringGenerator.Builder().withinRange('0', 'Z')
				.filteredBy(CharacterPredicates.LETTERS, CharacterPredicates.DIGITS).build();
		String code = randomStringGenerator.generate(5).toString();
		int num = 0;
		RoomModel room = roomDAO.checkRoomAvailability(request.getRoomId(), num);
		if (room != null) {
			book.setBookingId(code);
			book.setRoomId(request.getRoomId());
			book.setUserId(userId);
			book.setFromDate(request.getFromDate());
			book.setToDate(request.getToDate());
			book.setStatus(0);
			book = bookingDetailsDAO.saveBooking(book);
			roomDAO.updateRoomAvailability(request.getRoomId());
		} else {
			book = null;
		}
		return book;
	}

	@Override
	public List<BookingDetailsModel> countForTheDay() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDateTime now = LocalDateTime.now();
		String dt = dtf.format(now);
		List<BookingDetailsModel> count = bookingDetailsDAO.getCountForTheDay(dt);
		return count;
	}

	@Override
	public void checkIn(BookingDetailsRequest request) {
		bookingDetailsDAO.checkIn(request);
	}

	@Override
	public void checkOut(BookingDetailsRequest request) {
		bookingDetailsDAO.checkOut(request);
		BookingDetailsModel booking = bookingDetailsDAO.getRoomDtails(request);
		String roomId = booking.getRoomId();
		roomDAO.updateRoomAvailabilityAfterCheckOut(roomId);
	}
}
