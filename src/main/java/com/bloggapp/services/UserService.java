package com.bloggapp.services;

import java.util.List;

import com.bloggapp.entities.User;
import com.bloggapp.payload.UserDto;

public interface UserService {
	
	
	UserDto registerNewUSer(UserDto userDto);
	
	 UserDto createUser(UserDto userDto);
	 
	 UserDto updateUser(UserDto useDtor,Integer userId);
	 
	 UserDto getUserById(Integer userId);
	 
	 List<UserDto>getAllUsers();
	 
	 void deleteUser(Integer userId);
	 

}
