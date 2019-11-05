package cqwiki.cqp.me.service.serviceImp;

import cqwiki.cqp.me.service.WikiChallengeHandle;
import cqwiki.cqp.me.service.WikiMsgHandle;
import cqwiki.cqp.me.service.WikiStoneHandle;
import cqwiki.cqp.me.service.WikiWarriorHandle;
//import static com.sobte.cqp.jcq.util.StringHelper.lineSeparator;
import static cqwiki.cqp.me.StaticMap.README;


public class WikiMsgHandleImp implements WikiMsgHandle {

    private WikiStoneHandle StoneHandle;
    private WikiWarriorHandle WarriorHandle;
    private WikiChallengeHandle ChallengeHandle;
    public WikiMsgHandleImp(){
    	WarriorHandle=new WikiWarriorHandleImp();
        StoneHandle=new WikiStoneHandleImp();
        ChallengeHandle =new WikiChallengeHandleImp();
    }
    
	@Override
	public String Group_Msg_Handle(String msg) {
		// TODO Auto-generated method stub
		//帮助类型
		msg=msg.trim().replaceAll("\r|\n", "");
		if(msg.indexOf("帮助")==0){
            System.out.println("执行帮助指令");
            //System.out.println(usehelp);
            return README;
        }
		if(msg.split(" ").length>1)
        {
        String stone=msg.split(" ")[1];
        if(stone.indexOf("符文")==0){
        	return StoneHandle.Stone_Msg_Handle(msg.trim().replaceAll("\r|\n", ""));
        }
        if(stone.indexOf("石头")==0){
        	return StoneHandle.Stonetype_Msg_Handle(msg.trim().replaceAll("\r|\n", ""));
        }
        if(stone.indexOf("挑战")==0){
        	return ChallengeHandle.Challenge_Msg_Handle(msg.trim().replaceAll("\r|\n", ""));
        }
        }
    	else{
    		return "接下来要输入石头,符文,挑战+数字或勇士名称,具体可输入帮助查看。";
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
