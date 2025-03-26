/**
 * Legal Disclaimer
 * Source Code or Sample Code not belongs to Alipay technological assets, partners agree to take full responsibility for any activities or actions under its use.
 * Partners are authorised to use for Approved Purposes.
 * "Approved Purpose" means, subject to any restrictions set out in the Participation Documents, (i) in relation to a Partner, as is necessary in order to facilitate that Partner’s participation in Alipay+ Core; and (ii) in relation to Alipay+, as is necessary in order to facilitate the provision of the Services (including the exercise of rights and performance of obligations under Participation Documents, managing AML/Fraud Risk, resolving any Disputes or facilitating the provision of Partner Products by other Partners).
 * You should check our NDA for detail Legal T&C.
 */
package com.futurebank.order.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Map;

/**
 * an util that deal with signature
 *
 * @author wqy01239909
 * @version $Id: SignatureTool.java, v 0.1 2022‐07-05 10:34 wqy01239909 Exp $
 */
public class SignatureTool {

    /** rsa static string **/
    private static final String RSA = "RSA";

    /** SHA256withRSA static string **/
    private static final String SHA256WITHRSA = "SHA256withRSA";

    /** UTF-8 static string **/
    private static final String DEFAULT_CHARSET = "UTF-8";

    /** class that deals with base64 encrypt **/
    private static Base64Encryptor base64Encryptor = new DefaultBase64Encryptor();

    /**
     * create signature
     *
     * @param httpMethod http method
     * @param path path of http request url
     * @param clientId client id
     * @param reqTimeStr time of http request
     * @param reqBody body of http request
     * @param acqpPrivateKey private key that created by acqp
     * @return signature
     * @throws Exception throws if common exception happens
     */
    public static String sign(String httpMethod, String path, String clientId, String reqTimeStr,
                              String reqBody, String acqpPrivateKey) throws Exception {
        String reqContent = genSignContent(httpMethod, path, clientId, reqTimeStr, reqBody);
        return encode(signWithSHA256RSA(reqContent, acqpPrivateKey), DEFAULT_CHARSET);
    }

    /**
     * verify signature
     *
     * @param httpMethod http method
     * @param path path of http request url
     * @param clientId client id
     * @param rspTimeStr time of http response
     * @param rspBody body of http response
     * @param signature signature in response
     * @param alipayPlusPublicKey public key that created and given by a+
     * @return result of verify
     * @throws Exception throws if common exception happens
     */
    public static boolean verify(String httpMethod, String path, String clientId, String rspTimeStr,
                                 String rspBody, String signature,
                                 String alipayPlusPublicKey) throws Exception {
        String rspContent = genSignContent(httpMethod, path, clientId, rspTimeStr, rspBody);
        System.out.println("rspContent:   "+rspContent);
        System.out.println("signature:   "+decode(signature, DEFAULT_CHARSET));
        return verifySignatureWithSHA256RSA(rspContent, decode(signature, DEFAULT_CHARSET),
                alipayPlusPublicKey);
    }

    /**
     * generate the appended original string for sign
     *
     * @param httpMethod http method
     * @param path path of http request url
     * @param clientId client id
     * @param timeString time of http request or response
     * @param content body of http request or response
     * @return original string for sign
     */
    public static String genSignContent(String httpMethod, String path, String clientId,
                                        String timeString, String content) {
        String payload = httpMethod + " " + path + "\n" + clientId + "." + timeString + "."
                + content;

        return payload;
    }

    /**
     * create signature
     *
     * @param reqContent original string for signing
     * @param acqpPrivateKey the private key that created by acqp
     * @return signature
     * @throws Exception throws if common exception happens
     */
    public static String sign(String reqContent, String acqpPrivateKey) throws Exception {
        return encode(signWithSHA256RSA(reqContent, acqpPrivateKey), DEFAULT_CHARSET);
    }

    /**
     * verify signature which encoded with base64
     *
     * @param rspContent original string for signing
     * @param signature signature
     * @param alipayPlusPublicKey the public key that created by a+
     * @return result of verify
     * @throws Exception throws if common exception happens
     */
    public static boolean verify(String rspContent, String signature,
                                 String alipayPlusPublicKey) throws Exception {
        return verifySignatureWithSHA256RSA(rspContent, decode(signature, DEFAULT_CHARSET),
                alipayPlusPublicKey);
    }

    /**
     * verify signature
     *
     * @param rspContent original string for signing
     * @param signature signature
     * @param strPk the public key that created by a+
     * @return result of verify
     * @throws Exception throws if common exception happens
     */
    private static boolean verifySignatureWithSHA256RSA(String rspContent, String signature,
                                                        String strPk) throws Exception {
        PublicKey publicKey = getPublicKeyFromBase64String(strPk);

        Signature publicSignature = Signature.getInstance(SHA256WITHRSA);
        publicSignature.initVerify(publicKey);
        publicSignature.update(rspContent.getBytes(DEFAULT_CHARSET));
        System.out.println("公钥加密后的内容:"+publicSignature);
        byte[] signatureBytes = base64Encryptor.decode(signature);
        System.out.println("alipay+加密后的内容:"+ Arrays.toString(signatureBytes));
        return publicSignature.verify(signatureBytes);

    }

    /**
     * create signature with SHA256RSA
     *
     * @param reqContent original string for signing
     * @param strPrivateKey the private key that created by merchant
     * @return signature
     * @throws Exception throws if common exception happens
     */
    private static String signWithSHA256RSA(String reqContent,
                                            String strPrivateKey) throws Exception {
        Signature privateSignature = Signature.getInstance(SHA256WITHRSA);
        privateSignature.initSign(getPrivateKeyFromBase64String(strPrivateKey));
        privateSignature.update(reqContent.getBytes(DEFAULT_CHARSET));
        byte[] s = privateSignature.sign();

        return base64Encryptor.encodeToString(s);
    }

    /**
     * get public key object from publicKeyString
     * @param publicKeyString publicKeyString
     * @return public key object
     * @throws Exception throws if common exception happens
     */
    private static PublicKey getPublicKeyFromBase64String(String publicKeyString) throws Exception {
        byte[] b1 = base64Encryptor.decode(publicKeyString);
        X509EncodedKeySpec X509publicKey = new X509EncodedKeySpec(b1);
        KeyFactory kf = KeyFactory.getInstance(RSA);
        return kf.generatePublic(X509publicKey);
    }

    /**
     * get private key object from privateKeyString
     * @param privateKeyString private Key String
     * @return private key object
     * @throws Exception throws if common exception happens
     */
    private static PrivateKey getPrivateKeyFromBase64String(String privateKeyString) throws Exception {
        byte[] b1 = base64Encryptor.decode(privateKeyString);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(b1);
        KeyFactory kf = KeyFactory.getInstance(RSA);
        return kf.generatePrivate(spec);
    }

    /**
     * encode string by url encoder
     * @param originalStr  original string
     * @param characterEncoding charset
     * @return encoded string
     * @throws UnsupportedEncodingException throws if UnsupportedEncodingException happens
     */
    private static String encode(String originalStr,
                                 String characterEncoding) throws UnsupportedEncodingException {
        return URLEncoder.encode(originalStr, characterEncoding);
    }

    /**
     * decode string by URLDecoder
     * @param originalStr  original string
     * @param characterEncoding charset
     * @return decoded string
     * @throws UnsupportedEncodingException throws if UnsupportedEncodingException happens
     */
    private static String decode(String originalStr,
                                 String characterEncoding) throws UnsupportedEncodingException {
        return URLDecoder.decode(originalStr, characterEncoding);
    }

    /**
     * 好易联签名
     * 第一步： 設所有發送或者接收到的數據為集合M，將集合M內非空參數值的參數按照參數名ASCII碼從小到大排序（字典序），使用URL鍵值對的格式（即key1=value1&key2=value2…）拼接成字符串stringA。 特別注意以下重要規則：
     * 參數名ASCII碼從小到大排序（字典序）；
     * 如果參數的值為空不參與簽名；
     * 參數名區分大小寫；
     * 驗證調用返回或支付中心主動通知簽名時，傳送的sign參數不參與簽名，將生成的簽名與該sign值作校驗。
     * 支付中心接口可能增加字段，驗證簽名時必須支持增加的擴展字段
     * 第二步： 在stringA最後拼接上key[即 StringA +"&key=" + 私鑰 ] 得到stringSignTemp字符串，並對stringSignTemp進行MD5運算，再將得到的字符串所有字符轉換為大寫，得到sign值signValue。
     * @param params
     * @param privateKey
     * @return
     * @throws Exception
     */
    public static String easyLinkSign(Map<String, Object> params, String privateKey) throws Exception {
        StringBuilder stringA = new StringBuilder();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            if (entry.getValue() != null) {
                String valueStr = entry.getValue().toString();
                if (!valueStr.isEmpty()) {
                    stringA.append(entry.getKey()).append("=").append(valueStr).append("&");
                }
            }
        }
        stringA.append("key=").append(privateKey);

        // MD5 加密
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] digest = md.digest(stringA.toString().getBytes(StandardCharsets.UTF_8));
        StringBuilder hexString = new StringBuilder();
        for (byte b : digest) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString().toUpperCase();
    }
}
