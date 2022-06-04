package model.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Seller implements Serializable{

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String email;
	private Date birthdate;
	private double basesalary;
	private Department dp;
	
	Seller(){
	}

	
	public Seller(Integer id, String name, String email, Date birthdate, double basesalary, Department dp) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.birthdate = birthdate;
		this.basesalary = basesalary;
		this.dp = dp;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public double getBasesalary() {
		return basesalary;
	}

	public void setBasesalary(double basesalary) {
		this.basesalary = basesalary;
	}

	public Department getDp() {
		return dp;
	}

	public void setDp(Department dp) {
		this.dp = dp;
	}


	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Seller other = (Seller) obj;
		return Objects.equals(id, other.id);
	}


	@Override
	public String toString() {
		return "seller [id=" + id + ", name=" + name + ", email=" + email + ", birthdate=" + birthdate + ", basesalary="
				+ basesalary + ", dp=" + dp + "]";
	}
	
	
	
}
