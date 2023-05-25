package com.amedakevin.app.webParent.admin.user;

import com.amedakevin.app.common.entity.Role;
import com.amedakevin.app.common.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public List<User> listAll(){
        return (List<User>) userRepository.findAll();
    }
    public List<Role> listRoles(){
        return (List<Role>) roleRepository.findAll();
    }
    public void save(User user){
        userRepository.save(user);
    }
}
