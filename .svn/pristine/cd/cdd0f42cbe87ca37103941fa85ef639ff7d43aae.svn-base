package com.shangyong.thjdq.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shangyong.thjdq.dto.JdqInvokeChannelDto;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;

import java.nio.charset.Charset;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;

/**
 * CodecDemo
 *
 * @author hejiangshan on 2018年12月19日
 * @version 1.0
 */
public class CodecDemo {

    private static String jdqPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDOHgBdoItbrb25p9uguw7C04By\n"
            + "kSo+Art7+a3jVZ3AXvesUCIbtkMDlKS5uEt9gYt6JDT+ggejaDTvrpdcLwzBFJXi\n"
            + "yHNL4XBCH88SL++AAvLPyXTq30X3fyGkdEs/kDox9jWcmGSWlyDBKu1bwpYSsxi2\n"
            + "GJ5s+3/HqsJmVsI7nQIDAQAB";

    /**
     * java使用pkcs8形式的key
     */
    private static String jdqPrivateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAM4eAF2gi1utvbmn\n"
            + "26C7DsLTgHKRKj4Cu3v5reNVncBe96xQIhu2QwOUpLm4S32Bi3okNP6CB6NoNO+u\n"
            + "l1wvDMEUleLIc0vhcEIfzxIv74AC8s/JdOrfRfd/IaR0Sz+QOjH2NZyYZJaXIMEq\n"
            + "7VvClhKzGLYYnmz7f8eqwmZWwjudAgMBAAECgYBLdyqVHSHI+Ezdu7qrF7Ho3T1L\n"
            + "NSEtQRzZ4GmtMXynoV23JkPGt63DktnY8cQZ+KNTngwVHxCi4JV5KAhdBRD6u4s6\n"
            + "R2QzHcgrMKmMSLzRzT43EBjOpXJrlG6zjBhTWUklOQHGoFSRTmp2aXfw7Ot8ykpL\n"
            + "3L3o/x95jyrp1XWkVQJBAP0CsboC9uKvodAlMVSbwSl+SY/OxDrsA1vZSQYGudnY\n"
            + "TG1CGqs+UA5InWT+vKdOoc8IQHCL/JA+Bly4KMfO84MCQQDQjXbd9EpDLf5I2VWK\n"
            + "El8YcbGcu33QlXmg5+OnPasMCaNdaVax5HaIt/8HvqZlF7uYyJ/Uitnb9/jyaRj4\n"
            + "hkpfAkA6gcv9esACraCalG8y5I2nfbefFK5V/cvNCpMTXKzPjnAl99pY7SDwHKSg\n"
            + "/TM88TcDvCHCpOxzopQwDroHcnjnAkAZ71224GhTzmZu/MgMcZht28WwrghJZi1H\n"
            + "+05uP9cDmjc8qJrlaHntKqSGfAkJTVhA+cIWlmXdWwsUakl2royVAkEAo4frBh7t\n"
            + "Ta+yv2jKreXJMyU7Ob9q75otbd4zaz6eDh7aufWSaoKtY0ZD+4A13AmziWvQc0p8\n"
            + "h7SglUTKKVaScA==";

    private static String partnerPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCzDNZwZMASr+44KhKwtLwGmLiN\n"
            + "xnU9EpCF4mc4k4Tb6txCNeMElbK1uxVMz5cU/IgEBSSjUx08nXZ1FGxktRoJPJBa\n"
            + "sNoTM+AqiDIHP4eWCq8Bts4vLWG+PsbIq9OzMjUMltJz/X0IL9bqwVbi76iGfrcX\n"
            + "6vBTvEcQajhc+VpjDwIDAQAB";

    /**
     * java使用pkcs8形式的key
     */
    private static String partnerPrivateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALMM1nBkwBKv7jgq\n"
            + "ErC0vAaYuI3GdT0SkIXiZziThNvq3EI14wSVsrW7FUzPlxT8iAQFJKNTHTyddnUU\n"
            + "bGS1Ggk8kFqw2hMz4CqIMgc/h5YKrwG2zi8tYb4+xsir07MyNQyW0nP9fQgv1urB\n"
            + "VuLvqIZ+txfq8FO8RxBqOFz5WmMPAgMBAAECgYBJdkXxXVT1FdvOjWyXtb/DkRt/\n"
            + "h+KISTqkw+yH6PbE6Eqwm71mHylwqK6nkjCxup7vz1Bl9m7zdMOqa9PBShDA83qJ\n"
            + "Z39FpNwoRuhbYX9jlSu8uPVh7FTfqghA7G9woOUYfOYibWg55RyziaLKlAFTYmps\n"
            + "dGgM7YqQPkpkhIh4qQJBAOHNqb2exq3R5fKdzQDoUir0NwoGYA7vZBHSCvOLtGt5\n"
            + "ZzN4gXoTR/VvDAJUUvqED/U+L5JaxzLWkc/TJWR4VRUCQQDK/pYopTlZDbvDSA9O\n"
            + "2PMnHLs50WgvR/T7WEeU145ahjQBzoFTt9OeUaltbLcZSwvhQ4bWYoZXxALw7ojt\n"
            + "LWiTAkEAnNRJBmhWubdFLIMpNW4mU6sW5yGSag0exbnK3Wi0wMirQvZ5hx4JJpAg\n"
            + "GSYUE+bRZpXwWIgwPkEFOQD/wY5KGQJALNAvTiHLm0FQ5jeOSZXTeBr7qjP0kBuO\n"
            + "lb5TbyR3JLzj1lJEcrruWNEmWiXZ9hd/M+e4aLOvfMik7ZN7XwDbSQJBAKJtL/UT\n"
            + "uEy+/wmrL+LRu7QrO6wXcnK/rEru3nmHoECpnFKtad4tWJF7ky2T23JO9CiDmrhl\n"
            + "3h70N8l7TeWWp9I=";

    public static class MetaInfo {
        private static String testChannelCode = "new_test";
        private static String originEncryptKey = "1234567890asdfgh";
        private static String compressType = "gzip";
        private static JSONObject originData = new JSONObject();

        static {
            originData.put("user_name", "小明");
            originData.put("user_age", 18);
            originData.put("extra_info", "hello ad）*&……%%￥#");
            originData.put("long_data",
                    "{\"face_result_active\":{\"id_exceptions\":{\"id_photo_monochrome\":0,\"id_attacked\":0},"
                            + "\"result_faceid\":{\"thresholds\":{\"1e-6\":78.038,\"1e-5\":74.399,\"1e-4\":69.315,"
                            + "\"1e-3\":62.169},\"confidence\":84.793},\"request_id\":\"1542854983,"
                            + "33ec3647-25ae-429d-a29e-df11277fcb1d\",\"time_used\":562},\"jdq_order_id\":\"XXXXX\","
                            + "\"loan_info\":{\"period\":\"6\",\"amount\":\"10000\"},"
                            + "\"device_info\":{\"device_model\":\"iPhone\",\"device_id\":\"\",\"ip\":\"127.0.0.1\","
                            + "\"jailbreak_flag\":true,\"device_type\":\"IOS\",\"device_os\":\"ios 11.4.1\","
                            + "\"openudid\":\"\"},\"user_login_upload_log\":{\"address\":\"中国上海市上海市杨浦区政通路189号-3楼\","
                            + "\"latitude\":\"31.301847\",\"longitude\":\"121.512752\"},"
                            + "\"user_info\":{\"id_negative_valid_state\":\"{\\\"Temporary ID Photo\\\":0.0,"
                            + "\\\"Screen\\\":0.0,\\\"Edited\\\":0.0,\\\"Photocopy\\\":0.0,\\\"ID Photo\\\":1.0}\","
                            + "\"nation\":\"汉\",\"industry_code\":\"6\",\"id_card\":\"620522198908140319\","
                            + "\"industry\":\"计算机/互联网\",\"company_address\":\"安徽省,合肥市,庐阳区|合生回家000006\","
                            + "\"id_negative\":\"http://bktest-10010.oss-cn-hangzhou.aliyuncs"
                            + ".com/53229177/d3130bf5b6d24e4d9368ec7503a11801.png\",\"telecom_auth\":\"1\","
                            + "\"id_positive_valid_state\":\"{\\\"Temporary ID Photo\\\":0.0,\\\"Screen\\\":0.0,"
                            + "\\\"Edited\\\":0.0,\\\"Photocopy\\\":0.0,\\\"ID Photo\\\":1.0}\","
                            + "\"work_profession\":\"批发/零售业\",\"company_city\":\"合肥市\","
                            + "\"id_card_address\":\"甘肃省秦安县兴国镇宋洼村55号\",\"educate\":\"大专以下\",\"loan_usage_code\":\"6\","
                            + "\"id_expiry_date\":\"2036-09-30\",\"id_positive\":\"http://bktest-10010.oss-cn-hangzhou"
                            + ".aliyuncs.com/53229177/2473500d2e7e4705b7e808d88395645b.png\","
                            + "\"company_tel\":\"0211234567\","
                            + "\"id_signing_authority\":\"秦安县公安局\",\"face\":\"http://bktest-10010.oss-cn-hangzhou"
                            + ".aliyuncs"
                            + ".com/53229177/eb421370882b487193ab3d1f904f4ef0.png\",\"phone\":\"13122053988\","
                            + "\"company_name\":\"和姐姐家\",\"loan_usage\":\"旅游贷款\",\"name\":\"宋文华\","
                            + "\"id_start_date\":\"2016-09-30\",\"educate_code\":\"1\"},"
                            + "\"user_contact\":{\"name_spare\":\"测试服\",\"relation_spare_code\":\"91\",\"name\":\"花花\","
                            + "\"mobile\":\"15853664852\",\"relation_spare\":\"兄弟姓名\",\"relation_code\":\"81\","
                            + "\"relation\":\"父母姓名\",\"mobile_spare\":\"18964573188\"},"
                            + "\"address_book\":[{\"name\":\"谭晶\","
                            + "\"mobile\":\".86 13647336742\"},{\"name\":\"徐超\",\"mobile\":\"15852753251\"},"
                            + "{\"name\":\"向文辉\",\"mobile\":\"18271631169\"},{\"name\":\"尹光蓉\","
                            + "\"mobile\":\"15001956228\"},"
                            + "{\"name\":\"山东饺子馆\",\"mobile\":\"176 2148 1118\"},{\"name\":\"王远亮，雷雷\","
                            + "\"mobile\":\"15212570891\"},{\"name\":\"齐_玉兰二期c_1700_南主卧\",\"mobile\":\"13262982872\"},"
                            + "{\"name\":\"李守洪\",\"mobile\":\"18724019948\"}],"
                            + "\"app_data\":{\"com_demo_syh\":{\"name\":\"闪赢花\",\"version\":\"1_0\"}},"
                            + "\"operator\":\"\"}");
        }
    }

//    @Accessors(chain = true)
    public static class InvokeConfig {
        private boolean needEncrypt;
        private boolean needCompress;

        public boolean isNeedEncrypt() {
            return needEncrypt;
        }

        public void setNeedEncrypt(boolean needEncrypt) {
            this.needEncrypt = needEncrypt;
        }

        public boolean isNeedCompress() {
            return needCompress;
        }

        public void setNeedCompress(boolean needCompress) {
            this.needCompress = needCompress;
        }
    }

    /**
     * 借点钱方使用借点钱私钥加密加签
     */
    public static JdqInvokeChannelDto jdqBuildInvokeChannelRequest(InvokeConfig config) throws Exception {
        JdqInvokeChannelDto request = new JdqInvokeChannelDto();
        request.setChannel_code(MetaInfo.testChannelCode);
        String originData = JSON.toJSONString(MetaInfo.originData);
        final RSAPrivateKey privateKey = RSATool.parseRSAPrivateKey(jdqPrivateKey);
        if (StringUtils.isNotEmpty(originData)) {
            byte[] dataBytes = originData.getBytes(Charset.forName("UTF-8"));
            if (config.isNeedCompress()) {
                request.setCompress(1);
                request.setCompress_type(MetaInfo.compressType);
                dataBytes = GzipTool.compress(dataBytes);
            }
            if (config.isNeedEncrypt()) {
                request.setEncrypt(1);
                byte[] rawEncryptKey = MetaInfo.originEncryptKey.getBytes(Charset.forName("UTF-8"));
                request.setEncrypt_key(Base64.encodeBase64String(
                        RSATool.rsaEncryptByPrivateKey(privateKey, rawEncryptKey)));
                dataBytes = AESTool.aesEncrypt(dataBytes, MetaInfo.originEncryptKey);
            }
            originData = Base64.encodeBase64String(dataBytes);
        }
        request.setData(originData);
        String originSign = PreSignTool.buildToSignString(JdqInvokeChannelDto.class, request);
        request.setSign(Base64.encodeBase64String(RSATool
                .rsaSignByPrivateKey(privateKey, originSign.getBytes(Charset.forName("UTF-8")))));
        return request;
    }

    /**
     * 机构方使用借点钱公钥验签解密
     * <p>验签</p>
     * <p>需要解密则解密：先用RSA解密得到解密的密钥，然后用AES解密</p>
     * <p>需要解压则解压</p>
     */
    public static void channelParseJdqInvokeRequest(JdqInvokeChannelDto request) throws Exception {
        // 先验签
        String originSign = PreSignTool.buildToSignString(JdqInvokeChannelDto.class, request);
        jdqPrivateKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDOHgBdoItbrb25p9uguw7C04BykSo+Art7+a3jVZ3AXvesUCIbtkMDlKS5uEt9gYt6JDT+ggejaDTvrpdcLwzBFJXiyHNL4XBCH88SL++AAvLPyXTq30X3fyGkdEs/kDox9jWcmGSWlyDBKu1bwpYSsxi2GJ5s+3/HqsJmVsI7nQIDAQAB";

        final RSAPublicKey publicKey = RSATool.parseRSAPublicKey(jdqPublicKey);
        final boolean verify = RSATool.rsaVerifySignByPublicKey(publicKey,
                originSign.getBytes(Charset.forName("UTF-8")), Base64.decodeBase64(request.getSign()));
        if (!verify) {
            throw new RuntimeException("验签不通过");
        }
        if (request.getData() == null || request.getData().length() == 0) {
            System.out.println("无业务数据");
            return;
        }
        // 原始字符串进行base64解码
        byte[] toDecodeBytes = Base64.decodeBase64(request.getData());
        // 需要解密则解密
        if (request.getEncrypt() == 1) {
            // 需要解密
            final byte[] bytes =
                    RSATool.rsaDecryptByPublicKey(publicKey, Base64.decodeBase64(request.getEncrypt_key()));
            String originEncryptKey = new String(bytes, Charset.forName("UTF-8"));
            request.setEncrypt_key(originEncryptKey);
            toDecodeBytes = AESTool.aesDecrypt(toDecodeBytes, originEncryptKey);
        }
        // 需要解压则解压缩
        if (request.getCompress() == 1) {
            toDecodeBytes = GzipTool.unCompress(toDecodeBytes);
        }
        // 转换为原始数据
        request.setData(new String(toDecodeBytes, Charset.forName("UTF-8")));
    }

    /**
     * 测试借点钱请求机构方
     */
    private static void testJdqInvokeChannel(InvokeConfig config) throws Exception {
        final String postBody = JSON.toJSONString(jdqBuildInvokeChannelRequest(config));
        System.out.println("借点钱请求串: ");
        System.out.println(postBody);
        JdqInvokeChannelDto request = JSON.parseObject(postBody, JdqInvokeChannelDto.class);
        channelParseJdqInvokeRequest(request);
        System.out.println("机构验签后解析得到的串: ");
        System.out.println(JSON.toJSONString(request));
        final String data = request.getData();
        System.out.println("机构得到的业务数据: ");
        System.out.println(data);
    }


    public static class InvokeJdqRequest {
        private String channel_code;
        private String call;
        private String version;
        private String sign;
        private String data;
        private long timestamp;
        // 可选：是否加密
        private Integer encrypt;
        // 可选：加密密钥
        private String encrypt_key;

        public String getChannel_code() {
            return channel_code;
        }

        public void setChannel_code(String channel_code) {
            this.channel_code = channel_code;
        }

        public String getCall() {
            return call;
        }

        public void setCall(String call) {
            this.call = call;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public long getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }

        public Integer getEncrypt() {
            return encrypt;
        }

        public void setEncrypt(Integer encrypt) {
            this.encrypt = encrypt;
        }

        public String getEncrypt_key() {
            return encrypt_key;
        }

        public void setEncrypt_key(String encrypt_key) {
            this.encrypt_key = encrypt_key;
        }
    }

    /**
     * 机构方拼接请求参数，并签名(使用机构方私钥)
     */
    public static InvokeJdqRequest channelBuildInvokeJdqRequest() throws Exception {
        InvokeJdqRequest request = new InvokeJdqRequest();
        request.setChannel_code(MetaInfo.testChannelCode);
        request.setCall("testApi");
        request.setData(JSON.toJSONString(MetaInfo.originData));
        request.setVersion("1.0");
        request.setTimestamp(new Date().getTime() / 1000);
        // （不需要对参数加密可忽略）
        request.setEncrypt(1); // 需要加密
        byte[] rawEncryptKey = MetaInfo.originEncryptKey.getBytes(Charset.forName("UTF-8"));
        // （不需要对参数加密可忽略）加密密钥使用机构私钥进行RSA加密并base64编码，放入请求参数中
        request.setEncrypt_key(Base64.encodeBase64String(
                RSATool.rsaEncryptByPrivateKey(RSATool.parseRSAPrivateKey(partnerPrivateKey), rawEncryptKey)));
        // （不需要对参数加密可忽略）使用AES加密，密钥使用原始加密密钥，加密后使用base64编码
        request.setData(Base64.encodeBase64String(
                AESTool.aesEncrypt(request.getData().getBytes(Charset.forName("UTF-8")),
                        MetaInfo.originEncryptKey)));
        // 准备加签
        String originSign = PreSignTool.buildToSignString(InvokeJdqRequest.class, request);
        // 加签：使用机构私钥加签，签名使用base64编码
        request.setSign(Base64.encodeBase64String(
                RSATool.rsaSignByPrivateKey(RSATool.parseRSAPrivateKey(partnerPrivateKey),
                        originSign.getBytes(Charset.forName("UTF-8")))));
        return request;
    }

    /**
     * 借点钱验签(使用机构方公钥)
     */
    public static void jdqParseChannelInvokeRequest(InvokeJdqRequest request) throws Exception {
        String originSign = PreSignTool.buildToSignString(InvokeJdqRequest.class, request);
        final RSAPublicKey publicKey = RSATool.parseRSAPublicKey(partnerPublicKey);
        final boolean verify = RSATool.rsaVerifySignByPublicKey(publicKey,
                originSign.getBytes(Charset.forName("UTF-8")), Base64.decodeBase64(request.getSign()));
        if (!verify) {
            throw new RuntimeException("验签不通过");
        }
        // 查看是否需要解密
        if (request.getEncrypt() != null && request.getEncrypt().intValue() == 1) {
            // 需要解密
            final byte[] bytes =
                    RSATool.rsaDecryptByPublicKey(publicKey, Base64.decodeBase64(request.getEncrypt_key()));
            String originEncryptKey = new String(bytes, Charset.forName("UTF-8"));
            request.setEncrypt_key(originEncryptKey);
            request.setData(new String(
                    AESTool.aesDecrypt(Base64.decodeBase64(request.getData()), originEncryptKey), "UTF-8"));
        }
    }

    /**
     * 测试借点钱请求机构方
     */
    private static void testChannelInvokeJdq() throws Exception {
        final String postBody = JSON.toJSONString(channelBuildInvokeJdqRequest());
        System.out.println("机构加签后请求串: ");
        System.out.println(postBody);
        InvokeJdqRequest request = JSON.parseObject(postBody, InvokeJdqRequest.class);
        jdqParseChannelInvokeRequest(request);
        System.out.println("借点钱验签&解密后请求串: ");
        System.out.println(JSON.toJSONString(request));
    }

    /**
     * 测试借点钱请求机构方
     */
    private static void testChannelInvokeJdq(String req) throws Exception {
        InvokeJdqRequest request = JSON.parseObject(req, InvokeJdqRequest.class);
        jdqParseChannelInvokeRequest(request);
        System.out.println("借点钱验签&解密后请求串: ");
        System.out.println(JSON.toJSONString(request));
    }

    public static void testFlow() throws Exception {
        /**
         * 测试借点钱请求机构接口
         */
        // 不需要压缩，不需要加密
        System.out.println("--------- 不需要压缩，不需要加密 ---------");
        testJdqInvokeChannel(new InvokeConfig());
        // 需要压缩，不需要加密
        System.out.println("--------- 需要压缩，不需要加密 ---------");
        InvokeConfig invokeConfig = new InvokeConfig();
        invokeConfig.setNeedCompress(true);
        testJdqInvokeChannel(invokeConfig);
        // 不需要压缩，需要加密
        System.out.println("--------- 不需要压缩，需要加密 ---------");
        invokeConfig = new InvokeConfig();
        invokeConfig.setNeedEncrypt(true);
        testJdqInvokeChannel(invokeConfig);
        // 需要压缩，需要加密
        System.out.println("--------- 需要压缩，需要加密 ---------");
        invokeConfig = new InvokeConfig();
        invokeConfig.setNeedEncrypt(true);
        invokeConfig.setNeedCompress(true);
        testJdqInvokeChannel(invokeConfig);
        /**
         * 测试机构请求借点钱
         */
        System.out.println("--------- 机构请求借点钱 ---------");
        testChannelInvokeJdq();
        /**
         *对结构请求验签
         */
        String channelReq = "{\n"
                + "  \"channel_code\": \"new_test\",\n"
                + "  \"call\": \"call\",\n"
                + "  \"version\": \"1.0\",\n"
                + "  \"data\": \"{\\\"extra_info\\\":\\\"hello ad）*&……%%￥#\\\",\\\"user_name\\\":\\\"小明\\\","
                + "\\\"long_data\\\":\\\"{\\\\\\\"face_result_active\\\\\\\":{\\\\\\\"id_exceptions"
                + "\\\\\\\":{\\\\\\\"id_photo_monochrome\\\\\\\":0,\\\\\\\"id_attacked\\\\\\\":0},"
                + "\\\\\\\"result_faceid\\\\\\\":{\\\\\\\"thresholds\\\\\\\":{\\\\\\\"1e-6\\\\\\\":78.038,"
                + "\\\\\\\"1e-5\\\\\\\":74.399,\\\\\\\"1e-4\\\\\\\":69.315,\\\\\\\"1e-3\\\\\\\":62.169},"
                + "\\\\\\\"confidence\\\\\\\":84.793},\\\\\\\"request_id\\\\\\\":\\\\\\\"1542854983,"
                + "33ec3647-25ae-429d-a29e-df11277fcb1d\\\\\\\",\\\\\\\"time_used\\\\\\\":562},"
                + "\\\\\\\"jdq_order_id\\\\\\\":\\\\\\\"XXXXX\\\\\\\","
                + "\\\\\\\"loan_info\\\\\\\":{\\\\\\\"period\\\\\\\":\\\\\\\"6\\\\\\\","
                + "\\\\\\\"amount\\\\\\\":\\\\\\\"10000\\\\\\\"},"
                + "\\\\\\\"device_info\\\\\\\":{\\\\\\\"device_model\\\\\\\":\\\\\\\"iPhone\\\\\\\","
                + "\\\\\\\"device_id\\\\\\\":\\\\\\\"\\\\\\\",\\\\\\\"ip\\\\\\\":\\\\\\\"127.0.0.1\\\\\\\","
                + "\\\\\\\"jailbreak_flag\\\\\\\":true,\\\\\\\"device_type\\\\\\\":\\\\\\\"IOS\\\\\\\","
                + "\\\\\\\"device_os\\\\\\\":\\\\\\\"ios 11.4.1\\\\\\\",\\\\\\\"openudid\\\\\\\":\\\\\\\"\\\\\\\"},"
                + "\\\\\\\"user_login_upload_log\\\\\\\":{\\\\\\\"address\\\\\\\":\\\\\\\"中国上海市上海市杨浦区政通路189号-3"
                + "楼\\\\\\\",\\\\\\\"latitude\\\\\\\":\\\\\\\"31.301847\\\\\\\","
                + "\\\\\\\"longitude\\\\\\\":\\\\\\\"121.512752\\\\\\\"},"
                + "\\\\\\\"user_info\\\\\\\":{\\\\\\\"id_negative_valid_state\\\\\\\":\\\\\\\"{\\\\\\\\\\\"Temporary "
                + "ID Photo\\\\\\\\\\\":0.0,\\\\\\\\\\\"Screen\\\\\\\\\\\":0.0,\\\\\\\\\\\"Edited\\\\\\\\\\\":0.0,"
                + "\\\\\\\\\\\"Photocopy\\\\\\\\\\\":0.0,\\\\\\\\\\\"ID Photo\\\\\\\\\\\":1.0}\\\\\\\","
                + "\\\\\\\"nation\\\\\\\":\\\\\\\"汉\\\\\\\",\\\\\\\"industry_code\\\\\\\":\\\\\\\"6\\\\\\\","
                + "\\\\\\\"id_card\\\\\\\":\\\\\\\"620522198908140319\\\\\\\","
                + "\\\\\\\"industry\\\\\\\":\\\\\\\"计算机/互联网\\\\\\\",\\\\\\\"company_address\\\\\\\":\\\\\\\"安徽省,合肥市,"
                + "庐阳区|合生回家000006\\\\\\\",\\\\\\\"id_negative\\\\\\\":\\\\\\\"http://bktest-10010.oss-cn-hangzhou"
                + ".aliyuncs.com/53229177/d3130bf5b6d24e4d9368ec7503a11801.png\\\\\\\","
                + "\\\\\\\"telecom_auth\\\\\\\":\\\\\\\"1\\\\\\\","
                + "\\\\\\\"id_positive_valid_state\\\\\\\":\\\\\\\"{\\\\\\\\\\\"Temporary ID Photo\\\\\\\\\\\":0.0,"
                + "\\\\\\\\\\\"Screen\\\\\\\\\\\":0.0,\\\\\\\\\\\"Edited\\\\\\\\\\\":0.0,"
                + "\\\\\\\\\\\"Photocopy\\\\\\\\\\\":0.0,\\\\\\\\\\\"ID Photo\\\\\\\\\\\":1.0}\\\\\\\","
                + "\\\\\\\"work_profession\\\\\\\":\\\\\\\"批发/零售业\\\\\\\","
                + "\\\\\\\"company_city\\\\\\\":\\\\\\\"合肥市\\\\\\\","
                + "\\\\\\\"id_card_address\\\\\\\":\\\\\\\"甘肃省秦安县兴国镇宋洼村55号\\\\\\\","
                + "\\\\\\\"educate\\\\\\\":\\\\\\\"大专以下\\\\\\\",\\\\\\\"loan_usage_code\\\\\\\":\\\\\\\"6\\\\\\\","
                + "\\\\\\\"id_expiry_date\\\\\\\":\\\\\\\"2036-09-30\\\\\\\","
                + "\\\\\\\"id_positive\\\\\\\":\\\\\\\"http://bktest-10010.oss-cn-hangzhou.aliyuncs"
                + ".com/53229177/2473500d2e7e4705b7e808d88395645b.png\\\\\\\","
                + "\\\\\\\"company_tel\\\\\\\":\\\\\\\"0211234567\\\\\\\","
                + "\\\\\\\"id_signing_authority\\\\\\\":\\\\\\\"秦安县公安局\\\\\\\","
                + "\\\\\\\"face\\\\\\\":\\\\\\\"http://bktest-10010.oss-cn-hangzhou.aliyuncs"
                + ".com/53229177/eb421370882b487193ab3d1f904f4ef0.png\\\\\\\","
                + "\\\\\\\"phone\\\\\\\":\\\\\\\"13122053988\\\\\\\","
                + "\\\\\\\"company_name\\\\\\\":\\\\\\\"和姐姐家\\\\\\\",\\\\\\\"loan_usage\\\\\\\":\\\\\\\"旅游贷款\\\\\\\","
                + "\\\\\\\"name\\\\\\\":\\\\\\\"宋文华\\\\\\\",\\\\\\\"id_start_date\\\\\\\":\\\\\\\"2016-09-30\\\\\\\","
                + "\\\\\\\"educate_code\\\\\\\":\\\\\\\"1\\\\\\\"},"
                + "\\\\\\\"user_contact\\\\\\\":{\\\\\\\"name_spare\\\\\\\":\\\\\\\"测试服\\\\\\\","
                + "\\\\\\\"relation_spare_code\\\\\\\":\\\\\\\"91\\\\\\\",\\\\\\\"name\\\\\\\":\\\\\\\"花花\\\\\\\","
                + "\\\\\\\"mobile\\\\\\\":\\\\\\\"15853664852\\\\\\\","
                + "\\\\\\\"relation_spare\\\\\\\":\\\\\\\"兄弟姓名\\\\\\\","
                + "\\\\\\\"relation_code\\\\\\\":\\\\\\\"81\\\\\\\",\\\\\\\"relation\\\\\\\":\\\\\\\"父母姓名\\\\\\\","
                + "\\\\\\\"mobile_spare\\\\\\\":\\\\\\\"18964573188\\\\\\\"},"
                + "\\\\\\\"address_book\\\\\\\":[{\\\\\\\"name\\\\\\\":\\\\\\\"谭晶\\\\\\\","
                + "\\\\\\\"mobile\\\\\\\":\\\\\\\".86 13647336742\\\\\\\"},{\\\\\\\"name\\\\\\\":\\\\\\\"徐超\\\\\\\","
                + "\\\\\\\"mobile\\\\\\\":\\\\\\\"15852753251\\\\\\\"},{\\\\\\\"name\\\\\\\":\\\\\\\"向文辉\\\\\\\","
                + "\\\\\\\"mobile\\\\\\\":\\\\\\\"18271631169\\\\\\\"},{\\\\\\\"name\\\\\\\":\\\\\\\"尹光蓉\\\\\\\","
                + "\\\\\\\"mobile\\\\\\\":\\\\\\\"15001956228\\\\\\\"},{\\\\\\\"name\\\\\\\":\\\\\\\"山东饺子馆\\\\\\\","
                + "\\\\\\\"mobile\\\\\\\":\\\\\\\"176 2148 1118\\\\\\\"},"
                + "{\\\\\\\"name\\\\\\\":\\\\\\\"王远亮，雷雷\\\\\\\",\\\\\\\"mobile\\\\\\\":\\\\\\\"15212570891\\\\\\\"},"
                + "{\\\\\\\"name\\\\\\\":\\\\\\\"齐_玉兰二期c_1700_南主卧\\\\\\\","
                + "\\\\\\\"mobile\\\\\\\":\\\\\\\"13262982872\\\\\\\"},{\\\\\\\"name\\\\\\\":\\\\\\\"李守洪\\\\\\\","
                + "\\\\\\\"mobile\\\\\\\":\\\\\\\"18724019948\\\\\\\"}],"
                + "\\\\\\\"app_data\\\\\\\":{\\\\\\\"com_demo_syh\\\\\\\":{\\\\\\\"name\\\\\\\":\\\\\\\"闪赢花\\\\\\\","
                + "\\\\\\\"version\\\\\\\":\\\\\\\"1_0\\\\\\\"}},\\\\\\\"operator\\\\\\\":\\\\\\\"\\\\\\\"}\\\","
                + "\\\"user_age\\\":18}\",\n"
                + "  \"timestamp\": 1548132493,\n"
                + "  \"sign\": \"reZVJq7laJfGJeRQs-Pq5xLvX-bZEJquHhPSJNvXZX-7G6v0ZiwU6CEo9"
                +
                "-2PBgZgYGEEMZZhVX8xIJhSMNqY83XAP2nEWiOGnr2zJyjlClUUgU51SMNUeEkvTRkdztvPatdvcwDe84yosJ1kZBXYNpRPDlN0H00kXYj3Drmn0hI\"\n"
                + "}";
        System.out.println("机构原始请求：");
        System.out.println(channelReq);
        testChannelInvokeJdq(channelReq);
        /**
         *对结构加密请求验签解密
         */
        String channelEncryptReq = "{\"channel_code\":\"new_test\",\"call\":\"call\",\"version\":\"1.0\","
                + "\"data\":\"QJEiPsc2LHyDKRjZdcEnTp9qAcLYEkCnB9R4GA9Y8NsjzmZsQhjt+eBREutzFuGUemc"
                + "+xao2ORT8wuFJ5d9mlO1Jo1LOpAKlrQKqY0tLwJBx3bZOAJ1EWaWc9wg2igXZA7ERSCOCDMX2VDnNQl"
                + "\\/1JkC8uolmHljIXwuqm7NydLv97UfbmpyN8ytGWdShN+a7\\/8LklOaGDTEVsDJg9W"
                + "\\/4xly7O6PXVwujv9eYFfMNmPrzc9oklQCwTfdrgEbBGCzN143S4kmuAlLcSccEdLA\\/klpEGQunXdOBbWWc8gzxBISTO+1"
                +
                "+YLsOSzvW6im3lbOfz5DRUI5QiAv3nBckU3vRxS1anysJvKFf8NURSyOlGvssNAXX5K1GN3i2pZdK8fVp93Oj2q2NVSGBAzFjmcJkw"
                + "kipjap\\/rphwSThouTk4hGBfj8WIttyHTnlf4bOxXCCKgmd1OzT3CJmGbjOgXlqP8xUF4mrmAWKSsexPOatXl+S\\/DnzIako"
                + "i0ykEk0hm1e86mI3Y22Y1P1LAtC2EtkyHx6PQDliRS4AFPQ06vl4Fr3yt45sCO6y+TGwz9FPkwTg0CGTpeB"
                + "GQDiGwkKW3mzdk4+FxVugenIZJmpuKZELI+qrxbX+gyV1TuoU0S0Wdv0PXmr460Ta+5B8fy99Gcknjm3RAJ+l4Pg74eea"
                + "gTDRQmffaAT3CyQr9\\/Bz8mn4AGyUlgKPjrJCusd1aIUnOyZQoI4kwQZQN5S6zfkZGWzkGUPnfRCrg7X\\/rh857zWJMEz+1"
                + "NVsObNNeXjj2vFwr3FFyfmy5E+T33u+kochZFKpQuvss5gfCJlYIWfY5vSpoRokauAhno7nhvDpJf8W6IXXAl+bJN"
                + "7Q8KX3jWwl7rWsgyJ0w6lHL85v\\/zZ+WqMtV7BYnnp1o8HgMj69CkkxdiimHPNxJydVY\\/Hc7c2I7zkSJsB4y"
                + "gHy\\/5jtbfmnjambUmR6FEJl274KWFe+M\\/9iZbPGdWQt1ZKRyXJIlcCYJuvhCTkwhSUebWhqRLU1Bsz8qLf4Z"
                + "Z0v6lY4BI\\/dG\\/SdobkCLNywXsi2xWarYTT50UlFvDVxD2NlF7EDsnnCSG1Dr9WKH38\\/yg1YRIgIM1oxP38QUCYu1z7I"
                + "iKXmZuBYApEmjlKru6h1NftMU4gtCNij+QtDwCKVXa4NGbZV36apIp9XENIt0yXCB3QMMQr7ZY17Mmx8sdBDOh2FMdoZJLGAQFUd"
                + "PGlt8bKzqMed2tn5I42iQjo3luhJxL8yIVc+rP75ktYDlCNR9691MYNXg3IgHNtNx6bKPCUVzqrdiFEm6a5RSOCPNeHPXNNzOyo"
                + "TCbrsNZT0A8vXj\\/Q3FLqcr\\/NbtWQoHRScfY8hupXfRK3PgyCmKWStW870lsmKMJT6CoCleuOFVJI6vMHfbWOblSS8"
                + "8hgD\\/uvClIYvReN9mE2Ozd02F8WHhVD8xjPPMa4dhK56u3czziGnWy2ZewTM8lDiAuTezQbeRk0bu40v5Ch4shWD3P7VV6u"
                + "xdBy8vE\\/VcOoS3igrc561ClCiotu7K1UB0bSHbF9kvQ7ibdrCrxehGt7lhKXAb9\\/o\\/4I0zlZrwsKYbxF3ZjBcL7XTK"
                + "HMBRD6VMqeTUSN2uYN1MDZStsEU6ERl6oUzG5uoKGI+4Ty2skpY4TUEOmA1e8ulPgu6BROve0P18sPQDaG349nRoVB+BM4"
                + "2DCo+oQ49dpXE2KWLQw6sYA7ZQmN\\/pcaCna\\/F+lDbXnF+rbZloGQbi0mPu9AxQfJGqQL3VKT8cKkphUVrvj4jxLrQ8BF"
                + "YA+szugc3BlF1gPJNNas9szGsyPxVWvDAko4D7GycDSkzQ13y2z4upKB20lKy80Kuk0AwhMhkc2LBUL9o9QkplPLRe3dnScB"
                + "ECz3yNe6FejN7lde26v+\\/1pDyB8XSEIq5B1BhcbGxpbo8+c8sZYMaJaIOVZ1jK4HSbEXuKeAy9d7TDGqmUjCUAj"
                + "\\/KyeyH9fpf2t+zw3gVB3ILf02+xwneYWOaYXm+Vykq15Gl\\/vfjPJ5XTgS2iZF1UsyLrapcxrkU"
                + "+BltQqERFbCgNN6aP7lLPx42rurpgtQOUDgqujnwuotpd+aZcVJuZ8cKOn82Zez6U78f0yWXjUI0kXokWDNytKQY+JA"
                + "\\/NiqGcRoQtCDMTr3t+2zoyy9342lp0v7Ogpi6rwSZMKzVkPBLFkxE\\/dJgjdQzn"
                + "+qMsqLcgw6ltRLyNaJJTnVACXAJEUNePL6gPl4Fb49ovuQ+URUL3BObL5Ry\\/om"
                + "+72c12zFOFgMlb8LmAT0PoM6EylyfJHHGFuZyiHsk27e7mTNnBIvbzsz7BiGczSWIq7t\\/L"
                + "\\/rhnvkRVbMxdl6q4wHxt0p7I2qTraYG5vAqMtZzU"
                +
                "+FSg5DhsZNvnisMzI1lDyun6EdBQm9MjUeLgrVIEdIDkxm3TlTcLBJWyu7zE3UOPuXZxCOqFk2ZZCCGkUb6qf87iQlmuaM1xMhfZL0O4m3awq8XoRre5YSlwG\\/f6P+CNM5Wa8LCmG8Rd2YwXC+10yhzAUQ+lTKnk1Iac2zI+HKpcvxrUItHgplpOxjmRzzdiP65zCRMNXD1BYLFBKUPCqbN70lwsquEhi5QxjnNR9pSnrPGm8M2qN2gan7kGelj5v+VSlT2xOG2TXgXUJKPWFIIXIiMdI3nTYthAi9vSwUt43T\\/4i9caeJTFhCteFZBYSyJufDrturrnkZe5jv9sjVQ3v+zRGOXy1juBMfLWl2eYWfcrvaN+1\\/\\/wmyKNKcljEjjXarjCRF\\/CPWY4AfBAbhq16oAO+jpS6j4LoXq4FT34eAnMZ8n90agI4Lmk3TBl2yfsNFJHIYxJluGfw\\/v7\\/XTdaDMAQ4DXCx+aOyaWQfLuQmlGx7Rzzm7G6Nw9yvW5z6WAu9SexwmdEnq3yFsDhDylqu0C6gVhwlBSiwrzB0XfF1mMgdGf5cuGslTcKjRPswGiUQingQnzSsb85m+I8vKJ1K4cHH6W4sd2SrpwZE1TE4y\\/bkRRpuK15My8+6Hewed3RZxLo1E1Q4I9KNwqevRKxSkbWV3iniA2c0M27eRSIFOnigtfBF69hg+rPTh0TAk6zvW4cI3K+t4MCWcjeMLHUvfJOa93Z0+jBDLp8698QdtXZSdaZxq37PsOitfF2Ac9pZcJ3s2Pw11t9Xtd5IHD1j6Xd4Q3\\/oRtUUpinWWMCowuEfAEzszBxQ4kKq82lLb5H5b5PIk5oC3Jhw9IpGXU37jKXDS2LAeLYep7e79EErkMWTfTPgQkFVDx8XQ+uJZHrYylU30dqao8Hpv82trjeeejhvNlK5ndD5LDbBI7DE9eGCNMYC8D5yati6YUpAdyDrSN3xzFzZIjVvoeEDXYq1DZfys92UqhZ8dm69VJ8b6zXybRBbZIWt\\/lz4+ey6aWy8xWcg8qeFABsRlhLfMLMJ\\/5dzF4Nj9z\\/V6ykQcmqc0fkJMwnqqHyDgQWUjmFQ4EFtsQpl\\/5Cc\\/kaHJMGUScQJL1tgxdDs+WLpUZrMTn0538kWYDcO35qAps5OtI5PVwGGjBBSd9zu+cCRPOP2SjKUxldL78wTuxkiYe3Rd4\\/MLnNyaSRwFaF7NrKoJZbwshEIMk6JShEFWFBoG0s09y+5imOgR7ofjIDrjNudVKvFggiBm\\/9+sODlqEp0epQUsasGVjozXmmtvl9YkP6w6rp6YAnIqtgFN8iALr\\/+s77VI1Y9AwJNGazQe9OR4MEWr1Bld0wyRAQRXbV1QK9k2CVABHCjAFL5ogGZFD07p+tJwSDzOPZ5XAycAibKirv3XloKVPMBWDjVsK6Oakf3c2COqBzd7c3+b76HmbPDTb4ClatNeZCsJgngnIaCqLqRbF1JmKeDCQyVv6XxOeEXYlsIoP0\\/terhKEeqEFVjxWukALxWvSpzk5HL1parIXG7Ar\\/ox7PW8dirNM5n4\\/iRlew14HoTFQNA==\",\"timestamp\":1548244494,\"encrypt\":1,\"encrypt_key\":\"P6g8JrlubvN0VFmwYEGQiiz+BhisA+ow4V6xcNd0mMHFfZfufn97bIzYlvkUJO8eu6weR735vpR\\/dDlMLTTvHTLj9JB82H5WwXCCf\\/dDMjTMACQZsD4XeCvJ+AzqZR4WKOf4odEk9GJTKZZAWStkOiZOhAv\\/r\\/ImvgAm3t9jKAE=\",\"sign\":\"ZdoVlr23GgkJsOK5qzNPBrZL8tNAFNQucnlfex4plHM71af3yMlVf5T3THt7tk3UrtMiqfgLfaUJ1Vl-V2IpoaIluinJRmU3yM8nUEmngzm8EzVZXGeQYUEvjDkRn5lUWQSnzplnVVpZlr7CuA_7FiTuieKkF4YBJDp69llnol8\"}\n";
        System.out.println("机构原始加密后请求：");
        System.out.println(channelEncryptReq);
        testChannelInvokeJdq(channelEncryptReq);
    }

    public static void main(String[] args) throws Exception {

        InvokeJdqRequest invokeJdqRequest = channelBuildInvokeJdqRequest();

        jdqParseChannelInvokeRequest(invokeJdqRequest);
//        testFlow();
        JdqInvokeChannelDto jdqInvokeChannelDto = new JdqInvokeChannelDto();
        jdqInvokeChannelDto.setChannel_code("new_mysdj");
        jdqInvokeChannelDto.setSign("G7IgA2m707nDbOPEe4aCMj4Qk39nINpH6MXL9mh0a1bhFOwbe21oXucNyQr2U7Kej5EVT4PuAFejwq+tYaB01atiiixNylXbMe86gZwPvR7Qud3CknbyVkbrzwPhK2XrEnRi3p4EJ9TwBSSVzBK3X6eQMvbNkxPcb6BlapZM0G0=");
        jdqInvokeChannelDto.setData("OzDHJk4fCuputakBDOYlyu/7ICFAvpWEhYSj9zT1KBhx4s/ZBee8G2lnd4FsCaoQzHi1I85P0Qv6EYj2JFRjY7mbrlrA1h5340gRbBLtUN7q/IScObQEPnNg64RoBtl+7dSqUssFdM1ZJQVds/gn8tnFijHdM0NF6uQauo2yBZsn5uHU2zwGVGIFB2iAty8H");
        jdqInvokeChannelDto.setEncrypt(1);
        jdqInvokeChannelDto.setEncrypt_key("KeQbsV1DqdfpaRgbck1ddbhYU4ICmnDpU30hb65wMW/cYADimZEVZP4gQyuyR6qWMGPG4dRzdKp3AWi1fprSZZ5iiDSop7/WWNur3+hBHAhuc2Yb94IFSh165KEQeZH2/X0MD4WEr3K7k2ovYsiSCeJFXXCXAtAjV6+SdPxb0Qw=");
        jdqInvokeChannelDto.setCompress(0);
//        jdqInvokeChannelDto.setCompress_type("1");
        channelParseJdqInvokeRequest(jdqInvokeChannelDto);
    }

}
