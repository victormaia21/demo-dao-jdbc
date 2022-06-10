package model.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
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
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(
					"INSERT INTO seller "
					+ "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
					+ "VALUES "
					+ "(?, ?, ?, ?, ?)",Statement.RETURN_GENERATED_KEYS
					);
			
			ps.setString(1, obj.getName());
			ps.setString(2, obj.getEmail());
			ps.setDate(3,new java.sql.Date(obj.getBirthdate().getTime()));
			ps.setDouble(4, obj.getBasesalary());
			ps.setInt(5, obj.getDp().getId());
			
			int x = ps.executeUpdate();
			
			if(x>0) {
				ResultSet rs = ps.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs);
			}
			else {
				throw new DbException("ERRO INESPERADO, NÃO OCORREU A QUEBRA DE LINHA");
			}
		}
		
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(ps);
		}
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
				Department dep = instantiateDepartment(rs);
				Seller sel = instantiateseller(rs,dep);
				return sel;
			}
			return null;
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(ps);
			DB.closeResultSet(rs);
		}
		
	}

	private Seller instantiateseller(ResultSet rs, Department dep) throws SQLException {
		Seller sel = new Seller();
		sel.setId(rs.getInt("Id"));
		sel.setName(rs.getString("Name"));
		sel.setEmail(rs.getString("Email"));
		sel.setBirthdate(rs.getDate("BirthDate"));
		sel.setBasesalary(rs.getDouble("BaseSalary"));
		sel.setDp(dep);
		return sel;
	}

	private Department instantiateDepartment(ResultSet rs) throws SQLException {
		Department dep = new Department();
		dep.setName(rs.getString("DepName"));
		dep.setId(rs.getInt("DepartmentId"));
		return dep;
	}

	@Override
	public List<Seller> findall() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "ORDER BY Name");
			
			
			rs = ps.executeQuery();
			List<Seller>list = new ArrayList<Seller>();
			Map<Integer,Department>map = new HashMap<>();
			
			while(rs.next()) {
				Department dep = map.get(rs.getInt("DepartmentId"));
				
				if(dep == null) {
					dep = instantiateDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}
				Seller sel = instantiateseller(rs, dep);
				list.add(sel);
			}
			return list;
			
		}
		catch(SQLException e) {
			throw new db.DbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);
		}
	}

	@Override
	public List<Seller> findbyDepartment(Department department) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.DepartmentId = department.Id "
					+ "WHERE DepartmentId = ? "
					+ "ORDER BY Name");
			
			ps.setInt(1, department.getId());
			rs = ps.executeQuery();
			List<Seller>list = new ArrayList<Seller>();
			Map<Integer,Department>map = new HashMap<>();
			
			while(rs.next()) {
				Department dep = map.get(rs.getInt("DepartmentId"));
				
				if(dep == null) {
					dep = instantiateDepartment(rs);
					map.put(rs.getInt("DepartmentId"), dep);
				}
				Seller sel = instantiateseller(rs, dep);
				list.add(sel);
			}
			return list;
			
		}
		catch(SQLException e) {
			throw new db.DbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(ps);
		}
		
		
	}

	@Override
	public List<Seller> findbyidDepartment(Department department) {
		// TODO Auto-generated method stub
		return null;
	}

	
	

}
