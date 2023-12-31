package com.bloggapp.services;

import java.util.List;

import com.bloggapp.entities.Post;
import com.bloggapp.payload.PostDto;
import com.bloggapp.payload.PostResponse;

public interface PostService {
	//create
	PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	
	//update 
	PostDto updatePost(PostDto postDto,Integer postId);
	
	//delete
	void deletePost(Integer postId);
	
	//get all posts
	PostResponse getAllPost(Integer pageNumbe,Integer pageSize,String sortBy,String sortDir);
	
	//get singlePost
	PostDto getPostById(Integer postId);
	
	//get all post by category
	PostResponse getPostByCategory(Integer categoryId,Integer pageNumber,Integer pageSize);
	
	//get all post by user
   PostResponse getPostByUser(Integer UserId,Integer pageNumber,Integer pageSize);
   
   //search posts
   List<PostDto>searchPosts(String keyword);
	
	
	

	



	
	

}
