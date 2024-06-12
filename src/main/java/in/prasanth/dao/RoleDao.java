package in.prasanth.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import in.prasanth.entity.Role;

@Repository
public interface RoleDao extends CrudRepository<Role, String> {

}
