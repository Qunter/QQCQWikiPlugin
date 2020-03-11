package cqwiki.cqp.me.service.serviceImp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cqwiki.cqp.me.dao.WarriorWiki;
import cqwiki.cqp.me.dao.daoImp.WarriorWikiDao;
import cqwiki.cqp.me.service.WikiWarriorHandle;
import static cqwiki.cqp.me.StaticMap.WARROIRMAP;
import static cqwiki.cqp.me.StaticMap.WARROIRKEYMAP;
import static com.sobte.cqp.jcq.util.StringHelper.lineSeparator;

public class WikiWarriorHandleImp implements WikiWarriorHandle{
	private static WarriorWiki wikiData;
	public WikiWarriorHandleImp(){
        wikiData =new WarriorWikiDao();
    }

    /**
     * 群消息处理中枢
     * @param msg 群消息类
     * @return 查询结果
     */
    public String Warrior_Msg_Handle(String msg) {
    	List<String> list = new ArrayList<String>();
        //查询格式:  格式 勇士简称或名称 关键词
        String []keywords=msg.split(" ");
        
        try{
        	//允许只输入一个字来查找勇士，按最先匹配到的数据返回结果
        	
        	   
		   if(keywords.length>2){
			  if(keywords[2].indexOf("帮助")==0){
	     		   return "关键字词库为"+WARROIRKEYMAP.keySet().toString();
	     	  }
	
		      String heroname=keywords[1];
		      boolean flag=false;
		      if(WARROIRMAP.containsKey(heroname)){heroname=WARROIRMAP.get(heroname);}
		      else {
		        Set<String> keyset=  WARROIRMAP.keySet();
		        for (String str : keyset) {
		            if(str.indexOf(heroname)!=-1) {
		               heroname=str;
		               flag=true;
		               break;
		               }
		        }
		        if(flag==false)
		            return "找不到名为{"+heroname+"}的勇士，勇士简称查找可到http://wiki.biligame.com/cq/克鲁赛德战记英雄简称盘点";
		        }
		        list.add(WARROIRMAP.get(heroname));
	        	String keyword;
	        	int max=keywords[2].length()<5?keywords[2].length():5;
	            if(keywords[2]!=null&&keywords[2].length()>0){
	            	//for(int i= 0;i<keywords[2].length();i++)
	            	//关键字最多5个字
	            	for(int i= 0;i<max;i++)
	            	{
	            		keyword=String.valueOf(keywords[2].charAt(i));
	            		keyword=WARROIRKEYMAP.get(keyword);
	            		if(keyword!=null&&keyword.length()>0){
	            			list.add(keyword);break;}	
	            	}
	            }
	            //else return "找不到关键字"+keywords[2]+"，输入帮助查看关键字词库";
	            if(list.size()<2){return "找不到关键字"+keywords[2]+"，输入帮助查看关键字词库";}

	    	}else{
	    		return keywords[1]+" 如果是勇士,请加上正确的查询参数哦";
	    	}
        	
        	if(keywords.length>3)
                list.add(keywords[3]);
            else list.add("0");
        	
            return "返回"+list.get(0)+lineSeparator
            		+getWarriorData(list.get(0),list.get(1),list.get(2));
            }
        catch (Exception e){
            return "查找勇士数据出错";
      }

    }
    /**
     * 返回 勇士数据方法
     * @param name 勇士名称
     * @param id 数据id
     * @return 返回勇士数据
     * @throws IOException IO异常
     */
    private String getWarriorData(String name ,String id,String extra) throws IOException {
    	
        //System.out.println(id);
        switch(id){
            case "hero_dialogue":return wikiData.getWarrior_dialogue(name,id);
            case "hero_state":return wikiData.getWarrior_state(name,id,extra);
            case "hero_skill_sp":return wikiData.getWarrior_sp(name,id);
            case "wpt_attr_box":return wikiData.getWarrior_wpt(name,id);
            default:break;
        }
        return wikiData.getWarrior_data(name,id);
    }
}
