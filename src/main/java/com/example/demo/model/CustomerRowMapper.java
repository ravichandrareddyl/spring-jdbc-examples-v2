package com.example.demo.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;
@Configuration
public class CustomerRowMapper implements RowMapper<Customer>{
	
	public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
		Customer cust = new Customer();
        cust.setId(rs.getInt("id"));
        cust.setName(rs.getString("name"));
        return cust;
    }
}
