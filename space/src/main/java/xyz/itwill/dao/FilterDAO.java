package xyz.itwill.dao;

import java.util.List;
import java.util.Map;

import xyz.itwill.dto.Space;

public interface FilterDAO {
	List<Space> selectSpaceSno(Map<String, Object> params);
	int selectSpaceCount(Map<String, Object> params);
	List<Space> selectSpaceList(Map<String, Object> map);
}
