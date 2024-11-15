package com.dao.in_memory;

import java.util.List;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.util.List;

public class DbDeveloperDao implements DeveloperDao {
    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/venkat"; // Update with your database name
    private static final String USERNAME = "root"; // Replace with your MySQL username
    private static final String PASSWORD = "root"; // Replace with your MySQL password

    private static final String CREATE_DB = "CREATE TABLE IF NOT EXISTS DEVELOPER(" +
            "id INT PRIMARY KEY," +
            "name VARCHAR(255) NOT NULL);";
    private static final String SELECT_ALL = "SELECT * FROM DEVELOPER";
    private static final String SELECT = "SELECT * FROM DEVELOPER WHERE id = %d";
    private static final String INSERT_DATA = "INSERT INTO DEVELOPER (id, name) VALUES (%d , '%s')";
    private static final String UPDATE_DATA = "UPDATE DEVELOPER SET name = '%s' WHERE id = %d";
    private static final String DELETE_DATA = "DELETE FROM DEVELOPER WHERE id = %d";

    private final DbClient dbClient;

    public DbDeveloperDao() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl(CONNECTION_URL);
        dataSource.setUser(USERNAME);
        dataSource.setPassword(PASSWORD);

        dbClient = new DbClient(dataSource);
        dbClient.run(CREATE_DB);
        System.out.println("Developers data structure created");
    }

    @Override
    public void add(Developer developer) {
        dbClient.run(String.format(INSERT_DATA, developer.getId(), developer.getName()));
        System.out.println("Developer: Id " + developer.getId() + ", name: " + developer.getName() + " added");
    }

    @Override
    public List<Developer> findAll() {
        return dbClient.selectForList(SELECT_ALL);
    }

    @Override
    public Developer findById(int id) {
        Developer developer = dbClient.select(String.format(SELECT, id));

        if (developer != null) {
            System.out.println("Developer: Id " + id + ", found");
            return developer;
        } else {
            System.out.println("Developer: Id " + id + ", not found");
            return null;
        }
    }

    @Override
    public void update(Developer developer) {
        dbClient.run(String.format(UPDATE_DATA, developer.getName(), developer.getId()));
        System.out.println("Developer: Id " + developer.getId() + ", updated");
    }

    @Override
    public void deleteById(int id) {
        dbClient.run(String.format(DELETE_DATA, id));
        System.out.println("Developer: Id " + id + ", deleted");
    }
}
