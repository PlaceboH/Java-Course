package com.example.daoImpl;

import com.example.daoInterface.Dao;
import com.example.models.Instalment;
import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class InstalmentDao implements Dao<Instalment>{
	
	private Connection connection;
	private EventDao eventDao;
	
	public InstalmentDao() {
		this.eventDao = new EventDao();
	}

	@Override
	public Instalment get(long id) {
		String sql = "SELECT * FROM instalment WHERE instalment_id=?";
		Instalment instalment = null;
		try {
			connect();
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				instalment = new Instalment(id, rs.getString("instalment_number"),
						rs.getFloat("payment"), LocalDate.parse(rs.getString("payment_date")),
						eventDao.get(rs.getLong("event_id")) );
			}
			pstmt.close();
			disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return instalment;
	}

	@Override
	public List<Instalment> getAll() {
		String sql = "SELECT * FROM instalment";
		List<Instalment> Instalments = new ArrayList<>();
		
		try {
			connect();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				Instalments.add(new Instalment(rs.getLong("instalment_id"),
						rs.getString("instalment_number"),
						rs.getFloat("payment"), LocalDate.parse(rs.getString("payment_date")),
						eventDao.get(rs.getLong("event_id"))
						));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Instalments;
	}

	@Override
	public void add(Instalment instalment) {
		String sql = "INSERT INTO instalment(instalment_number, payment, payment_date, event_id) VALUES (?,?,?,?)";

		try {
			connect();
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, instalment.getInstalmentNumber());
			pstmt.setFloat(2, instalment.getPayment());
			pstmt.setString(3, instalment.getPaymentDate().toString());
			pstmt.setLong(4, instalment.getEvent().getEventId());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public int update(Instalment instalment) {
		String sql = "UPDATE price_list SET instalment_number = ?, payment = ?, payment_date = ?, "
				+ "postcode = ?, event_id = ?, price_list_id = ?" + "WHERE instalment_id = ?";
		int modified = 0;
		try {
			connect();
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, instalment.getInstalmentNumber());
			pstmt.setFloat(2, instalment.getPayment());
			pstmt.setString(3, instalment.getPaymentDate().toString());
			pstmt.setLong(4, instalment.getEvent().getEventId());
			pstmt.setLong(5, instalment.getInstalmentId());
			modified  = pstmt.executeUpdate();
			pstmt.close();
			disconnect();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return modified;
	}

	@Override
	public int delete(long instalmentId) {
		String sql = "DELETE FROM instalment WHERE instalment_id=?";
		int deleted = 0;
		try {
			connect();
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setLong(1, instalmentId);
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
