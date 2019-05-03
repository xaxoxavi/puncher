package com.esliceu.puncher.controller;

import com.esliceu.puncher.data.model.Professor;
import com.esliceu.puncher.data.model.Signing;
import com.esliceu.puncher.data.repository.ProfessorRepository;
import com.esliceu.puncher.data.repository.SigningRepository;
import com.esliceu.puncher.dto.ProfessorDto;
import com.esliceu.puncher.dto.SigningDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {


    private final ModelMapper modelMapper;

    private final ProfessorRepository professorRepository;

    private final SigningRepository signingRepository;

    @Autowired
    public UserController(ModelMapper modelMapper, ProfessorRepository professorRepository, SigningRepository signingRepository) {
        this.modelMapper = modelMapper;
        this.professorRepository = professorRepository;
        this.signingRepository = signingRepository;
    }


    @RequestMapping("/users/profes")
    public List<ProfessorDto> findAllProfessors(){

        List<Professor> professors = (List<Professor>) professorRepository.findAll();

        return professors.stream().map(profe -> modelMapper.map(profe,ProfessorDto.class))
                .collect(Collectors.toList());

    }

    @RequestMapping("/users/{userId}/signings")
    public List<SigningDto> signingsByUser(@PathVariable Long userId){

        List<Signing> signings = signingRepository.findByUserId(userId);

        return signings.stream().map(signing -> modelMapper.map(signing, SigningDto.class))
                .collect(Collectors.toList());


    }

}
