package com.dao.in_memory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DbClient {
    private final DataSource dataSource;

    public DbClient(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void run(String str) {
        try (Connection con = dataSource.getConnection(); // Statement creation
             Statement statement = con.createStatement()
        ) {
            statement.executeUpdate(str); // Statement execution
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Developer select(String query) {
        List<Developer> developers = selectForList(query);
        if (developers.size() == 1) {
            return developers.get(0);
        } else if (developers.size() == 0) {
            return null;
        } else {
            throw new IllegalStateException("Query returned more than one object");
        }
    }

    public List<Developer> selectForList(String query) {
        List<Developer> developers = new ArrayList<>();

        try (Connection con = dataSource.getConnection();
             Statement statement = con.createStatement();
             ResultSet resultSetItem = statement.executeQuery(query)
        ) {
            while (resultSetItem.next()) {
                // Retrieve column values
                int id = resultSetItem.getInt("id");
                String name = resultSetItem.getString("name");
                Developer developer = new Developer(id, name);
                developers.add(developer);
            }

            return developers;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return developers;
    }
}