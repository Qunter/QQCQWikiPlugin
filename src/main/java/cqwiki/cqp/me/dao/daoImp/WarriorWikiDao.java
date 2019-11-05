package cqwiki.cqp.me.dao.daoImp;




import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cqwiki.cqp.me.dao.WarriorWiki;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static com.sobte.cqp.jcq.util.StringHelper.lineSeparator;
/**
 *   刚才添加了图鉴的id
     综合评分=hero_score_all
     推图评分=hero_score_story
     竞技评分=hero_score_pvp
     挑战评分=hero_score_ch
     勇士标签=hero_tag
     勇士别名=hero_nickname
     勇士评价=hero_est
     勇士详解=hero_link
     背景故事=hero_story
     吃书推荐=hero_book
     方块技能=hero_skill
     技能说明=hero_skill_info
     消块机制=hero_skill_m
     无专武技能判定=hero_skill_nwp
     有专武技能判定=hero_skill_wp
     特殊技能推荐=hero_skill_sp
     专属精粹武器=hero_wp
     专武评价=hero_wp_est
     词条推荐=hero_wp_attr
     符文推荐=hero_wp_ct
     戒指词条推荐=hero_ring
     推荐阵容搭配=hero_team
     服装=hero_costume
     城镇对话=hero_dialogue
     全部评分=hero_score
     能力值=hero_state
 */

public class WarriorWikiDao implements WarriorWiki {
    /**
     * 测试方法
     * @param url
     * @return
     */
    public String getWikiHtmlData(String url) {
        try {
            Document doc =  Jsoup.connect(url).get();//
            Elements element = doc.getElementsByClass("comment");
            System.out.println(element.select("#hero_est").text());
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 返回一个勇士简称的map集合
     * @return Map
     */
    public Map<String,String> blurryWarriorName() {
        try {
            Document doc =  Jsoup.connect("http://wiki.joyme.com/cq/克鲁赛德战记英雄简称盘点").get();//
            Elements elements = doc.getElementsByClass("wikitable sortable");
            Map<String,String> map = new HashMap<String,String>();
            for (Element element:elements){
                Elements es = element.select("tr");
                for(Element tdelement:es){
                    Elements tdes = tdelement.select("td");
                    for(int i = 0; i < tdes.size(); i++){
                        if(i==0){
                        	map.put(tdes.get(0).text(),tdes.get(0).text());
                            continue;
                        }
                        if (tdes.get(i).text().indexOf(" ")>=0){
                            Arrays.stream(tdes.get(i).text().split(" ")).forEach(x->map.put(x,tdes.get(0).text()));
                        }else {
                            map.put(tdes.get(i).text(),tdes.get(0).text());
                        }
                    }
                }
            }
            return map;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
    /**
     * 查询勇士评价信息
     * @param name 要查询的勇士名称(全称)
     * @return
     * @throws Exception
     */
    public String getWarrior_Hero_est(String name) throws Exception{
            Document doc =  Jsoup.connect("http://wiki.joyme.com/cq/"+name).get();//
            Element element = doc.getElementById("hero_est");
            if(element==null){
                Elements elements = doc.getElementsByClass("comment");
                return elements.select("#hero_est").text();
            }
            return element.select("#hero_est").text();
    }

    /**
     * 通过id 查询勇士数据
     * @param name 勇士名称
     * @param id 要查询的数据id
     * @return 勇士数据
     */
    public String getWarrior_data(String name ,String id) throws IOException {
        Document doc =  Jsoup.connect("http://wiki.joyme.com/cq/"+name).get();//
        Element element = doc.getElementById(id);
        return element.select("#"+id).text();
    }
    /**
     * 通过id 查询勇士对话数据
     * @param name 勇士名称
     * @param id 要查询的数据id
     * @return 勇士对话数据
     */
    public String getWarrior_dialogue(String name ,String id) throws IOException {
        Document doc =  Jsoup.connect("http://wiki.joyme.com/cq/"+name).get();//
        Element element = doc.getElementById(id);
        String dialogue="";
        Elements tdes = element.select("td");
        for (Element td:tdes){
        Elements talk = td.getElementsByClass("hero_talk1");
        Elements talker = td.getElementsByClass("hero_name_talk1");
        for(int i = 0; i < talk.size(); i++){
        		dialogue+=talker.get(i).text();
        		dialogue+=":";
        		dialogue+=talk.get(i).text();
        		dialogue+=" ";	
        }
        dialogue+=lineSeparator;//输入回车
        }
        return dialogue;
    }

    /**
     * 通过id 查询勇士属性数据
     * @param name 勇士名称
     * @param id 要查询的数据id
     * @return 勇士对话数据
     */
	public String getWarrior_state(String name, String id,String extra) throws IOException {
		Document doc =  Jsoup.connect("http://wiki.joyme.com/cq/"+name).get();//
        if (extra.equals("0"))
		{
        Element element = doc.getElementById(id);
        Elements tres = element.select("tr");
        String state="";
        for (Element tr:tres){
        	state+=tr.text();

        	state+=lineSeparator;//输入回车
        } 
        return state;
        }
        else {
        	int num;
            String state="";
        	try{
        	   num=Integer.parseInt(extra);
        	   if(num<1||num>30) {   
        		   num=1;state="调整为1书"+lineSeparator;
        	   }
        	  }catch(NumberFormatException e){
        		  num=1;state="调整为1书"+lineSeparator;
        	  }
        	Element element = doc.getElementById("hero_book_num");
            Elements tres = element.select("tr");
            Elements first =tres.get(0).children();
            Elements wanted =tres.get(num).children();
            for (int i = 0; i < first.size(); i++){
            	state+=first.get(i).text();
            	state+=":";
            	state+=wanted.get(i).text();
            	state+=lineSeparator;
            } 
            return state;
        		
        }
	}
	
	/**
     * 通过id 查询勇士sp数据
     * @param name 勇士名称
     * @param id 要查询的数据id
     * @return 勇士数据
     */
	public String getWarrior_sp(String name, String id) throws IOException {
		Document doc =  Jsoup.connect("http://wiki.joyme.com/cq/"+name).get();//
        Integer i=1;
        String sp = "";
		while(true)
        { 
          String sp_n=id+"_n_"+i.toString();
          String sp_r=id+"_r_"+i.toString();
          i++;
          Element element = doc.getElementById(sp_n);
          Element element_r = doc.getElementById(sp_r);
          if(element.text().length()==0) break;
          else sp+=element.text()+" "+element_r.text()+lineSeparator;
        }
		
        return sp;
		
	}

	@Override
	public String getWarrior_wpt(String name, String id) throws IOException {
		// TODO Auto-generated method stub
		Document doc =  Jsoup.connect("http://wiki.joyme.com/cq/"+name).get();// 
        String wpt = "";
        Elements elements = doc.getElementsByClass(id);
        
        for (int i = 0; i < elements.size(); i++){
        	wpt+=elements.get(i).text();
        	wpt+=lineSeparator;
        } 
		return wpt;
	}
}
