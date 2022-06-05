package Application;

import model.dao.Daofactory;
import model.dao.Sellerdao;

public class Program {

	public static void main(String[] args) {
		

		Sellerdao sellerdao = Daofactory.createselleSellerdao();
		
	}

}
