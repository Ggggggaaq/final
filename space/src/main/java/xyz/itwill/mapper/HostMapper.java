package xyz.itwill.mapper;

import java.util.List;
import java.util.Map;

import xyz.itwill.dto.Host;
import xyz.itwill.dto.Member;
import xyz.itwill.dto.Host;

public interface HostMapper {
	int insertHost(Host host);
	int updateHost(Host host);
	Host selectHost(String hId);
	int selectHostCount();
	List<Host> selectHostList(Map<String, Object> map);
	List<Host> selectstatusHostList(Map<String, Object> map);
	int deleteHost(String hId);
	
	int removeHost(String hId);
	int updatePassword(Host host);
	Host selectEmailHost(String hEmail);
}
