package com.hotel.eBooking.Dao;

import com.hotel.eBooking.Model.BookingModel;
import com.hotel.eBooking.RequestClass.RegistrationRequest;

public interface RegistrationDAO {

	BookingModel register(BookingModel bookingModel);

	BookingModel isUserExist(RegistrationRequest request);

	BookingModel login(String emailId, String password);
	
}
