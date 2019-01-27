package dao.daoImp;

import dao.queryWiki;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class queryWikiDao implements queryWiki {
    @Override
    public String getWikiHtmlData(String url) {
        try {
            Document doc =  Jsoup.connect(url).get();//
            System.out.println(doc);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "";
    }
}
