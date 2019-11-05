package cqwiki.cqp.me.service.serviceImp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cqwiki.cqp.me.service.WikiChallengeHandle;
import cqwiki.cqp.me.dao.ChallegeWiki;
import cqwiki.cqp.me.dao.daoImp.ChallegeWikiDao;
import static cqwiki.cqp.me.StaticMap.EPISODEMAP;

public class WikiChallengeHandleImp implements WikiChallengeHandle {
	
	private static ChallegeWiki challegeWiki;
	public WikiChallengeHandleImp() {
		challegeWiki=new ChallegeWikiDao();
		
	}
	

	//规则：查询 挑战1-10    技能、攻略、掉落、属性  1-5
	//规则：查询 挑战1-10  (1-99或不输入或其它随意)

	
	@Override
	public String Challenge_Msg_Handle(String msg) {
		// TODO Auto-generated method stub
		String []chamsg=msg.split(" ");
		String chaname="";
		chaname=chamsg[1];

        Pattern p = Pattern.compile("[^\\d]*?(\\d+)[^\\d]*");  
        Matcher m = p.matcher(chaname);  
        boolean result = m.find();  
        String find_result = null; 
	        if (result) {  
	        	find_result= m.group(1);  
	        }else return "请在挑战后面加数字";
	    chaname=EPISODEMAP.get(Integer.valueOf(find_result));
	    if(chaname==null||chaname.length()==0) return "挑战"+find_result+"还未开放";   
	        
	    String key="";
	    int number;
		if(chamsg.length>2){
			key=chamsg[2];

			p = Pattern.compile("^[0-9]+$");
	        m= p.matcher(key);
	        if( m.matches() ){
	        	return challegeWiki.getTeamDetail_data(chaname,Integer.parseInt(chamsg[2]));
			} 
	        
			switch(key.substring(0, 2)){
			 case "帮助":return "接下来输入阵容，返回所有队伍，或者访问http://wiki.joyme.com/cq/树星的幕后";
			 case "技能":key="技能";break;
			 case "攻略":key="攻略";break;
			 case "掉落":key="掉落";break;
			 case "属性":key="属性";break;
			 case "全部":key="全部";break;
			 default:return challegeWiki.getTeam_data(chaname);
			}
			
			if(chamsg.length>3){
				p = Pattern.compile("^[0-9]+$");
		        m= p.matcher(chamsg[3]);
		        if( !m.matches() ){
		        	return "第四个参数需要输入数字";
		        } 
				number=Integer.parseInt(chamsg[3]); 
				if(number==5){
					if(Integer.valueOf(find_result)==2||Integer.valueOf(find_result)==3);
					else return "目前版本没有第五个boss";
				}
				if(number>0&&number<6) return challegeWiki.getBoss_data(chaname, number);
				else return "第四个参数的数字不规范";
			}
			return challegeWiki.getTeam_data(chaname);
	   }else{//没有参数直接返回阵容
			return challegeWiki.getTeam_data(chaname);
	   }
		
	}

}
