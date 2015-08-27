package netgloo.commons.util;

// Download the twilio-java library from http://twilio.com/docs/libraries
import java.util.HashMap;
import java.util.Map;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.SmsFactory;
import com.twilio.sdk.resource.instance.Account;
import com.twilio.sdk.resource.instance.Sms;
 
public class SmsSender {
 
    /* Find your sid and token at twilio.com/user/account */
    public static final String TEST_ACCOUNT_SID = "ACc3d5e42d51b44368f6df74c4365dbfa4";
    public static final String TEST_AUTH_TOKEN = "f1f68d7dc50b3cbc2f75c784ae5ca587";
 
    public static void main(String[] args) throws TwilioRestException {
 
        TwilioRestClient client = new TwilioRestClient(TEST_ACCOUNT_SID, TEST_AUTH_TOKEN);
 
        Account account = client.getAccount();
 
        SmsFactory messageFactory = account.getSmsFactory();
        Map<String,String> params = new HashMap<String, String>();
        params.put("To", "+14083073765");
        params.put("From", "+14082903691");
        params.put("Body", "Hello, Your Meet2Eat app phone number verification code is 1234");
        Sms sms = messageFactory.create(params);
        System.out.println(sms.getSid()); 
    }
}