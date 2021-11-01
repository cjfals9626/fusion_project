package persistence.dao;

import persistence.PooledDataSource;
import persistence.dto.AdminDTO;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO {
    private final DataSource ds = PooledDataSource.getDataSource();
    public List<AdminDTO> findAll(){
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
                LocalDateTime birth = rs.getTimestamp("birth").toLocalDateTime();
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
}
