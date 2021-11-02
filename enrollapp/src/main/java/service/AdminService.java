package service;

import persistence.dao.AdminDAO;
import persistence.dto.AdminDTO;

import java.util.List;

public class AdminService {
    private final AdminDAO adminDAO;

    public AdminService(AdminDAO boardDAO) {
        this.adminDAO = boardDAO;
    }
    public List<AdminDTO> findAdminAll(){
        List<AdminDTO> all = adminDAO.findAdminAll();
        return all;
    }

    public void insertAdmin(int admin_id){
        //adminDAO.insertAdmin(admin_id);
    }

    public void updateAdmin(int admin_id){
        adminDAO.updateAdmin(admin_id);
    }
}