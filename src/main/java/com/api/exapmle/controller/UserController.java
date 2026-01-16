package com.api.exapmle.controller;

import com.api.exapmle.dto.APIResponse;
import com.api.exapmle.dto.UserDto;
import com.api.exapmle.entity.User;
import com.api.exapmle.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController

@RequestMapping("api/v1/employee")

public class UserController {

    private UserService userService;

    public UserController(UserService userService) {

        this.userService = userService;

    }
        @PostMapping ("/save")


            // http://localhost:8080/api/v1/employee/save

            public ResponseEntity <APIResponse <String>> saveEmployee( @RequestBody UserDto userDto) {
            System.out.println(userDto.getName());
            System.out.println(userDto.getEmail());       //for seen output in backend
            System.out.println(userDto.getMobile());


            userService.saveEmployee(userDto);//for seen output in database through service layer

            APIResponse<String> response = new APIResponse<>();
            response.setMessage("done");
            response.setStatus(201);
            response.setData("saved!!");

            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
               //   http://localhost:8080/api/v1/employee/delete?id=

 @DeleteMapping("/delete")
            public ResponseEntity<APIResponse<String>> deleteEmployee( @RequestParam long id)

			{
                userService.deleteEmployee(id);
                APIResponse<String> response = new APIResponse<>();
                response.setMessage("Delete....");
                response.setStatus(200);
                response.setData("Emplyee is deleted");

                return new ResponseEntity<>(response, HttpStatus.OK);
            }

    @PutMapping("/update")
   // http://localhost:8080/api/v1/employee

    public ResponseEntity<APIResponse<String>> UpdateRegistrations(
            @RequestBody UserDto dto
    ){
        userService.updateRegistrationById(dto);

        APIResponse<String> response = new APIResponse<>();
        response.setMessage("Updated....");
        response.setStatus(200);
        response.setData("Employee Record is udpdated");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/all")

    public ResponseEntity<APIResponse<List<User>>> getAllEmployees(){

        List<User> users = userService.getALLEmployees();
        APIResponse<List<User>> response = new APIResponse<>();
        response.setMessage("Done!!!");
        response.setStatus(200);
        response.setData(users);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/getReg")
    public ResponseEntity<APIResponse<User>> getRegById(
            @RequestParam long id
    ) {
        User user = userService.getRegistrationById(id);
        APIResponse<User> response = new APIResponse<>();
        response.setMessage("Employee Details");
        response.setStatus(200);
        response.setData(user);
        return new ResponseEntity<>(response, HttpStatus.OK);

    }


        }





