package in.prasanth.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import in.prasanth.dao.UserDao;
import in.prasanth.entity.UserTbl;

@Service
public class MyUserDetailsService implements UserDetailsService {
	@Autowired
	private UserDao userdao;

	@Override
	public UserDetails loadUserByUsername(String s) {
		UserTbl userTbl = userdao.findById(s).get();

		if (userTbl != null) {
			return new org.springframework.security.core.userdetails.User(userTbl.getUserName(),
					userTbl.getUserPassword(), getAuthorities(userTbl));
		} else {
			throw new UsernameNotFoundException("Username is not valid");
		}
	}

	private Set getAuthorities(UserTbl userTbl) {
		Set authorities = new HashSet<>();
		userTbl.getRole().forEach(role -> {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
		});
		return authorities;
	}
}
