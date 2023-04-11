package com.cob.salesforce.controllers.admin.user;

import com.cob.salesforce.models.admin.user.UserModel;
import com.cob.salesforce.services.admin.user.UserCreatorService;
import com.cob.salesforce.services.admin.user.UserFinderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/admin/user")
public class UserController {
    @Autowired
    UserCreatorService creator;

    @Autowired
    UserFinderService finder;

    @PostMapping
    @ResponseBody
    public ResponseEntity create(@RequestBody UserModel model) {
        return new ResponseEntity(creator.create(model), HttpStatus.OK);
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
}
