package org.scoula.persistence;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;

import static org.junit.jupiter.api.Assertions.fail;

public class JDBCTests {

    @BeforeAll
    public static void setup(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("JDBC드라이버 연결이 됨")
    public void testConnection() {
        String url = "jdbc:mysql://localhost:3306/scoula_db";
        try(Connection con = DriverManager.getConnection(url, "scoula", "1234")){
            System.out.println(con);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
