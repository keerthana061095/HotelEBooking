package com.hotel.eBooking.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotel.eBooking.Dao.RoomDAO;
import com.hotel.eBooking.Model.RoomModel;
import com.hotel.eBooking.RequestClass.RoomRequest;
import com.hotel.eBooking.Service.RoomService;

@Service
public class RoomServImpl implements RoomService {
	
	@Autowired
	RoomDAO roomDAO;

	@Override
	public RoomModel createRoom(RoomRequest request) {
		RoomModel room = new RoomModel();
		room.setRoomId(request.getRoomId());
		room = roomDAO.createRoom(room);
		return room;
	}

	@Override
	public RoomModel isRoomExist(RoomRequest request) {
		RoomModel room = roomDAO.isRoomIdExist(request);
		return room;
	}

	@Override
	public List<RoomModel> getAvailableRooms() {
		List<RoomModel> room = roomDAO.getAvailableRooms();
		return room;
	}

}
