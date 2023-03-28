package xyz.itwill.dao;

import java.util.List;
import java.util.Map;

import xyz.itwill.dto.Pay;
import xyz.itwill.dto.Question;
import xyz.itwill.dto.Review;
import xyz.itwill.dto.SelectMember;
import xyz.itwill.dto.Space;
import xyz.itwill.dto.Review;

public interface MemberHostBoardDAO {
	
	
	//Member Review
	 int insertReview(Review review);
	 int updateReview(Review review);
	 int deleteReview(int rNo); 
	 int selectReviewCount(String rMid);
	 Review selectReview(int rNo);
	 List<SelectMember> selectReviewList(Map<String, Object> map);
	 List<Pay> selectReserveListmId(String mId);
	
	//Member Reserve
	List<SelectMember> SelectReserveList(Map<String, Object> map);
	int deleteReserve(int pNo);
	int selectReserveCount(String pMid);

	
	//Member Question
	Question selectQuestion(int qNo);
	List<SelectMember> SelectQuestionList(Map<String, Object> map);
	int deleteQuestion(int qNo);
	int selectQuestionCount(String qMid);
	int insertQuestion(Question question);
	int updateQuestion(Question question);
	

	//Space Review
	int selectSpaceReviewCount(int rSno);
	List<SelectMember> selectSpaceReviewList(Map<String, Object> map);
		
	//Space Question
	int selectSpaceQuestionCount(int qSno);
	List<SelectMember> selectSpaceQuestionList(Map<String, Object> map);
		
	
	
	//Host Reserve
	int selectHostReserveCount(String hId);
	//int selectHostReserve(String hId);
	List<SelectMember> selectHostReserveList(Map<String, Object> map);

	//Host Review
	int selectHostReviewCount(String hId);
	List<SelectMember> selectHostReviewList(Map<String, Object> map);
	
	//Host Question
	int selectHostQuestionCount(String hId);
	List<SelectMember> selectHostQuestionList(Map<String, Object> map);
	
	// Filter 
	double selectStarAverage(int sNo);
	List<Space> selectSpaceSno(Map<String, Object> params);
	int selectSpaceCount(Map<String, Object> params);
	List<Space> selectSpaceList(Map<String, Object> map);
	    
}
