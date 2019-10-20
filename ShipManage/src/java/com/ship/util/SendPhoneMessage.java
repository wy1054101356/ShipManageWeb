/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ship.util;

import com.ship.util.*;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author 思之声
 */
public class SendPhoneMessage {

    //用户名
    public static String Uid = "wy1054101356";

    //接口安全秘钥
    public static String Key = "d41d8cd98f00b204e980";

    public static void SendPhoneMessage(String number, String tel) {

        SendPhoneClient client = SendPhoneClient.getInstance();
        //int result = client.sendMsgUtf8(Uid, Key, "本次注册验证码:" + number, tel);  //UTF发送 
    }

}
