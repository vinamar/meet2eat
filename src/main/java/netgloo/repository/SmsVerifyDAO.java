package netgloo.repository;

import netgloo.models.SmsVerifyDo;

import org.springframework.data.repository.Repository;

public interface SmsVerifyDAO extends Repository<SmsVerifyDo, Long> {
	
	SmsVerifyDo findByPhoneno(String phoneno);
	SmsVerifyDo findById(int id);
	void save(SmsVerifyDo smsverify);
}
