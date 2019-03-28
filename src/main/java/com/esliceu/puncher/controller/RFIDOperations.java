package com.esliceu.puncher.controller;

// Models
import com.esliceu.puncher.controller.model.AssignmentContainer;
import com.esliceu.puncher.controller.model.SigningRequest;
import com.esliceu.puncher.data.model.Reader;
import com.esliceu.puncher.data.model.Signing;
import com.esliceu.puncher.data.model.User;

// Repository
import com.esliceu.puncher.data.repository.SigningRepository;
import com.esliceu.puncher.repositories.UserRepository;

// HTTP stuff
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

// Annotations
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@RestController
public class RFIDOperations {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SigningRepository signingRepository;

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

    @RequestMapping(value = "/signings/validate", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity validate(@RequestBody SigningRequest signingRequest) {
        User user = userRepository.findByRfid(signingRequest.getRfid());

        // The user is not valid AKA null
        if (user == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        // These values must come from SigningRequest class
        String signingType = "";
        String signingDate = "";
        Reader reader = null;

        Signing signing = new Signing(signingType, signingDate, reader, user);
        signingRepository.save(signing);
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
