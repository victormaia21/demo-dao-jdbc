package Application;

import java.util.Date;
import java.util.List;

import model.dao.Daofactory;
import model.dao.Sellerdao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		

		Sellerdao sellerdao = Daofactory.createseller();
		
		
		System.out.println("=== TEST 1: seller findById =====");
		Seller seller = sellerdao.findbyid(3);
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
		
		
		System.out.println("\\n=== TEST 4: seller insert =====");
		Seller newseller = new Seller(null, "Victor", "vc@gmail.com", new Date(), 4000.0, department);
		sellerdao.insert(newseller);
		System.out.println("Insert " + newseller.getId());
		
		
		System.out.println("\\n=== TEST 5: seller Update =====");
		seller = sellerdao.findbyid(1);
		seller.setName("Martha weiner");
		 sellerdao.update(seller);
		System.out.println("Update completo");
		
		System.out.println("\\n=== TEST 6: seller delete =====");
		sellerdao.Deletebyid(2);
		System.out.println("Deletado com sucesso");
		
		
		
		
		}
		

	

}
	

