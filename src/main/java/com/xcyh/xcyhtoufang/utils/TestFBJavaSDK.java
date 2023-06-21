package com.xcyh.xcyhtoufang.utils;

import com.facebook.ads.sdk.APIContext;
import com.facebook.ads.sdk.APINodeList;
import com.facebook.ads.sdk.AdAccount;
import com.facebook.ads.sdk.Campaign;


public class TestFBJavaSDK {

    public static final APIContext context = new APIContext(
            "8b4e55a7acd72b7cae057e53e6664eb4",
            "31303a5a92a1c74cb867bfffb9f8a737d64bd12607d618c0d5dd9224ec0dfe24",
            "1209036453359971"
    );
    public void test()
    {
        AdAccount account = new AdAccount("act_{3355738908088004}", context);
        try {
            APINodeList<Campaign> campaigns = account.getCampaigns().requestAllFields().execute();
            for(Campaign campaign : campaigns) {
                System.out.println(campaign.getFieldName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
