package Application;

import java.util.Date;

import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		

		Department dp = new Department(2, "Victor");
		Seller sl = new Seller(23, "VICTOR", "JV@GMAIL.COM", new Date(), 2000.00, dp);
		
		System.out.println(sl);
	}

}
