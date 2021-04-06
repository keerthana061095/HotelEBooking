package com.hotel.eBooking.Service;

import java.util.List;

import com.hotel.eBooking.Model.RoomModel;
import com.hotel.eBooking.RequestClass.RoomRequest;

public interface RoomService {

	RoomModel createRoom(RoomRequest request);

	RoomModel isRoomExist(RoomRequest request);

	List<RoomModel> getAvailableRooms();

}
