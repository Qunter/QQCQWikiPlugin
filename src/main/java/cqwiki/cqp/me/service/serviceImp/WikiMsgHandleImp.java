package cqwiki.cqp.me.service.serviceImp;

import cqwiki.cqp.me.service.WikiMsgHandle;
import cqwiki.cqp.me.service.WikiStoneHandle;
import cqwiki.cqp.me.service.WikiWarriorHandle;

import static com.sobte.cqp.jcq.util.StringHelper.lineSeparator;



public class WikiMsgHandleImp implements WikiMsgHandle {

    private WikiStoneHandle StoneHandle;
    private WikiWarriorHandle WarriorHandle;
    public WikiMsgHandleImp(){
    	WarriorHandle=new WikiWarriorHandleImp();
        StoneHandle=new WikiStoneHandleImp();
    }
    
	@Override
	public String Group_Msg_Handle(String msg) {
		// TODO Auto-generated method stub
		//帮助类型
		msg=msg.trim().replaceAll("\r|\n", "");
		if(msg.indexOf("帮助")==0&&msg.length()==2){
            System.out.println("执行帮助指令");
            String helpHint =
                    "这里是可爱的wiki机器人,请按照如下方式调教我:"+lineSeparator
                    +"查tiao询jiao格式为：查询+空格+勇士名称+空格+关键词"+lineSeparator
                    +"或：查询+空格+符文+空格+符文名"+lineSeparator
                    +"或：查询+空格+石头+空格+符文类别"+lineSeparator
                    +"目前提供查询的关键词如下："+WarriorHandle.getKeymap().keySet().toString()+lineSeparator
                    +"勇士名称可参考wiki简称页面  http://wiki.joyme.com/cq/克鲁赛德战记英雄简称盘点"+lineSeparator
                    +"关键词\"属性\"后可加空格和数字1-25表示查询该继承书数下的属性"+lineSeparator;
            //System.out.println(usehelp);
            return helpHint;
        }
		if(msg.split(" ").length>1){
            String stone=msg.split(" ")[1];
            if(stone.indexOf("符文")==0){
                return StoneHandle.Stone_Msg_Handle(msg.trim().replaceAll("\r|\n", ""));
            }
            if(stone.indexOf("石头")==0){
                return StoneHandle.Stonetype_Msg_Handle(msg.trim().replaceAll("\r|\n", ""));
            }
        } else {
    		return "接下来要输入石头,符文或勇士名称,具体可输入帮助查看。";
    	}
        System.out.println(msg);
        //查询类型处理
        try{
           return WarriorHandle.Warrior_Msg_Handle(msg);
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getLocalizedMessage());
            return "查询规则不正确,正确规则输入帮助查看";
        }
	}
	@Override
	public String Private_Msg_Handle(String msg) {
		// TODO Auto-generated method stub
		return null;
	}


    
    

}
