package cn.stylefeng.guns.modular.spark.util;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 由于工具类拥有状态所以使用多例
 */
@Component
@Scope("prototype")
public class StudentAuthentication {
    private String baseURL = "http://jwxw.gzcc.cn/";
    private String captchaURL = baseURL + "CheckCode.aspx";
    private String submitURL = baseURL + "default2.aspx";
    private String saveBaseURL = "C:/Users/Administrator/Desktop/test/";
    private String save_img_url = saveBaseURL + "img.gif";
    public String save_head_img_url = saveBaseURL + "head.jpg";
    public String save_head_img_name;
    public String studentID = "201606110062";
    public String studentPassword = "llg1997729";
    public String check;
    private String classTableGnmkdm = "N121601";
    private String personInfoGnmkdm = "N121501";
    private Map<Integer, byte[]> captchaImg = new HashMap<>();
    private String viewState;
    private String indexURL;
    private String cookie="";
    public String name;
    private String Location;
    public String[][] classTable = new String[8][13];
    public String phone;
    public String email;
    public String schoolDate ;
    public String imgSrc ;

    public static void main(String[] args) {
        new StudentAuthentication().launch("","");
    }

    public void launch(String codeSaveUrl,String codeName) {
        try {
            getCookie();
            SaveImg(codeSaveUrl,codeName);
            load();
            getNameByUrl();
            getKB();
            getPersonInfo();
        } catch (Exception e) {
            System.out.println("捕獲異常");
            e.printStackTrace();
        }
    }

    /**
     * 输出为:
     * ASP.NET_SessionId=4kusfii0urpbrazkhxvuas45
     * 隐藏字段 = dDwyODE2NTM0OTg7Oz74kxBxGi3w7jUfyZCkgy/B+RGrKQ==
     * <p>
     * _VIEWSTATE存在于首页的源码中
     */
    public void getCookie() {

        try {
            URL url = new URL(baseURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // 设置请求超时时间(3秒超时直接返回)
            conn.setReadTimeout(3000);
            if (conn.getResponseCode() != 200) {
                System.out.println("error");
                return;
            }
            // 获取Set-Cookie
            Map<String,List<String>> map=conn.getHeaderFields();
            List<String> stringList=map.get("Set-Cookie");
            int begin,end;
            for(String str:stringList){
                //String str = conn.getHeaderField("Set-Cookie");
                end = str.indexOf(';', 0);
                cookie += str.substring(0, end)+";";
            }
            System.out.println(cookie);
            BufferedReader read = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            //读取整个网页的源码获取viewState
            StringBuffer buffer = new StringBuffer();
            String str;
            while ((str = read.readLine()) != null) {
                buffer.append(str);
            }
            read.close();
            begin = buffer.indexOf("__VIEWSTATE\"");
            begin = buffer.indexOf("value=\"", begin + 1);
            begin = buffer.indexOf("\"", begin + 1);
            end = buffer.indexOf("\"", begin + 1);
            viewState = buffer.substring(begin + 1, end);
            System.out.println("隐藏字段 = " + viewState);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 存储验证码到本地
     */
    public void SaveImg(String urls,String fileName) {

        try {
            /**
             * img变量的值为http://218.197.80.27/CheckCode.aspx
             * 也就是上面图片中，验证码的网址，在浏览器中，右键验证码 即可选择复制图片网址
             */
            URL url = new URL(captchaURL);
            HttpURLConnection openConnection = (HttpURLConnection) url.openConnection();
            openConnection.setRequestMethod("GET");
            openConnection.setReadTimeout(5000);
            // cookie一同提交(ASP.NET_SessionId=4kusfii0urpbrazkhxvuas45,只需要等号后面的一串数据)
            openConnection.setRequestProperty("Cookie", cookie);
            InputStream in = openConnection.getInputStream();
            //必须使用字节流，不能使用字符流，不然图片无法打开
            byte[] by = new byte[50000];
            // 将验证码保存到本地
            FileOutputStream file = new FileOutputStream(urls+fileName);
            int len = 0;
            while ((len = in.read(by)) != -1) {
                file.write(by, 0, len);
            }
            file.close();
            in.close();
            //将验证码保存到数组
            captchaImg.put(len, by);
            System.out.println("读取验证码完毕!");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        /**
         * 登陆
         * @throws IOException
         */
        public void load() throws IOException {
            System.out.println("check = " + check);
            //拼接请求字符串
            String str = "__VIEWSTATE=" + URLEncoder.encode(viewState, "gb2312") + "&txtUserName=" + studentID + "&TextBox2="
                    + studentPassword + "&txtSecretCode=" + check + "&RadioButtonList1=" + URLEncoder.encode("学生", "gb2312")
                    + "&Button1=&lbLanguage=&hidPdrs=&hidsc=";
            ;
            System.out.println("参数列表:" + str);
            //登录提交的网址
            URL url = new URL(submitURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //Post提交
            conn.setRequestMethod("POST");
            conn.setReadTimeout(5000);
            conn.setUseCaches(false);
            //禁止程序自己跳转到目标网址，必须设置，不然程序会自己响应
            //302返回码，自己请求跳转后的网址，出现Object Had Moved!错误
            conn.setInstanceFollowRedirects(false);
            // 写入cookie
            conn.setRequestProperty("Cookie", cookie);
            conn.setDoOutput(true);
            OutputStream out = conn.getOutputStream();
            //写入参数
            out.write(str.getBytes());
            out.close();
            //打印返回码
            System.out.println("返回码:" + conn.getResponseCode());
            //打印服务器返回的跳转网址
            Location = conn.getHeaderField("Location");

            System.out.println("Location :" + Location);
            BufferedReader read = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String temp;
            //读取页面源码
            StringBuffer ab = new StringBuffer();
            while ((temp = read.readLine()) != null) {
                ab.append(temp);
            }
            if (conn.getResponseCode() != 302) {
                //getError为自定义函数，提取出错误信息
                System.out.println("错误");
                return;
            }
        }

    /**
     * 获取名字
     *
     * @param
     * @return
     */
    public String getNameByUrl() {
        indexURL = baseURL + Location;
        try {
            URL Url = new URL(indexURL);
            HttpURLConnection conn = (HttpURLConnection) Url.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(2000);
            conn.setRequestProperty("Cookie", cookie);
            BufferedReader read = new BufferedReader(new InputStreamReader(conn.getInputStream(), "gb2312"));
            StringBuffer sb = new StringBuffer();
            String temp;
            while ((temp = read.readLine()) != null) {
                sb.append(temp);
            }
            //匹配姓名
            Pattern pattern = Pattern.compile("xm=[\\u4e00-\\u9fa5]*");
            Matcher matcher = pattern.matcher(sb.toString());
            matcher.find();
            this.name = sb.substring(matcher.start() + 3, matcher.end());
            System.out.println(name);
            //匹配gnmkdm(有错)
            /*
            Pattern pattern_two = Pattern.compile("gnmkdm=[a-zA-Z0-9]*");
            Matcher matcher_two = pattern_two.matcher(sb.toString());
            matcher_two.find();
            String gnmkdm = sb.substring(matcher_two.start() + 7, matcher_two.end());
            System.out.println(gnmkdm);
            */
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取课表
     */
    public void getKB() throws UnsupportedEncodingException {
        System.out.println("读取课表");
        String url = "http://jwxw.gzcc.cn/tjkbcx.aspx?xh=" + studentID + "&xm="
                + URLEncoder.encode(this.name, "GB2312") + "&gnmkdm=" + this.classTableGnmkdm;
        System.out.println("获取课表的参数: " + url);
        URL Url;
        try {
            Url = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) Url.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(3000);
            conn.setRequestProperty("Cookie", cookie);
            conn.setRequestProperty("Referer", indexURL);
            conn.setInstanceFollowRedirects(false);
            conn.setDoOutput(true);
            BufferedReader read = new BufferedReader(new InputStreamReader(conn.getInputStream(), "gb2312"));
            StringBuffer sb = new StringBuffer();
            String temp;
            while ((temp = read.readLine()) != null) {
                sb.append(temp);
            }
            //打印出含有课表的网页源码
            Document document = Jsoup.parse(sb.toString());
            Element element = document.getElementById("Table6");
            Elements elements = element.children();
            Element tbody = elements.last();
            StringBuffer html = new StringBuffer();
            //获取每一节的所有课程信息并且加上#做分隔
            for (Element element1 : tbody.children()) {
                for (Element element2 : element1.children()) {
                    for (TextNode textNode : element2.textNodes()) {
                        html.append(textNode.text());
                    }
                    html.append("#");
                }
                html.append("-END-\n");
            }
            //匹配截取每一节的信息并保存到list
            Pattern pattern = Pattern.compile("(?<=节#).*(?=#-END-)");
            Matcher matcher = pattern.matcher(html.toString());
            List<String> classList = new ArrayList();
            while (matcher.find()) {
                classList.add(matcher.group());
            }
            //剔除分隔符后的课表
            List<String[]> classDetailList = new ArrayList();
            for (String s : classList) {
                String[] strings = s.split("#");
                classDetailList.add(strings);
            }
            //截取每一个课表所占的节数，例如（3,4）
            Pattern pp = Pattern.compile("(?<=\\()[0-9],[0-9]+(?=\\))|(?<=\\()[0-9]+(?=\\))");
            for (String[] strings : classDetailList) {
                //剔除空的字符串
                if (strings.length < 7) continue;
                for (int i = 0; i < strings.length; i++) {
                    if (strings[i].length() > 1) {
                        Matcher mm = pp.matcher(strings[i]);
                        mm.find();
                        String[] numbers = mm.group().split(",");
                        //保存到二维数组组成的课表上
                        for (String index : numbers) {
                            classTable[i + 1][Integer.parseInt(index)] = strings[i];
                        }
                    }
                }
            }
            System.out.println(classDetailList);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 获取个人信息
     * xsgrxx.aspx
     */
    public void getPersonInfo() throws UnsupportedEncodingException {
        System.out.println("读取个人信息");
        String url = baseURL + "xsgrxx.aspx?xh=" + studentID + "&xm="
                + URLEncoder.encode(this.name, "GB2312") + "&gnmkdm=" + this.personInfoGnmkdm;
        System.out.println("获取课表的参数: " + url);
        URL Url;
        try {
            Url = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) Url.openConnection();
            conn.setRequestMethod("GET");
            conn.setReadTimeout(3000);
            conn.setRequestProperty("Cookie", cookie);
            conn.setRequestProperty("Referer", indexURL);
            conn.setInstanceFollowRedirects(false);
            conn.setDoOutput(true);
            BufferedReader read = new BufferedReader(new InputStreamReader(conn.getInputStream(), "gb2312"));
            StringBuffer sb = new StringBuffer();
            String temp;
            while ((temp = read.readLine()) != null) {
                sb.append(temp);
            }
            System.out.println(sb.toString());
            //打印出含有课表的网页源码
            Document document = Jsoup.parse(sb.toString());
            //获取到tobdy包含信息的标签
             phone = document.select("#TELNUMBER").first().val();
             email = document.select("#dzyxdz").first().val();
             schoolDate = document.select("#lbl_rxrq").text();
             imgSrc = document.select("#xszp").first().attr("src");
            System.out.println(phone);
            //存取头像
            URL urls = new URL(baseURL + imgSrc);
            HttpURLConnection openConnection = (HttpURLConnection) urls.openConnection();
            openConnection.setRequestMethod("GET");
            openConnection.setReadTimeout(5000);
            // cookie一同提交(ASP.NET_SessionId=4kusfii0urpbrazkhxvuas45,只需要等号后面的一串数据)
            openConnection.setRequestProperty("Cookie", cookie);
            InputStream in = openConnection.getInputStream();
            //必须使用字节流，不能使用字符流，不然图片无法打开
            byte[] by = new byte[50000];

            // 将验证码保存到本地
            FileOutputStream file = new FileOutputStream(save_head_img_url);
            int len = 0;
            while ((len = in.read(by)) != -1) {
                file.write(by, 0, len);
            }
            file.close();
            in.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
