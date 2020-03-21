package cqwiki.cqp.me;

import static com.sobte.cqp.jcq.util.StringHelper.lineSeparator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cqwiki.cqp.me.dao.StoneWiki;
import cqwiki.cqp.me.dao.WarriorWiki;
import cqwiki.cqp.me.dao.daoImp.StoneWikiDao;
import cqwiki.cqp.me.dao.daoImp.WarriorWikiDao;

public final class StaticMap {
	 
     public static Map<String,String> WARROIRMAP;//勇士map
     public static Map<String,String> WARROIRKEYMAP ;//勇士关键字map
     public static List<String> ALLSTONE;//符文列表
     public static Map<String,String> CLASSMAP;//符文类别map
     public static Map<Integer,String> EPISODEMAP;//挑战map
     public static Map<Integer,String> CRUSADEMAP;//讨伐map
     public static String README;//帮助
     public static String WARROIRKEY;//勇士关键字
     private static StoneWiki stoneData;
     private static WarriorWiki wikiData;
     
     static{
     //勇士相关的全局量
    	wikiData=new WarriorWikiDao();
    	//加载模糊匹配数据
    	WARROIRMAP=wikiData.blurryWarriorName();
    	putwarroirkeymap();
     //符文相关的全局量
    	stoneData =new StoneWikiDao();
 	    //加载模糊匹配数据
    	ALLSTONE = stoneData.AllStoneName();
 	    putstoneclassmap();
     //挑战相关的全局量
		putepisodemap();
	 //讨伐相关的全局量	
		putcrusademap();
	
     //帮助
	 //WARROIRKEY	="评价,背景,专武,词条,符文,戒指,阵容,方块,技能,主动,被动,特殊,消块,服装,属性,对话,评分,可支持模糊查询";
	 WARROIRKEY	="背景,专武,词条,符文,戒指,阵容,方块,技能,特殊,消块,属性,能力,可支持模糊查询";
	 README=
                 "这里是可爱的wiki机器人,暂无法查询勇士评分,敬请谅解。"
                 + "请按照如下方式调教我:"+lineSeparator
                 +"查tiao询jiao格式为：查询+空格+符文+空格+符文名"+lineSeparator
                 +" 如：查询 符文 中枢"+lineSeparator
                 +"目前符文输入尽量全称，不要别称"+lineSeparator
                 +"或：查询+空格+石头+空格+符文类别"+lineSeparator
                 +" 如：查询 石头 固有 史诗 3 2"+lineSeparator
                 +"目前提供查询的符文类别如下："+CLASSMAP.keySet().toString()+lineSeparator
                 +"类型,品级,星级至少一种。其它只支持一种且只能放开头,参数输错则自动随机。"+lineSeparator
                 +"或：查询+空格+挑战1-10+空格+1-5"+lineSeparator
                 +" 如：查询 挑战7 4"+lineSeparator
                 +"表示挑战7第4个boss"+lineSeparator
                 +"或：查询+空格+挑战1-10+阵容(1-99)"+lineSeparator
                 +" 如：查询 挑战7 阵容"+lineSeparator
                 +" 如：查询 挑战7 阵容4"+lineSeparator
                 +"分别表示挑战7全部阵容和第4个阵容"+lineSeparator
                 +"其中挑战可换讨伐,讨伐1表示沙漠,讨伐2表示雪原,但只查第四个boss"+lineSeparator
                 +" 如：查询 讨伐2 (阵容(1-99))"+lineSeparator
                 +"或：查询+空格+勇士名称+空格+关键词"+lineSeparator
                 +" 如：查询 黄毛 评分"+lineSeparator
                 +"目前提供查询的关键词如下："+WARROIRKEY+lineSeparator
                 +"勇士名称可参考wiki简称页面  https://wiki.biligame.com/cq/克鲁赛德战记英雄简称盘点"+lineSeparator;
 
     }
     
     /** 前面没有 * 的说明已经 就行处理可以返回消息
      * 图鉴的数据id
     * 综合评分=hero_score_all
      *推图评分=hero_score_story
      *竞技评分=hero_score_pvp
     * 挑战评分=hero_score_ch
      *勇士标签=hero_tag
      *勇士别名=hero_nickname
      *勇士评价=hero_est
      *勇士详解=hero_link
      背景故事=hero_story
      *吃书推荐=hero_book
      方块技能=hero_skill
      *技能说明=hero_skill_info
      消块机制=hero_skill_m
      *无专武技能判定=hero_skill_nwp
     * 有专武技能判定=hero_skill_wp
      特殊技能推荐=hero_skill_sp
      专属精粹武器=hero_wp  
      *专武评价=hero_wp_est
      词条推荐=wpt_attr_box
      符文推荐=wpt_attr_box
      戒指词条推荐=hero_ring
      推荐阵容搭配=hero_team
      *服装=hero_costume
      *城镇对话=hero_dialogue
     * 全部评分=hero_score
      能力值=hero_state
      */
     public static void putwarroirkeymap(){
    	 WARROIRKEYMAP=new HashMap<String,String>();
         //WARROIRKEYMAP.put("价", "hero_est");
         WARROIRKEYMAP.put("背", "hero_story");
         WARROIRKEYMAP.put("景", "hero_story");
         WARROIRKEYMAP.put("故", "hero_story");
         WARROIRKEYMAP.put("事", "hero_story");
         WARROIRKEYMAP.put("专", "hero_wp");
         WARROIRKEYMAP.put("精", "hero_wp");
         WARROIRKEYMAP.put("粹", "hero_wp");
         WARROIRKEYMAP.put("武", "hero_wp");
         WARROIRKEYMAP.put("器", "hero_wp");
         WARROIRKEYMAP.put("词", "wpt_attr_box");
         WARROIRKEYMAP.put("条", "wpt_attr_box");
         WARROIRKEYMAP.put("符", "wpt_attr_box");
         WARROIRKEYMAP.put("文", "wpt_attr_box");
         WARROIRKEYMAP.put("戒", "hero_ring");
         WARROIRKEYMAP.put("指", "hero_ring");
         WARROIRKEYMAP.put("组", "hero_team");
         WARROIRKEYMAP.put("队", "hero_team");
         WARROIRKEYMAP.put("伍", "hero_team");
         WARROIRKEYMAP.put("阵", "hero_team");
         WARROIRKEYMAP.put("容", "hero_team");
         WARROIRKEYMAP.put("搭", "hero_team");
         WARROIRKEYMAP.put("配", "hero_team");
         WARROIRKEYMAP.put("方", "hero_skill");
         WARROIRKEYMAP.put("技", "hero_skill");
         //WARROIRKEYMAP.put("主", "hero_skill_0");
         //WARROIRKEYMAP.put("被", "hero_skill_2");
         WARROIRKEYMAP.put("特", "hero_skill_sp");
         WARROIRKEYMAP.put("殊", "hero_skill_sp");
         WARROIRKEYMAP.put("s", "hero_skill_sp");
         WARROIRKEYMAP.put("S", "hero_skill_sp");
         //WARROIRKEYMAP.put("无", "hero_skill_nwp");
         //WARROIRKEYMAP.put("有", "hero_skill_wp");
         WARROIRKEYMAP.put("消", "hero_skill_m");
         WARROIRKEYMAP.put("机", "hero_skill_m");
         WARROIRKEYMAP.put("制", "hero_skill_m");
         //WARROIRKEYMAP.put("分", "hero_score");
         //WARROIRKEYMAP.put("星", "hero_score");
         WARROIRKEYMAP.put("属", "hero_state");
         WARROIRKEYMAP.put("性", "hero_state");
         WARROIRKEYMAP.put("力", "hero_state");
         WARROIRKEYMAP.put("值", "hero_state");
         //WARROIRKEYMAP.put("对", "hero_dialogue");
         //WARROIRKEYMAP.put("话", "hero_dialogue");
         //WARROIRKEYMAP.put("彩", "hero_dialogue");
         //WARROIRKEYMAP.put("蛋", "hero_dialogue");
         //WARROIRKEYMAP.put("衣", "hero_costume");
         //WARROIRKEYMAP.put("服", "hero_costume");
         //WARROIRKEYMAP.put("装", "hero_costume");
         //WARROIRKEYMAP.put("裙", "hero_costume");
     }

	/*
	 类型 固有= inherence 一般= ordinary 套装= set
	 品级 普通= common  稀有= rare  史诗= epic
	 星级  1 = one  2= two  3= three
	 */
     public static void putstoneclassmap(){
    	 CLASSMAP=new HashMap<String,String>();
    	 CLASSMAP.put("固有", "inherence_data-type");
    	 CLASSMAP.put("一般", "ordinary_data-type");
    	 CLASSMAP.put("套装", "set_data-type");
		 CLASSMAP.put("普通", "common_data-rank");
		 CLASSMAP.put("稀有", "rare_data-rank");
		 CLASSMAP.put("史诗", "epic_data-rank");
		 CLASSMAP.put("1", "one_data-grade");
		 CLASSMAP.put("2", "two_data-grade");
		 CLASSMAP.put("3", "three_data-grade");
	 } 
     
    //规则：查询 挑战1-10  1-5
 	//规则：查询 挑战1-10    阵容 (1-99或不输入或其它随意)
     
     public static void putepisodemap(){
 		//正则，只保留数字
 		EPISODEMAP= new HashMap<Integer,String>();
 		EPISODEMAP.put(1, "c1-树星的幕后-c4");
 		EPISODEMAP.put(2, "c2-树星的幕后-c4");
 		EPISODEMAP.put(3, "c3-树星的幕后-c4");
 		EPISODEMAP.put(4, "c4-树星的幕后-c4");
 		EPISODEMAP.put(5, "c5-倒塌的幻想神殿-c7");
 		EPISODEMAP.put(6, "c6-倒塌的幻想神殿-c7");
 		EPISODEMAP.put(7, "c7-倒塌的幻想神殿-c7");
 		EPISODEMAP.put(8, "c8-海市蜃楼的雪原-c10");
 		EPISODEMAP.put(9, "c9-海市蜃楼的雪原-c10");
 		EPISODEMAP.put(10, "c10-海市蜃楼的雪原-c10");
 	    }
     //规则：查询 讨伐1-2  
 	 //规则：查询 讨伐1-2     阵容 (1-99或不输入或其它随意)
     public static void putcrusademap() {
		// TODO Auto-generated method stub
    	CRUSADEMAP= new HashMap<Integer,String>();
    	CRUSADEMAP.put(1, "wb1-遗迹的守护者-wb1");
    	CRUSADEMAP.put(2, "wb2-圣域的封印-wb2");
    	CRUSADEMAP.put(3, "wb3-规划署第2研究所-wb3");
	}
     
     
}
