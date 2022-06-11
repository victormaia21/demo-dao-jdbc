package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.Departmentdao;
import model.entities.Department;

public class DepartmentdaoJDBC implements Departmentdao{
	
	private Connection conn = null;
	
	public DepartmentdaoJDBC(Connection conn) {
		this.conn = conn;
	}
	@Override
	public void insert(Department obj) {
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(
					"INSERT INTO department "
					+ "(Id, Name) "
					+ "VALUES "
					+ "(?, ?)"
					);
			
			ps.setInt(1, obj.getId());
			ps.setString(2, obj.getName());
			
			ps.executeUpdate();
		}
		catch (SQLException e){
			throw new db.DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(ps);
		}
		
	}

	@Override
	public void update(Department obj) {
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(
					"UPDATE department "
					+ "SET Name = ? "
					+ "WHERE Id = ?"
					);
			
			ps.setString(1, obj.getName());
			ps.setInt(2, obj.getId());
			
			
			ps.executeUpdate();
		}
		catch(SQLException e) {
			throw new db.DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(ps);
		}
		
	}

	@Override
	public void deletebyid(Integer id) {
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement(
					"DELETE FROM department "
		        	 + "WHERE Id = ?"
					);
			
			ps.setInt(1, id);
			ps.executeUpdate();
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(ps);
		}
		
	}

	@Override
	public Department findybyid(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement(
					"SELECT * FROM department WHERE Id = ?"
					);
			
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				Department dep = instantiantedepartment(rs);
				return dep;
			}
			return null;
			
			
			
		}
		catch(SQLException e) {
			throw new db.DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
		
	}

	@Override
	public List<Department> findall() {
		PreparedStatement ps = null;
	    ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement(
					"SELECT * FROM department ORDER BY Name");
					
			
			rs = ps.executeQuery();
			List<Department>list = new ArrayList<>();
			while(rs.next()) {
				Department dep = instantiantedepartment(rs);
			list.add(dep);
			}
			return list;
			
		}
		catch(SQLException e) {
			throw new db.DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
	}
	private Department instantiantedepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setId(rs.getInt("Id"));
		dep.setName(rs.getString("Name"));
	return dep;	
	}

}
