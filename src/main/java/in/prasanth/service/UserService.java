package in.prasanth.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.prasanth.dao.UserDao;
import in.prasanth.entity.Role;
import in.prasanth.entity.UserTbl;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;

	public UserTbl registerNewUser(UserTbl user) {
		UserTbl userrecord = userDao.save(user);
		return userrecord;
	}

	public UserTbl addData() {
		UserTbl u1 = new UserTbl();
		u1.setUserName("arjun");
		u1.setUserPassword("arjun 123");
		u1.setUserFirstName("arjun");
		u1.setUserLastName("Naidu");
//		UserTbl u2 = new UserTbl();
//		u2.setUserName("Bharat");
//		u2.setUserPassword("Bharat123");
//		u2.setUserFirstName("Bharat");
//		u2.setUserLastName("Reddy");
		Role r1 = new Role();
		r1.setRoleName("Admin");
		r1.setRoleDescription("Admin role");
		Role r2 = new Role();
		r2.setRoleName("User");
		r2.setRoleDescription("User role");
		Set<Role> r = new HashSet<>();
		r.add(r1);
		r.add(r2);
//		Set<Role> q = new HashSet<>();
//		q.add(r2);
		u1.setRole(r);
		//u2.setRole(q);
		UserTbl save1 = userDao.save(u1);
		//UserTbl save2 = userDao.save(u2);
		return save1;
	}

	public UserTbl forAdmin() {
		Optional<UserTbl> optional = userDao.findById("Ajay");
		//Optional<UserTbl> id = userDao.findById("Ajay");
		return optional.get();
	}

	public UserTbl forUser() {
		Optional<UserTbl> optional = userDao.findById("Bharat");
		return optional.get();
	}

	public UserTbl addUser() {
		// TODO Auto-generated method stub
		return null;
	}

}
