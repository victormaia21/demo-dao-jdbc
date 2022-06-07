package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DbException;
import model.dao.Sellerdao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements Sellerdao {

	private Connection conn = null;
	
	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Seller obj) {
	}

	@Override
	public void update(Seller obj) {
	}

	@Override
	public void Deletebyid(Integer id) {
	}

	@Override
	public Seller findbyid(Integer id) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName\r\n "
					+ "FROM seller INNER JOIN department\r\n "
					+ "ON seller.DepartmentId = department.Id\r\n "
					+ "WHERE seller.Id = ?"
					);
			
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				Department dep = new Department();
				dep.setId(rs.getInt("DepartmentId"));
				dep.setName(rs.getString("DepName"));
				Seller sel = new Seller();
				sel.setName(rs.getString("Name"));
				sel.setEmail(rs.getString("Email"));
				sel.setId(rs.getInt("Id"));
				sel.setBirthdate(rs.getDate("BirthDate"));
				sel.setBasesalary(rs.getDouble("BaseSalary"));
				sel.setDp(dep);
				return sel;
			}
			return null;
			
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
	}

	@Override
	public List<Seller> findall() {
		return null;
	}

}
