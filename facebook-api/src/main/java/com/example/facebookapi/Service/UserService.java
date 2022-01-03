package com.example.facebookapi.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.random.RandomGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.facebookapi.Entity.Post;
import com.example.facebookapi.Entity.User;
import com.example.facebookapi.Repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	
	public User submitMetaDataOfUser(User user) {
		
		Date date=new Date();
		long time=date.getTime();
		Timestamp dateTime=new Timestamp(time);
		String x = generateRandomString();
		user.setUserID(x);
		//postData.setPostID(UUID.randomUUID());
		user.setActive(true);
		user.setJoiningDate(dateTime);
		
		return userRepository.save(user);
	}
	
	public String generateRandomString() {
		 
	    int leftLimit = 97; // letter 'a'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 10;
	    Random random = new Random();
	    StringBuilder buffer = new StringBuilder(targetStringLength);
	    for (int i = 0; i < targetStringLength; i++) {
	        int randomLimitedInt = leftLimit + (int) 
	          (random.nextFloat() * (rightLimit - leftLimit + 1));
	        buffer.append((char) randomLimitedInt);
	    }
	    String generatedString = buffer.toString();

	    return generatedString;
	}
	
	public ArrayList<User> retrieveAllUserDetails(){
		return userRepository.findAll();
	}
	
	public User getUserData(String userID) {
		return userRepository.findAllByuserID(userID);
	}
	
	public ArrayList<User> deleteUserFromDB(String userID){
		userRepository.deleteById(userID);
		
		ArrayList<User> result=retrieveAllUserDetails();
		return result;
	}
}
