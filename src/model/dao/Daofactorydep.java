package model.dao;

import db.DB;
import model.dao.impl.DepartmentdaoJDBC;

public class Daofactorydep {

    public static Departmentdao createdepartment() {
    	return new DepartmentdaoJDBC(DB.getConnection());
    }

}

