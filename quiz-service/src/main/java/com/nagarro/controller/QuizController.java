package com.nagarro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.model.QuestionWrapper;
import com.nagarro.model.QuizDto;
import com.nagarro.model.Response;
import com.nagarro.service.QuizService;

@RestController
@RequestMapping("/quiz")
public class QuizController {
	@Autowired
	QuizService quizService;
	@PostMapping("/create")
	public ResponseEntity<String>createQuiz(@RequestBody QuizDto quizDto){
		return quizService.createQuiz(quizDto.getCategoryName(),quizDto.getNumQuestions(),quizDto.getTitle());
	}
	@GetMapping("/get/{id}")
	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){
		return quizService.getQuizQuestions(id);
		
	}
	
	@PostMapping("/submit/{id}")
	public ResponseEntity<Integer>submitQuiz(@PathVariable Integer id, @RequestBody List<Response>responses){
		return quizService.calculateResult(id,responses);
	}

}
