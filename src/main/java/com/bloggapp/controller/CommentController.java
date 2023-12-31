package com.bloggapp.controller;





import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PutExchange;

import com.bloggapp.exceptions.BlogAPIException;
import com.bloggapp.payload.ApiResponse;
import com.bloggapp.payload.CommentDto;
import com.bloggapp.services.CommentService;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
	@Autowired
	private CommentService commentService;
	
	@PostMapping("/create/{postId}/{userId}")
	public ResponseEntity<CommentDto>createComment(@RequestBody CommentDto commentDto, @PathVariable("postId") Integer postId,@PathVariable ("userId")Integer userId)
	{
		
		CommentDto createComment = this.commentService.createComment(commentDto,postId,userId);
		
		
		return  new ResponseEntity<CommentDto>(createComment,HttpStatus.CREATED);
		
	}
	@DeleteMapping("/delete/{commentId}")
	public ResponseEntity<ApiResponse>deleteComment(@PathVariable ("commentId") Integer commentId)
	{
		this.commentService.deleteComment(commentId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("comment deleted successfully!!",true),HttpStatus.OK);
		
	}
	@PutMapping("/update/{commentId}/{postId}")
	public ResponseEntity<BlogAPIException>UpdateComment(@RequestBody CommentDto commentDto,
			@PathVariable(value="commentId")Integer commentId,
			@PathVariable(value="postId")Integer postId )
	{
		CommentDto updateComment = this.commentService.updateComment(commentDto, postId, commentId);
		return new ResponseEntity<BlogAPIException>(HttpStatus.OK);
	}
	@GetMapping("/get/{postId}")
	public ResponseEntity<List<CommentDto>>getByPostId(@PathVariable("postId") Integer postId)
	{
		List<CommentDto> comments = this.commentService.getAllCommnetByPostId(postId);
		return  new ResponseEntity<List<CommentDto>>(comments,HttpStatus.OK);
	}
	
	@GetMapping("/get{commentId}")
	public ResponseEntity<CommentDto>getByCommentId(@PathVariable("commentId") Integer commentId)
	{
		CommentDto commentDto = this.commentService.getAllCommentByCommentId(commentId);
		return new ResponseEntity<CommentDto>(commentDto,HttpStatus.OK);
	}

}
