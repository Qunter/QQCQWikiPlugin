package cqwiki.cqp.me.service.serviceImp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cqwiki.cqp.me.dao.WarriorWiki;
import cqwiki.cqp.me.dao.daoImp.WarriorWikiDao;
import cqwiki.cqp.me.service.WikiWarriorHandle;
/** 前面有 * 的说明已经 就行处理可以返回消息
 * 图鉴的数据id
 综合评分=hero_score_all
 推图评分=hero_score_story
 竞技评分=hero_score_pvp
 挑战评分=hero_score_ch
 勇士标签=hero_tag
 勇士别名=hero_nickname
 *勇士评价=hero_est
 勇士详解=hero_link
 *背景故事=hero_story
 *吃书推荐=hero_book
 *方块技能=hero_skill
 *技能说明=hero_skill_info
 *消块机制=hero_skill_m
 *无专武技能判定=hero_skill_nwp
 *有专武技能判定=hero_skill_wp
 *特殊技能推荐=hero_skill_sp
 *专属精粹武器=hero_wp
 *专武评价=hero_wp_est
 *词条推荐=wpt_attr_box
 *符文推荐=wpt_attr_box
 *戒指词条推荐=hero_ring
 *推荐阵容搭配=hero_team
 * 服装=hero_costume
 * 城镇对话=hero_dialogue
 * 全部评分=hero_score
 * 能力值=hero_state
 */
public class WikiWarriorHandleImp implements WikiWarriorHandle{
	private static WarriorWiki wikiData;
    private static Map<String,String> map;
    Map<String,String> keymap ;
    public Map<String, String> getKeymap() {
		return keymap;
	}
	public WikiWarriorHandleImp(){
        wikiData =new WarriorWikiDao();
        //加载模糊匹配数据
        map = wikiData.blurryWarriorName();
        putkeymap();
    }
    /**
     * 补充关键字库
     */
    public void putkeymap(){
        keymap= new HashMap<String,String>();
        keymap.put("勇士评价", "hero_est");
        keymap.put("评价", "hero_est");
        keymap.put("专武", "hero_wp_r6");
        keymap.put("专属武器", "hero_wp_r6");
        keymap.put("精粹武器", "hero_wp_r6");
        keymap.put("专属精粹武器", "hero_wp_r6");
        keymap.put("方块技能", "hero_skill");
        keymap.put("技能", "hero_skill");
        keymap.put("主动技能", "hero_skill_0");
        keymap.put("被动技能", "hero_skill_2");
        keymap.put("背景", "hero_story");
        keymap.put("故事", "hero_story");
        keymap.put("背景故事", "hero_story");
        keymap.put("特殊技能", "hero_skill_sp");
        keymap.put("sp", "hero_skill_sp");
        keymap.put("词条推荐", "wpt_attr_box");
        keymap.put("词条", "wpt_attr_box");
        keymap.put("符文推荐", "wpt_attr_box");
        keymap.put("符文", "wpt_attr_box");
        keymap.put("戒指", "hero_ring");
        keymap.put("戒指词条", "hero_ring");
        keymap.put("推荐阵容", "hero_team");
        keymap.put("组队", "hero_team");
        keymap.put("队伍", "hero_team");
        keymap.put("阵容", "hero_team");
        keymap.put("搭配", "hero_team");
        keymap.put("无专", "hero_skill_nwp");
        keymap.put("有专", "hero_skill_wp");
        keymap.put("消块", "hero_skill_m");
        keymap.put("消块机制", "hero_skill_m");
        keymap.put("服装", "hero_costume");
        keymap.put("时装", "hero_costume");
        keymap.put("衣服", "hero_costume");
        keymap.put("小裙子", "hero_costume");
        keymap.put("对话", "hero_dialogue");
        keymap.put("彩蛋", "hero_dialogue");
        keymap.put("评分", "hero_score");
        keymap.put("英雄评分", "hero_score");
        keymap.put("属性", "hero_state");
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
        	if(keywords.length>1){
        	   if(keywords[1].indexOf("帮助")==0){
        		   return "接下来要输入石头,符文或勇士名称,勇士名称可参考 http://wiki.joyme.com/cq/克鲁赛德战记英雄简称盘点。";
        	   }
        	   if(keywords.length>2){
			      String heroname=keywords[1];
			      boolean flag=false;
			      if(map.containsKey(heroname)){}
			      else {
			         Set<String> keyset=  map.keySet();
			         for (String str : keyset) {
			            if(str.indexOf(heroname)!=-1) {
			               heroname=str;
			               flag=true;
			               break;
			               }
			            }
			            if(flag==false)
			                return "找不到名为{"+heroname+"}的勇士，勇士简称查找可到http://wiki.joyme.com/cq/克鲁赛德战记英雄简称盘点";
			        	}
			    	}else{
			    		return keywords[1]+" 如果是勇士,请加上正确的查询参数哦";
			    	}
        	   
            }
        	
        	if(keywords.length>2)
            {
        		if(keywords[2].indexOf("帮助")==0){
         		   return "关键字词库为"+keymap.keySet().toString();
         	   }
            String keyword=keymap.get(keywords[2]);
            if(keyword!=null&&keyword.length()>0){
            	list.add(keyword);
            }
            else return "找不到关键字"+keywords[2]+"，可查看词库"+keymap.keySet().toString();
            }
        	
        	if(keywords.length>3)
                list.add(keywords[3]);
            else list.add("0");
        	
            return getWarriorData(list.get(0),list.get(1),list.get(2));
            }
        catch (Exception e){
            return null;
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
            case "hero_dialogue":return wikiData.getWarrior_dialogue(map.get(name),id);
            case "hero_state":return wikiData.getWarrior_state(map.get(name),id,extra);
            case "hero_skill_sp":return wikiData.getWarrior_sp(map.get(name),id);
            case "wpt_attr_box":return wikiData.getWarrior_wpt(map.get(name),id);
            default:break;
        }
        return wikiData.getWarrior_data(map.get(name),id);
    }
}
