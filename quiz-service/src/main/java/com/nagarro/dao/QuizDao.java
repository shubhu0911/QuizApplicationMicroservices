package com.nagarro.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.model.Quiz;

public interface QuizDao extends JpaRepository<Quiz, Integer>{

}
