import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import java.util.*;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by zhy19712 on 07/07/2017.
 */
public class test{




    public static void main(String[] args) {
        String url = "http://www.creei.cn";
        Spider.create(new ImgProcess(url)).addUrl(url).thread(7).run();
    }
}
