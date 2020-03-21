package com.evertz.contact.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.evertz.contact.model.Contact;

public class ContactDAOImpl implements ContactDAO {
	
	
	private JdbcTemplate jdbcTemplate;
	
	public ContactDAOImpl(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	@Override
	public int save(Contact contact) {
		String sql = "INSERT INTO contact (name,email,address,phone) VALUE (?,?,?,?)";
		return jdbcTemplate.update(sql, contact.getName(), contact.getEmail(),contact.getAddress(),contact.getPhone());
	
	}

	@Override
	public int update(Contact contact) {
		String sql = "UPDATE contact SET name=?, email=?, address=?, phone=? WHERE contact_id=?";
		return jdbcTemplate.update(sql, contact.getName(), contact.getEmail(),contact.getAddress(),contact.getPhone(), contact.getId());
	}

	@Override
	public Contact get(Integer id) {
		String sql = "SELECT * FROM contact WHERE contact_id="+id;
		ResultSetExtractor<Contact> extractor = new ResultSetExtractor<Contact>() {

			@Override
			public Contact extractData(ResultSet rs) throws SQLException, DataAccessException {
				if(rs.next()) {
					String name  = rs.getString("name");
					String email  = rs.getString("email");
					String address  = rs.getString("address");
					String phone  = rs.getString("phone");
					return new Contact(id, name, email, address, phone);

				}
				return null;
			}
		};
		return jdbcTemplate.query(sql, extractor);
	}

	@Override
	public int delete(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Contact> list() {
		// TODO Auto-generated method stub
		return null;
	}

}
