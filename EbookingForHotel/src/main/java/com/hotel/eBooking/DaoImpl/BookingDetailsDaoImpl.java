package com.hotel.eBooking.DaoImpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hotel.eBooking.Config.HibernateConfig;
import com.hotel.eBooking.Dao.BookingDetailsDAO;
import com.hotel.eBooking.Model.BookingDetailsModel;
import com.hotel.eBooking.RequestClass.BookingDetailsRequest;

@Repository
public class BookingDetailsDaoImpl implements BookingDetailsDAO {
	
	@Autowired
	HibernateConfig conf;
	
	@Autowired
	JdbcTemplate jdbc;
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public BookingDetailsModel saveBooking(BookingDetailsModel book) {
		SessionFactory sf = conf.sessionFactory().getObject();
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		try {
			session.save(book);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return book;
	}

	@Override
	public List<BookingDetailsModel>  getCountForTheDay(String dt) {
		List<BookingDetailsModel> resultList = new ArrayList<>();
		try {
			Session session = sessionFactory.getCurrentSession();
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaQuery<BookingDetailsModel> cq = cb.createQuery(BookingDetailsModel.class);
			Root<BookingDetailsModel> rootEntry = cq.from(BookingDetailsModel.class);
			List<Predicate> p = new ArrayList<>();
			p.add(cb.equal(rootEntry.get("fromDate"), dt));
			p.add(cb.equal(rootEntry.get("status"), 0));
			p.add(cb.equal(rootEntry.get("status"), 1));
			if (!p.isEmpty()) {
				Predicate[] pr = new Predicate[p.size()];
				pr = p.toArray(pr);
				cq.where(pr);
			}
			resultList =   session.createQuery(cq).getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultList;
	}
	


	@Override
	public void checkIn(BookingDetailsRequest request) {
		try {
			Session session = sessionFactory.getCurrentSession();
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaUpdate<BookingDetailsModel> cu = cb.createCriteriaUpdate(BookingDetailsModel.class);
			Root<BookingDetailsModel> root = cu.from(BookingDetailsModel.class);
			cu.set("status", 1);
			cu.where(cb.equal(root.get("bookingId"), request.getBookingId()));
			Transaction trans = session.beginTransaction();
			session.createQuery(cu).executeUpdate();
			trans.commit();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void checkOut(BookingDetailsRequest request) {
		try {
			Session session = sessionFactory.getCurrentSession();
			CriteriaBuilder cb = session.getCriteriaBuilder();
			CriteriaUpdate<BookingDetailsModel> cu = cb.createCriteriaUpdate(BookingDetailsModel.class);
			Root<BookingDetailsModel> root = cu.from(BookingDetailsModel.class);
			cu.set("status", 2);
			cu.where(cb.equal(root.get("bookingId"), request.getBookingId()));
			Transaction trans = session.beginTransaction();
			session.createQuery(cu).executeUpdate();
			trans.commit();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public BookingDetailsModel getRoomDtails(BookingDetailsRequest request) {
		SessionFactory sf = conf.sessionFactory().getObject();
		Session session = sf.openSession();
		BookingDetailsModel bookingDetails = null;
		try {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<BookingDetailsModel> criteria = builder.createQuery(BookingDetailsModel.class);
			Root<BookingDetailsModel> root = criteria.from(BookingDetailsModel.class);
			bookingDetails = session.createQuery(criteria.select(root).where(builder.equal(root.get("bookingId"),request.getBookingId())))
					.getSingleResult();
		} catch (Exception e) {
			 e.printStackTrace();
		} finally {
			session.close();
		}
		
		return bookingDetails;
	}
}
