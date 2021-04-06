package com.hotel.eBooking.Service;

import com.hotel.eBooking.Model.BookingModel;
import com.hotel.eBooking.RequestClass.RegistrationRequest;

public interface RegistrationService {

	BookingModel register(RegistrationRequest request);

	BookingModel isUserExist(RegistrationRequest request);

	BookingModel login(String emailId, String password);

}
