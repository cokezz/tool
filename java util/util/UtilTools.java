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
     * ������ʽ������֤ 
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
     * �ַ����ǿշ�null�ж� long.tang 
     */  
    public static boolean isEmpty(String val) {  
        if (val == null || val.equals("") || val.equalsIgnoreCase("null")) {  
            return true;  
        } else {  
            return false;  
        }  
    }  
  
    /** 
     * ��Java����ϵ�л�ΪJson���ϸ�ʽ<br> 
     *  
     * @param pObject 
     *            �����Java���� 
     * @return 
     */  
    public static final String encodeObject2Json(Object pObject) {  
        return encodeObject2Json(pObject,"MM/dd/yyyy HH:mm:ss");  
    }  
      
     /** 
     * �������� 
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
     * ����������ʱ���ʽ��Java����ϵ�л�ΪJson���ϸ�ʽ 
     * Json-Lib�ڴ�������ʱ���ʽʱ��Ҫʵ����JsonValueProcessor�ӿ�,�����������ṩһ�����صķ����Ժ��� 
     * ����ʱ���ʽ��Java����������л� 
     *  
     * @param pObject 
     * �����Java���� 
     * @return 
     */  
    public static final String encodeObject2Json(Object pObject,String pFormatString) {  
        String jsonString = "[]";  
        if (null == pObject) {  
            // log.warn("�����Java����Ϊ��,���ܽ������л�ΪJson���ϸ�ʽ.����!");  
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
     * ���ַ���ת��Ϊʱ�� 
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
     * ��ʽ������ 
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
     * @description ��ʽ�����ڸ�ʽ 
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
     * ȡ�õ�ǰ���� 
     * now Date() 
     */  
    public static String getDate(){  
         SimpleDateFormat sdf = new SimpleDateFormat(YYYYMMDDHHmmss);  
         String  str_date = sdf.format(new Date());  
        return str_date;  
    }  
      
      
    /** 
     * ȡ�õ�ǰ���� 
     * 20151013152115 
     */  
    public static String getCurrentDate(){  
         SimpleDateFormat sdf = new SimpleDateFormat(yyyymmddhhmmss);  
         String  str_date = sdf.format(new Date());  
        return str_date;  
    }  
      
    public static void main(String[] args) {  
          
        System.out.println("===>>"+ToDBC("������"));  
        System.out.println("===>>"+ToSBC("hello"));  
        System.out.println("��:"+supplementNum("03",3));  
    }  
      
    public static String getCurrentTime(){  
        return String.valueOf(System.currentTimeMillis());  
    }  
      
    /** 
     * ��0 
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
     *ȡ�õ�ǰ�꼰����  
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
     * @author long.tang ������� 
     * @parm type : year / month / day 
     * @param num 
     *            ex : 2 
     * @return 
     */  
    public static Date getNewDate(String type, int num) {  
        Calendar c = Calendar.getInstance();  
        c.setTime(new Date()); // ���õ�ǰ����  
        if (!isEmpty(type) && type.equals("year")) {  
            c.add(Calendar.YEAR, num); // ���ڼӶ�����  
        } else if (!isEmpty(type) && type.equals("month")) {  
            c.add(Calendar.MONTH, num); // ���ڼӶ�����  
        } else if (!isEmpty(type) && type.equals("day")) {  
            c.add(Calendar.DATE, num); // ���ڼӶ�����  
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
        c.setTime(date); // ���õ�ǰ����  
        if (!isEmpty(type) && type.equals("year")) {  
            c.add(Calendar.YEAR, num); // ���ڼӶ�����  
        } else if (!isEmpty(type) && type.equals("month")) {  
            c.add(Calendar.MONTH, num); // ���ڼӶ�����  
        } else if (!isEmpty(type) && type.equals("day")) {  
            c.add(Calendar.DATE, num); // ���ڼӶ�����  
        } else if(!isEmpty(type) && type.equals("hour")) {  
            c.add(Calendar.HOUR, num); // ���ڼӶ���ʱ  
        } else if(!isEmpty(type) && type.equals("minute")) {  
            c.add(Calendar.MINUTE, num); // ���ڼӶ��ٷ�  
        } else if(!isEmpty(type) && type.equals("second")) {  
            c.add(Calendar.SECOND, num); // ���ڼӶ�����  
        } else if(!isEmpty(type) && type.equals("millisecond")) {  
            c.add(Calendar.MILLISECOND, num); // ���ڼӶ��ٺ���  
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
     * �Ƚ����� 
     * @param Date ����  Month�Ƚ��·� Compare �ȽϷ� 
     * @author Long.Tang 
     * @return boolean 
     */  
  
    public static boolean compareBabyMonth(String date,String compare,int month,String type){  
        Date now_Date=new Date();  
        String nowdate= formatDate(now_Date);  
        boolean isTrueBirth = false;  
        Date babyBirthDay=formatDateByStr(date, YYYYMMDD);  
        Date babyBirth=null;  
        //��ǰ����-����>=6  
        //����+6С�ڵ��ڵ�ǰ����  
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
                    //System.out.println("dt1����dt2");  
                    return 1;//����  
                } else if (dt1.getTime() < dt2.getTime()) {  
                    //System.out.println("dt1С��dt2");  
                    return -1;//С��  
                } else {  
                    return 0;//����  
                }  
            } catch (Exception ex) {  
                // TODO Auto-generated catch block  
                ex.printStackTrace();  
            }  
         
        return 0;  
    }  
      
      
      
    /** 
     * ʹ��java����AD�� 
     * @author long.tang 
     * @date 2015-1-26 
     * @throws �쳣˵�� 
     * @param host ����AD���������IP 
     * @param post AD��������Ķ˿� 
     * @param username �û��� 
     * @param password ���� 
     * @return Integer 1 success ��0  false �� -1 exception   
     */  
    public static Integer connectAD(String host,String post,String username,String password) {  
        DirContext ctx=null;  
        int isLogin = 0;  
        Hashtable<String,String> HashEnv = new Hashtable<String,String>();  
        HashEnv.put(Context.SECURITY_AUTHENTICATION, "simple"); // LDAP���ʰ�ȫ����(none,simple,strong)  
        HashEnv.put(Context.SECURITY_PRINCIPAL, username!=null?username:""); //AD���û���  
        HashEnv.put(Context.SECURITY_CREDENTIALS, password!=null?password:""); //AD������  
        HashEnv.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory"); // LDAP������  
        HashEnv.put("com.sun.jndi.ldap.connect.timeout","3000");//���ӳ�ʱ����Ϊ3��  
        HashEnv.put(Context.PROVIDER_URL," ldap://" + host + ":" + post);//Ĭ�϶˿�389  
        try {  
            if(password!=null&&!password.equals("")){  
                ctx = new InitialDirContext(HashEnv);//��ʼ��������  
                //System.out.println("�����֤�ɹ�!");  
                isLogin = 1;  
            }else{  
                //System.out.println("�����֤ʧ��!");  
                isLogin = 0;//û�����������������ʧ��  
            }  
              
        } catch (AuthenticationException e) {  
            //System.out.println("�����֤ʧ��!");  
            e.printStackTrace();  
            isLogin = 0;  
        } catch (javax.naming.CommunicationException e) {  
            //System.out.println("AD������ʧ��!");  
            e.printStackTrace();  
            isLogin = -1;  
        } catch (Exception e) {  
            //System.out.println("�����֤δ֪�쳣!");  
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
     * Action�ж�̬����URL����Json 
     * ֻ֧��GET���� 
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
            //��������  
            JSONObject json = JSONObject.fromObject(jsonStr);  
            for(Object k : json.keySet()){  
                Object v = json.get(k);   
                //����ڲ㻹������Ļ�����������  
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
     * ��ָ�� URL ����POST���������� 
     *  
     * @param url 
     *            ��������� URL 
     * @param param 
     *            ����������������Ӧ���� name1=value1&name2=value2 ����ʽ�� 
     * @return ������Զ����Դ����Ӧ��� 
     */  
    public static String sendPost(String url, String param) {  
        PrintWriter out = null;  
        BufferedReader in = null;  
        String result = "";  
        try {  
            URL realUrl = new URL(url);  
            // �򿪺�URL֮�������  
            URLConnection conn = realUrl.openConnection();  
            // ����ͨ�õ���������  
            conn.setRequestProperty("accept", "*/*");  
            conn.setRequestProperty("Content-Type", "application/octet-stream");  
            conn.setRequestProperty("connection", "Keep-Alive");  
            conn.setRequestProperty("Charset", "UTF-8");  
            conn.setRequestProperty("user-agent",  
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");  
            // ����POST�������������������  
            conn.setDoOutput(true);  
            conn.setDoInput(true);  
            // ��ȡURLConnection�����Ӧ�������  
            out = new PrintWriter(conn.getOutputStream());  
            // �����������  
            out.print(param);  
            // flush������Ļ���  
            out.flush();  
            // ����BufferedReader����������ȡURL����Ӧ  
            in = new BufferedReader(  
                    new InputStreamReader(conn.getInputStream()));  
            String line;  
            while ((line = in.readLine()) != null) {  
                result += line;  
            }  
        } catch (Exception e) {  
            System.out.println("���� POST ��������쳣��" + e);  
            e.printStackTrace();  
        }  
        // ʹ��finally�����ر��������������  
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
     * �õ����ڲ���  
     * ת���������  
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
     * �ж��Ƿ�Ϊ�����ַ��� 
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
                }else if(date.indexOf("/")>-1){// ��ʽΪ/  
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
     * @description ��ȡϵͳ��ǰʱ�� 
     * @version 1.0 
     * @author  
     * @update 2012-6-1 ����11:33:14 
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
     * ��ȡCSV�ļ� 
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
     * ��ȡ�ļ����� 
     * @param path 
     * @return 
     */  
    public static String getFileContent(String path) {  
        String result="";  
        try {  
            FileReader fr = new FileReader(path);// ��Ҫ��ȡ���ļ�·��  
            BufferedReader br = new BufferedReader(fr);  
            String str = br.readLine();  
            result=str;  
            while (str != null)// �����ǰ�в�Ϊ��  
            {  
                System.out.println(str);// ��ӡ��ǰ��  
                str = br.readLine();// ��ȡ��һ��  
                if(str!=null){  
                  result+=str;  
                }  
            }  
            br.close();// �ر�BufferReader��  
            fr.close(); // �ر��ļ���  
        } catch (IOException e)// ��׽�쳣  
        {  
            System.out.println("ָ���ļ�������");// �����쳣  
        }  
        return result;  
    }  
      
      
      
    /*** 
     * �����ʽ���� 
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
     * ���תȫ�� 
     *  
     * @param input 
     *            String. 
     * @return ȫ���ַ���. 
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
     * ȫ��ת��� 
     *  
     * @param input 
     *            String. 
     * @return ����ַ��� 
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