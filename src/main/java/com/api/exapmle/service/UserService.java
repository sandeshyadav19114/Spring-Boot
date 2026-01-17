package com.api.exapmle.service;

import com.api.exapmle.dto.UserDto;
import com.api.exapmle.entity.User;
import com.api.exapmle.exception.ResourceNotFound;
import com.api.exapmle.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public void saveEmployee(UserDto userDto){
        User e=new User();
        BeanUtils.copyProperties(userDto,e);
         userRepository.save(e);

    }
    public void deleteEmployee(long id) {
        userRepository.deleteById(id);
    }
    public void updateRegistrationById(UserDto userDto) {
        User emp = new User();
        BeanUtils.copyProperties(userDto, emp);
        userRepository.save(emp);
    }

    public List<User> getALLEmployees() {
        List<User> users = userRepository.findAll();
        return users;
    }
    public User getRegistrationById(long id) {

        User user = userRepository.findById(id).orElseThrow(
                ()->new ResourceNotFound("Employee does not exist done")
        );
        return user;
    }
 
}

}
