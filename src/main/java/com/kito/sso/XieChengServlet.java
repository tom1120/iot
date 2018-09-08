package com.kito.sso;/**
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
public class XieChengServlet extends HttpServlet {

    private String loginUrl;
    private String responseText;
    private String ssotype;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setHeader("Content-type", "text/html;charset=UTF-8");
        String ticketUrl = "https://www.corporatetravel.ctrip.com/corpservice/authorize/ticket";
        String appKey = "obk_jyt";
        String appSecurity = "obk_jyt";
        String ticketPostString = "{\"appKey\":\"" + appKey + "\",\"appSecurity\":\"" + appSecurity + "\"}";
        String ticketResponse = send(ticketUrl, "POST", ticketPostString);
        JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON(ticketResponse);
        String ticket = (String) jsonObject.get("Token");
        PrintWriter pw = resp.getWriter();

        if (ticket == null || ticket.equals("")) {
            pw.println("获取ticket失败:" + jsonObject.toString());
            return;

        }

        System.out.println("ticket = " + ticket);
//        String loginUrl=null;
//        String responseText=null;
        String employeeID="KT02425";
//        String employeeID = (String) req.getSession().getAttribute("workcode");//获取oa的workcode员工编码
        if (employeeID == null||employeeID.equals("")) {
            pw.println("获取员工编码为空！");
            return;
        }

//        String TA="N000001";
//        String cost1="成本中心1";
//        String cost2="成本中心2";
//        String cost3="成本中心3";


        this.ssotype = req.getParameter("ssotype");
        if (this.ssotype.equals("h5")) {
            setH5LoginContent(loginUrl, appKey, ticket, employeeID);
        } else if(this.ssotype.equals("pc")) {
            setPcLoginContent(loginUrl, appKey, ticket, employeeID);
        }else{
            pw.println("未知参数！");
            return;
        }



        pw.println(responseText);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        this.doGet(req, resp);
    }

    private void setPcLoginContent(String loginUrl, String appKey, String ticket, String employeeID) {


        loginUrl = "https://www.corporatetravel.ctrip.com/corpservice/authorize/login";
        responseText = "<head>\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html;charset=utf-8\" >\n" +
                "    <script>\n" +
                "        function formSubmit() {\n" +
                "            document.getElementById(\"fLogin\").submit();\n" +
                "        }\n" +
                "    </script>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <form name=\"fLogin\" id=\"fLogin\" method=\"post\" action=\"" + loginUrl + "\">\n" +
                "        <input type=\"hidden\" name=\"AppKey\" value=\"" + appKey + "\"/>\n" +
                "        <input type=\"hidden\" name=\"Ticket\" value=\"" + ticket + "\"/>\n" +
                "        <input type=\"hidden\" name=\"EmployeeID\" value=\"" + employeeID + "\"/>\n" +
                "        <script>\n" +
                "            formSubmit();\n" +
                "        </script>\n" +
                "    </form>\n" +
                "\n" +
                "</body>";

    }


    private void setH5LoginContent(String loginUrl, String appKey, String ticket, String employeeID) {


        this.loginUrl = "https://ct.ctrip.com/m/SingleSignOn/H5SignInfo";
        this.responseText = "<head>\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html;charset=utf-8\" >\n" +
                "    <script>\n" +
                "        function formSubmit() {\n" +
                "            document.getElementById(\"fLogin\").submit();\n" +
                "        }\n" +
                "    </script>\n" +
                "</head>\n" +
                "<body>\n" +
                "<form name=\"fLogin\" id=\"fLogin\" method=\"post\" action=\"" + loginUrl + "\">\n" +
                "    <input type=\"hidden\" name=\"accessuserid\" value=\"" + appKey + "\"/>\n" +
                "    <input type=\"hidden\" name=\"employeeid\" value=\"" + employeeID + "\"/>\n" +
                "    <input type=\"hidden\" name=\"initpage\" value=\"" + "Home" + "\"/>\n" +
                "    <input type=\"hidden\" name=\"appid\" value=\"" + "jyt" + "\"/>\n" +
                "    <input type=\"hidden\" name=\"token\" value=\"" + ticket + "\"/>\n" +
                "    <script>\n" +
                "        formSubmit();\n" +
                "    </script>\n" +
                "</form>\n" +
                "</body>";

    }


    public static String send(String request, String requestMethod, String output) {
        InputStream input = null;
        OutputStream out = null;
        StringBuffer buffer = new StringBuffer();
        try {
            // 建立连接
            URL url = new URL(request);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setRequestMethod(requestMethod);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.connect();
            if (output != null) {
                out = connection.getOutputStream();
                out.write(output.getBytes("UTF-8"));
                out.flush();
                out.close();
            }
            // 流处理
            input = connection.getInputStream();
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
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (output != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        return buffer.toString();
    }

}