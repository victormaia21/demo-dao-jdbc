package model.dao;

import java.util.List;

import model.entities.Department;
import model.entities.Seller;

public interface Sellerdao {

	void insert(Seller obj);
	void update(Seller obj);
	void Deletebyid(Integer id);
	Seller findbyid(Integer id);
	List<Seller> findall();
	List<Seller> findbyidDepartment(Department department);
	List<Seller> findbyDepartment(Department department);
}
