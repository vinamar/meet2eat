package netgloo.repository;

import java.util.List;

import netgloo.models.UserDo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface UserDAO extends Repository<UserDo, Long> {
	
	void save(UserDo group);
	UserDo findById(int id);
	UserDo findByUid(String uid);
	UserDo findByPhoneno(String phoneno);
	
	@Query( "select o from UserDo o where phoneno in :phonenos" )
	List<UserDo> findByPhonenos(@Param("phonenos") List<String> phonenos);
}
