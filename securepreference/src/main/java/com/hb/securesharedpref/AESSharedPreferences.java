package com.hb.securesharedpref;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hb.securesharedpref.utility.PreferenceProvider;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AESSharedPreferences implements PreferenceProvider {

    private SharedPreferences sharedPreferences;

    AESSharedPreferences(Context context,String sharedPreferenceName) {
        if (context != null){
            try {
                String masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC);
                sharedPreferences = EncryptedSharedPreferences.create(
                        sharedPreferenceName,
                        masterKeyAlias,
                        context,
                        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
                );
            } catch (GeneralSecurityException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void putString(String key, String value) {
        sharedPreferences.edit().putString(key, value).apply();
    }

    @Override
    public String getString(String key, String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }


    @Override
    public void putBoolean(String key, boolean value) {
        sharedPreferences.edit().putBoolean(key, value).apply();
    }

    @Override
    public boolean getBoolean(String key, boolean defaultValue) {
        return sharedPreferences.getBoolean(key, defaultValue);
    }


    @Override
    public void putInt(String key, int value) {
        sharedPreferences.edit().putInt(key, value).apply();
    }

    @Override
    public int getInt(String key, int defaultValue) {
        return sharedPreferences.getInt(key, defaultValue);
    }


    @Override
    public void putFloat(String key, float value) {
        sharedPreferences.edit().putFloat(key, value).apply();
    }

    @Override
    public float getFloat(String key, float defaultValue) {
        return sharedPreferences.getFloat(key, defaultValue);
    }


    @Override
    public void putDouble(String key, double value) {
        sharedPreferences.edit().putLong(key, Double.doubleToRawLongBits(value)).apply();
    }

    @Override
    public double getDouble(String key, double defaultValue) {
        return Double.longBitsToDouble(sharedPreferences.getLong(key, Double.doubleToLongBits(defaultValue)));
    }

    @Override
    public void remove(String key) {
        sharedPreferences.edit().remove(key).apply();
    }

    @Override
    public void clear() {
        sharedPreferences.edit().clear().apply();
    }

    @Override
    public void putStringSet(String key, Set<String> value) {
        sharedPreferences.edit().putStringSet(key,value).apply();
    }

    @Override
    public Set<String> getStringSet(String key, Set<String> defaultValue) {
        return sharedPreferences.getStringSet(key,defaultValue);
    }

    @Override
    public <T> void saveObject(String key, T object) {
        String objectString = new Gson().toJson(object);
        sharedPreferences.edit().putString(key, objectString).apply();
    }

    @Override
    public <T> T getObject(String key, Class<T> object) {
        if (containsKey(key)) {
            String objectString = sharedPreferences.getString(key, null);
            if (objectString != null) {
                return new Gson().fromJson(objectString, object);
            }
        }
        return null;
    }


    @Override
    public <T> void saveObjectsList(String key, List<T> objectList) {
        String objectString = new Gson().toJson(objectList);
        sharedPreferences.edit().putString(key, objectString).apply();
    }

    @Override
    public <T> List<T> getObjectsList(String key, Class<T> classType) {

        if (containsKey(key)) {
            String objectString = sharedPreferences.getString(key, null);
            if (objectString != null) {

                ArrayList<T> t = new Gson().fromJson(objectString, new TypeToken<List<T>>() {
                }.getType());

                List<T> finalList = new ArrayList<>();

                for (int i = 0; i < t.size(); i++) {
                    String s = String.valueOf(t.get(i));
                    finalList.add(new Gson().fromJson(s, classType));
                }

                return finalList;
            }
        }

        return null;
    }


    @Override
    public boolean containsKey(String key) {
        return sharedPreferences.contains(key);
    }

}
