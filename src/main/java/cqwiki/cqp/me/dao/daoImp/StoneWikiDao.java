package cqwiki.cqp.me.dao.daoImp;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cqwiki.cqp.me.dao.StoneWiki;
import static com.sobte.cqp.jcq.util.StringHelper.lineSeparator;
public class StoneWikiDao implements StoneWiki{

	@Override
	public String getStone_data(String name, String id) {
		try{
		// TODO Auto-generated method stub
		String stone="";
		Document doc =  Jsoup.connect("http://wiki.joyme.com/cq/"+name).get();//
        Element element = doc.getElementById("rune");
        if(element==null){
            Elements elements = doc.getElementsByClass("comment");
            return elements.select("#rune").text();
        }
        Elements divs= element.children();
        for (int i = 0; i < divs.size(); i++){
        	Elements divses= divs.get(i).children();
        	for (int j = 0; j < divses.size(); j++){
            	if(divses.get(j).text().trim().length()!=0) 
            		stone+=divses.get(j).text()+lineSeparator;
            }
        	
        }
        return stone;
		}catch(Exception e){
			return null;
  	  	}
	}
	public List<String> AllStoneName() {
        try {
            Document doc =  Jsoup.connect("http://wiki.joyme.com/cq/模板:符文一览").get();//
            Elements elements = doc.getElementsByClass("wikitable sortable");
            List<String> list = new ArrayList<String>();
            for (Element element:elements){
                Elements es = element.select("tr");
                for(int i = 1; i < es.size(); i++){
                    Elements tdes = es.get(i).select("td");
                    list.add(tdes.get(0).text());
                    }
                }
            return list;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
	@Override
	public String getStonetype_data(Map<String, String> map) {
		// TODO Auto-generated method stub
		 try {
            Document doc =  Jsoup.connect("http://wiki.joyme.com/cq/模板:符文一览").get();//
            Elements elements = doc.getElementsByClass("wikitable sortable");
            String content = "";
            int maxline=0;
        	List<String> type=new ArrayList<String>();
        	List<String> rank=new ArrayList<String>();
        	List<String> grade=new ArrayList<String>();
        	for(String key :map.keySet()){
        		if(map.get(key).equals("data-type")){type.add(key);}
        		else if(map.get(key).equals("data-rank")){rank.add(key);}
        		else if(map.get(key).equals("data-grade")){grade.add(key);}
        	}
        	
        	for (Element element:elements){
        		boolean flag_type=false,flag_rank=false,flag_grade=false;
        		Elements es = new Elements();
        		for(String attr:type){
        			es.addAll(element.getElementsByAttributeValue("data-type", attr));
        			flag_type=true;
        		}
        		if(flag_type)
        		{
        		doc = Jsoup.parse("<table>"+es.outerHtml()+"</table>");
        		element=doc.getElementsByTag("table").first();
        		es.clear();
        		}
        		for(String attr:rank){
        			es.addAll(element.getElementsByAttributeValue("data-rank", attr));
        			flag_rank=true;
        		}
        		if(flag_rank)
        		{
        		doc = Jsoup.parse("<table>"+es.outerHtml()+"</table>");
        		element=doc.getElementsByTag("table").first();
        		es.clear();
        		}
        		for(String attr:grade){
        			es.addAll(element.getElementsByAttributeValue("data-grade", attr));
        			flag_grade=true;
        		}
        		for(int i = 0; i < es.size(); i++){
                    Elements tdes = es.get(i).select("td");
            	       
            	    content+=tdes.get(0).text()+" ";
            	    content+=tdes.get(4).text()+" ";
            	    content+="套装:"+tdes.get(5).text()+" 来源:";
            	    String source=tdes.get(6).text();
            	    for(String sub_source:source.split(";")){
            	    	content+=sub_source.split(",")[0]+" ";
            	    }
            	       content+=lineSeparator;
            	       maxline++;
            	       if(maxline>16) return content+"智能防刷屏，以下数据略。"+lineSeparator;
                    }	
            	}            
            return content;
	        }catch (Exception e){
	            e.printStackTrace();
	            return null;
	        }
	}
}
