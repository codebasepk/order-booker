package com.byteshaft.order_booker;

import android.app.Activity;
import android.telephony.SmsManager;
import android.util.Log;

public class BinarySmsSender extends Activity {

    public void sendDataSms(String phoneNumber, String port, String smsCommand) {
        SmsManager smsManager = getSmsManager();
        Log.i("BinarySMS", getSmsFeedbackFormattedMessage(phoneNumber, port, smsCommand));
        smsManager.sendDataMessage(
                phoneNumber, null, Short.valueOf(port), smsCommand.getBytes(), null, null
        );
    }

    private SmsManager getSmsManager() {
        return SmsManager.getDefault();
    }


    private String getSmsFeedbackFormattedMessage(String number, String port, String command) {
        return String.format(
                "Sending data SMS \"%s\" to %s on port number: %s",
                command, number, String.valueOf(port)
        );
    }
}
