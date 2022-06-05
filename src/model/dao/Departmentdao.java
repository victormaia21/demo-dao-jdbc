package model.dao;

import java.util.List;

import model.entities.Department;

public interface Departmentdao {

	void insert(Department obj);
	void update(Department obj);
	void deletebyid(Integer id);
	Department findybyid(Integer id);
	List<Department>findall();
}
