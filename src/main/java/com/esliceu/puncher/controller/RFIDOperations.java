package com.esliceu.puncher.controller;

// Models

import com.esliceu.puncher.controller.model.AssignmentContainer;
import com.esliceu.puncher.controller.model.SigningRequest;
import com.esliceu.puncher.controller.model.SigningResponse;
import com.esliceu.puncher.controller.model.Status;
import com.esliceu.puncher.data.model.Reader;
import com.esliceu.puncher.data.model.Signing;
import com.esliceu.puncher.data.model.User;
import com.esliceu.puncher.data.repository.SigningRepository;
import com.esliceu.puncher.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Optional;

// Repository
// Annotations

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

    @RequestMapping(value = "/signings", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<SigningResponse> validate(@RequestBody SigningRequest signingRequest) {
        User user = userRepository.findByRfid(signingRequest.getRfid());

        // The user is not valid AKA null
        if (user == null) {
            return new ResponseEntity<>(new SigningResponse(Status.KO,"picture","Marcatge no vàlid"),HttpStatus.BAD_REQUEST);
        }

        if (signingRequest.getSigningDate() == null){
            signingRequest.setSigningDate(new Date());

        }

        // These values must come from SigningRequest class
        String signingType = "";

        Reader reader = null;

        Signing signing = new Signing(signingType, signingRequest.getSigningDate(), reader, user);
        signingRepository.save(signing);
        return new ResponseEntity<>(new SigningResponse(Status.OK,"picture","Marcatge vàlid"),HttpStatus.OK);
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
