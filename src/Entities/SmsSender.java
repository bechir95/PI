/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;


public class SmsSender {

    // Find your Account Sid and Auth Token at twilio.com/console
    public static final String ACCOUNT_SID = "AC135263906093cb0ce5c41c4513489022";
    public static final String AUTH_TOKEN  = "b40f2b3b24e7c8833e41d945ed241d70";
    public static final String API_PHONE   = "+14243528782";

    public static void SendSMS(String to, String from, String body){
        
        Message message = Message
                .creator(new PhoneNumber(to),  // to
                         new PhoneNumber(from),  // from
                         body)
                .create();
    }
}