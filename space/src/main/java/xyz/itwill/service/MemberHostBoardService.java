package xyz.itwill.service;

import java.util.List;
import java.util.Map;

import xyz.itwill.dto.Question;
import xyz.itwill.dto.Review;
import xyz.itwill.dto.SelectMember;
import xyz.itwill.exception.BoardNotFoundException;
import xyz.itwill.exception.MemberNotFoundException;


public interface MemberHostBoardService {
	
	//Member Review
    void addReview(Review review) ;
    void modifyReview(Review review) throws BoardNotFoundException;
	void removeReview(int rNo) throws BoardNotFoundException; 
	int getReviewCount(String rMid) throws MemberNotFoundException;
	Review getReview(int nNo) throws BoardNotFoundException;
	
	
	//Member Question
	int getQuestionCount(String qMid) ;
	void removeQuestion(int qNo) throws BoardNotFoundException; 
	void addQuestion(Question question);
	void modifyQuestion(Question question) ;
	
	
	

}