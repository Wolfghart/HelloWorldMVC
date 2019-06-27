package contact.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import contact.EmailForm;

@Entity
public class Email {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String email;
	
	@ManyToOne
	@JoinColumn(name="contact_fk")
	private Person contact;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Person getContact() {
		return contact;
	}

	public void setContact(Person contact) {
		this.contact = contact;
	}
	
	public EmailForm getEmailForm()
	{
		EmailForm ef=new EmailForm();
		ef.setId(id);
		ef.setEmail(email);
		ef.setPersonID(contact.getId());
		return ef;
	}
	
	
}
