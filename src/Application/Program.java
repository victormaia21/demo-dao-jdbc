package Application;

import model.dao.Daofactory;
import model.dao.Sellerdao;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		

		Sellerdao sellerdao = Daofactory.createseller();
		Seller seller = sellerdao.findbyid(3);
		
		System.out.println(seller);

}
	
}
