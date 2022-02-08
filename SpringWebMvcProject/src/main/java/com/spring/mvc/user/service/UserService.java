package com.spring.mvc.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.mvc.user.model.UserVO;
import com.spring.mvc.user.repository.IUserMapper;

@Service
public class UserService implements IUserService {

   @Autowired
   private IUserMapper mapper;
   
   @Override
   public int checkId(String account) {
      
      return 0;
   }

   @Override
   public void register(UserVO user) {
      

   }

   @Override
   public UserVO selectOne(String account) {
      
      return null;
   }

   @Override
   public void delete(String account) {
      

   }

}