package com.example.daoImpl;

import com.example.daoInterface.Dao;
import com.example.models.Payment;
import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PaymentDao implements Dao<Payment>{

	private Connection connection;
	private PersonDao personDao;
	private EventDao eventDao;

	public PaymentDao() {
		this.personDao = new PersonDao();
		this.eventDao = new EventDao();
	}
	
	@Override
	public Payment get(long id) {
		String sql = "SELECT * FROM payment WHERE payment_id=?";
		Payment payment = null;
		
		try {
			connect();
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				payment = new Payment(id, LocalDate.parse(rs.getString("payment_date")),
										rs.getFloat("payment_amount"), rs.getString("instalment_number"),
						eventDao.get(rs.getLong("event_id")),
						personDao.get(rs.getLong("person_id")));
				}
			pstmt.close();
			disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return payment;
	}

	@Override
	public List<Payment> getAll() {
		String sql = "SELECT * FROM payment";
		List<Payment> payments = new ArrayList<>();
		
		try {
			connect();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				payments.add(new Payment(rs.getLong("payment_id"), LocalDate.parse(rs.getString("payment_date")),
						rs.getFloat("payment_amount"), rs.getString("instalment_number"),
						eventDao.get(rs.getLong("event_id")),
						personDao.get(rs.getLong("person_id")) ) );
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return payments;
	}

	@Override
	public void add(Payment payment) {
		String sql = "INSERT INTO payment(payment_date, payment_amount, instalment_number ,person_id, event_id) VALUES (?,?,?,?,?)";

		try {
			connect();
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, payment.getPaymentDate().toString());
			pstmt.setFloat(2, payment.getPaymentAmount());
			pstmt.setString(3, payment.getInstalmentNumber());
			pstmt.setLong(4, payment.getPerson().getPersonId());
			pstmt.setLong(5, payment.getEvent().getEventId());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public int update(Payment payment) {
		String sql = "UPDATE payment SET payment_date = ?, payment_amount = ?, instalment_number = ?, event_id = ?, person_id = ?" + "WHERE payment_id = ?";
		int modified = 0;
		try {
			connect();
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, payment.getPaymentDate().toString());
			pstmt.setFloat(2, payment.getPaymentAmount());
			pstmt.setString(3, payment.getInstalmentNumber());
			pstmt.setLong(4, payment.getEvent().getEventId());
			pstmt.setLong(5, payment.getPerson().getPersonId());
			pstmt.setLong(6, payment.getPaymentId());
			modified  = pstmt.executeUpdate();
			pstmt.close();
			disconnect();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return modified;
	}

	@Override
	public int delete(long paymentId) {
		String sql = "DELETE FROM payment WHERE payment_id=?";
		int deleted = 0;
		try {
			connect();
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setLong(1, paymentId);
			deleted = pstmt.executeUpdate();
			disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return deleted;
	}
	
	private void connect() throws SQLException {
		SQLiteDataSource dataSource = new SQLiteDataSource();
		dataSource.setUrl("jdbc:sqlite:isp-sqlite.db");
		connection = dataSource.getConnection();
	}
	
	private void disconnect() throws SQLException {
		if(connection==null)
			return;
		connection.close();
		connection = null;
	}

}
