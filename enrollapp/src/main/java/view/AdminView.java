package view;

import persistence.dto.AdminDTO;

import java.util.List;

public class AdminView {
    public void printAll(List<AdminDTO> dtos){
        for(AdminDTO dto:dtos){
            System.out.println("dto.toString() = " + dto.toString());
        }
    }
}