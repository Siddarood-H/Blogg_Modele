package com.bloggapp.repositories;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bloggapp.entities.Comment;
@Repository
public interface CommentRepo extends JpaRepository<Comment, Integer> {
	
	@Query("select p from Post p where p.postId = :postId")
	List<Comment> findByPostId(@Param ("postId")Integer postId); 
	//SELECT c FROM Comment c WHERE c.postId = :postId
	
}
