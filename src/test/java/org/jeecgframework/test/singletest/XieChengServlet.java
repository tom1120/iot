package org.jeecgframework.test.singletest;/**
 * Created by zhaoyipc on 2017/9/13.
 */

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author zhaoyi
 * @date 2017-09-2017/9/13-11:44
 */
public class XieChengServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ticketUrl="https://www.corporatetravel.ctrip.com/corpservice/authorize/ticket";
        String appKey="obk_jyt";
        String appSecurity="obk_jyt";
        String ticketPostString="{\"appKey\":\""+appKey+"\",\"appSecurity\":\""+appSecurity+"\"}";
        String ticketResponse=send(ticketUrl,"POST",ticketPostString);
        JSONObject jsonObject=(JSONObject) JSONSerializer.toJSON(ticketResponse);
        String ticket= (String) jsonObject.get("Token");
        System.out.println("ticket = " + ticket);
        String loginUrl=null;
        String responseText=null;
        String employeeID="";

//        String TA="N000001";
//        String cost1="成本中心1";
//        String cost2="成本中心2";
//        String cost3="成本中心3";

        loginUrl="https://www.corporatetravel.ctrip.com/corpservice/authorize/login";
        responseText="<head>\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html;charset=utf-8\" >\n" +
                "    <script>\n" +
                "        function formSubmit() {\n" +
                "            document.getElementById(\"fLogin\").submit();\n" +
                "        }\n" +
                "    </script>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <form name=\"fLogin\" id=\"fLogin\" method=\"post\" action=\"\">\n" +
                "        <input type=\"hidden\" name=\"AppKey\" value=\"\"/>\n" +
                "        <input type=\"hidden\" name=\"Ticket\" value=\"\"/>\n" +
                "        <input type=\"hidden\" name=\"EmployeeID\" value=\"\"/>\n" +
                "        <script>\n" +
                "            formSubmit();\n" +
                "        </script>\n" +
                "    </form>\n" +
                "\n" +
                "</body>";

        resp.setHeader("Content-type","text/html;charset=UTF-8");
        PrintWriter pw=resp.getWriter();
        pw.println(responseText);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        this.doGet(req,resp);
    }

    public static String send(String request,String requestMethod,String output){

        StringBuffer buffer = new StringBuffer();
        try {
            // 建立连接
            URL url = new URL(request);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setRequestMethod(requestMethod);
            if (output != null) {
                OutputStream out = connection.getOutputStream();
                out.write(output.getBytes("UTF-8"));
                out.flush();
                out.close();
            }
            // 流处理
            InputStream input = connection.getInputStream();
            InputStreamReader inputReader = new InputStreamReader(input, "UTF-8");
            BufferedReader reader = new BufferedReader(inputReader);
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            // 关闭连接、释放资源
            reader.close();
            inputReader.close();
            input.close();
            input = null;
            connection.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }finally {

        }

        return buffer.toString();
    }

}
