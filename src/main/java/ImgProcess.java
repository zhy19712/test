import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.*;

/**
 * Created by admin on 2017/7/17.
 */
public class ImgProcess implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);
    private String url = "";
    public ImgProcess(String url){
        this.url = url;
    }
    @Override
    public void process(Page page) {
        //  page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+/\\w+)").all());
        //   page.putField("author", page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
//        page.putField("name", page.getHtml().xpath("head/title/text()").toString());
       /* page.putField("img_src", page.getHtml().xpath("//body//img//@src").all());
        page.putField("img_alt", page.getHtml().xpath("//body//img//@alt").all());*/
        List listBackSrc0 = page.getHtml().xpath("//img//@src").all();
        System.out.println("原先的src"+listBackSrc0.size());
        List listBackSrc = new ArrayList();
        for (int i = 0; i < listBackSrc0.size();i++){
            String s = String.valueOf(listBackSrc0.get(i));
            //在这里没有去处理src是空串的情况。
            if(s.startsWith("http")){
                listBackSrc.add(s);
            }
            else if(s.startsWith("//")){
                listBackSrc.add("http://www.creei.cn"+s.replace("//", "/"));
            }
            else if(s.startsWith("/")){
                listBackSrc.add("http://www.creei.cn"+s);
            }else {
                listBackSrc.add("http://www.creei.cn/"+s);
            }
            /**/
        }
        System.out.println("处理过的src"+listBackSrc.size());

        List listBackTitle = page.getHtml().xpath("//img//@alt").all();
        System.out.println("title的有效长度"+listBackTitle.size());
        List<Map> list = new ArrayList();
        for(int i = 0;i < listBackSrc.size();i++){
            Map<String,Object> map = new HashMap<String, Object>();
            String src =  String.valueOf(listBackSrc.get(i));
            String title =  String.valueOf(listBackTitle.get(i));
           if(!src.endsWith("http://www.creei.cn/")  &&  title != null && !"".equals(title) && !"null".equals(title)){
                map.put("src",src);
                map.put("title",title);
                list.add(map);
           }

        };
        //创建一个map。来存储url，文件名字和文件夹的名字
        Map<String,String> mapStore = new HashMap<String, String>();
        Iterator ite = list.iterator();
        while(ite.hasNext()){
            System.out.println(ite.next());
        }
        for(int i = 0;i < list.size();i++){
            String src = String.valueOf(list.get(i).get("src"));
            String[] array = src.split("/");
            mapStore.put("src",src);
            mapStore.put("filename",array[array.length-1]);
        }



        //  page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));
    }

    @Override
    public Site getSite() {
        return site;
    }
}
