package com.hotel.eBooking.DaoImpl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hotel.eBooking.Config.HibernateConfig;
import com.hotel.eBooking.Dao.RoomDAO;
import com.hotel.eBooking.Model.RoomModel;
import com.hotel.eBooking.RequestClass.RoomRequest;

@Repository
public class RoomDaoImpl implements RoomDAO {
	
	@Autowired
	HibernateConfig conf;
	
	@Autowired
	JdbcTemplate jdbc;
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public RoomModel createRoom(RoomModel room) {
		SessionFactory sf = conf.sessionFactory().getObject();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(room);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return room;
	}

	@Override
	public RoomModel isRoomIdExist(RoomRequest request) {
		SessionFactory sf = conf.sessionFactory().getObject();
		Session session = sf.openSession();
		List<RoomModel> roomId = null;
		try {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<RoomModel> criteria = builder.createQuery(RoomModel.class);
			Root<RoomModel> root = criteria.from(RoomModel.class);
			roomId = session.createQuery(criteria.select(root).where(builder.equal(root.get("roomId"), request.getRoomId())))
					.getResultList();
		} catch (Exception e) {
			 e.printStackTrace();
		} finally {
			session.close();
		}
		
		return roomId.isEmpty() ? null : roomId.get(0);
	}

	@Override
	public List<RoomModel> getAvailableRooms() {
		SessionFactory sf = conf.sessionFactory().getObject();
		Session session = sf.openSession();
		List<RoomModel> room = null;
		try {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<RoomModel> criteria = builder.createQuery(RoomModel.class);
			Root<RoomModel> root = criteria.from(RoomModel.class);
			room = session.createQuery(criteria.select(root).where(builder.equal(root.get("availability"),0)))
					.getResultList();
		} catch (Exception e) {
			 e.printStackTrace();
		} finally {
			session.close();
		}
		
		return room;
	}

	@Override
	public void updateRoomAvailability(String roomId) {
		try {
			Session session = sessionFactory.getCurrentSession();
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaUpdate<RoomModel> cu = cb.createCriteriaUpdate(RoomModel.class);
			Root<RoomModel> root = cu.from(RoomModel.class);
			cu.set("availability", true);
			cu.where(cb.equal(root.get("roomId"), roomId));
			Transaction trans = session.beginTransaction();
			session.createQuery(cu).executeUpdate();
			trans.commit();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public RoomModel checkRoomAvailability(String roomId, int num) {
		SessionFactory sf = conf.sessionFactory().getObject();
		Session session = sf.getCurrentSession();
		RoomModel room = null;
		try {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<RoomModel> criteria = builder.createQuery(RoomModel.class);
			Root<RoomModel> root = criteria.from(RoomModel.class);
			room = session.createQuery(criteria.select(root).where(builder.equal(root.get("roomId"),roomId),
					builder.equal(root.get("availability"), num))).getSingleResult();
		} catch (Exception e) {
			 return null;
		}
		return room;
	}

	@Override
	public void updateRoomAvailabilityAfterCheckOut(String roomId) {
		try {
			Session session = sessionFactory.getCurrentSession();
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaUpdate<RoomModel> cu = cb.createCriteriaUpdate(RoomModel.class);
			@SuppressWarnings("rawtypes")
			Root root = cu.from(RoomModel.class);
			cu.set("availability", false);
			cu.where(cb.equal(root.get("roomId"), roomId));
			Transaction trans = session.beginTransaction();
			session.createQuery(cu).executeUpdate();
			trans.commit();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<RoomModel> getTotalRooms() {
		SessionFactory sf = conf.sessionFactory().getObject();
		Session session = sf.openSession();
		List<RoomModel> room = null;
		try {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<RoomModel> criteria = builder.createQuery(RoomModel.class);
			Root<RoomModel> root = criteria.from(RoomModel.class);
			room = session.createQuery(criteria.select(root))
					.getResultList();
		} catch (Exception e) {
			 e.printStackTrace();
		} finally {
			session.close();
		}
		
		return room;
	}
}
