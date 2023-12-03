package com.example.web.controllers;

import com.example.web.dto.InstalmentDTO;
import com.example.web.models.Instalment;
import com.example.web.services.InstalmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/instalment")
public class InstalmentController {

    private final InstalmentService instalmentService;
    private final ModelMapper modelMapper;

    @Autowired
    public InstalmentController(InstalmentService instalmentService, ModelMapper modelMapper){
        this.instalmentService = instalmentService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/buypackage")
    public void buyPackage(@RequestBody InstalmentDTO instalmentDTO, BigDecimal amount, BindingResult bindingResult){
        Instalment instalment = convertToInstalment(instalmentDTO);

        //instalmentService.
    }

    @PostMapping("/buyinstallment") //добавить проверку
    public ResponseEntity<HttpStatus> startInstalment(@RequestBody InstalmentDTO instalmentDTO, BindingResult bindingResult){
        //Instalment instalment = convertToInstalment(instalmentDTO);
        //instalment.getOwner().getEmail();
        instalmentService.addInstalment(instalmentDTO);
        return ResponseEntity.ok(HttpStatus.OK);

    }

    @GetMapping("/all")
    public List<Instalment> showInstalments(){
        System.out.println("test controller");
        return instalmentService.showInstalments();
    }


    private Instalment convertToInstalment(InstalmentDTO instalmentDTO){
        return modelMapper.map(instalmentDTO, Instalment.class);
    }

    private InstalmentDTO convertToInstalmentDTO(Instalment instalment){
        return modelMapper.map(instalment, InstalmentDTO.class);
    }
}
