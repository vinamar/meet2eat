package netgloo.repository;

import java.util.List;

import netgloo.models.UserDo;
import netgloo.models.UserFriendsDo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface UserFriendsDAO extends Repository<UserFriendsDo, Long> {
	
	void save(UserFriendsDo group);
	UserFriendsDo findById(int id);
	List<UserFriendsDo> findByUid(String uid);
	
	@Query( "select o from UserDo o where phoneno in :phonenos" )
	List<UserDo> findByPhonenos(@Param("phonenos") List<String> phonenos);
	
	@Query( "select o from UserFriendsDo o where uid in :uid and frienduid in :frienduid" )
	UserFriendsDo findByUidAndFrienduid(@Param("uid") long uid, @Param("frienduid") String frienduid);
	
	@Query( "select o from UserFriendsDo o where uid in :uid and friendphoneno in :friendphoneno" )
	UserFriendsDo findByUidAndFriendphoneno(@Param("uid") long uid, @Param("friendphoneno") String friendphoneno);
	
}
