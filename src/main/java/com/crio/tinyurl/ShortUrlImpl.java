package com.crio.tinyurl;

import java.util.HashMap;
import java.util.Map.Entry;
import java.security.SecureRandom;

import com.crio.shorturl.ShortUrl;

public class ShortUrlImpl implements ShortUrl{

    String finalurl = "http://short.url/";

    HashMap<String, String> surl = new HashMap<String, String>();

    HashMap<String, Integer> hit = new HashMap<String, Integer>();

    String choose = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    public String registerNewUrl(String longUrl){
        finalurl = "http://short.url/";
        SecureRandom rnd = new SecureRandom();

        if(surl.containsKey(longUrl)){
            return surl.get(longUrl);
        }else{
            for(int i=0;i<9;i++){
                finalurl = finalurl + choose.charAt(rnd.nextInt(choose.length()));
            }
        }

        if(surl.containsValue(finalurl)){
            finalurl = registerNewUrl(longUrl);
        }else{
            hit.put(longUrl, 0);
        }

        surl.put(longUrl,finalurl);

        return finalurl;
    }


    public String registerNewUrl(String longUrl, String shortUrl){
        String c = null;
        for (Entry<String, String> entry : surl.entrySet()) {
            if (entry.getValue().equals(shortUrl)) {
                return c;
            }
        }

        surl.put(longUrl,shortUrl);
        hit.put(longUrl, 0);

        return shortUrl;
    }


    public String getUrl(String shortUrl){
        String result = "";
        for (Entry<String, String> entry : surl.entrySet()) {
            if (entry.getValue().equals(shortUrl)) {
                result = entry.getKey();
            }
        }

        if(result != ""){
            hit.replace(result,hit.get(result),hit.get(result)+1);
        }else{
            return null;
        }

        return result;
    }


    public Integer getHitCount(String longUrl){
        if(!hit.containsKey(longUrl)){
            return 0;
        }
        return hit.get(longUrl);
    }


    public String delete(String longUrl){
        surl.remove(longUrl);
        return "";
    }



}