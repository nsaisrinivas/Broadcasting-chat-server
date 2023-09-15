package com.sample.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.sample.exception.BussinessException;
import com.sample.model.RegistrationModel;
import com.sample.services.RegistrationService;

@Controller
public class RegisterServlet {
	private static Logger logger = Logger.getLogger(RegisterServlet.class);
	String index = "index";
	String signup="signup";
	
	/*
	 * Configure the logger */
	public RegisterServlet() {
		super();
		BasicConfigurator.configure();
	}

	@Autowired
	private RegistrationService registrationservice;
	
	/*
	 * Assign the path to store the images to variable called path.*/
	@Value("${project.image}")
	private String path;

	/*
	 * Redirect to the starting page*/
	@GetMapping("/")
	public String signuppage() {
		return signup;
	}

	/*
	 * Redirect to the signup page*/
	@GetMapping("/signup")
	public String redirect() {
		return signup;
	}

	/*
	 * Redirect to login page*/
	@GetMapping("/index")
	public String login1() {
		return index;
	}

	/*
	 * Redirect to the global chat*/
	@GetMapping("/chatroom")
	public String chat() {
		return "chatroom";
	}

	/*
	 * logout from the appliation.*/
	@GetMapping("/logout")
	public String logout() {
		logger.info("logged out...");
		return "logout";
	}

	/**
	 * Getting the required details from the user from the form and sending the
	 * details to the regiserDAO to insert into the database.
	 * @throws IOException 
	 */
	@PostMapping("/register")
	public String register(HttpServletRequest request,@RequestParam("image") MultipartFile image) throws IOException {
		HttpSession session=request.getSession();
		String error="error";
		String password = request.getParameter("password");
		String passenc = Base64.getEncoder().encodeToString(password.getBytes());
		String mobilenumber=request.getParameter("mobilenumber");
		if (registrationservice.searchmobile(mobilenumber)) {
			String filename = this.registrationservice.uploadImage(path, image, mobilenumber);
			RegistrationModel registrationmodel = new RegistrationModel(request.getParameter("name"), passenc,
					mobilenumber, filename);
			try {
				registrationservice.register(registrationmodel);
				if(session.getAttribute(error)!=null) {
					session.removeAttribute(error);
				}
			} catch (BussinessException e) {
				logger.info(e.getErrorMessage());
			}
			logger.info("User Registered");
			return index;
		}
		session.setAttribute(error, "mobilenumber already exist");
		return "signup";
	}

	/**
	 * Getting the user mobilenumber and password from the form and passing those
	 * details to the logindao(Database package) for validation and maintaining the
	 * userdetails session.
	 */
	@PostMapping("/login")
	public String login(HttpServletRequest request) {
		String error="error";
		HttpSession session = request.getSession();
		String password = request.getParameter("password");
		String passenc = Base64.getEncoder().encodeToString(password.getBytes());
		RegistrationModel model = new RegistrationModel();
		model.setMobilenumber(request.getParameter("mobilenumber"));
		model.setPassword(passenc);
		List<String> userdetails = registrationservice.validate(model);
		try {
		if (userdetails == null || userdetails.isEmpty()) {
			session.setAttribute(error, "Wrong Crendentials");
			throw new BussinessException("Wrong Credetials");
		}}catch(BussinessException e) {
			logger.info(e.getErrorMessage());
			return index;
		}
		logger.info("User LoggedIn..");
		session.removeAttribute(error);
		session.setAttribute("userdetails", userdetails);
		return "redirect:/chatroom";

	}

}
