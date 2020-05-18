package po;

import java.sql.*;
import java.util.*;

public class Department {

	private String address;
	private String name;
	private Integer id;



	public String getAddress() {
		return address ;
	}

	public String getName() {
		return name ;
	}

	public Integer getId() {
		return id ;
	}

	public void setAddress(String name ) {
		this.address = name;
	}

	public void setName(String name ) {
		this.name = name;
	}

	public void setId(Integer name ) {
		this.id = name;
	}

}
