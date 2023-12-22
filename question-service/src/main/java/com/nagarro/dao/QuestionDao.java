package com.nagarro.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nagarro.model.Question;

@Repository
public interface QuestionDao extends JpaRepository<Question,Integer>{

	List<Question> findByCategory(String category);

	@Query(value="SELECT q.id FROM Question q WHERE q.category = :category ORDER BY RAND() LIMIT :numQ\r\n"
			+ "", nativeQuery=true)
	List<Integer> findRandomQuestionsByCategory(String category, int numQ);
	

}
