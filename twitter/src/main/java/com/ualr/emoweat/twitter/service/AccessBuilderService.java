package com.ualr.emoweat.twitter.service;

import com.ualr.emoweat.core.utils.Property;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

/**
* @author - Tolgahan CAKALOGLU "Jackalhan"
*/

public class AccessBuilderService {

    private static ConfigurationBuilder configurationBuilder = null;
    private static Twitter twitter = null;

    private static void init() {
        configurationBuilder = new ConfigurationBuilder();
        configurationBuilder.setDebugEnabled(true)
                .setOAuthConsumerKey(Property.getValue("twitterConsumerKey"))
                .setOAuthConsumerSecret(Property.getValue("twitterConsumerSecret"))
                .setOAuthAccessToken(Property.getValue("twitterAccessToken"))
                .setOAuthAccessTokenSecret(Property.getValue("twitterAccessTokenSecret"));
    }

    public static Twitter build() {
        if (twitter == null) {
            init();
            TwitterFactory tf = new TwitterFactory(configurationBuilder.build());
            twitter = tf.getInstance();
        }
        return twitter;
    }

    public static Configuration getConfiguration()
    {
        if(configurationBuilder == null)
        {
            init();
        }
        return configurationBuilder.build();

    }


}
