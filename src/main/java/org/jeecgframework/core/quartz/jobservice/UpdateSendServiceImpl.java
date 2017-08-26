package org.jeecgframework.core.quartz.jobservice;

import org.springframework.stereotype.Service;

import java.io.Serializable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017-8-23.
 */

@Service("updateSendServiceImpl")
public class UpdateSendServiceImpl implements UpdateSendService,Serializable {

    private static final String URL = "jdbc:mysql://m.kito.cn:3306/kitoshop?useUnicode=true&characterEncoding=UTF-8";
    private static final String USER = "kitoshop";
    private static final String PASSWORD = "kito2018";

    private static final String URL2 = "jdbc:mysql://121.201.125.154:3306/jeecg?useUnicode=true&characterEncoding=UTF-8";
    private static final String USER2 = "root";
    private static final String PASSWORD2 = "kitoiotserver42899975";

    @Override
    public void executeUpdate() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement stmt = con.createStatement();
            ResultSet rSet = stmt.executeQuery("SELECT Boxmax,device_no from kito_qianlong_boxlist");
            Connection con2 = DriverManager.getConnection(URL2, USER2, PASSWORD2);
            Statement stmt2 = con2.createStatement();
            ResultSet rSet2 = stmt2.executeQuery("SELECT wifi_mac,device_key from device_initbind_params");
            List M2 = new ArrayList();
            List D2 = new ArrayList();
            List D1 = new ArrayList();

            while (rSet2.next()) {
                String m2 = rSet2.getString("wifi_mac");
                String d2 = rSet2.getString("device_key");
                M2.add(m2);
                D2.add(d2);
            }

            for (int i=0;i<D2.size();i++){
                String sql = "update kito_qianlong_boxlist set device_no = '" + D2.get(i) + "' where device_no is null and Boxmax = '" + M2.get(i) + "'";
                stmt.executeUpdate(sql);
            }

            rSet.close();
            rSet2.close();
            stmt.close();
            stmt2.close();
            con.close();
            con2.close();

        } catch (Exception e) {e.printStackTrace();};
    }
}
