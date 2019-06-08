package cqwiki.cqp.me.dao;

import java.io.IOException;
import java.util.Map;

public interface WarriorWiki {
    //获取html 数据
     String getWikiHtmlData(String url);
    //获取勇士评价数据 name:勇士名称
     String getWarrior_Hero_est(String name) throws Exception;
     //获取模糊匹配勇士名称
    Map<String,String> blurryWarriorName();
    //获取勇士数据 name 勇士名称(要全称) id 要获取的数据id
    String getWarrior_data(String name ,String id) throws IOException;
    String getWarrior_dialogue(String name ,String id)throws IOException;
    String getWarrior_state(String name ,String id,String extra)throws IOException;
	String getWarrior_sp(String name, String id)throws IOException;
	String getWarrior_wpt(String name, String id)throws IOException;
}
