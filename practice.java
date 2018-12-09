import java.io.*;
import java.net.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class practice {
        private static String httpRequest(String requestUrl) {
            StringBuffer buffer = null;
            BufferedReader bufferedReader = null;
            InputStreamReader inputStreamReader = null;
            InputStream inputStream = null;
            HttpURLConnection httpUrlConn = null;

            try {
                // 建立get请求
                URL url = new URL(requestUrl);
                httpUrlConn = (HttpURLConnection) url.openConnection();
                httpUrlConn.setDoInput(true);
                httpUrlConn.setRequestMethod("GET");

                // 获取输入流
                inputStream = httpUrlConn.getInputStream();
                inputStreamReader = new InputStreamReader(inputStream, "utf-8");
                bufferedReader = new BufferedReader(inputStreamReader);

                // 从输入流读取结果
                buffer = new StringBuffer();
                String str = null;
                while ((str = bufferedReader.readLine()) != null) {
                    buffer.append(str);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // 释放资源
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (inputStreamReader != null) {
                    try {
                        inputStreamReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (httpUrlConn != null) {
                    httpUrlConn.disconnect();
                }
            }
            return buffer.toString();
        }
       public static String htmlFiter(String html){
            StringBuffer buffer=new StringBuffer();
            String str1="";
            String str2="";
            String str3="";
            html.replaceAll("\t","");
            Pattern p=Pattern.compile("\n");
            Matcher m=p.matcher(html);
            html=m.replaceAll("" );//
            p=Pattern.compile("(.*)(<tbody>+)(.*?)(</tbody>+)(.*)");
            m=p.matcher(html);
            if(m.matches()) {
                str1 = m.group(3);
                p = Pattern.compile("(.*)(<tr>+)(.*?)(</tr>+)(.*?)");
                m = p.matcher(str1);
                if (m.matches()) {
                    str1 = m.group(3);
                    p=Pattern.compile("(.*)(<td>.*)(.*?)(</td>)(.*)");
                    m=p.matcher(str2);
                    if(m.matches()){
                        str3=m.group(3);
                        buffer.append(str3);        //正则把我心态搞崩了，就没弄出来过，红岩的作业对我来说太难了，我一直在努力地跟进度，但是我太过愚笨，所以达不到要求请见谅。
                    }                               //因为爬取数据也没爬下来，所以数据库也没做出来，抱歉
                }
            }


////                   从教务在线上班级的所有课程的源代码中选取其中的有用信息
//            Pattern p= Pattern.compile("(.*)(<tbody>)(.*?)(</tbody>)(.*)");
//            Matcher m=p.matcher(html);
//            if(m.matches()){
//                str1=m.group(3);
//                //课程内容   <div class="kbTd" zc="00000000001">...</div>  zcn内全都是二进制的数字
//                p=Pattern.compile("(.*)(<div class=\"kbTd\">)(.*?)(zc=.*?)(.*?)(</div>)");
//                m=p.matcher(str1);
//                if(m.matches()){
//                    str2=m.group(5);
//                    buffer.append(str2);
//                }
//            }
            return buffer.toString();
        }
        public static String getInfo(){
            String html=httpRequest("http://jwzx.cqupt.edu.cn/kebiao/kb_bj.php?bj=13001710");
            String result=htmlFiter(html);


            return result;
        }
    public static void main(String[] args)  {
            String info=getInfo();
        System.out.println(info);
    }
}
