package com.manage.tavern.model.wechat;

import lombok.Data;

/**
 * @description:
 * @author: dll
 * @date: Created in 2023/6/5 15:13
 * @version:
 * @modified By:
 */
@Data
public class WeChatForm {

    /**
     * 消息签名
     */
    private String msg_signature;
    private String timestamp;
    private String nonce;
    private String echostr;
    private String token;
    private String aes_key;

    private String receiveid;

    /**
     * 用于消息体的加密，长度固定为43个字符，从a-z, A-Z, 0-9共62个字符中选取，是AESKey的Base64编码。解码后即为32字节长的AESKey
     */
    private String EncodingAESKey;

    @Override
    public String toString() {
        return "WeChatForm{" +
                "msg_signature='" + msg_signature + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", nonce='" + nonce + '\'' +
                ", echostr='" + echostr + '\'' +
                ", token='" + token + '\'' +
                ", aes_key='" + aes_key + '\'' +
                ", receiveid='" + receiveid + '\'' +
                ", EncodingAESKey='" + EncodingAESKey + '\'' +
                '}';
    }
}
