package com.example.demo.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

import com.example.demo.model.Customer;
import com.example.demo.model.CustomerRowMapper;

@Configuration
public class ExampleDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private CustomerRowMapper custRowMaper;

	public List<Customer> getListOfUsers() {

		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
				.withCatalogName("DEMOPKG").withProcedureName("GETCUSTOMERS")
				.returningResultSet("CUST_RESULTS", custRowMaper);

		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("p_id", "1");
		SqlParameterSource in = new MapSqlParameterSource(inParamMap);

		Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
		System.out.println(simpleJdbcCallResult);

		return (List<Customer>) simpleJdbcCallResult.get("CUST_RESULTS");
	}

	public int[] batchUpdate(final List<Customer> customers) {
		int[] updateCounts = jdbcTemplate.batchUpdate(
				"update CUSTOMER set id = ?, " + "name = ? where email = ?",
				new BatchPreparedStatementSetter() {
					public void setValues(PreparedStatement ps, int i)
							throws SQLException {
						ps.setInt(1, customers.get(i).getId());
						ps.setString(2, customers.get(i).getName());
						ps.setString(3, customers.get(i).getEmail());
					}

					public int getBatchSize() {
						return customers.size();
					}
				});
		return updateCounts;
	}
	
	public Customer readCust(int id) { 
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
			.withCatalogName("DEMOPKG")
			.withProcedureName("GETCUSTOMERS");

		Map<String, Object> inParamMap = new HashMap<String, Object>();
		inParamMap.put("p_id", "1");
		SqlParameterSource in = new MapSqlParameterSource(inParamMap);
        
        Map out = simpleJdbcCall.execute(in);
        Customer cust = new Customer();
        cust.setId(id);
        cust.setName((String) out.get("out_name"));
        return cust;
    }

	
	

}
