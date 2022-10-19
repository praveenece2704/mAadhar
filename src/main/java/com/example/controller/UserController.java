package com.example.controller;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.ApplicationDetails;
import com.example.model.DeathDetails;
import com.example.model.Registration;
import com.example.model.UserLogin;
import com.example.repository.AdminRepository;
import com.example.repository.ApplicationRepository;
import com.example.repository.DeathRepository;
import com.example.repository.RegisterationRepository;
import com.example.repository.UserRepository;

@RestController
@RequestMapping("/")
public class UserController {
	
	@Autowired
	AdminRepository adrepo;
	
	@Autowired
	ApplicationRepository aprepo;
	
	@Autowired
	RegisterationRepository regrepo;
	
	@Autowired
	UserRepository urepo;
	
	@Autowired
	DeathRepository drepo;

	@PostMapping("register")
	public Registration register(@RequestBody Registration r)
	{
		return  regrepo.save(r);
	}
	
	@PostMapping("login")
	public String login(@RequestBody UserLogin u)
	{
		String email= u.getEmail();
		String password=u.getPassword();
		System.out.println(email+" "+password);
		if(email!=null && password !=null)
		{
			if(regrepo.findByEmail(email).equals(regrepo.findByPassword(password)))
			{
				urepo.save(u);
				return "Login Successfull";
			}
			else
			{
				return "Please Register before Login";
			}
		}
		else
		{
			return "Please enter the valid details";
		}
	}
	
	@PostMapping("appdetails")
	public ApplicationDetails appdetails(@RequestBody ApplicationDetails app)
	{
		Random r=new Random();
		int randomnumber=r.nextInt(999999);
		String formatted=String.format("%05d", randomnumber);
		app.setCitizenId(Integer.parseInt(formatted));
		app.setApplicationId(Integer.parseInt(formatted)/2);
		return aprepo.save(app);
	}
	
    // for checking purpose from PostMan
	@GetMapping("getAllUsers")
	public List<Registration> getallusers()
	{
		return this.regrepo.findAll();
	}
	
	@GetMapping("getAllLogin")
	public List<UserLogin> getalllogin()
	{
		return urepo.findAll();
	}
	
	@GetMapping("getAppDetails/{email}")
	public Optional<ApplicationDetails> getalldetails(@PathVariable String email)
	{
		return aprepo.findById(email);
	}
	

	@GetMapping("updateAadhar")
	public ApplicationDetails updateaadhar(@RequestBody ApplicationDetails a )
	{
		String email=a.getEmail();
		aprepo.deleteById(email);
		return aprepo.save(a);	
	}
	
	@GetMapping("applyDuplicate/{email}")
	public Optional<ApplicationDetails> applyduplicate(@PathVariable String email)
	{
		return aprepo.findById(email);
	}
	
	@GetMapping("viewStatus/{email}")
	public Object viewstatus(@PathVariable String email)
	{
		return aprepo.findByCurrentStatus(email);
	}
	
	@PostMapping("deathUpdate")
	public DeathDetails deadthUpdate(@RequestBody DeathDetails data)
	{
		return drepo.save(data);
	}

}
