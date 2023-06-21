package com.xcyh.xcyhtoufang.utils;

import com.facebook.ads.sdk.APIContext;
import com.facebook.ads.sdk.AdAccount;
import com.facebook.ads.sdk.Campaign;
import com.facebook.ads.sdk.APIException;

public class QuickStartExample {

    public static final String ACCESS_TOKEN = "1209036453359971|G5gZ6uAEDJWvcAyCIGuGqLMpjkM";
    public static final Long ACCOUNT_ID =578942610814600L;
    public static final String APP_SECRET = "31303a5a92a1c74cb867bfffb9f8a737d64bd12607d618c0d5dd9224ec0dfe24";

    public static final APIContext context = new APIContext(ACCESS_TOKEN, APP_SECRET);
    public  void test() {
        try {
            AdAccount account = new AdAccount(ACCOUNT_ID, context);
            Campaign campaign = account.createCampaign()
                    .setName("Java SDK Test Campaign")
                    .setObjective(Campaign.EnumObjective.VALUE_LINK_CLICKS)
                    .setSpendCap(10000L)
                    .setStatus(Campaign.EnumStatus.VALUE_PAUSED)
                    .execute();
            System.out.println(campaign.fetch());
        } catch (APIException e) {
            e.printStackTrace();
        }
    }
}
