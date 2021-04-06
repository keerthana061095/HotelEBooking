package com.hotel.eBooking.Service;

import java.util.List;

import com.hotel.eBooking.Model.BookingDetailsModel;
import com.hotel.eBooking.RequestClass.BookingDetailsRequest;

public interface BookingDetailsService {

	BookingDetailsModel saveBookingDetails(BookingDetailsRequest request, String userId);

	List<BookingDetailsModel> countForTheDay();

	void checkIn(BookingDetailsRequest request);

	void checkOut(BookingDetailsRequest request);


}
