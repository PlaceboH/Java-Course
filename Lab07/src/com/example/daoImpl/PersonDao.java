package com.example.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.sqlite.SQLiteDataSource;

import com.example.daoInterface.Dao;
import com.example.models.Person;

public class PersonDao implements Dao<Person>{
	
	private Connection connection = null;

	@Override
	public Person get(long id) {
		String sql = "SELECT first_name, second_name FROM person WHERE person_id=?";
		Person person = null;
		
		try {
			connect();
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				person = new Person(id, rs.getString("first_name"), rs.getString("second_name"));
			}
			pstmt.close();
			disconnect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return person;
	}

	@Override
	public List<Person> getAll() {
		String sql = "SELECT person_id, first_name, second_name FROM person";
		List<Person> people = new ArrayList<>();
		
		try {
			connect();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while(rs.next()) {
				people.add(new Person(rs.getLong("person_id"), rs.getString("first_name"), rs.getString("second_name")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return people;
	}

	@Override
	public void add(Person person) {
		String sql = "INSERT INTO person(first_name,second_name) VALUES (?,?)";

		try {
			connect();
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, person.getFirstName());
			pstmt.setString(2, person.getSecondName());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public int update(Person person) {
		String sql = "UPDATE person SET first_name = ?, second_name = ? " + "WHERE person_id = ?";
		int modified = 0;
		try {
			connect();
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, person.getFirstName());
			pstmt.setString(2, person.getSecondName());
			pstmt.setLong(3, person.getPersonId());
			modified  = pstmt.executeUpdate();
			pstmt.close();
			disconnect();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return modified;
	}

	@Override
	public int delete(long personId) {
		String sql = "DELETE FROM person WHERE person_id=?";
		int deleted = 0;
		try {
			connect();
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setLong(1, personId);
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
