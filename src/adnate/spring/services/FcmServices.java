package adnate.spring.services;

import java.util.List;

import adnate.spring.pojos.Fcm;

public interface FcmServices {
   void insertToken(Fcm fcm);
   List<Fcm> getAlltokens();
   void updateToken(Fcm fcm);
   Fcm getTokenByIMEI(String imei);
}
