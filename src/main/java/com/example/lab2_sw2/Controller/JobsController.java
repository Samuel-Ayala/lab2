package com.example.lab2_sw2.Controller;


import com.example.lab2_sw2.Entity.JobsEntity;
import com.example.lab2_sw2.Repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/job")


public class JobsController {
    @Autowired
    JobRepository jobRepository;


    @GetMapping(value = {"","/listar"})
    public String listaJobs(Model model){
        List<JobsEntity> listaJob = jobRepository.findAll();

        model.addAttribute("listaJob",listaJob);
        return "job/listaJob";


    }
    @PostMapping("/BuscarJob")
    public String buscarJob(@RequestParam("salario") int salario , Model model){

        List<JobsEntity> listaJobs = jobRepository.EncontrarSalario(salario);
        model.addAttribute("jobList", listaJobs);
        return "job/listaJob";
    }


    @GetMapping("/nuevo")
    public String nuevoJob(){
        return "job/nuevoformulario";

    }

    @PostMapping("/guardar")
    public String guardarJob(JobsEntity job){
        jobRepository.save(job);
        return "redirect:/job";
    }

    @GetMapping("/editar")
    public String editarJobForm(@RequestParam("id") String id, Model model) {
        Optional<JobsEntity> opt = jobRepository.findById(id);
        if (opt.isPresent()) {
            JobsEntity job = opt.get();
            model.addAttribute("job", job);
            return "job/editJob";
        } else {
            return "redirect:/job";

        }
    }

}
