package com.xcyh.xcyhtoufang.utils;

import com.facebook.ads.sdk.APIContext;
import com.facebook.ads.sdk.APINodeList;
import com.facebook.ads.sdk.AdAccount;
import com.facebook.ads.sdk.Campaign;


public class TestFBJavaSDK {

    public static final APIContext context = new APIContext(
            "EAARLnLxSsWMBAGdPSqDmpgnNtOLvjBOWr1cb5ZBYZA5IwP1A0drZBvmw8NkifqkUaxi66hMVqERXAic2w14LoRqId3r3wd5sNTR94HfaZCqR4tInmAPIs4Dx18NSHExoHBd9mNz9NwQILnsMdps0WPX0WeG4IDBZCDCX2ME2OS9mBeIqI8Xd7",
            "31303a5a92a1c74cb867bfffb9f8a737d64bd12607d618c0d5dd9224ec0dfe24"
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
