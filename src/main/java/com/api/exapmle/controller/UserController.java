
package com.api.exapmle.controller;

import com.api.exapmle.dto.APIResponse;
import com.api.exapmle.dto.UserDto;
import com.api.exapmle.entity.User;
import com.api.exapmle.service.UserService;
import com.api.exapmle.service.WhatsappSender;
import com.api.exapmle.service.SmsSender;
import com.api.exapmle.service.EmailSender;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/v1/employee")
public class UserController {

    private final UserService userService;
    private final WhatsappSender whatsappSender;
    private final SmsSender smsSender;
    private final EmailSender emailSender;

    public UserController(UserService userService,
                          WhatsappSender whatsappSender,
                          SmsSender smsSender,
                          EmailSender emailSender) {
        this.userService = userService;
        this.whatsappSender = whatsappSender;
        this.smsSender = smsSender;
        this.emailSender = emailSender;
    }

    @PostMapping("/save")
    public ResponseEntity<APIResponse<String>> saveEmployee(@RequestBody UserDto userDto) {
        // Save employee
        userService.saveEmployee(userDto);

        // Send notifications
        String smsMessage = "Hello " + userDto.getName() + ", your registration is successful!";
        smsSender.sendSms(userDto.getMobile(), smsMessage);
        whatsappSender.sendWhatsappMessage(userDto.getMobile(), smsMessage);
        emailSender.sendEmail(userDto.getEmail(), "Registration Successful", smsMessage);

        // Build response
        APIResponse<String> response = new APIResponse<>();
        response.setMessage("Employee saved and notifications sent");
        response.setStatus(201);
        response.setData("saved!!");

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<APIResponse<String>> updateEmployee(@RequestBody UserDto dto) {
        userService.updateRegistrationById(dto);

        String message = "Hello " + dto.getName() + ", your profile has been updated.";
        smsSender.sendSms(dto.getMobile(), message);
        whatsappSender.sendWhatsappMessage(dto.getMobile(), message);
        emailSender.sendEmail(dto.getEmail(), "Profile Updated", message);

        APIResponse<String> response = new APIResponse<>();
        response.setMessage("Employee updated and notifications sent");
        response.setStatus(200);
        response.setData("Employee Record updated");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<APIResponse<String>> deleteEmployee(@RequestParam long id) {
        User user = userService.getRegistrationById(id); // get info for notifications
        userService.deleteEmployee(id);

        String message = "Hello " + user.getName() + ", your profile has been deleted.";
        smsSender.sendSms(user.getMobile(), message);
        whatsappSender.sendWhatsappMessage(user.getMobile(), message);
        emailSender.sendEmail(user.getEmail(), "Profile Deleted", message);

        APIResponse<String> response = new APIResponse<>();
        response.setMessage("Employee deleted and notifications sent");
        response.setStatus(200);
        response.setData("Employee is deleted");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<APIResponse<List<User>>> getAllEmployees() {
        List<User> users = userService.getALLEmployees();
        APIResponse<List<User>> response = new APIResponse<>();
        response.setMessage("Done!!!");
        response.setStatus(200);
        response.setData(users);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getReg")
    public ResponseEntity<APIResponse<User>> getRegById(@RequestParam long id) {
        User user = userService.getRegistrationById(id);
        APIResponse<User> response = new APIResponse<>();
        response.setMessage("Employee Details");
        response.setStatus(200);
        response.setData(user);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

   //******************************* Pagination & Sorting in Spring boot - JPARepository  **********************
    @GetMapping
    public ResponseEntity<APIResponse<List<User>>> fetchAllRegistrations(
            @RequestParam(value = "pageNo",  defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize",  defaultValue = "5", required = false) int pageSize

    ){
        List<User> employees = userService.getRegistrations(pageNo,pageSize);
        APIResponse<List<User>> response = new APIResponse<>();
        response.setMessage("Employee Records");
        response.setStatus(200);
        response.setData(employees);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

//  *************************************************  Sorting  ********************************************************************
    public ResponseEntity<APIResponse<List<User>>> fetchAllRegistrations(
            @RequestParam(value = "pageNo",  defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize",  defaultValue = "5", required = false) int pageSize,
            @RequestParam(value = "sortBy",  defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDir",  defaultValue = "asc", required = false) String sortDir
    ){
        List<User> employees = userService.getRegistrations(pageNo,pageSize,sortBy,sortDir);
        APIResponse<List<User>> response = new APIResponse<>();
        response.setMessage("Employee Records");
        response.setStatus(200);
        response.setData(employees);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
