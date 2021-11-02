package persistence.dao;

import persistence.PooledDataSource;
import persistence.dto.AdminDTO;

import javax.sql.DataSource;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class AdminDAO {
    private final DataSource ds = PooledDataSource.getDataSource();
    public List<AdminDTO> findAdminAll(){
        Connection conn = null;
        String sql = "SELECT * FROM USER WHERE group_id = 1";
        Statement stmt= null;
        ResultSet rs = null;

        List<AdminDTO> adminDTOS = new ArrayList<>();
        try {
            conn = ds.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                AdminDTO adminDTO = new AdminDTO();
                int id = rs.getInt("id");
                String pw = rs.getString("pw");
                int group_id = rs.getInt("group_id");
                String name = rs.getString("name");
                LocalDate birth = rs.getTimestamp("birth").toLocalDateTime().toLocalDate();
                String phoneNumber = rs.getString("phoneNumber");

                adminDTO.setId(id);
                adminDTO.setPw(pw);
                adminDTO.setGroup_id(group_id);
                adminDTO.setName(name);
                adminDTO.setBirth(birth);
                adminDTO.setPhoneNumber(phoneNumber);
                adminDTOS.add(adminDTO);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }finally {
            try{
                if(conn != null && !rs.isClosed()){
                    rs.close();
                }
                if(conn != null && !stmt.isClosed()){
                    rs.close();
                }
                if(conn != null && !conn.isClosed()){
                    conn.close();
                }
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }
        return adminDTOS;
    }

    public void insertAdmin(int id) {
        Scanner s = new Scanner(System.in);

        String pw;
        String name;
        LocalDate birth;
        String phoneNumber;

        Connection conn = null;

        Statement stmt = null;
        ResultSet rs = null;
        CallableStatement cstmt = null;
        String sql = "select * from user where group_id = 1 and id = " + id;

        try{
            conn = ds.getConnection();
            conn.setAutoCommit(false);

            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            if(!rs.isBeforeFirst())
            {
                System.out.println("생성가능한 아이디");
                System.out.print("input pw : ");
                pw = s.next();
                System.out.print("input name : ");
                name = s.next();
                System.out.print("input birth : ");
                birth = LocalDate.parse(s.next(), DateTimeFormatter.ISO_DATE);
                System.out.print("input phoneNumber : ");
                phoneNumber = s.next();

                cstmt = conn.prepareCall("{call insertAdmin(?,?,?,?,?)}");
                cstmt.setInt(1, id);
                cstmt.setString(2, pw);
                cstmt.setString(3, name);
                cstmt.setDate(4, Date.valueOf(birth));
                cstmt.setString(5, phoneNumber);

                cstmt.execute();

                conn.commit();
            }
            else {
                System.out.println("중복된 id 존재");
            }
        } catch (SQLException e) {
            System.out.println("error : " + e);
            try {
                conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } finally{
            try{
                if(conn != null){
                    rs.close();
                }
                if(conn != null){
                    rs.close();
                }
                if(conn != null){
                    conn.close();
                }
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    public void updateAdmin(int admin_id) {
        Connection conn = null;
        String sql = "SELECT * FROM USER WHERE group_id = 1 and id = " + admin_id;
        System.out.println(sql);
        Statement stmt= null;
        ResultSet rs = null;
        CallableStatement cstmt = null;
        List<AdminDTO> adminDTOS = new ArrayList<>();
        try {
            conn = ds.getConnection();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            if(rs.isBeforeFirst()){
                rs.next();
                int id = rs.getInt("id");
                String pw = rs.getString("pw");
                int group_id = rs.getInt("group_id");
                String name = rs.getString("name");
                LocalDate birth = rs.getTimestamp("birth").toLocalDateTime().toLocalDate();
                String phoneNumber = rs.getString("phoneNumber");

                Scanner s = new Scanner(System.in);
                System.out.print("input pw :");
                pw = s.next();
                System.out.print("input name :");
                name = s.next();
                System.out.print("input birth yyyy-mm-dd:");
                birth = LocalDate.parse(s.next(), DateTimeFormatter.ISO_DATE);
                System.out.print("input phoneNumber :");
                String phoneNUmber = s.next();

                cstmt = conn.prepareCall("{call updateAdmin(?,?,?,?,?)}");
                cstmt.setInt(1, id);
                cstmt.setString(2, pw);
                cstmt.setString(3, name);
                cstmt.setDate(4, Date.valueOf(birth));
                cstmt.setString(5, phoneNUmber);

                cstmt.executeUpdate();
            }else{
                System.out.println("no");
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }finally {
            try{
                if(conn != null && !rs.isClosed()){
                    rs.close();
                }
                if(conn != null && !stmt.isClosed()){
                    rs.close();
                }
                if(conn != null && !conn.isClosed()){
                    conn.close();
                }
            }
            catch(SQLException e){
                e.printStackTrace();
            }
        }
    }
}
