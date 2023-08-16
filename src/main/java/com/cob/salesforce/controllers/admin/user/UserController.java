package com.cob.salesforce.controllers.admin.user;

import com.cob.salesforce.exception.business.UserException;
import com.cob.salesforce.models.admin.security.CurrentLoggInUser;
import com.cob.salesforce.models.admin.user.UserModel;
import com.cob.salesforce.models.validation.PatientFields;
import com.cob.salesforce.services.admin.user.UserCreatorService;
import com.cob.salesforce.services.admin.user.UserFinderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@CrossOrigin
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserCreatorService creator;

    @Autowired
    UserFinderService finder;

    @Autowired
    ModelMapper mapper;

    @PostMapping(path = "/create")
    public ResponseEntity create(@RequestBody UserModel model) throws NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        return new ResponseEntity(creator.create(model),HttpStatus.OK);
    }
    @GetMapping(path = "/find")
    @ResponseBody
    public ResponseEntity getAll() {
        return new ResponseEntity(finder.getAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/find/{userId}")
    @ResponseBody
    public ResponseEntity getById(@PathVariable Long userId) {
        return new ResponseEntity(finder.getById(userId), HttpStatus.OK);
    }
    @GetMapping(path = "/find/loggedIn/{userId}")
    @ResponseBody
    public ResponseEntity getLoggedIn(@PathVariable String userId) {
        return new ResponseEntity(mapper.map(finder.getById(Long.valueOf(userId)), CurrentLoggInUser.class), HttpStatus.OK);
    }

    @GetMapping(path = "/find/clinics/{userId}")
    @ResponseBody
    public ResponseEntity getUserClinics(@PathVariable Long userId) {
        return new ResponseEntity(finder.findByUserId(userId), HttpStatus.OK);
    }


}
