package org.gb.util;

import com.alibaba.fastjson.JSONObject;
import org.gb.vo.SysUser;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 *语音验证码调用示例代码 － 聚合数据
 *在线接口文档：http://www.juhe.cn/docs/61
 **/

public class JuheDemo {
    public static final String DEF_CHATSET = "UTF-8";
    public static final int DEF_CONN_TIMEOUT = 30000;
    public static final int DEF_READ_TIMEOUT = 30000;
    public static String userAgent =  "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";

    //配置您申请的KEY
    public static final String APPKEY ="680177d4ad25ae80abfec99d320feeff";

    //1.发送语音验证码
    public static String getRequest1(String phone){
        String result =null;
        String url ="http://op.juhe.cn/yuntongxun/voice";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("valicode","phoneCode");//验证码内容，字母、数字 4-8位
        params.put("to","phone");//接收手机号码
        params.put("playtimes","3");//验证码播放次数，默认3
        params.put("key",APPKEY);//应用APPKEY(应用详细页查询)
        params.put("dtype","json");//返回数据的格式,xml或json，默认json

        try {
            result =net(url, params, "GET");
            //JSONObject object = JSONObject.fromObject(result);
            JSONObject object = (JSONObject) JSONObject.toJSON(result);
            //if(object.getInt("error_code")==0){
            if(object.getInteger("error_code")==0){
                System.out.println(object.get("result"));
            }else{
                System.out.println(object.get("error_code")+":"+object.get("reason"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }



    /*public static void main(String[] args) {
        getRequest1();
    }*/

    /**
     *
     * @param strUrl 请求地址
     * @param params 请求参数
     * @param method 请求方法
     * @return  网络请求字符串
     * @throws Exception
     */
    public static String net(String strUrl, Map params,String method) throws Exception {
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        String rs = null;
        try {
            StringBuffer sb = new StringBuffer();
            if(method==null || method.equals("GET")){
                strUrl = strUrl+"?"+urlencode(params);
            }
            URL url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            if(method==null || method.equals("GET")){
                conn.setRequestMethod("GET");
            }else{
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
            }
            conn.setRequestProperty("User-agent", userAgent);
            conn.setUseCaches(false);
            conn.setConnectTimeout(DEF_CONN_TIMEOUT);
            conn.setReadTimeout(DEF_READ_TIMEOUT);
            conn.setInstanceFollowRedirects(false);
            conn.connect();
            if (params!= null && method.equals("POST")) {
                try {
                    DataOutputStream out = new DataOutputStream(conn.getOutputStream());
                    out.writeBytes(urlencode(params));
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
            InputStream is = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sb.append(strRead);
            }
            rs = sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }
        return rs;
    }

    //将map型转为请求参数型
    public static String urlencode(Map<String,Object>data) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry i : data.entrySet()) {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue()+"","UTF-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    /**
     * 检查验证码
     * @param user
     * @return
     */
    /*public static String GetPhoneCode(SysUser user) {
        String mobileNumber = user.getPhone();//手机号码
        String code = user.getPhoneCode();//验证码
        try {
            String str = getRequest1(mobileNumber,code);
            if("success".equals(str)){
                return "1";
            }else{
                return "2";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }*/

    /**
     * 发送验证码
     * @param phone
     */
    public static void postPhone(SysUser phone) {
        String mobileNumber = phone.getPhone();//接收验证码的手机号码
        try {
            String str = getRequest1(mobileNumber);
            if("success".equals(str)){
                System.out.println("发送成功！");
            }else{
                System.out.println("发送失败！");
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
