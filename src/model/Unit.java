package model;

import java.util.List;

import javax.persistence.*;
@Entity
@NamedQueries({ 
	@NamedQuery(name="Person.findAll", query="SELECT p FROM Unit p"),
	@NamedQuery(name="Person.findByName", query="SELECT p FROM Unit p WHERE p.name = :name"),
})
public class Unit {

	private String name;
	private String code;
	
	@Id
    @SequenceGenerator(name="my_seq", sequenceName="SEQ_1", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="my_seq")
	private int id;
	
	@OneToMany(mappedBy = "unit",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE},
            fetch = FetchType.LAZY,
            orphanRemoval = true)
	private List<Unit> units;
	
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
	
	//Argumentideta konstruktor!!!
	public Unit() {}

	
	@Override
	public String toString() {
		return "Unit [id=" + id + ", name=" + name + ", code=" + code + "]";
	}
	
	
	
}