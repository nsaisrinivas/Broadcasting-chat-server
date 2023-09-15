package com.sample.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sample.exception.BussinessException;
import com.sample.model.RegistrationModel;
import com.sample.repository.RegistrationRepository;

@Service
public class RegistrationService {

	private final RegistrationRepository repository;
	
	public RegistrationService(RegistrationRepository repository) {
		super();
		this.repository = repository;
	}
	/*
	 * get the path and file to upload the file in the corresponding path with mobile number as name of the file*/
	public String uploadImage(String path,MultipartFile file,String mobilenumber) throws IOException {
		String updatedname="";
		try {
		 String name=file.getOriginalFilename();
		 if(name!=null) {
		 updatedname=mobilenumber.concat(name.substring(name.lastIndexOf(".")));}
		 String filepath=path+File.separator+updatedname;
		 File f=new File(path);
		 if(!f.exists())
		 {
			 f.mkdir();
		 }
		 
		 Files.copy(file.getInputStream(), Paths.get(filepath));
		 }catch(NullPointerException e) {
			 return "";
		 }
		return updatedname;
	}
	/*
	 * search whether the entered mobile number is already present in the database or not.*/
	public boolean searchmobile(String mobilenumber) {
		if(repository.findmobile(mobilenumber)!=null) {
			return false;
		}
		return true;
	}
	/*
	 * getting the details of user from the register servlet and inserting them into the auth table.*/
	public RegistrationModel register(RegistrationModel  registrationmodel) {
		if(registrationmodel==null) {
			throw new BussinessException("insufficient data to store in the database");
		}
			return repository.save(registrationmodel);
	}
	
	/*
	 * Validating the obtained details from the register servlet and sending the usedetails back if the validation is successful
	 * */
	public List<String> validate(RegistrationModel registrationmodel){
		List<String> list=new ArrayList<>();
		RegistrationModel model=repository.findbymobilenumber(registrationmodel.getMobilenumber(), registrationmodel.getPassword());
		if(model!=null) {
			list.add(model.getName());
			list.add(model.getMobilenumber());
		}
		else {
			return Collections.emptyList();
		}
		return list;
	}
}
