package com.esliceu.puncher.controller;

import com.esliceu.puncher.controller.model.AssignmentContainer;
import com.esliceu.puncher.data.model.User;
import com.esliceu.puncher.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class RFIDOperations {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/user/assign",method = RequestMethod.POST)
    public ResponseEntity assigjn(@RequestBody  AssignmentContainer container){
        assignRfidToUser(container);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/users/assign",method = RequestMethod.POST)
    public ResponseEntity assigjn(@RequestBody  List<AssignmentContainer> container){
        container.forEach(this::assignRfidToUser);
        return new ResponseEntity(HttpStatus.OK);
    }


    private void assignRfidToUser(AssignmentContainer assignment){

            Optional<User> userWrap = userRepository.findById(assignment.getUserId());
            if (userWrap.isPresent()){
                User user = userWrap.get();
                user.setRfid(assignment.getRfid());
                userRepository.save(user);
            }

    }

}
