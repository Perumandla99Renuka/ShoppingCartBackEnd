package com.niit.shoppingcart;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.niit.shoppingcart.dao.UserDetailsDAO;
import com.niit.shoppingcart.model.UserDetails;

@Controller
public class UserController {
	
	@Autowired
	UserDetailsDAO userDetailsDAO;
	
	@Autowired
	UserDetails userDetails;
	
    // when the clicked login 
	//base on the credentials, I want find whether he is admin or not 
	// if he is admin, i want to navigate to adminHome page
	// if the user, i want to navigate to home page
	// if the user does not exist with this credetails, i want to give error messsage
	
@RequestMapping("/login")	 
public ModelAndView login(@RequestParam(value="id") String id,
@RequestParam(value="password") String password,HttpSession session)
{
	ModelAndView mv = new ModelAndView("home");
	String msg;
	userDetails = userDetailsDAO.isValidUser(id, password);
	if(userDetails == null){
		msg = "Invalid User...please try again";
	}
	else
	{
		//find out whether the user is admin or not
		if (userDetails.getRole().equals("ROLE_ADMIN"))
		{
			mv= new ModelAndView("adminHome");
			
		}
			session.setAttribute("welcomemsg", userDetails.getName());
			session.setAttribute("userID", userDetails.getId());
	}

	return mv;

}

}


