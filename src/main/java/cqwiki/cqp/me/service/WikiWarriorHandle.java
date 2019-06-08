package cqwiki.cqp.me.service;

import java.util.Map;

public interface WikiWarriorHandle {
	//勇士处理业务接口
    String Warrior_Msg_Handle(String msg);

    Map<String, String> getKeymap();
    
}
