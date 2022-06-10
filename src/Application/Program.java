package Application;

import java.util.List;

import model.dao.Daofactory;
import model.dao.Sellerdao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		

		Sellerdao sellerdao = Daofactory.createseller();
		
		Seller seller = sellerdao.findbyid(3);
		System.out.println("=== TEST 1: seller findById =====");
		System.out.println(seller);
		
		
		System.out.println("\n=== TEST 2: seller findByDepartment =====");
		Department department = new Department(3, null);
		List<Seller> list = sellerdao.findbyDepartment(department);
		for (Seller obj : list) {
			System.out.println(obj);
		}
		
		System.out.println("\\n=== TEST 3: seller findAll =====");
		list = sellerdao.findall();
		for(Seller obj : list) {
			System.out.println(obj);
		}
		
		}
		

	

}
	

