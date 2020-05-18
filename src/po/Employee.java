package po;

import java.sql.*;
import java.util.*;

public class Employee {

	private java.sql.Date birthday;
	private String name;
	private Integer id;
	private Double salary;



	public java.sql.Date getBirthday() {
		return birthday ;
	}

	public String getName() {
		return name ;
	}

	public Integer getId() {
		return id ;
	}

	public Double getSalary() {
		return salary ;
	}

	public void setBirthday(java.sql.Date name ) {
		this.birthday = name;
	}

	public void setName(String name ) {
		this.name = name;
	}

	public void setId(Integer name ) {
		this.id = name;
	}

	public void setSalary(Double name ) {
		this.salary = name;
	}

}
