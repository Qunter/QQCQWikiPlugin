package dao;

import java.util.Map;

public interface queryWiki {
    //获取html 数据
     String getWikiHtmlData(String url);
    //获取勇士评价数据 name:勇士名称
     String getWarrior_Hero_est(String name) throws Exception;

    Map<String,String> blurryWarriorName();
}
