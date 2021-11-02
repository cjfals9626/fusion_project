import persistence.MyBatisConnectionFactory;
import persistence.dao.*;
import persistence.dto.*;
import service.AdminService;
import service.ProfessorService;
import service.StudentService;
import view.UserView;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        AdminDAO adminDAO = new AdminDAO();
        UserView userView = new UserView();
        AdminService adminService = new AdminService(adminDAO);
        List<AdminDTO> adminAll = adminService.findAdminAll();
        userView.printAdminAll(adminAll);
        System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
        StudentDAO studentDAO = new StudentDAO();
        StudentService studentService = new StudentService(studentDAO);
        List<StudentDTO> studentAll = studentService.findStudentAll();
        userView.printStudentAll(studentAll);
        System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
        ProfessorDAO professorDAO = new ProfessorDAO();
        ProfessorService professorService = new ProfessorService(professorDAO);
        List<ProfessorDTO> professorAll = professorService.findProfessorAll();
        userView.printProfessorAll(professorAll);

        System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
        adminDAO.findAdminAll();
        adminDAO.insertAdmin(753);
        adminDAO.findAdminAll();
    }
}
