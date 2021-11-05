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

    public List<AdminDTO> findAll(){
        List<AdminDTO> all = adminDAO.findAll();
        return all;
    }
}