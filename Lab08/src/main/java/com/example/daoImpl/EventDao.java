package com.example.daoImpl;

import com.example.daoInterface.Dao;
import com.example.models.Event;
import org.sqlite.SQLiteDataSource;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EventDao implements Dao<Event>{

	private Connection connection;

	public EventDao() {}
	
	@Override
	public Event get(long id) {
		String sql = "SELECT * FROM event WHERE event_id=?";
		Event event = null;
		
		try {
			connect();
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				event = new Event(id, rs.getString("location"), rs.getString("name"),
						LocalDate.parse(rs.getString("date")) );
				}
			pstmt.close();
			disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return event;
	}

	@Override
	public List<Event> getAll() {
		String sql = "SELECT * FROM event";
		List<Event> events = new ArrayList<>();
		
		try {
			connect();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				events.add(new Event(rs.getLong("event_id"), rs.getString("location"), rs.getString("name"),
						LocalDate.parse(rs.getString("date"))));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return events;
	}

	@Override
	public void add(Event event) {
		String sql = "INSERT INTO event(location, name, date) VALUES (?,?,?)";

		try {
			connect();
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, event.getLocation());
			pstmt.setString(2, event.getName());
			pstmt.setString(3, event.getLocalDate().toString());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public int update(Event event) {
		String sql = "UPDATE event SET location = ?, name = ?, date = ?" + "WHERE event_id = ?";
		int modified = 0;
		try {
			connect();
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, event.getLocation());
			pstmt.setString(2, event.getName());
			pstmt.setString(3, event.getLocalDate().toString());
			pstmt.setLong(4, event.getEventId());
			modified  = pstmt.executeUpdate();
			pstmt.close();
			disconnect();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return modified;
	}

	@Override
	public int delete(long eventId) {
		String sql = "DELETE FROM event WHERE event_id=?";
		int deleted = 0;
		try {
			connect();
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setLong(1, eventId);
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
