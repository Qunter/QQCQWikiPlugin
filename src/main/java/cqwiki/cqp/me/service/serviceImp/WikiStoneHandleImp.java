package cqwiki.cqp.me.service.serviceImp;

import java.util.HashMap;
import java.util.Map;

import cqwiki.cqp.me.dao.StoneWiki;
import cqwiki.cqp.me.dao.daoImp.StoneWikiDao;
import cqwiki.cqp.me.service.WikiStoneHandle;
import static com.sobte.cqp.jcq.util.StringHelper.lineSeparator;
import static cqwiki.cqp.me.StaticMap.ALLSTONE;
import static cqwiki.cqp.me.StaticMap.CLASSMAP;



/*
类型 固有= inherence 一般= ordinary 套装= set
品级 普通= common  稀有= rare  史诗= epic
星级  1 = one  2= two  3= three
*/
public class WikiStoneHandleImp implements WikiStoneHandle{
	 
	 private static StoneWiki stoneData;
	 public WikiStoneHandleImp(){
	    stoneData =new StoneWikiDao();
	    
	}
  
	//通过符文名查询
	@Override
	public String Stone_Msg_Handle(String msg) {
		// TODO Auto-generated method stub
		String []stonemsg=msg.split(" ");
		String stonename="";
		boolean flag=false;
		if(stonemsg.length>2){
			stonename=stonemsg[2];
			if(stonename.indexOf("帮助")==0){
				return "接下来输入最简的符文名，返回最先匹配到的数据，符文简称查找可到http://wiki.joyme.com/cq/模板:符文一览";
			}
	        if (ALLSTONE.contains(stonename)){
	        }else {
	            for (String str : ALLSTONE) {
	                if(str.indexOf(stonename)!=-1) {
	                	stonename=str;
	                    flag=true;
	                    break;
	                }
	            }
	            if(flag==false)
	                return "找不到名为{"+stonename+"}的符文，符文简称查找可到http://wiki.joyme.com/cq/模板:符文一览";
	        }  
		}
		else{
			return "接下来输入最简的符文名，返回最先匹配到的数据，符文简称查找可到http://wiki.joyme.com/cq/模板:符文一览";
		}
		return stoneData.getStone_data(stonename,"");
	}
	//通过符文类型查询
	@Override
	public String Stonetype_Msg_Handle(String msg) {
		// TODO Auto-generated method stub
		
		String []typemsg=msg.split(" ");
		String typename="";
		Map<String,String> category= new HashMap<String,String>();//类型//品级//星级
		if(typemsg.length<=2){
			return "接下来输入至少一种右边括号里的内容，参数包含:类型(固有,一般,套装),品级(普通,稀有,史诗),星级(1,2,3),其它情况(参数错误,帮助)";
		}		
		for(int i=2;i<typemsg.length;i++){
			typename=typemsg[i];
			if(i==2){
				if(typename.indexOf("帮助")==0){
					return "命令为:查询 石头 参数 。其中参数包含:类型(固有,一般,套装),品级(普通,稀有,史诗),星级(1,2,3),其它情况(参数错误,帮助)"+lineSeparator
							+"类型,品级,星级至少一种。其它只支持一种且只能放开头,参数输错则自动随机。";
				}
			}
			typename=CLASSMAP.get(typename);
			if(typename!=null&&typename.length()>0){
				category.put(typename.split("_")[0], typename.split("_")[1]);
			}
		}
		if(category.isEmpty()) {
			String[] alltype={"inherence","ordinary","set","common","rare","epic","one","two","three"};
			int index=(int) (Math.random()*3);
			category.put(alltype[index], "data-type");
			index=(int) (Math.random()*3+3);
			category.put(alltype[index], "data-rank");
			index=(int) (Math.random()*2+6);
			category.put(alltype[index], "data-grade");
		};
		return stoneData.getStonetype_data(category);
	}

}
