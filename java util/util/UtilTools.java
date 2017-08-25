package com.util.tools;  
  
import java.io.BufferedReader;  
import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileReader;  
import java.io.IOException;  
import java.io.InputStream;  
import java.io.InputStreamReader;  
import java.io.PrintWriter;  
import java.io.UnsupportedEncodingException;  
import java.net.URL;  
import java.net.URLConnection;  
import java.net.URLDecoder;  
import java.text.ParseException;  
import java.text.SimpleDateFormat;  
import java.util.ArrayList;  
import java.util.Calendar;  
import java.util.Date;  
import java.util.HashMap;  
import java.util.Hashtable;  
import java.util.Iterator;  
import java.util.List;  
import java.util.Map;  
  
import javax.naming.AuthenticationException;  
import javax.naming.Context;  
import javax.naming.directory.DirContext;  
import javax.naming.directory.InitialDirContext;  
  
import net.sf.json.JSONArray;  
import net.sf.json.JSONObject;  
import net.sf.json.JsonConfig;  
  
import com.oemp.util.json.JsonValueProcessorImpl;  
import com.util.BytesEncodingDetect;  
  
  
public class UtilTools {  
  
    /** 
     * 正则表达式数字验证 
     *  
     * @author Long.Tang 
     * @param str 
     * @return 
     */  
    public static boolean isNumber(String str) {  
        if (str != null && !str.equals("")) {  
            java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("[0-9]*");  
            java.util.regex.Matcher match = pattern.matcher(str);  
            return match.matches();  
        } else {  
            return false;  
        }  
    }  
  
    /** 
     * 字符串非空非null判断 long.tang 
     */  
    public static boolean isEmpty(String val) {  
        if (val == null || val.equals("") || val.equalsIgnoreCase("null")) {  
            return true;  
        } else {  
            return false;  
        }  
    }  
  
    /** 
     * 将Java对象系列化为Json资料格式<br> 
     *  
     * @param pObject 
     *            传入的Java对象 
     * @return 
     */  
    public static final String encodeObject2Json(Object pObject) {  
        return encodeObject2Json(pObject,"MM/dd/yyyy HH:mm:ss");  
    }  
      
     /** 
     * 解码数据 
     * @param text 
     * @return 
     * @throws UnsupportedEncodingException 
     */  
    public static String deCodeStr(String text) throws UnsupportedEncodingException{  
        String str="";  
        if(!isEmpty(text)){  
            str= URLDecoder.decode(text,"UTF-8");  
        }  
        return str;  
    }  
      
      
    /** 
     *  
     * 将含有日期时间格式的Java对象系列化为Json资料格式 
     * Json-Lib在处理日期时间格式时需要实现其JsonValueProcessor接口,所以在这里提供一个重载的方法对含有 
     * 日期时间格式的Java对象进行序列化 
     *  
     * @param pObject 
     * 传入的Java对象 
     * @return 
     */  
    public static final String encodeObject2Json(Object pObject,String pFormatString) {  
        String jsonString = "[]";  
        if (null == pObject) {  
            // log.warn("传入的Java对象为空,不能将其序列化为Json资料格式.请检查!");  
        } else {  
            JsonConfig cfg = new JsonConfig();  
            cfg.registerJsonValueProcessor(java.sql.Timestamp.class,new JsonValueProcessorImpl(pFormatString));  
            cfg.registerJsonValueProcessor(java.util.Date.class,new JsonValueProcessorImpl(pFormatString));  
            cfg.registerJsonValueProcessor(java.sql.Date.class,new JsonValueProcessorImpl(pFormatString));  
  
              
            if (pObject instanceof ArrayList) {  
                JSONArray jsonArray = JSONArray.fromObject(pObject, cfg);  
                jsonString = jsonArray.toString();  
            } else {  
                JSONObject jsonObject = JSONObject.fromObject(pObject, cfg);  
                jsonString = jsonObject.toString();  
            }  
        }  
        return jsonString;  
    }  
      
      
    /** 
     * yyyy-MM-dd 
     */  
    public static final String YYYYMMDD = "yyyy-MM-dd";  
  
    /** 
     * yyyy-MM-dd HH:mm:ss 
     */  
    public static final String YYYYMMDDHHmmss = "yyyy-MM-dd HH:mm:ss";  
    /** 
     * yyyyMMddHHmmss 
     */  
    public static final String yyyymmddhhmmss = "yyyyMMddHHmmss";  
  
    /** 
     * 将字符串转化为时间 
     *  
     * @param dateStr 
     * @param pattern 
     * @return 
     */  
    public static Date formatDateByStr(String dateStr, String pattern) {  
        Date returnDate = null;  
        if (null != dateStr && !"".equals(dateStr)) {  
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);  
            try {  
                returnDate = sdf.parse(dateStr);  
            } catch (ParseException e) {  
                e.printStackTrace();  
            }  
        }  
        return returnDate;  
  
    }  
  
    /** 
     * 格式化日期 
     *  
     * @author Long.Tang 
     */  
    public static String formatDate(Date date, String pattern) {  
        String str_date = "";  
        try {  
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);  
            str_date = sdf.format(date);  
        } catch (Exception e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        return str_date;  
    }  
      
      
    /** 
     *  
     * @return 
     * @description 格式化日期格式 
     * @version 1.0 
     * @author  
     * @update 2012-6-2 
     */  
    public static String formatDate(Date date)  
    {  
        String returnDate = null;  
        if(null!= date && !"".equals(date))  
        {  
            SimpleDateFormat fa = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
            returnDate = fa.format(date);  
        }  
        return returnDate;  
              
    }  
      
    /** 
     * 取得当前日期 
     * now Date() 
     */  
    public static String getDate(){  
         SimpleDateFormat sdf = new SimpleDateFormat(YYYYMMDDHHmmss);  
         String  str_date = sdf.format(new Date());  
        return str_date;  
    }  
      
      
    /** 
     * 取得当前日期 
     * 20151013152115 
     */  
    public static String getCurrentDate(){  
         SimpleDateFormat sdf = new SimpleDateFormat(yyyymmddhhmmss);  
         String  str_date = sdf.format(new Date());  
        return str_date;  
    }  
      
    public static void main(String[] args) {  
          
        System.out.println("===>>"+ToDBC("ｈｅｌｌｏ"));  
        System.out.println("===>>"+ToSBC("hello"));  
        System.out.println("补:"+supplementNum("03",3));  
    }  
      
    public static String getCurrentTime(){  
        return String.valueOf(System.currentTimeMillis());  
    }  
      
    /** 
     * 补0 
     * @package com.util.tools 
     * @author long.tang 
     * @date 2016-12-23 
     * @method UtilTools.supplementNum() 
     * @project h3c_dbs  
     * @return 
     */  
    public static String supplementNum(String str,int num){  
        if(str.length()<num){  
            str="0"+str;  
        }else{  
            return str;  
        }  
        return supplementNum(str,num);  
    }  
      
    /** 
     *取得当前年及季度  
     */  
    public static String getQuarter(){  
        String date=formatDate(new Date(),"yyMM");  
        String str=date.substring(0,2);  
        String month=date.substring(2,date.length());  
        str=str+"Q"+getMonthQ(month);  
        return str;  
    }  
    /** 
    *  
    * @package com.util.tools 
    * @author long.tang 
    * @date 2016-12-23 
    * @method UtilTools.getMonthQ() 
    * @project h3c_dbs  
    * @param month 
    */  
    public static String getMonthQ(String month){  
        try {  
            if(Integer.parseInt(month)>=1 && Integer.parseInt(month)<4){  
                return "1";  
            }  
            if(Integer.parseInt(month)>=4 && Integer.parseInt(month)<7){  
                return "2";  
            }  
            if(Integer.parseInt(month)>=7 && Integer.parseInt(month)<10){  
                return "3";  
            }  
            if(Integer.parseInt(month)>=10 && Integer.parseInt(month)<13){  
                return "4";  
            }  
        } catch (NumberFormatException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        return "0";  
    }  
      
    /** 
     * @author long.tang 日期相加 
     * @parm type : year / month / day 
     * @param num 
     *            ex : 2 
     * @return 
     */  
    public static Date getNewDate(String type, int num) {  
        Calendar c = Calendar.getInstance();  
        c.setTime(new Date()); // 设置当前日期  
        if (!isEmpty(type) && type.equals("year")) {  
            c.add(Calendar.YEAR, num); // 日期加多少年  
        } else if (!isEmpty(type) && type.equals("month")) {  
            c.add(Calendar.MONTH, num); // 日期加多少年  
        } else if (!isEmpty(type) && type.equals("day")) {  
            c.add(Calendar.DATE, num); // 日期加多少年  
        }  
        return c.getTime();  
    }  
      
    /** 
     * @parm type : year / month / day /  
     * @param num 
     *            ex : 2 
     * @return 
     */  
    public static Long getNewDate2(Date date, String type, int num) {  
        Calendar c = Calendar.getInstance();  
        c.setTime(date); // 设置当前日期  
        if (!isEmpty(type) && type.equals("year")) {  
            c.add(Calendar.YEAR, num); // 日期加多少年  
        } else if (!isEmpty(type) && type.equals("month")) {  
            c.add(Calendar.MONTH, num); // 日期加多少月  
        } else if (!isEmpty(type) && type.equals("day")) {  
            c.add(Calendar.DATE, num); // 日期加多少日  
        } else if(!isEmpty(type) && type.equals("hour")) {  
            c.add(Calendar.HOUR, num); // 日期加多少时  
        } else if(!isEmpty(type) && type.equals("minute")) {  
            c.add(Calendar.MINUTE, num); // 日期加多少分  
        } else if(!isEmpty(type) && type.equals("second")) {  
            c.add(Calendar.SECOND, num); // 日期加多少秒  
        } else if(!isEmpty(type) && type.equals("millisecond")) {  
            c.add(Calendar.MILLISECOND, num); // 日期加多少毫秒  
        }  
        return c.getTime().getTime();  
    }  
  
    public static Long getStartTime() {  
        Calendar todayStart = Calendar.getInstance();  
        todayStart.set(Calendar.HOUR, 0);  
        todayStart.set(Calendar.MINUTE, 0);  
        todayStart.set(Calendar.SECOND, 0);  
        todayStart.set(Calendar.MILLISECOND, 0);  
        return todayStart.getTime().getTime();  
    }  
      
    public static Long getYesterdayTime() {  
        Calendar todayStart = Calendar.getInstance();  
        todayStart.add(Calendar.DATE,   -1);  
        todayStart.set(Calendar.HOUR, 0);  
        todayStart.set(Calendar.MINUTE, 0);  
        todayStart.set(Calendar.SECOND, 0);  
        todayStart.set(Calendar.MILLISECOND, 0);  
        return todayStart.getTime().getTime();  
    }  
  
    public static Long getEndTime() {  
        Calendar todayEnd = Calendar.getInstance();  
        todayEnd.set(Calendar.HOUR, 23);  
        todayEnd.set(Calendar.MINUTE, 59);  
        todayEnd.set(Calendar.SECOND, 59);  
        todayEnd.set(Calendar.MILLISECOND, 999);  
        return todayEnd.getTime().getTime();  
    }  
      
      
      
      
    /** 
     * 比较日期 
     * @param Date 日期  Month比较月份 Compare 比较符 
     * @author Long.Tang 
     * @return boolean 
     */  
  
    public static boolean compareBabyMonth(String date,String compare,int month,String type){  
        Date now_Date=new Date();  
        String nowdate= formatDate(now_Date);  
        boolean isTrueBirth = false;  
        Date babyBirthDay=formatDateByStr(date, YYYYMMDD);  
        Date babyBirth=null;  
        //当前日期-生日>=6  
        //生日+6小于等于当前日期  
        Calendar c = Calendar.getInstance();  
        c.setTime(babyBirthDay);  
        c.add(c.MONTH,month);  
        babyBirth=c.getTime();  
        String bady_Birth=formatDate(babyBirth);  
          int res=compare_date(bady_Birth,nowdate,type);  
          if(compare.equals(">=")){  
              if(res<=0){  
                  isTrueBirth=true;  
              }   
          }else if(compare.equals(">")){  
              if(res<0){  
                  isTrueBirth=true;  
              }  
          }else if(compare.equals("=")){  
              if(res==0){  
                  isTrueBirth=true;  
              }  
          }else if(compare.equals("<")){  
              if(res>0){  
                  isTrueBirth=true;  
              }  
          }else if(compare.equals("<=")){  
              if(res>=0){  
                  isTrueBirth=true;  
              }  
          }  
            
          
        return isTrueBirth;  
    }  
      
      
      
      
    /** 
     * @author Long.Tang 
     * @param DATE1 
     * @param DATE2 
     * @return 
     */  
  public static int compare_date(String DATE1, String DATE2,String Type) {  
          
          
     SimpleDateFormat df = null;  
     if(Type.equals("DAY")){  
         df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
     }else if(Type.equals("MONTH")){  
         df=new SimpleDateFormat("yyyy-MM-dd");  
     }  
            try {  
                Date dt1 = df.parse(DATE1);  
                Date dt2 = df.parse(DATE2);  
                if (dt1.getTime() > dt2.getTime()) {  
                    //System.out.println("dt1大于dt2");  
                    return 1;//大于  
                } else if (dt1.getTime() < dt2.getTime()) {  
                    //System.out.println("dt1小于dt2");  
                    return -1;//小于  
                } else {  
                    return 0;//等于  
                }  
            } catch (Exception ex) {  
                // TODO Auto-generated catch block  
                ex.printStackTrace();  
            }  
         
        return 0;  
    }  
      
      
      
    /** 
     * 使用java连接AD域 
     * @author long.tang 
     * @date 2015-1-26 
     * @throws 异常说明 
     * @param host 连接AD域服务器的IP 
     * @param post AD域服务器的端口 
     * @param username 用户名 
     * @param password 密码 
     * @return Integer 1 success 、0  false 、 -1 exception   
     */  
    public static Integer connectAD(String host,String post,String username,String password) {  
        DirContext ctx=null;  
        int isLogin = 0;  
        Hashtable<String,String> HashEnv = new Hashtable<String,String>();  
        HashEnv.put(Context.SECURITY_AUTHENTICATION, "simple"); // LDAP访问安全级别(none,simple,strong)  
        HashEnv.put(Context.SECURITY_PRINCIPAL, username!=null?username:""); //AD的用户名  
        HashEnv.put(Context.SECURITY_CREDENTIALS, password!=null?password:""); //AD的密码  
        HashEnv.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory"); // LDAP工厂类  
        HashEnv.put("com.sun.jndi.ldap.connect.timeout","3000");//连接超时设置为3秒  
        HashEnv.put(Context.PROVIDER_URL," ldap://" + host + ":" + post);//默认端口389  
        try {  
            if(password!=null&&!password.equals("")){  
                ctx = new InitialDirContext(HashEnv);//初始化上下文  
                //System.out.println("身份验证成功!");  
                isLogin = 1;  
            }else{  
                //System.out.println("身份验证失败!");  
                isLogin = 0;//没有输入密码属于身份失败  
            }  
              
        } catch (AuthenticationException e) {  
            //System.out.println("身份验证失败!");  
            e.printStackTrace();  
            isLogin = 0;  
        } catch (javax.naming.CommunicationException e) {  
            //System.out.println("AD域连接失败!");  
            e.printStackTrace();  
            isLogin = -1;  
        } catch (Exception e) {  
            //System.out.println("身份验证未知异常!");  
            e.printStackTrace();  
            isLogin = -1;  
        } finally{  
            if(null!=ctx){  
                try {  
                    ctx.close();  
                    ctx=null;  
                } catch (Exception e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
        return isLogin;  
    }  
      
      
    /** 
     * Action中动态解析URL返回Json 
     * 只支持GET请求 
     */  
     public static String getBackJson(String url){  
        StringBuffer htmlBuffer = new StringBuffer();  
        String returnStr = null;  
        try {  
            InputStream inputSource = new URL(url).openStream();  
            int ch;  
            while ((ch = inputSource.read()) > -1) {  
                htmlBuffer.append((char) ch);  
            }  
            inputSource.close();  
            returnStr = new String(htmlBuffer);  
            returnStr = new String(returnStr.getBytes("ISO8859_1"),"UTF-8");  
        } catch (Exception e) {  
            System.out.println("error>>>for getBackJson.>>>");  
            e.printStackTrace();  
        }  
        return returnStr;  
    }  
       
       
       
     /** 
      * JsonString to map 
      */  
     public static Map<String, Object> jsonStrToMap(String jsonStr){  
            Map<String, Object> map = new HashMap<String, Object>();  
            //最外层解析  
            JSONObject json = JSONObject.fromObject(jsonStr);  
            for(Object k : json.keySet()){  
                Object v = json.get(k);   
                //如果内层还是数组的话，继续解析  
                if(v instanceof JSONArray){  
                    List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();  
                    Iterator<JSONObject> it = ((JSONArray)v).iterator();  
                    while(it.hasNext()){  
                        JSONObject json2 = it.next();  
                        list.add(jsonStrToMap(json2.toString()));  
                    }  
                    map.put(k.toString(), list);  
                } else {  
                    map.put(k.toString(), v);  
                }  
            }  
            return map;  
        }  
  
       
  
    /** 
     * 向指定 URL 发送POST方法的请求 
     *  
     * @param url 
     *            发送请求的 URL 
     * @param param 
     *            请求参数，请求参数应该是 name1=value1&name2=value2 的形式。 
     * @return 所代表远程资源的响应结果 
     */  
    public static String sendPost(String url, String param) {  
        PrintWriter out = null;  
        BufferedReader in = null;  
        String result = "";  
        try {  
            URL realUrl = new URL(url);  
            // 打开和URL之间的连接  
            URLConnection conn = realUrl.openConnection();  
            // 设置通用的请求属性  
            conn.setRequestProperty("accept", "*/*");  
            conn.setRequestProperty("Content-Type", "application/octet-stream");  
            conn.setRequestProperty("connection", "Keep-Alive");  
            conn.setRequestProperty("Charset", "UTF-8");  
            conn.setRequestProperty("user-agent",  
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");  
            // 发送POST请求必须设置如下两行  
            conn.setDoOutput(true);  
            conn.setDoInput(true);  
            // 获取URLConnection对象对应的输出流  
            out = new PrintWriter(conn.getOutputStream());  
            // 发送请求参数  
            out.print(param);  
            // flush输出流的缓冲  
            out.flush();  
            // 定义BufferedReader输入流来读取URL的响应  
            in = new BufferedReader(  
                    new InputStreamReader(conn.getInputStream()));  
            String line;  
            while ((line = in.readLine()) != null) {  
                result += line;  
            }  
        } catch (Exception e) {  
            System.out.println("发送 POST 请求出现异常！" + e);  
            e.printStackTrace();  
        }  
        // 使用finally块来关闭输出流、输入流  
        finally {  
            try {  
                if (out != null) {  
                    out.close();  
                }  
                if (in != null) {  
                    in.close();  
                }  
            } catch (IOException ex) {  
                ex.printStackTrace();  
            }  
        }  
        return result;  
    }  
      
      
    /**  
     * 得到日期参数  
     * 转化后的日期  
     * @param date  
     * @return  
     */  
    public static Date getParamDate(String date){  
        String format_str=isDate(date);  
        //System.out.println(format_str);  
        if(!isEmpty(format_str)){  
             try {  
                SimpleDateFormat dateFormat = new SimpleDateFormat(format_str);   
                return dateFormat.parse(date);  
            } catch (ParseException e) {  
                // TODO Auto-generated catch block  
                e.printStackTrace();  
                return null;  
            }  
        }else{  
            return null;  
        }  
    }  
      
      
    /** 
     * 判断是否为日期字符串 
     * @param arg 
     * @throws Exception 
     */  
    public static String isDate(String date){  
        String format_str="";  
          
        if (!isEmpty(date)) {  
  
            try {  
                if(date.indexOf("-")>-1){  
                    String str_len=date.substring(0,date.indexOf("-"));  
                    if(str_len.length()<3){  
                        format_str="MM-dd-yyyy";  
                    }else{  
                        format_str="yyyy-MM-dd";  
                    }  
                }else if(date.indexOf("/")>-1){// 格式为/  
                    String str_len=date.substring(0,date.indexOf("/"));  
                    if(str_len.length()<3){  
                        format_str="MM/dd/yyyy";  
                    }else{  
                        format_str="yyyy/MM/dd";  
                    }  
                }  
                SimpleDateFormat dateFormat = new SimpleDateFormat(format_str);  
                dateFormat.parse(date);  
            } catch (Exception e) {  
                format_str=null;  
            }  
              
  
        }  
        return format_str;  
  
    }  
      
    /** 
     *  
     * @return 
     * @description 获取系统当前时间 
     * @version 1.0 
     * @author  
     * @update 2012-6-1 下午11:33:14 
     */  
    public static Date getCurrrentDate1()  
    {  
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());  
  
        SimpleDateFormat fa = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        Date faDate = null;  
        try {  
            faDate = fa.parse(date);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return faDate;  
    }  
      
      
    /** 
     * 读取CSV文件 
     *  
     * @param file 
     * @return 
     */  
    public static List<String> readCSV(File file) {  
        List<String> dataList = new ArrayList<String>();  
        BufferedReader br = null;  
        try {  
            BytesEncodingDetect encode = new BytesEncodingDetect();  
            int index = encode.detectEncoding(file);  
            String charset = BytesEncodingDetect.javaname[index];  
            InputStreamReader insReader = new InputStreamReader(new FileInputStream(file), charset);  
            br = new BufferedReader(insReader);  
            String line = "";  
            while ((line = br.readLine()) != null) {  
                dataList.add(line);  
                break;  
            }  
        } catch (Exception e) {  
        } finally {  
            if (br != null) {  
                try {  
                    br.close();  
                    br = null;  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
        return dataList;  
    }  
      
      
      
      
    /** 
     * 读取文件内容 
     * @param path 
     * @return 
     */  
    public static String getFileContent(String path) {  
        String result="";  
        try {  
            FileReader fr = new FileReader(path);// 需要读取的文件路径  
            BufferedReader br = new BufferedReader(fr);  
            String str = br.readLine();  
            result=str;  
            while (str != null)// 如果当前行不为空  
            {  
                System.out.println(str);// 打印当前行  
                str = br.readLine();// 读取下一行  
                if(str!=null){  
                  result+=str;  
                }  
            }  
            br.close();// 关闭BufferReader流  
            fr.close(); // 关闭文件流  
        } catch (IOException e)// 捕捉异常  
        {  
            System.out.println("指定文件不存在");// 处理异常  
        }  
        return result;  
    }  
      
      
      
    /*** 
     * 输出格式日期 
     * @param date 
     * 2015-11-30 11:30:22.237 
     * @return 2015-11-30 11:30:22 
     */  
    public static String sqlDateFormat(String date){  
        if(!UtilTools.isEmpty(date)){  
            if(date.indexOf(".")>-1){  
                String new_date = date.substring(0,date.lastIndexOf("."));  
                return new_date;  
            }else{  
                return date;  
            }  
        }else{  
            return date;  
        }  
    }  
      
    /** 
     * 半角转全角 
     *  
     * @param input 
     *            String. 
     * @return 全角字符串. 
     */  
    public static String ToSBC(String input) {  
        char c[] = input.toCharArray();  
        for (int i = 0; i < c.length; i++) {  
            if (c[i] == ' ') {  
                c[i] = '\u3000';  
            } else if (c[i] < '\177') {  
                c[i] = (char) (c[i] + 65248);  
  
            }  
        }  
        return new String(c);  
    }  
  
    /** 
     * 全角转半角 
     *  
     * @param input 
     *            String. 
     * @return 半角字符串 
     */  
    public static String ToDBC(String input) {  
  
        char c[] = input.toCharArray();  
        for (int i = 0; i < c.length; i++) {  
            if (c[i] == '\u3000') {  
                c[i] = ' ';  
            } else if (c[i] > '\uFF00' && c[i] < '\uFF5F') {  
                c[i] = (char) (c[i] - 65248);  
  
            }  
        }  
        String returnString = new String(c);  
  
        return returnString;  
    }  
      
  
}  