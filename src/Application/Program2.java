package Application;

import java.util.List;

import model.dao.Daofactorydep;
import model.dao.Departmentdao;
import model.entities.Department;

public class Program2 {

	public static void main(String[] args) {
		
		Departmentdao department = Daofactorydep.createdepartment();
		
		System.out.println("Findall");
		List<Department>list = department.findall();
		
		for(Department obj : list) {
			System.out.println(obj);
		}
		
		System.out.println("Findybyid");
		Department dep = department.findybyid(4);
		System.out.println(dep);
		
		
		System.out.println("Deletebyid");
		department.deletebyid(5);
		System.out.println("Id DELETADO COM SUCESSO");
		
		System.out.println("update");
		Department newdep = new Department(6, "Bundas");
		department.update(newdep);
		
		System.out.println("Insert");
		dep.setName("Bungas");
		dep.setId(10);
		department.insert(dep);
		
		System.out.println("Intens inserido no banco de dados");
		

	}

}
