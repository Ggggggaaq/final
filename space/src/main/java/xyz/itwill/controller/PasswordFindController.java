package xyz.itwill.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.RequiredArgsConstructor;
import xyz.itwill.dto.Member;
import xyz.itwill.exception.MemberNotFoundException;
import xyz.itwill.service.MemberService;

@RequiredArgsConstructor
@Controller
public class PasswordFindController {
	
	private final MemberService memberService;
	
	//member 비밀번호찾기
		@RequestMapping(value="/member_find",method = RequestMethod.GET )
		public String find() {
			return "member/member_find";
		}
		
		//member 아이디, 비밀번호찾기(10자리 숫자 랜덤배열)
		@RequestMapping(value="/member_find", method = RequestMethod.POST)
		public String find(@ModelAttribute Member member,Model model) {
		    try {
		    	Member FoundMember=memberService.getEmailMember(member.getMEmail());
		        memberService.pwModifyMember(member);
		        model.addAttribute("mId",FoundMember.getMId());
		        model.addAttribute("mPw",member.getMPw());
		        model.addAttribute("mStatus",FoundMember.getMStatus());
		        return "member/member_find_result";
		    } catch (MemberNotFoundException e) {
		        model.addAttribute("errorMessage", e.getMessage());
		        
		        return "member/member_find"; 
		    }
		}
		
		//member 비밀번호찾기 후 DB솔트처리.
		@RequestMapping(value="/member_find_end" )
		public String find2(@ModelAttribute Member member) throws MemberNotFoundException {
		
			memberService.pwModifyMember2(member);
		
			return "home";
		}
		
		
		//host 비밀번호찾기
		@RequestMapping(value="/host_find",method = RequestMethod.GET )
		public String hfind() {
			return "host/host_find";
		}
		
		//host 아이디, 비밀번호찾기(10자리 숫자 랜덤배열)
		@RequestMapping(value="/host_find", method = RequestMethod.POST)
		public String hfind(@ModelAttribute Member member,Model model) {
			try {
				Member FoundMember=memberService.getEmailMember(member.getMEmail());
				memberService.pwModifyMember(member);
				model.addAttribute("mId",FoundMember.getMId());
				model.addAttribute("mPw",member.getMPw());
				model.addAttribute("mStatus",FoundMember.getMStatus());
				return "host/host_find_result";
			} catch (MemberNotFoundException e) {
				model.addAttribute("errorMessage", e.getMessage());
				
				return "host/host_find"; 
			}
		}
		
		//host 비밀번호찾기 후 DB솔트처리.
		@RequestMapping(value="/host_find_end" )
		public String hfind2(@ModelAttribute Member member) throws MemberNotFoundException {
			
			memberService.pwModifyMember2(member);
			
			return "host";
		}
}
