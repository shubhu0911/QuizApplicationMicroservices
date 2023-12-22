package com.nagarro.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nagarro.dao.QuizDao;
import com.nagarro.feign.QuizInterface;
import com.nagarro.model.QuestionWrapper;
import com.nagarro.model.Quiz;
import com.nagarro.model.Response;

@Service
public class QuizService {
	@Autowired
	QuizDao quizDao;
//	@Autowired
//	QuestionDao questionDao;
	@Autowired
	QuizInterface quizInterface;

	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

		
//		List<Integer>questions=//call the generate url- RestTemplate http://localhost:8080/question/generate
//		Quiz quiz=new Quiz();
//		quiz.setTitle(title);
//		quiz.setQuestions(questions);
//		quizDao.save(quiz);
		List<Integer>questions=quizInterface.getQuestionsForQuiz(category, numQ).getBody();
		Quiz quiz=new Quiz();
		quiz.setTitle(title);
		quiz.setQuestionIds(questions);
		quizDao.save(quiz);
		return new ResponseEntity<>("Success",HttpStatus.CREATED);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
		// TODO Auto-generated method stub
		Quiz quiz=quizDao.findById(id).get();
		List<Integer>questionIds=quiz.getQuestionIds();
		quizInterface.getQuestionsFromId(questionIds);
		ResponseEntity<List<QuestionWrapper>>questions=quizInterface.getQuestionsFromId(questionIds);

		return questions;
	}

	public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
		ResponseEntity<Integer>score=quizInterface.getScore(responses);
		return score;
	}
	
	

}
