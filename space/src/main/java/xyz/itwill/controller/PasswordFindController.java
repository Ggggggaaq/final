package xyz.itwill.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.RequiredArgsConstructor;
import xyz.itwill.dto.Host;
import xyz.itwill.dto.Member;
import xyz.itwill.exception.HostNotFoundException;
import xyz.itwill.exception.MemberNotFoundException;
import xyz.itwill.service.HostService;
import xyz.itwill.service.MemberService;

@RequiredArgsConstructor
@Controller
public class PasswordFindController {
	
	private final MemberService memberService;
	private final HostService hostService;
	
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
		    	member.setMId(FoundMember.getMId()); // 찾은 회원의 아이디를 입력 폼에 설정
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
		@RequestMapping(value="/host/host_find",method = RequestMethod.GET )
		public String hfind() {
			return "host/host_find";
		}
		
		//host 아이디, 비밀번호찾기(10자리 숫자 랜덤배열)
		@RequestMapping(value="/host/host_find", method = RequestMethod.POST)
		public String hfind(@ModelAttribute Host host,Model model) {
			try {
				Host foundHost=hostService.getEmailHost(host.getHEmail());
				hostService.pwModifyHost(host);
				model.addAttribute("hId",foundHost.getHId());
				model.addAttribute("hPw",host.getHPw());
				model.addAttribute("hStatus",foundHost.getHStatus());
				return "host/host_find_result";
			} catch (HostNotFoundException e) {
				model.addAttribute("errorMessage", e.getMessage());
				
				return "host/host_find"; 
			}
		}
		
		//host 비밀번호찾기 후 DB솔트처리.
		@RequestMapping(value="/host/host_find_end" )
		public String hfind2(@ModelAttribute Host host) throws MemberNotFoundException, HostNotFoundException {
			
			hostService.pwModifyHost2(host);
			
			return "host";
		}
}
