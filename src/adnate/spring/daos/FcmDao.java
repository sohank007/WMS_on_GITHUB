package adnate.spring.daos;

import java.util.List;

import adnate.spring.pojos.Fcm;

public interface FcmDao {
	 void insertToken(Fcm fcm);
	 void updateToken(Fcm fcm);
	 List<Fcm> getAlltokens();
	 Fcm getTokenByIMEI(String imei);
}
