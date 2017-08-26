package org.jeecgframework.core.quartz;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;


public class Test {

    private static final String URL="jdbc:mysql://localhost:3306/jeecg?useUnicode=true&characterEncoding=UTF-8";
    private static final String USER="kitoshop";
    private static final String PASSWORD="kito2018";

    private static final String URL2="jdbc:mysql://m.kito.cn:3306/jeecg?useUnicode=true&characterEncoding=UTF-8";
    private static final String USER2="root";
    private static final String PASSWORD2="kitoiotserver42899975";


    public static void main(String args[]) throws Exception{

        String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

        Class.forName(driverName);

        Connection con = DriverManager.getConnection(URL,USER,PASSWORD);
        Statement statement = con.createStatement();
        ResultSet rSet = statement.executeQuery("SELECT Boxmax,device_no from kito_qianlong_boxlist");

        Connection con2 = DriverManager.getConnection(URL2,USER2,PASSWORD2);
        Statement statement2 = con2.createStatement();
        ResultSet rSet2 = statement2.executeQuery("SELECT wifi_mac,device_key from device_initbind_params");
        while(rSet.next()){
            String m1 = rSet.getString("Boxmax");
            String d1 = rSet.getString("device_no");
            while(rSet2.next()){
                String m2 = rSet2.getString("wifi_mac");
                String d2 = rSet2.getString("device_key");
                if (m1 != null && d1 == null) {
                    String sql = "update kito_qianlong_boxlist set device_no =" + d2 + " where Boxmax =" + m2;
                    statement.executeUpdate(sql);
                }
            }
            rSet.close();
            rSet2.close();
            statement.close();
            statement2.close();
            con.close();
        }
    }
}

