package com.bloggapp.payload;



import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.bloggapp.entities.Category;
import com.bloggapp.entities.Comment;
import com.bloggapp.entities.User;


import lombok.NoArgsConstructor;
@NoArgsConstructor

public class PostDto {
	private Integer postId;
	
	private String title;
	
	private String content;
	
	private String imageName; 
	
	private Date addedDate;
	
	private CategoryDto category;
	
	private UserDto user;
	
	public Integer getPostId() {
		return postId;
	}
	public void setPostId(Integer postId) {
		this.postId = postId;
	}
	public Set<CommentDto> getComments() {
		return comments;
	}
	public void setComments(Set<CommentDto> comments) {
		this.comments = comments;
	}
	private Set<CommentDto>comments=new HashSet<>();
	

	public Integer getId() {
		return postId;
	}
	public void setId(Integer postId) {
		this.postId = postId;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public Date getAddedDate() {
		return addedDate;
	}
	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}
	public CategoryDto getCategory() {
		return category;
	}
	public void setCategory(CategoryDto category) {
		this.category = category;
	}
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}

	

}
