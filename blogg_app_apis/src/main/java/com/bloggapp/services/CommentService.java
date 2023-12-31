package com.bloggapp.services;


import java.util.List;
import java.util.Set;

import com.bloggapp.payload.CommentDto;

public interface CommentService {
	
	CommentDto createComment(CommentDto commentDto, Integer postId, Integer userId);
	
	CommentDto updateComment(CommentDto commentDto,Integer postId,Integer commentId);
	
	void deleteComment(Integer commentId);
	
	List<CommentDto> getAllCommnetByPostId(Integer postId);
	
	CommentDto getAllCommentByCommentId(Integer commentId);


	
	
	
	
	

}
