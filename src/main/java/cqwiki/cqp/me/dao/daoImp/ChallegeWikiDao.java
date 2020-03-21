package cqwiki.cqp.me.dao.daoImp;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cqwiki.cqp.me.dao.ChallegeWiki;
import static com.sobte.cqp.jcq.util.StringHelper.lineSeparator;

public class ChallegeWikiDao implements ChallegeWiki {

	@Override
	public String getTeam_data(String name) {
		// TODO Auto-generated method stub
		String subtype=name.split("-")[0];
		String maintype=name.split("-")[1];
		String ep=name.split("-")[2];
		try{
		Document doc =  Jsoup.connect("http://wiki.biligame.com/cq/"+maintype).get();//
		String content2 = "" ;
        Element element = doc.getElementById(subtype);
        if(element==null) {
        	element=doc.getElementById(ep);
           content2 ="无数据，自动换成最后一个挑战图"+lineSeparator;
           if(element==null) {
           	return "无数据，请等待更新"+lineSeparator;
           }
        }
        
        
        
        Elements team_mini_mb= element.getElementsByClass("team_mini_mb");
        //队伍标题
        String hero="";
        for(int i = 0; i < team_mini_mb.size(); i++){
        	content2+=String.valueOf(i+1)+" "+team_mini_mb.get(i).text()+" ";
        	Elements imgs = team_mini_mb.get(i).select("img");
        	for(int j = 0; j < imgs.size(); j++){
        		hero=imgs.get(j).attr("alt").replace("icon.png", "");
        		content2+=String.valueOf(j+1)+hero;
            }
        	content2+=lineSeparator;
        	
        }
        content2+="原来的命令加上队伍序号可查看队伍详情"+lineSeparator;
		return content2;
		}catch(Exception e){
			return "获取wiki挑战或讨伐阵容数据出错";
  	  	}
	}

	@Override
	public String getTeamDetail_data(String name,int index) {
		// TODO Auto-generated method stub
		
		String subtype=name.split("-")[0];
		String maintype=name.split("-")[1];
		String ep=name.split("-")[2];
		try{
			Document doc =  Jsoup.connect("http://wiki.biligame.com/cq/"+maintype).get();//
			String content = "" ;
	        Element element = doc.getElementById(subtype);
	        if(element==null) {
	        	element=doc.getElementById(ep);
	           content ="无数据，自动换成最后一个挑战图"+lineSeparator;
	           if(element==null) {
	           	return "无数据，请等待更新"+lineSeparator;
	           }
	        }
        Elements row$team_box_info = element.getElementsByClass("row team_box_info");
        //队伍详细配置
        Elements team_heros=null;//英雄名
        Elements team_attr=null;//英雄配置
        Elements sis_champ_tr=null;//其它配置
        
        if(index<=row$team_box_info.size()&&index>0) {content+="当前第"+index+"队"+lineSeparator;index--;}
        else {index=0;content+="已切换到第1队"+lineSeparator;}
        
        
        team_heros=row$team_box_info.get(index).getElementsByClass("col-md-5 team_hero");
        team_attr=row$team_box_info.get(index).getElementsByClass("col-md-7 team_attr");
        sis_champ_tr=row$team_box_info.get(index).getElementsByClass("sis_champ_tr");
        	
        	for(int j = 0; j < 3; j++){
        		content+=team_heros.get(j).text()+" ";
        		content+=team_attr.get(j).text()+" ";
        		content+=lineSeparator;
        	}
        	content+=sis_champ_tr.get(0).text()+lineSeparator;
        
		return content;
		}catch(Exception e){
			return "获取wiki挑战或讨伐阵容数据出错";
  	  	}
	}

	@Override
	public String getBoss_data(String name, int index) {
		// TODO Auto-generated method stub
		String subtype=name.split("-")[0];
		String maintype=name.split("-")[1];
		String title=subtype+"-"+index;
		String titlefromdiv="";
		try{
			Document doc =  Jsoup.connect("http://wiki.biligame.com/cq/"+maintype).get();//
			String content = "" ;
		Elements boss_title=doc.getElementsByClass("cq_boss_box_title");//boss关卡
		Elements boss_name=doc.getElementsByClass("cq_boss_box_content_name");//boss名
		Elements boss_info=doc.getElementsByClass("cq_boss_box_content");//boss信息
	    Elements helps;
	    
	    for(int i = 0; i < boss_info.size(); i++){
	    	titlefromdiv=boss_title.get(i).text();
	    	if(titlefromdiv.toLowerCase().indexOf(title)!=-1){
	    		content+=boss_title.get(i).text()+" "+boss_name.get(i).text()+lineSeparator;
	    		content+=boss_info.get(i).getElementsByClass("cq_boss_box_content").get(0).text()+lineSeparator;
	    		
	    		//content+="能力值 ";
	    		//Element tabel=boss_info.get(i).getElementsByClass("boss_attribute_table").get(0);
	    		//Elements trs = tabel.select("tr");
	    		//for(Element tr:trs){
                //	content+=tr.text()+lineSeparator;
                //}
	            
	    	}
	    	
	    }
		return content;
		}catch(Exception e){
			return "获取wiki挑战或讨伐boss数据出错";
  	  	}
	}
}
