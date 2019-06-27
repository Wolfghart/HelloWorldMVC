package contact;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import contact.model.Email;
import contact.model.EmailRepository;
import contact.model.Person;
import contact.model.PersonRepository;

@Controller
public class WebController {
	
	@Autowired
	private PersonRepository personRepository;

	@ModelAttribute(name = "persons")
	public Iterable<Person> getPersons()
	{
		return personRepository.findAll();
	}
	
    @GetMapping("/results")
    public String results() {
        return "results";
    }
    
    
    @GetMapping("/addperson")
    public String addPersonForm(
    		PersonForm personForm,
    		HttpSession session) {
    	session.setAttribute("personID", null);
        return "formPerson";
    }
  
    
    @GetMapping("/updateperson/{id}")
    public String updatePersonForm(
    		@PathVariable Integer id, 
    		Model model ,
    		HttpSession session) {
    	Person p=personRepository.findById(id).get();
    	if (p==null)
    		return "redirect:listPersons";
    	session.setAttribute("personID", p.getId());
    	model.addAttribute("personForm", p.getPersonForm());
        return "formPerson";
    }

    @PostMapping("/formPerson")
    public String addOrUpdatePerson(@Valid PersonForm personForm, 
    		BindingResult bindingResult,
    		HttpSession session) {

        if (bindingResult.hasErrors()) {
            return "formPerson";
        }
        Object o=session.getAttribute("personID");
        if (o!=null)
        	personForm.setId((int)o);
        personRepository.save(personForm.getPerson());

        return "redirect:/listPersons";
    }
    
    
    
    @GetMapping("/listPersons")
    public String listPersons() {
        return "persons";
    }
    
    @GetMapping("/detailPerson/{id}")
    public String detailPerson(
    		@PathVariable Integer id,
    		Model model) {
    	model.addAttribute("person", 
    			personRepository.findById(id).get());
        return "detailPerson";
    }
    
    @Autowired
    private EmailRepository emailRepository;
    
    @GetMapping("/addemail/{personID}")
    public String addPersonForm(EmailForm emailForm, 
    		@PathVariable Integer personID) {

    	emailForm.setPersonID(personID);
        return "formEmail";
    }
    
    @GetMapping("/updateemail/{emailID}")
    public String updateEmailForm( 
    		@PathVariable Integer emailID,
    		Model model) {
    	Email e=emailRepository.findById(emailID).get();
    	if (e==null)
    		return "redirect:listPersons";
    	model.addAttribute("emailForm", e.getEmailForm());
        return "formEmail";
    }
    
    @PostMapping("/formEmail")
    public String addOrUpdateMail(
    		@Valid EmailForm emailForm, 
    		BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "formEmail";
        }
        emailRepository.save(
        		emailForm.getEmail(personRepository));

        return "redirect:/detailPerson/"+emailForm.getPersonID();
    }
    
    /*
     * Suppressions
     */
    
    
    @GetMapping("/deletePerson/{personID}")
    public String deletePerson( 
    		@PathVariable Integer personID) {
    	personRepository.deleteById(personID);
        return "redirect:/listPersons";
    }
    
    @GetMapping("/deleteEmail/{emailID}")
    public String deleteEmail( 
    		@PathVariable Integer emailID) {
    	Email e=emailRepository.findById(emailID).get();
    	if (e==null)
    		return "redirect:/listPersons";
    	emailRepository.deleteById(emailID);
    	return "redirect:/detailPerson/"
    				+e.getContact().getId();
    }
    
    
}
