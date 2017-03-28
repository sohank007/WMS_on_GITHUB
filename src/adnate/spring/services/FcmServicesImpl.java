package adnate.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import adnate.spring.daos.FcmDao;
import adnate.spring.pojos.Fcm;

@Service
public class FcmServicesImpl implements FcmServices {

	@Autowired
	FcmDao fcmDao;
	
	@Override
	public void insertToken(Fcm fcm) {
		fcmDao.insertToken(fcm);
  }

	@Override
	public List<Fcm> getAlltokens() {
       return fcmDao.getAlltokens();
	}

	@Override
	public Fcm getTokenByIMEI(String imei) {
		return fcmDao.getTokenByIMEI(imei);
	}

	@Override
	public void updateToken(Fcm fcm) {
		fcmDao.updateToken(fcm);
		
	}

}
