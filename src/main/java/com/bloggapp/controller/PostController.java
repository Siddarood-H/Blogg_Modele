package com.bloggapp.controller;



import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bloggapp.config.AppConstants;
import com.bloggapp.entities.Post;
import com.bloggapp.payload.ApiResponse;
import com.bloggapp.payload.PostDto;
import com.bloggapp.payload.PostResponse;
import com.bloggapp.services.FileService;
import com.bloggapp.services.PostService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
public class PostController {
	@Autowired
	private PostService postService;
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;
	
	//create
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
			@PathVariable ("userId")Integer userId,
			@PathVariable("categoryId") Integer categoryId)
	{
		
		PostDto createPost = this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
	}
	
	//get By user
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<PostResponse>getPostByUser(@PathVariable("userId")Integer userId,@RequestParam(value="pageNumber",defaultValue=AppConstants.PAGE_NUMBER,required=false)Integer pageNumber ,
			@RequestParam(value="pageSize",defaultValue=AppConstants.PAGE_SIZE,required=false) Integer pageSize)
	{
		 PostResponse postByUser = this.postService.getPostByUser(userId,pageNumber,pageSize);
		
		return new ResponseEntity<PostResponse>(postByUser,HttpStatus.OK);	
	}
	//getBy category
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<PostResponse>getPostByCategory(@PathVariable("categoryId")Integer categoryId,@RequestParam(value="pageNumber",defaultValue=AppConstants.PAGE_NUMBER,required=false)Integer pageNumber ,
			@RequestParam(value="pageSize",defaultValue=AppConstants.PAGE_SIZE,required=false) Integer pageSize)
	{
		 PostResponse postByCategory = this.postService.getPostByCategory(categoryId,pageNumber,pageSize);
		
		return new ResponseEntity<PostResponse>(postByCategory,HttpStatus.OK);
	}
	//getAllPosts
	@GetMapping("/get/posts")
	public ResponseEntity<PostResponse>getAllPosts(@RequestParam(value="pageNumber",defaultValue=AppConstants.PAGE_NUMBER,required=false)Integer pageNumber ,
			@RequestParam(value="pageSize",defaultValue=AppConstants.PAGE_SIZE,required=false) Integer pageSize,
			@RequestParam(value ="sortBy",defaultValue=AppConstants.SORT_BY,required=false)String sortBy,
			@RequestParam(value = "sortDir",defaultValue=AppConstants.SORT_DIR,required = false)String sortDir)
	{
		 PostResponse postResponse = this.postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);
		return new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
	}
	
	//getpostById
	@GetMapping("/{postId}")
	public ResponseEntity<PostDto>getpostById(@PathVariable("postId") Integer postId)
	{
		PostDto postById = this.postService.getPostById(postId);
		
		return new ResponseEntity<PostDto>(postById,HttpStatus.OK);
		
	}
	
	//deletePost
	@DeleteMapping("/{postId}")
	public ResponseEntity<ApiResponse>deletePost(@PathVariable("postId") Integer postId)
	{
		this.postService.deletePost(postId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("data Deleted Successfully",true),HttpStatus.OK);
	}
	
	@PutMapping("/{postId}")
	public ResponseEntity<PostDto>updatePost(@RequestBody PostDto postDto,@PathVariable("postId") Integer postId)
	{
		PostDto updatePost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
	}
	
	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostDto>>searchPostByTitle(@PathVariable("keywords") String Keywords)
	{
		List<PostDto> searchPosts = this.postService.searchPosts(Keywords);
		return new ResponseEntity<List<PostDto>>(searchPosts,HttpStatus.OK);
	}
	//post image upload
	@PostMapping("/postimage/upload/{postId}")
	public ResponseEntity<PostDto>uploadImage(@RequestParam ("image") MultipartFile image,@PathVariable("postId") Integer postId)throws IOException
	{
		PostDto postDto = this.postService.getPostById(postId);
		String fileName = this.fileService.uploadImage(path, image);
	
		postDto.setImageName(fileName);
		PostDto updatePost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
		
	}
	//method to serve Files
	
	@GetMapping(value="post/profiles/{imageName}",produces=MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(@PathVariable("imageName")String
imageName, HttpServletResponse response) throws IOException{
		
		InputStream resource =this.fileService.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource,response.getOutputStream());
		
		
	}

}
