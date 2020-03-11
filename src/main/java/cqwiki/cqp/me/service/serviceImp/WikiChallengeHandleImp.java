package cqwiki.cqp.me.service.serviceImp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cqwiki.cqp.me.service.WikiChallengeHandle;
import cqwiki.cqp.me.dao.ChallegeWiki;
import cqwiki.cqp.me.dao.daoImp.ChallegeWikiDao;
import static cqwiki.cqp.me.StaticMap.EPISODEMAP;
import static cqwiki.cqp.me.StaticMap.CRUSADEMAP;

public class WikiChallengeHandleImp implements WikiChallengeHandle {
	
	private static ChallegeWiki challegeWiki;
	public WikiChallengeHandleImp() {
		challegeWiki=new ChallegeWikiDao();
		
	}
	

	//规则：查询 挑战1-10  1-5
	//规则：查询 挑战1-10    阵容 (1-99或不输入或其它随意)
	//规则：查询 讨伐1-2  
	//规则：查询 讨伐1-2     阵容 (1-99或不输入或其它随意)
	
	@Override
	public String Challenge_Msg_Handle(String msg) {
		// TODO Auto-generated method stub
		String []chamsg=msg.split(" ");
		String chaname=chamsg[1];

        Pattern p = Pattern.compile("[^\\d]*?(\\d+)[^\\d]*");  
        Matcher m = p.matcher(chaname);  
        boolean result = m.find();  
        String find_result = null; 
        
	    if (result) {  
	        find_result= m.group(1);  
	    }else return "请在挑战后面直接跟数字";
	    chaname=EPISODEMAP.get(Integer.valueOf(find_result));
	    if(chaname==null||chaname.length()==0) return "挑战"+find_result+"还未开放";   

	    String key="";String key1="";
	    int number;
		if(chamsg.length>2){
			key=chamsg[2];
			p = Pattern.compile("^[0-9]+$");
	        m= p.matcher(key);
	        if( m.matches() ){
	        	number=Integer.parseInt(chamsg[2]); 
				if(number==5){
					if(Integer.valueOf(find_result)==2||Integer.valueOf(find_result)==3);
					else return "目前版本没有第五个boss";
				}
				if(number>0&&number<6) 
					return challegeWiki.getBoss_data(chaname, number);
				else return "第三个参数的数字不规范";
			} 
	        
	        if(key.length()>1) {key1=key.substring(2);key=key.substring(0, 2);}
	        
			switch(key){
			 case "帮助":return "接下来输入阵容，返回所有队伍，或者访问http://wiki.biligame.com/cq/"+chaname;
			 case "阵容":key="阵容";break;
			 case "队伍":key="队伍";break;
			 case "打法":key="打法";break;
			 case "攻略":key="攻略";break;
			 default:return challegeWiki.getTeam_data(chaname);
			}
			
			if(key1==null||key1.length()==0) 
				return challegeWiki.getTeam_data(chaname);
			p = Pattern.compile("^[0-9]+$");
		    m= p.matcher(key1);
		    if( !m.matches() ){
		       return "阵容后面需要输入数字";
		    } 
			number=Integer.parseInt(key1); 
			return challegeWiki.getTeamDetail_data(chaname,number);

	   }else{//没有参数直接返回阵容
			return challegeWiki.getTeam_data(chaname);
	   }
		
	}


	@Override
	public String Crusade_Msg_Handle(String msg) {
		// TODO Auto-generated method stub
		String []chamsg=msg.split(" ");
		String chaname=chamsg[1];

        Pattern p = Pattern.compile("[^\\d]*?(\\d+)[^\\d]*");  
        Matcher m = p.matcher(chaname);  
        boolean result = m.find();  
        String find_result = null; 
        
	    if (result) {  
	        find_result= m.group(1);  
	    }else return "请在讨伐后面直接跟数字";
	    chaname=CRUSADEMAP.get(Integer.valueOf(find_result));
	    if(chaname==null||chaname.length()==0) return "讨伐"+find_result+"还未开放";   

	    String key="";String key1="";
	    int number;
		if(chamsg.length>2){
			key=chamsg[2];
			
			if(key.length()>1) {key1=key.substring(2);key=key.substring(0, 2);}
	        
			switch(key){
			 case "帮助":return "接下来输入阵容/队伍/打法/攻略，返回所有队伍，或者访问http://wiki.biligame.com/cq/"+chaname;
			 case "阵容":key="阵容";break;
			 case "队伍":key="队伍";break;
			 case "打法":key="打法";break;
			 case "攻略":key="攻略";break;
			 default:return challegeWiki.getBoss_data(chaname, 4);
			}
			
			if(key1==null||key1.length()==0) 
				return challegeWiki.getTeam_data(chaname);
			p = Pattern.compile("^[0-9]+$");
		    m= p.matcher(key1);
		    if( !m.matches() ){
		       return "阵容后面需要输入数字";
		    } 
			number=Integer.parseInt(key1); 
			return challegeWiki.getTeamDetail_data(chaname,number);

	   }else{//没有参数直接返回最后的boss
			return challegeWiki.getBoss_data(chaname, 4);
	   }
	}

}
