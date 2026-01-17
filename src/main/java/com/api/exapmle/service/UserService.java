package com.api.exapmle.service;

import com.api.exapmle.dto.UserDto;
import com.api.exapmle.entity.User;
import com.api.exapmle.exception.ResourceNotFound;
import com.api.exapmle.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        return userRepository.findAll();
    }
    public User getRegistrationById(long id) {

        User user = userRepository.findById(id).orElseThrow(
                ()->new ResourceNotFound("Employee does not exist done")
        );
        return user;
    }

// ********************************************** Pagination & Sorting in Spring boot - JPARepository  **********************
    public List<User> getRegistrations(int pageNo, int pageSize) {

            Pageable pageable = PageRequest.of(pageNo, pageSize);
            Page<User> page = userRepository.findAll(pageable);
            List<User> users = page.getContent();
            return users;

        }

//  *************************************************  Sorting  ********************************************************************
    
    public List<User> getRegistrations(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = null;


//Sort sort = null;
//		sort = sortDir.equalsIgnoreCase("asc")?Sort.by(Direction.ASC,sortBy):Sort.by(Direction.DESC,sortBy);

        if(sortDir.equalsIgnoreCase("asc")) {
            sort = Sort.by(Sort.Direction.ASC, sortBy);
        }else {
            sort = Sort.by(Sort.Direction.DESC, sortBy);
        }

        Pageable pageable = PageRequest.of(pageNo, pageSize,sort);
        Page<User> page = userRepository.findAll(pageable);
        List<User> users = page.getContent();

            sort = sortDir.equalsIgnoreCase("asc")?Sort.by(Sort.Direction.ASC, sortBy):Sort.by(Sort.Direction.DESC, sortBy);

            pageable = PageRequest.of(pageNo, pageSize, sort);
            page = userRepository.findAll(pageable);
            users = page.getContent();

            System.out.println(page.getNumber());
            System.out.println(page.getSize());
            System.out.println(page.getTotalPages());
            System.out.println(page.getTotalElements());
            System.out.println(pageable.getPageNumber());
            System.out.println(pageable.getPageSize());

            return users;

        }


    }


