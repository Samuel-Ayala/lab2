package com.example.lab2_sw2.Controller;

import com.example.lab2_sw2.Entity.Department;
import com.example.lab2_sw2.Entity.EmployeeEntity;
import com.example.lab2_sw2.Entity.JobsEntity;
import com.example.lab2_sw2.Repository.DepartmentRepository;
import com.example.lab2_sw2.Repository.EmployeeRepository;
import com.example.lab2_sw2.Repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@RequestMapping(value = "/employee")
@Controller
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    JobRepository jobsRepository;
    @Autowired
    DepartmentRepository departmentRepository;

    @GetMapping("/lista")
    public String listarEmployees(Model model) {
        List<EmployeeEntity> listaEmp = employeeRepository.findAll();
        model.addAttribute("lista", listaEmp);
        return "employee/listar";
    }

    @GetMapping("/crear")
    public String crearEmployee(){

        return "employee/crear";
    }

    @PostMapping("/guardar")
    public String guardarEmployee(EmployeeEntity employee, RedirectAttributes atr){
        employeeRepository.save(employee);
        atr.addFlashAttribute("msg","usuario creado exitosamente");
        return "redirect:/employee/lista";
    }


    @GetMapping("/editar")
    public String editarEmpleado(@RequestParam("id") String id,
                                 Model model){
        Optional<EmployeeEntity> opt = employeeRepository.findById(id);
        if (opt.isPresent()) {
            EmployeeEntity employee =opt.get();

            List<JobsEntity> listaJob = jobsRepository.findAll();
            List<Department> listaDep = departmentRepository.findAll();
            List<EmployeeEntity> listaMan = employeeRepository.findAll();
            model.addAttribute("listaJob", listaJob);
            model.addAttribute("listaDep", listaDep);
            model.addAttribute("listaMan", listaMan);
            model.addAttribute("employee", employee);
            return "employee/editar";
        } else {
            return "redirect:/employee/lista";
        }
    }

    @PostMapping("/buscar")
    public String buscarEmployee(@RequestParam("searchfield") String searchfield,
                                 @RequestParam("searchfield2") String searchfield2,
                                 Model model){

        List<EmployeeEntity> listaempleados = employeeRepository.findByFirst_name(searchfield);
        model.addAttribute("lista1",listaempleados);

        List<EmployeeEntity> listaempleados2 = employeeRepository.findByLast_name(searchfield2);
        model.addAttribute("lista2",listaempleados2);



        return "employee/lista";

    }

}
