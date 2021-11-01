import persistence.MyBatisConnectionFactory;
import persistence.dao.*;
import persistence.dto.*;
import service.AdminService;
import view.AdminView;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        AdminDAO adminDAO = new AdminDAO();
        AdminView adminView = new AdminView();
        AdminService adminService = new AdminService(adminDAO);
        List<AdminDTO> all = adminService.findAll();
        adminView.printAll(all);
    }
}
