package no.accelerate.assignmet2.repositories;

import no.accelerate.assignmet2.dao.models.Customer;
import no.accelerate.assignmet2.dao.models.CustomerCountry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;

@Repository
public class CustomerCountryRepoImpl implements CustomerCountryRepo{
    private final String url;
    private final String username;
    private final String password;

    public CustomerCountryRepoImpl(@Value("${spring.datasource.url}") String url,
                                   @Value("${spring.datasource.username}") String username,
                                   @Value("${spring.datasource.password}") String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    @Override
    public List<CustomerCountry> getAll() {
        return null;
    }

    @Override
    public CustomerCountry getById(int id) {
        return null;
    }

    @Override
    public List<CustomerCountry> getByName(String name) {
        return null;
    }

    @Override
    public List<CustomerCountry> getLimit(int i, int j) {
        return null;
    }

    public CustomerCountry getCountryWithHighestCount() {
        CustomerCountry customerCountry = null;
        String sql = "SELECT country, COUNT(*) AS country_count FROM customer GROUP BY country ORDER BY country_count DESC LIMIT 1";
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                customerCountry = new CustomerCountry(
                        result.getString("country"),
                        result.getInt("country_count")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Highest country count");
        return customerCountry;
    }
}