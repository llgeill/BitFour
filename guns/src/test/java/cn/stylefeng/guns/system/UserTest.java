package cn.stylefeng.guns.system;

import cn.stylefeng.guns.base.BaseJunit;
import cn.stylefeng.guns.modular.system.dao.UserMapper;
import org.junit.Test;
import org.junit.internal.runners.statements.RunAfters;
import org.springframework.util.ClassUtils;

import javax.annotation.Resource;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 用户测试
 *
 * @author fengshuonan
 * @date 2017-04-27 17:05
 */
public class UserTest {
    private String viewstate;
    private String cookie;
    private String img = "http://jwxw.gzcc.cn/CheckCode.aspx";
    private String school_url = "http://jwxw.gzcc.cn";
    private String login_url = "http://jwxw.gzcc.cn/default2.aspx";
    private String cookieId = "ASP.NET_SessionId";
    private String check;
    private String id="201606110062";
    private String password="llg1997729";
//    private String img="http://localhost:9090/kaptcha";
//    private String school_url="http://localhost:9090/login";


//    @Resource
//    UserMapper userMapper;


    @Test
    public void userTest() {
    }


    public static void main(String[] args) {
        UserTest userTest=new UserTest();
        userTest.getCookie();
        userTest.SaveImg();
        try {
            userTest.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 登陆
     *
     * @throws IOException
     */

    public void load() throws IOException {
        System.out.print("请输入验证码:");
        Scanner in = new Scanner(System.in);
        check = in.nextLine();
//输入保存到本地的验证码图片上的验证码
        System.out.println("check = " + check);
//拼接请求字符串
        String str = "__VIEWSTATE=" + URLEncoder.encode(viewstate, "gb2312") + "&txtUserName=" + id + "&TextBox2="
                + password + "&txtSecretCode=" + check + "&RadioButtonList1=" + URLEncoder.encode("学生", "gb2312")
                + "&Button1=&lbLanguage=&hidPdrs=&hidsc=";
        ;
        System.out.println("参数列表:" + str);

//登录提交的网址
        URL url = new URL(login_url);
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
        System.out.println("Location :" + conn.getHeaderField("Location"));
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


    public void getCookie() {
        try {
            URL url = new URL(school_url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
// 设置请求超时时间
            conn.setReadTimeout(1000);
            if (conn.getResponseCode() != 200) {
                System.out.println("error");
                return;
            }
// 获取Set-Cookie
            String str = conn.getHeaderField("Set-Cookie");
            int begin = str.indexOf(cookieId);
            int end = str.indexOf(';', begin);
            cookie = str.substring(begin, end);
            System.out.println(cookie);
            BufferedReader read = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//读取整个网页的源码
            StringBuffer buffer = new StringBuffer();
            while ((str = read.readLine()) != null) {
                buffer.append(str);
            }

            read.close();
            begin = buffer.indexOf("__VIEWSTATE\"");
            begin = buffer.indexOf("value=\"", begin + 1);
            begin = buffer.indexOf("\"", begin + 1);
            end = buffer.indexOf("\"", begin + 1);
            viewstate = buffer.substring(begin + 1, end);
            System.out.println("隐藏字段 = " + viewstate);

        } catch (MalformedURLException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }


    /**
     * 存储验证码到本地
     */

    public void SaveImg() {
        try {
/**
 * img变量的值为http://218.197.80.27/CheckCode.aspx
 * 也就是上面图片中，验证码的网址，在浏览器中，右键验证码 即可选择复制图片网址
 */
            URL url = new URL(img);
            HttpURLConnection openConnection = (HttpURLConnection) url.openConnection();
            openConnection.setRequestMethod("GET");
            openConnection.setReadTimeout(5000);
// cookie一同提交(ASP.NET_SessionId=4kusfii0urpbrazkhxvuas45,只需要等号后面的一串数据)
            openConnection.setRequestProperty("Cookie", cookie);
            InputStream in = openConnection.getInputStream();
//必须使用字节流，不能使用字符流，不然图片无法打开
            byte[] by = new byte[50000];

// 将验证码保存到本地
            FileOutputStream file = new FileOutputStream("d:\\img.gif");
            int len = 0;
            while ((len = in.read(by)) != -1) {
                file.write(by, 0, len);
            }
            file.close();
            in.close();
            System.out.println("读取验证码完毕!");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();

        }

    }


}
