package com.shangyong.thjdq.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shangyong.loan.autoconfigure.SleuthLoggerExclude;
import com.shangyong.thjdq.config.properties.JdqCommonProperties;
import com.shangyong.thjdq.config.properties.JdqKeyProperties;
import com.shangyong.thjdq.dto.InvokeJdqRequestDto;
import com.shangyong.thjdq.dto.JdqInvokeChannelDto;
import com.shangyong.thjdq.enums.JdqPushEnum;
import com.shangyong.thjdq.enums.ResponseCode;
import com.shangyong.thjdq.enums.UserChannelEnum;
import com.shangyong.thjdq.service.JdqSignService;
import com.shangyong.thjdq.util.AESTool;
import com.shangyong.thjdq.util.GzipTool;
import com.shangyong.thjdq.util.PreSignTool;
import com.shangyong.thjdq.util.RSATool;
import com.shangyong.thjdq.vo.Response;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.security.interfaces.RSAPublicKey;
import java.util.Date;

/**
 * 借点钱 签名serviceimpl
 * Created by zbb on 2019-03-18.
 */
@Service
public class JdqSignServiceImpl implements JdqSignService {

    private static final Logger log = LoggerFactory.getLogger(JdqSignServiceImpl.class);

    @Autowired
    private JdqKeyProperties jdqKeyProperties;
    @Autowired
    private JdqCommonProperties jdqCommonProperties;

    @Override
    @SleuthLoggerExclude(excludeOut = true, excludeInput = true)
    public boolean channelParseJdqInvokeRequest(JdqInvokeChannelDto request) {

        try {
            // 先验签
            String originSign = PreSignTool.buildToSignString(JdqInvokeChannelDto.class, request);
            final RSAPublicKey publicKey = RSATool.parseRSAPublicKey(jdqKeyProperties.getPublicKey());
            final boolean verify = RSATool.rsaVerifySignByPublicKey(publicKey,
                    originSign.getBytes(Charset.forName("UTF-8")), Base64.decodeBase64(request.getSign()));
            if (!verify) {
                throw new RuntimeException("验签不通过");
            }
            if (request.getData() == null || request.getData().length() == 0) {
                System.out.println("无业务数据");
                return false;
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

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("借点钱调用机构 签名解密失败 {}", e);

        }

        return false;

    }

    @Override
    @SleuthLoggerExclude(excludeOut = true, excludeInput = true)
    public InvokeJdqRequestDto channelBuildInvokeJdqRequest(JdqPushEnum jdqPushEnum, Object data) {

        try {

            InvokeJdqRequestDto request = new InvokeJdqRequestDto();
            request.setChannel_code(UserChannelEnum.JDQ.getName());
            request.setCall(jdqPushEnum.getCall());
            //请参考DdqPushEnum的ObjectName
            request.setData(JSON.toJSONString(data));
            request.setVersion("1.0");
            request.setTimestamp(new Date().getTime() / 1000);
            // （不需要对参数加密可忽略）
            request.setEncrypt(1); // 需要加密
            byte[] rawEncryptKey = jdqKeyProperties.getOriginEncryptKey().getBytes(Charset.forName("UTF-8"));
            // （不需要对参数加密可忽略）加密密钥使用机构私钥进行RSA加密并base64编码，放入请求参数中
            request.setEncrypt_key(Base64.encodeBase64String(
                    RSATool.rsaEncryptByPrivateKey(RSATool.parseRSAPrivateKey(jdqKeyProperties.getPartnerPrivateKey()), rawEncryptKey)));
            // （不需要对参数加密可忽略）使用AES加密，密钥使用原始加密密钥，加密后使用base64编码
            request.setData(Base64.encodeBase64String(
                    AESTool.aesEncrypt(request.getData().getBytes(Charset.forName("UTF-8")),
                            jdqKeyProperties.getOriginEncryptKey())));
            // 准备加签
            String originSign = PreSignTool.buildToSignString(InvokeJdqRequestDto.class, request);
            // 加签：使用机构私钥加签，签名使用base64编码
            request.setSign(Base64.encodeBase64String(
                    RSATool.rsaSignByPrivateKey(RSATool.parseRSAPrivateKey(jdqKeyProperties.getPartnerPrivateKey()),
                            originSign.getBytes(Charset.forName("UTF-8")))));
            return request;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("构建请求借点钱机构参数 异常 {}", e);
        }

        return null;

    }

    @Override
    @SleuthLoggerExclude(excludeOut = false, excludeInput = true)
    public Response sendJdqRequest(InvokeJdqRequestDto invokeJdqRequestDto) {

        try {

            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
            headers.setContentType(type);
            headers.add("Accept", MediaType.APPLICATION_JSON.toString());

            String body = JSONObject.toJSONString(invokeJdqRequestDto);
            log.debug("借点钱请求body {}", body);
            HttpEntity<String> formEntity = new HttpEntity<String>(body, headers);

            String result = restTemplate.postForObject(jdqCommonProperties.getRequestUrl(), formEntity, String.class);
            log.info("借点钱返回result {}", result);

            return ResponseCode.OK.toResponse();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("发送借点钱请求失败{}", e);
        }

        return null;
    }
}
