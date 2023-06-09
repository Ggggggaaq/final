package xyz.itwill.dao;

import java.util.List;
import java.util.Map;

import xyz.itwill.dto.Host;

public interface HostDAO {
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
