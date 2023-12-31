package com.bloggapp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bloggapp.payload.ApiResponse;
import com.bloggapp.payload.UserDto;
import com.bloggapp.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
@Autowired
	private UserService userService;


	/*
	 * public UserController(UserService userService) { super(); this.userService =
	 * userService;
	 * 
	 * }
	 */
//Post-create user

@PostMapping("/create")
public ResponseEntity<UserDto>createUser(@Valid @RequestBody UserDto userDto)
{
	UserDto createUserDto=this.userService.createUser(userDto);
	return new ResponseEntity<>(createUserDto,HttpStatus.CREATED);
}
//Put-update user
@PutMapping("/{userId}")
public ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable("userId") Integer userId)
{
	UserDto updatedUser=this.userService.updateUser(userDto, userId);
	return ResponseEntity.ok(updatedUser);
	
	
	/* return new ResponseEntity<UserDto>(updatedUser,HttpStatus.OK);  this will also work */
}
//Delete -delete user
@DeleteMapping("/{userId}")
public ResponseEntity<ApiResponse>deleteUser(@PathVariable("userId") Integer userId)
{
	 this.userService.deleteUser(userId);
	 return new  ResponseEntity<ApiResponse>(new ApiResponse("user deleterd successfully",true),HttpStatus.OK);	
	 //return new  ResponseEntity(Map.of("message","user Deleted succesfully"),HttpStatus.ok); this will also work
}

//Get-getting user
@GetMapping("/get")
public ResponseEntity<List<UserDto>> getAllUsers()
{
	return ResponseEntity.ok(this.userService.getAllUsers());
	
	//we can create status object here also if we want
}
@GetMapping("/{userId}")
public ResponseEntity<UserDto> getUserById(@PathVariable("userId") Integer userId)
{
	return ResponseEntity.ok(this.userService.getUserById(userId));
	
}
}
