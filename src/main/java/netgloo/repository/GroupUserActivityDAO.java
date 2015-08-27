package netgloo.repository;

import netgloo.models.GroupDetailDo;
import netgloo.models.GroupUserAcitivityDo;

import org.springframework.data.repository.Repository;

public interface GroupUserActivityDAO extends Repository<GroupUserAcitivityDo, Long> {
	
	GroupDetailDo findById(int id);
	void save(GroupUserAcitivityDo groupUserActivity);
	void delete(GroupUserAcitivityDo groupUserActivity);
	GroupUserAcitivityDo findByUidAndGroupid(long uid, int groupid);
	

}
