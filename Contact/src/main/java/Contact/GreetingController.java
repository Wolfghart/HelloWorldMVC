package contact;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@SessionAttributes("valueSession2")
public class GreetingController {

    @GetMapping("/greeting")
    public String greeting(
    		@RequestParam(name="name", required=false, defaultValue="World") String name, 
    		Model model) {
        model.addAttribute("name", name);
        return "greeting";
    }
    
    @GetMapping("/testValues")
    public String testValues(
    		Model model,
    		HttpSession session,
    		@CookieValue(value = "foo", defaultValue = "hello") String fooCookie,
    		HttpServletResponse response) {
        return testValues(-1, model, session, fooCookie, response);
    }
    
    @GetMapping("/testValues/{id}")
    public String testValues(
    		@PathVariable int id, 
    		Model model,
    		HttpSession session,
    		@CookieValue(value = "foo", defaultValue = "hello") String fooCookie,
    		HttpServletResponse response) {
        model.addAttribute("id", id);
        if (session.getAttribute("valueSession")==null)
        	session.setAttribute("valueSession", ""+id);
        model.addAttribute("foo", fooCookie.toString());
        response.addCookie(new Cookie("foo", ""+id));
        return "values";
    }
    
    private int value=42;
    @ModelAttribute("value") 
    public int getValue()
    {
    	return value;
    }
    
    private int valueSession2=42;
    @ModelAttribute("valueSession2") 
    public int getValueSession2()
    {
    	return valueSession2;
    }
     
    @RequestMapping("/endsession")
    public String endSession(SessionStatus status, HttpServletRequest request){
        return "redirect:testValues";
    }
 
    @ModelAttribute("someList")
    public List<String> getSomeList(){
    	return Arrays.asList("Element 1", "Element 2", "Element 3");
    }
   
    
    

}
