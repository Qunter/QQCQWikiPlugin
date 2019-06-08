package cqwiki.cqp.me.dao;

import java.util.List;
import java.util.Map;

public interface StoneWiki {
	List<String> AllStoneName();
	String getStone_data(String name ,String id);
	String getStonetype_data(Map<String,String> map);
}
