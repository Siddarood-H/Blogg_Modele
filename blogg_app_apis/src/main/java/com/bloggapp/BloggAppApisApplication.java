package com.bloggapp;



import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.bloggapp.config.AppConstants;
import com.bloggapp.entities.Role;
import com.bloggapp.repositories.RoleRepo;

@SpringBootApplication
@ComponentScan(basePackages = "com.bloggapp")
public class BloggAppApisApplication implements CommandLineRunner {
	@Autowired
	private RoleRepo roleRepo;

	public static void main(String[] args) {
		SpringApplication.run(BloggAppApisApplication.class, args);
		System.out.println("hii i am siddarood and  i am a Java Developer");
	}
	@Bean
	public ModelMapper modelMapper()
	{
		return new  ModelMapper();
	}
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub	
	
	
	try {
		Role role=new Role();
		role.setId(AppConstants.ADMIN_USER);
		role.setName("ADMIN_USER");
		
		Role role1=new Role();
		role.setId(AppConstants.NORMAL_USER);
		role.setName("NORMAL_USER");
		List<Role>roles=List.of(role,role1);
		List<Role>results=this.roleRepo.saveAll(roles);
		results.forEach(r->{System.out.println(r.getName());});
		
	
	}catch(Exception e)
	{
		e.printStackTrace();
	}
		

	}
}
