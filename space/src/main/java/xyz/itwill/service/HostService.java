package xyz.itwill.service;

import java.util.List;
import java.util.Map;

import xyz.itwill.dto.Host;
import xyz.itwill.dto.Host;
import xyz.itwill.exception.ExistsHostException;
import xyz.itwill.exception.HostNotFoundException;
import xyz.itwill.exception.LoginAuthFailException;
import xyz.itwill.exception.HostNotFoundException;

public interface HostService {
	void addHost(Host host) throws ExistsHostException;
	void modifyHost(Host host) throws HostNotFoundException;
	Host getHost(String hId) throws HostNotFoundException;
	Host loginAuth(Host host) throws LoginAuthFailException;
	int getHostCount();
	List<Host> getHostList(Map<String, Object> map);
	List<Host> getHoststatusList(Map<String, Object> map);
	void removehost(String hId) throws HostNotFoundException;
	
	void deleteHost(String hId) throws HostNotFoundException;
	
	//Host 비밀번호 찾기 
	void pwModifyHost(Host host) throws HostNotFoundException;
	void pwModifyHost2(Host host) throws HostNotFoundException;
	Host getEmailHost(String hEmail) throws HostNotFoundException;
}
