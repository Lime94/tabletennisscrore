package monopolybankir.com.tennisscore.utils;

import android.content.SharedPreferences;

public class SharedPrefsUtil {

     private static SharedPrefsUtil singletonSharedPref;

     private SharedPreferences mSharedPreferences;


     private final String flagIsSetPitcherDialogShowAgain = "flagIsSetPitcherDialogShowAgain";


     public static SharedPrefsUtil getInstance(SharedPreferences sharedPreferences){
          if(singletonSharedPref == null){
               singletonSharedPref = new SharedPrefsUtil(sharedPreferences);
          }
          return singletonSharedPref;
     }


     private SharedPrefsUtil(SharedPreferences sharedPreferences){
          this.mSharedPreferences = sharedPreferences;
     }

     public  void setPitcherDialogShowAgain(boolean value){
          mSharedPreferences.edit().putBoolean(flagIsSetPitcherDialogShowAgain,value).apply();
     }

     public  boolean getPitcherDialogShowAgain(){
          return mSharedPreferences.getBoolean(flagIsSetPitcherDialogShowAgain,true);
     }
}
