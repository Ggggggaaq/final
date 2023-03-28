package xyz.itwill.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import xyz.itwill.dao.MemberHostBoardDAO;
import xyz.itwill.dto.Faq;
import xyz.itwill.dto.Host;
import xyz.itwill.dto.Member;
import xyz.itwill.dto.Notice;
import xyz.itwill.dto.Pay;
import xyz.itwill.dto.Question;
import xyz.itwill.dto.Review;
import xyz.itwill.dto.SelectMember;
import xyz.itwill.dto.Space;
import xyz.itwill.exception.BoardNotFoundException;
import xyz.itwill.exception.MemberNotFoundException;
import xyz.itwill.exception.SpaceNotFoundException;
import xyz.itwill.service.MemberService;
import xyz.itwill.service.MemberHostBoardService;
import xyz.itwill.util.Pager;


@Controller
@RequiredArgsConstructor
public class MemberHostBoardController {
	
	private final MemberHostBoardService memberHostBoardService;
	private final MemberHostBoardDAO memberHostBoardDao;
	
			
	
			//Member Review Controller
			
		
	     	//member_review_write 페이지 이동.
			@RequestMapping(value="/member_review_write", method = RequestMethod.GET)
			public String reviewWrite(HttpSession session,Model model)  {
				Member loginMember = (Member)session.getAttribute("loginMember");
				
				List<Pay> reserveList=memberHostBoardDao.selectReserveListmId(loginMember.getMId());	 //회원 아이디를 통해 예약리스트 출력.
				model.addAttribute("mId",loginMember.getMId()); // 회원 아이디 추가.
			    model.addAttribute("reserveList", reserveList); // 구매한 내역list 추가
			    
				return "member/member_review_write";
			}
		
		    //Review 입력값 작성 후 게시글 등록 클릭했을경우 삽입 후 member_review페이지로 이동
			@RequestMapping(value = "member_review_write", method = RequestMethod.POST)
			public String reviewWrite(@ModelAttribute Review review) {
				
				
				memberHostBoardService.addReview(review);
				return "redirect:/member_review";
			}

			//rNo값을 전달받아 member_review_modify페이지로 이동처리.
			
			@RequestMapping(value ="member_review_modify/{rNo}", method = RequestMethod.GET)
			public String reviewModify(@PathVariable int rNo, Model model) throws BoardNotFoundException {
				model.addAttribute("review", memberHostBoardService.getReview(rNo)); //rNo값을 통해 현재 review테이블에 저장된 값 Model객체에 전달.
				return "member/member_review_modify";
			}
			
			//Form태그를 통해 전달받은 값들을 review 객체에 update처리.
			@RequestMapping(value ="member_review_modify", method = RequestMethod.POST)
			public String reviewModify(@RequestParam int rNo, @ModelAttribute Review modifiedReview) throws BoardNotFoundException {
				Review review= memberHostBoardService.getReview(rNo);
			    review.setRContent(modifiedReview.getRContent());
			    review.setRTitle(modifiedReview.getRTitle());
			    review.setRStar(modifiedReview.getRStar());
			    memberHostBoardService.modifyReview(review);	
			    return "redirect:/member_review";
			}
			
			//선택된 게시글을 전달받아 해당 게시글을 삭제 처리
			@RequestMapping(value ="member_review_delete/{rNo}" , method = RequestMethod.DELETE)
			@ResponseBody
			public String reviewRemove(@PathVariable int rNo) throws BoardNotFoundException {
				memberHostBoardService.removeReview(rNo);
				return "success";
			}
			
			

			//Review 전체 리스트 출력 시 페이징 처리를 위해 Json형식의 text로 Map객체 전달
					
					@RequestMapping(value = "member_reviewList", method = RequestMethod.GET)
					@ResponseBody
					public Map<String, Object> ReviewList(@RequestParam(defaultValue = "1") int pageNum,HttpSession session) {
						Member loginMember=(Member)session.getAttribute("loginMember");
						int totalQuestion=memberHostBoardDao.selectReviewCount(loginMember.getMId());
						int pageSize=6;
						int blockSize=5;
						
						Pager pager=new Pager(pageNum, totalQuestion, pageSize, blockSize);
						
						Map<String, Object> pageMap=new HashMap<String, Object>();
						pageMap.put("startRow", pager.getStartRow());
						pageMap.put("endRow", pager.getEndRow());
						pageMap.put("rMid", loginMember.getMId());
						
						List<SelectMember> reviewList=memberHostBoardDao.selectReviewList(pageMap);
						
						Map<String, Object> resultMap=new HashMap<String, Object>();
						resultMap.put("reviewList", reviewList);
						resultMap.put("pager", pager);

						return resultMap;
					}	
					
				
			
			
			
			///////////////////////////////////////////////////////////////////////////////////////////////////
			
			//Member Q&A Controller
			

			//게시글 등록 클릭했을경우 member_qna_write페이지로 이동
			@RequestMapping(value = "member_question_write", method = RequestMethod.GET)
			public String qnawrite(HttpSession session,Model model) {
				Member loginMember = (Member)session.getAttribute("loginMember");
				model.addAttribute("mId", loginMember.getMId());
				return "member/member_qna_write";
			}
			
			//입력값 작성 후 게시글 등록 클릭했을경우 삽입 후 member_qna페이지로 이동
			@RequestMapping(value = "member_question_write", method = RequestMethod.POST)
			public String qnawrite(@ModelAttribute Question question) {
				memberHostBoardService.addQuestion(question);
				return "redirect:/member_qna";
			}
			
			//rNo값을 전달받아 member_question_modify페이지로 이동처리.
			
			@RequestMapping(value ="member_question_modify/{qNo}", method = RequestMethod.GET)
			public String questionModify(@PathVariable int qNo, Model model) throws BoardNotFoundException {
				model.addAttribute("question", memberHostBoardDao.selectQuestion(qNo)); //rNo값을 통해 현재 review테이블에 저장된 값 Model객체에 전달.
				return "member/member_qna_modify";
			}
			
			//Form태그를 통해 전달받은 값들을 review 객체에 update처리.
			@RequestMapping(value ="member_question_modify", method = RequestMethod.POST)
			public String questionModify(@RequestParam int qNo, @ModelAttribute Question modifiedQuestion) throws BoardNotFoundException {
				Question question= memberHostBoardDao.selectQuestion(qNo);
				question.setQContent(modifiedQuestion.getQContent());
				question.setQTitle(modifiedQuestion.getQTitle());
			    memberHostBoardService.modifyQuestion(question);	
			    return "redirect:/member_qna";
			}
			

			//선택된 게시글을 전달받아 해당 게시글을 삭제 처리
			@RequestMapping(value ="member_question_delete/{qNo}" , method = RequestMethod.DELETE)
			@ResponseBody
			public String questionRemove(@PathVariable int qNo) throws BoardNotFoundException {
				memberHostBoardService.removeQuestion(qNo);
				return "success";
			}
			
			
			//Question 전체 리스트 출력 시 페이징 처리를 위해 Json형식의 text로 Map객체 전달
			
			@RequestMapping(value = "member_questionList", method = RequestMethod.GET)
			@ResponseBody
			public Map<String, Object> questionList(@RequestParam(defaultValue = "1") int pageNum,HttpSession session) {
				Member loginMember=(Member)session.getAttribute("loginMember");
				int totalQuestion=memberHostBoardService.getQuestionCount(loginMember.getMId());
				int pageSize=6;
				int blockSize=5;
				
				Pager pager=new Pager(pageNum, totalQuestion, pageSize, blockSize);
				
				Map<String, Object> pageMap=new HashMap<String, Object>();
				pageMap.put("startRow", pager.getStartRow());
				pageMap.put("endRow", pager.getEndRow());
				pageMap.put("qMid", loginMember.getMId());
				List<SelectMember> questionList=memberHostBoardDao.SelectQuestionList(pageMap);
				
				Map<String, Object> resultMap=new HashMap<String, Object>();
				resultMap.put("questionList", questionList);
				resultMap.put("pager", pager);
				
				return resultMap;
			}	
			
			
			
			///////////////////////////////////////////////////////////////////////////////////////////////////
			
		
			//Member Reserve Controller		
					
					//notice 전체 리스트 출력 시 페이징 처리를 위해 Json형식의 text로 Map객체 전달
					
					@RequestMapping(value = "member_reserveList", method = RequestMethod.GET)
					@ResponseBody
					public Map<String, Object> ReserveList(@RequestParam(defaultValue = "1") int pageNum,HttpSession session) {
						Member loginMember=(Member)session.getAttribute("loginMember");
						int totalQuestion=memberHostBoardDao.selectReserveCount(loginMember.getMId());
						int pageSize=6;
						int blockSize=5;
						
						Pager pager=new Pager(pageNum, totalQuestion, pageSize, blockSize);
						
						Map<String, Object> pageMap=new HashMap<String, Object>();
						pageMap.put("startRow", pager.getStartRow());
						pageMap.put("endRow", pager.getEndRow());
						pageMap.put("pMid", loginMember.getMId());
						List<SelectMember> reserveList=memberHostBoardDao.SelectReserveList(pageMap);
						
						Map<String, Object> resultMap=new HashMap<String, Object>();
						resultMap.put("reserveList", reserveList);
						resultMap.put("pager", pager);
					
						return resultMap;
					}	
					
				///////////////////////////////////////////////////////////////////////////////////////////////////
							
				
					
				//Space Controller		
					
					
					//space 전체 리스트 출력 시 페이징 처리를 위해 Json형식의 text로 Map객체 전달
					@RequestMapping(value = "member_spaceList", method = RequestMethod.POST)
					@ResponseBody
					public Map<String, Object> SpaceList(@RequestParam(defaultValue = "1") int pageNum,@RequestParam Map<String, Object> params) {	
						
						
						
						//필터를 통해 전달받은 모든 parameter를 params Map객체에 저장.
						int totalQuestion=memberHostBoardDao.selectSpaceCount(params);
						int pageSize=6;
						int blockSize=5;
						
						//페이징 처리를 위한 객체 생성.
						Pager pager=new Pager(pageNum, totalQuestion, pageSize, blockSize);
						
						
						if(totalQuestion != 0) {
							//sNo를 받아와서 출력하기 위한 객체 생성.
							List<Space> spaceList2=memberHostBoardDao.selectSpaceSno(params);
							//double과 int값을 저장하기 위해 Number 사용.
							List<Number> sNoList = new ArrayList<>();
							
							for (Space space : spaceList2) {
								//소숫점을 추가하기 위해 double사용.
								double averagestar = memberHostBoardDao.selectStarAverage(space.getSNo());
						        sNoList.add(space.getSNo());
						        sNoList.add(averagestar);
						    }
							
							
							//SQL 매퍼에 적용하기 위해 pageMap객체 생성 후 입력값 전달.
							Map<String, Object> pageMap=new HashMap<String, Object>();
							pageMap.put("startRow", pager.getStartRow());
							pageMap.put("endRow", pager.getEndRow());
							pageMap.put("sNoList", sNoList);
							pageMap.put("sort", (String)params.get("sort"));
							
							
							List<Space> spaceList=memberHostBoardDao.selectSpaceList(pageMap);
							
							Map<String, Object> resultMap=new HashMap<String, Object>();
							resultMap.put("spaceList", spaceList);
							resultMap.put("pager", pager); 
							resultMap.put("sNoList", sNoList); 
							return resultMap;
											}
						
						
						else {
					        Map<String, Object> resultMap = new HashMap<String, Object>();
					        resultMap.put("spaceList", new ArrayList<Space>()); // 빈 리스트 추가
					        resultMap.put("pager", pager);
					        return resultMap;
					    }
						
					}
					
					
					///////////////////////////////////////////////////////////////////////////////////////////////////
					
					
					//Host Review Controller
					
					
					//host Review 전체 리스트 출력 시 페이징 처리를 위해 Json형식의 text로 Map객체 전달
					
					@RequestMapping(value = "host_reviewList", method = RequestMethod.GET)
					@ResponseBody
					public Map<String, Object> hostReviewList(@RequestParam(defaultValue = "1") int pageNum,HttpSession session) {
						Host loginHost=(Host)session.getAttribute("loginHost");
						int totalQuestion=memberHostBoardDao.selectHostReviewCount(loginHost.getHId());
						int pageSize=6;
						int blockSize=5;
						
						Pager pager=new Pager(pageNum, totalQuestion, pageSize, blockSize);
						
						Map<String, Object> pageMap=new HashMap<String, Object>();
						pageMap.put("startRow", pager.getStartRow());
						pageMap.put("endRow", pager.getEndRow());
						pageMap.put("hId", loginHost.getHId());
						
						List<SelectMember> reviewList=memberHostBoardDao.selectHostReviewList(pageMap);
						
						Map<String, Object> resultMap=new HashMap<String, Object>();
						resultMap.put("reviewList", reviewList);
						resultMap.put("pager", pager);
						return resultMap;
					}	
					
					

					//Host  Reserve Controller
					
					
					//Host  Reserve 전체 리스트 출력 시 페이징 처리를 위해 Json형식의 text로 Map객체 전달
					
					@RequestMapping(value = "host_reserveList", method = RequestMethod.GET)
					@ResponseBody
					public Map<String, Object> hostReserveList(@RequestParam(defaultValue = "1") int pageNum,HttpSession session) {
						Host loginHost=(Host)session.getAttribute("loginHost");
						int totalQuestion=memberHostBoardDao.selectHostReserveCount(loginHost.getHId());
						int pageSize=6;
						int blockSize=5;
						
						Pager pager=new Pager(pageNum, totalQuestion, pageSize, blockSize);
						
						Map<String, Object> pageMap=new HashMap<String, Object>();
						pageMap.put("startRow", pager.getStartRow());
						pageMap.put("endRow", pager.getEndRow());
						pageMap.put("hId", loginHost.getHId());
						
						List<SelectMember> reserveList=memberHostBoardDao.selectHostReserveList(pageMap);
						
						Map<String, Object> resultMap=new HashMap<String, Object>();
						resultMap.put("reserveList", reserveList);
						resultMap.put("pager", pager);
						return resultMap;
					}	
					

					//Host Question Controller
					
					
					//Question 전체 리스트 출력 시 페이징 처리를 위해 Json형식의 text로 Map객체 전달
					
					@RequestMapping(value = "host_questionList", method = RequestMethod.GET)
					@ResponseBody
					public Map<String, Object> questionList2(@RequestParam(defaultValue = "1") int pageNum,HttpSession session) {
						Host loginHost=(Host)session.getAttribute("loginHost");
						int totalQuestion=memberHostBoardDao.selectHostQuestionCount(loginHost.getHId());
						int pageSize=6;
						int blockSize=5;
						
						Pager pager=new Pager(pageNum, totalQuestion, pageSize, blockSize);
						
						Map<String, Object> pageMap=new HashMap<String, Object>();
						pageMap.put("startRow", pager.getStartRow());
						pageMap.put("endRow", pager.getEndRow());
						pageMap.put("hId", loginHost.getHId());
						
						List<SelectMember> questionList=memberHostBoardDao.selectHostQuestionList(pageMap);
						
						Map<String, Object> resultMap=new HashMap<String, Object>();
						resultMap.put("questionList", questionList);
						resultMap.put("pager", pager);
						return resultMap;
					}	
					
					
					//Space Review,Q&A Controller
					
					
					
					@RequestMapping(value = "space_reviewList/{rSno}", method = RequestMethod.GET)
					@ResponseBody
					public Map<String, Object> SpaceReviewList(@RequestParam(defaultValue = "1") int pageNum,@PathVariable int rSno) {

						int totalQuestion=memberHostBoardDao.selectSpaceReviewCount(rSno);
						int pageSize=6;
						int blockSize=5;
						
						Pager pager=new Pager(pageNum, totalQuestion, pageSize, blockSize);
						
						Map<String, Object> pageMap=new HashMap<String, Object>();
						pageMap.put("startRow", pager.getStartRow());
						pageMap.put("endRow", pager.getEndRow());
						pageMap.put("rSno", rSno);
						List<SelectMember> reviewList=memberHostBoardDao.selectSpaceReviewList(pageMap);
						
						Map<String, Object> resultMap=new HashMap<String, Object>();
						resultMap.put("reviewList", reviewList);
						resultMap.put("pager", pager);
						return resultMap;
					}	
					
					@RequestMapping(value = "space_questionList/{qSno}", method = RequestMethod.GET)
					@ResponseBody
					public Map<String, Object> SpaceQuestionList(@RequestParam(defaultValue = "1") int pageNum,@PathVariable int qSno) {
						
						int totalQuestion=memberHostBoardDao.selectSpaceQuestionCount(qSno);
						int pageSize=6;
						int blockSize=5;
						
						Pager pager=new Pager(pageNum, totalQuestion, pageSize, blockSize);
						
						Map<String, Object> pageMap=new HashMap<String, Object>();
						pageMap.put("startRow", pager.getStartRow());
						pageMap.put("endRow", pager.getEndRow());
						pageMap.put("qSno", qSno);
						List<SelectMember> questionList=memberHostBoardDao.selectSpaceQuestionList(pageMap);
						
						Map<String, Object> resultMap=new HashMap<String, Object>();
						resultMap.put("questionList", questionList);
						resultMap.put("pager", pager);
						return resultMap;
					}	
}
