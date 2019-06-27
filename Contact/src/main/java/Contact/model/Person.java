package contact.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import contact.PersonForm;

@Entity
public class Person implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2783593869842384377L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private int age;
	
	@OneToMany(mappedBy = "contact")
	private Collection<Email> emails;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public PersonForm getPersonForm()
	{
		PersonForm p=new PersonForm();
		p.setAge(age);
		p.setName(name);
		p.setId(id);
		return p;
	}
	public Collection<Email> getEmails() {
		return emails;
	}
	public void setEmails(Collection<Email> emails) {
		this.emails = emails;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
