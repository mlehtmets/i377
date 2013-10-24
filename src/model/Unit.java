package model;

import javax.persistence.*;

@Entity
public class Unit{	
	@Id
    @SequenceGenerator(name = "my_seq", sequenceName = "SEQ1", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq")
	private Long Id;

	private Long super_unit_id;

	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		this.Id = id;
	}

	private String name;
	private String code;
	
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

	
	//Argumentideta konstruktor!!!
	public Unit() {}

	
	@Override
	public String toString() {
		return "Unit [id=" + Id + ", name=" + name + ", code=" + code + " super_unit_id= " + super_unit_id + "]";
	}

	public Long getSuper_unit_id() {
		return super_unit_id;
	}
	public void setSuper_unit_id(Long super_unit_id) {
		this.super_unit_id = super_unit_id;
	}

	
	
	
}