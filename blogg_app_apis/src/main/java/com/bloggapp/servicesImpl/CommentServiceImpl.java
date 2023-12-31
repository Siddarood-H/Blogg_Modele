package com.bloggapp.servicesImpl;


import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bloggapp.entities.Comment;
import com.bloggapp.entities.Post;
import com.bloggapp.entities.User;
import com.bloggapp.exceptions.BlogAPIException;
import com.bloggapp.exceptions.ResourceNotFoundException;
import com.bloggapp.payload.CommentDto;
import com.bloggapp.repositories.CommentRepo;
import com.bloggapp.repositories.PostRepo;
import com.bloggapp.repositories.UserRepo;
import com.bloggapp.services.CommentService;
@Service
public class CommentServiceImpl implements CommentService {
@Autowired
	private PostRepo postRepo;

@Autowired
private UserRepo userRepo;
@Autowired
private CommentRepo commentRepo;
@Autowired
private ModelMapper modelMapp;
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId,Integer userId) {
		
		Post post= this.postRepo.findById(postId)
				.orElseThrow(()->new ResourceNotFoundException("Post","post_ID",postId));
		User user= this.userRepo.findById(userId)
				.orElseThrow(()->new ResourceNotFoundException("User","user_ID",userId));
		Comment comment = this.modelMapp.map(commentDto, Comment.class);
		comment.setPost(post);
	    comment.setUser(user);
		Comment savedComment = commentRepo.save(comment);
		return this.modelMapp.map(savedComment, CommentDto.class);
		
	}
	@Override
	public void deleteComment(Integer commentId) {
		Comment comment = this.commentRepo.findById(commentId)
				.orElseThrow(()->new ResourceNotFoundException("Comment","comment_Id",commentId));
		this.commentRepo.delete(comment);
	}

	@Override
	public CommentDto updateComment(CommentDto commentDto, Integer postId, Integer commentId) {
		Post post = this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","post_ID",postId));
		Comment comment = this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","comment_ID",commentId));
		
		 if(!comment.getPost().getPostId().equals(post.getPostId()))
		 { 
			 throw new BlogAPIException(HttpStatus.NOT_FOUND,"CommentDoenotBelong");
		 }
			 comment.setContent(commentDto.getContent());
			 Comment updatedComment=this.commentRepo.save(comment);
		return this.modelMapp.map(updatedComment, CommentDto.class);
	}

	@Override
	public List<CommentDto> getAllCommnetByPostId(Integer postId) {
		List<Comment> comments = this.commentRepo.findByPostId(postId);
		List<CommentDto> commentDtos = comments.stream().map((post)->modelMapp.map(post, CommentDto.class)).collect(Collectors.toList());
		return commentDtos;
			
		
	}

	@Override
	public CommentDto getAllCommentByCommentId(Integer commentId) {
	 Comment comment = this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","Comment_ID",commentId));
	 
	 
		return this.modelMapp.map(comment, CommentDto.class);
	}
	

}
