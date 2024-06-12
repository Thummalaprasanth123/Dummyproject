package in.prasanth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.prasanth.dao.RoleDao;
import in.prasanth.entity.Role;

@Service
public class RoleService {
	@Autowired
	private RoleDao roleDao;

	public Role createNewRole(Role role) {
		Role rolerecord = roleDao.save(role);
		return rolerecord;

	}
}
