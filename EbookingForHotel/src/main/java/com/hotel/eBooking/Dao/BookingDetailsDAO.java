package com.hotel.eBooking.Dao;

import java.util.List;

import com.hotel.eBooking.Model.BookingDetailsModel;
import com.hotel.eBooking.RequestClass.BookingDetailsRequest;

public interface BookingDetailsDAO {

	BookingDetailsModel saveBooking(BookingDetailsModel book);

	List<BookingDetailsModel> getCountForTheDay(String dt);

	void checkIn(BookingDetailsRequest request);

	void checkOut(BookingDetailsRequest request);

	BookingDetailsModel getRoomDtails(BookingDetailsRequest request);

}
