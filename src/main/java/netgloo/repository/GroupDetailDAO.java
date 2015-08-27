package netgloo.repository;

import java.util.List;

import netgloo.models.GroupDetailDo;

import org.springframework.data.repository.Repository;

public interface GroupDetailDAO extends Repository<GroupDetailDo, Long> {
	
	List<GroupDetailDo> findByOwnerid(long ownerid);
	GroupDetailDo findById(int id);
	List<GroupDetailDo> findByLatitudeAndLongitude(Double latitude, Double longitude);
	List<GroupDetailDo> findByOwneridAndRetired(long owner,int retired);
	void save(GroupDetailDo group);
	void delete(GroupDetailDo group);
	

}
