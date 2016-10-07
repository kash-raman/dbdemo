package org.kash.tutorial.service;

import org.kash.tutorial.model.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by krangan on 10/6/2016.
 */
public class PersonDaoJDBCImpl implements PersonDao {
	@Override
	public List<Person> getAllPerson() {
		List personList = null;
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = getConnection(connection);
			statement = connection.prepareStatement("select * from person");
			ResultSet resultSet = statement.executeQuery();
			if(resultSet!=null){
				personList = new ArrayList();
			}
			while (resultSet.next()) {
				Person person = new Person();
				person.setId(resultSet.getInt(1));
				person.setFirstName(resultSet.getString(2));
				person.setLastName(resultSet.getString(3));
				personList.add(person);
			}
			statement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return personList;
	}

	@Override
	public Person getPerson(int id) {
		Person person = null;
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = getConnection(connection);
			statement = connection.prepareStatement("select * from person where id = ?");
			statement.setObject(1, id);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				person = new Person();
				person.setId(resultSet.getInt(1));
				person.setFirstName(resultSet.getString(2));
				person.setLastName(resultSet.getString(3));
			}
			statement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return person;
	}

	@Override
	public int getCount() {
		Connection connection = null;
		Statement statement = null;
		try {
			connection = getConnection(connection);
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select count(*) from person");
			while (resultSet.next()) {
				return resultSet.getInt(1);
			}
			statement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	private Connection getConnection(Connection connection) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
		connection = DriverManager.getConnection("jdbc:derby://localhost:1527/firstdb;create=true");
		return connection;
	}

}
