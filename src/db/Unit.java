package db;

public class Unit {
	
	private String name;
	private String code;
	private int id;
	
	public Unit(){}
	
	public Unit(String name, String code){
		this.name = name;
		this.setCode(code);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}