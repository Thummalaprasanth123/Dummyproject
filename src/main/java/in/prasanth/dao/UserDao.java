package in.prasanth.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import in.prasanth.entity.UserTbl;

@Repository
public interface UserDao extends CrudRepository<UserTbl, String> {

}
