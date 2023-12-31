package com.bloggapp.servicesImpl;


import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bloggapp.config.AppConstants;
import com.bloggapp.entities.Role;
import com.bloggapp.entities.User;
import com.bloggapp.exceptions.ResourceNotFoundException;
import com.bloggapp.payload.UserDto;
import com.bloggapp.repositories.RoleRepo;
import com.bloggapp.repositories.UserRepo;
import com.bloggapp.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepo userRepo;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private PasswordEncoder passwordencoder;
	@Autowired
	private RoleRepo roleRepo;

	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.dtoToUser(userDto);

		User saveUser = this.userRepo.save(user);

		return this.userToDto(saveUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());

		User updateUser = this.userRepo.save(user);
		UserDto useDto1 = this.userToDto(updateUser);

		return useDto1;
		// return this.userToDto(this.userRepo.save(user));
	}

	@Override
	public UserDto getUserById(Integer userId) {

		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = this.userRepo.findAll();

		List<UserDto> userDtos = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = this.userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
		this.userRepo.delete(user);

	}

	// converting userDto to userEntity
	private User dtoToUser(UserDto userDto)
	{
		User user=this.modelMapper.map(userDto, User.class);
		
		  
	/*	  user.setId(userDto.getId());
		 //  user.setName(userDto.getName());
		  //user.setEmail(userDto.getEmail()); 
		 // user.setPassword(userDto.getPassword());
		 //user.setAbout(userDto.getAbout());*/
		 
		  return user;
	
	}

	private UserDto userToDto(User user) {
		UserDto userDto = this.modelMapper.map(user, UserDto.class);

		/*
		 * UserDto userDto=new UserDto(); userDto.setId(user.getId());
		 * userDto.setName(user.getName()); userDto.setEmail(user.getEmail());
		 * userDto.setPassword(user.getPassword()); userDto.setAbout(user.getAbout());
		 */

		return userDto;
	}

	@Override
	public UserDto registerNewUSer(UserDto userDto) {
	User user=this.modelMapper.map(userDto, User.class);
	
	//we have encoded the password
	user.setPassword(this.passwordencoder.encode(user.getPassword()));
	
	//roles 
	Role role = this.roleRepo.findById(AppConstants.NORMAL_USER).get();
	Role role1=this.roleRepo.findById(AppConstants.ADMIN_USER).get();
	
	user.getRoles().add(role);
	user.getRoles().add(role1);
	User newUser = this.userRepo.save(user);
		return this.modelMapper.map(newUser, UserDto.class);
	}

}
