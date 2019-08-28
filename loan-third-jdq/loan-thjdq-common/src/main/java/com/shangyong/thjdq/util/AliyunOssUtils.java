package com.shangyong.thjdq.util;

import com.aliyun.oss.ClientBuilderConfiguration;
import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.auth.CredentialsProvider;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.aliyun.oss.model.OSSObject;
import com.shangyong.thjdq.config.properties.AliyunOssProperties;
import com.shangyong.thjdq.enums.PicTypeEnum;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Calendar;
import java.util.TimeZone;

public class AliyunOssUtils {

    private static final Logger log = LoggerFactory.getLogger(AliyunOssUtils.class);

    private static AliyunOssUtils instance = null;
    private static OSSClient ossClient = null;

    /**
     * 慢请求
     */
    private static final long SLOW_REQUESTS_THRESHOLD = 60 * 1000;
    /**
     * 设置60S请求超时
     */
    private static final int REQUEST_TIME_OUT = 60 * 1000;

    // 阿里云API的外网域名
    private static String ENDPOINT;
    // 阿里云API的外网域名访问链接
    private static String ENDPOINT_URL;
    // 阿里云API的内网域名
    private static String INTRANET_ENDPOINT;
    // 阿里云API的密钥Access Key ID
    private static String ACCESS_KEY_ID;
    // 阿里云API的密钥Access Key Secret
    private static String ACCESS_KEY_SECRET;
    // 阿里云API的bucket名称
    private static String BACKET_NAME;
    // 阿里云APIoss存储图片的顶级目录名
    private static String BASE_PATH;
    // 阿里云APIoss存储图片的API目录名
    private static String API_PATH;

    private AliyunOssUtils() {
    }

    //初始化属性
    static {
        instance = new AliyunOssUtils();
        AliyunOssProperties aliyunOssProperties = SpringContextUtils.getBean(AliyunOssProperties.class);
        ENDPOINT = aliyunOssProperties.getEndpoint();
        ENDPOINT_URL = aliyunOssProperties.getEndpoint();
        INTRANET_ENDPOINT = aliyunOssProperties.getIntranetEndpoint();
        ACCESS_KEY_ID = aliyunOssProperties.getAccessKeyId();
        ACCESS_KEY_SECRET = aliyunOssProperties.getAccessKeySecret();
        BACKET_NAME = aliyunOssProperties.getBucketName();
        BASE_PATH = aliyunOssProperties.getBasePath();
        API_PATH = aliyunOssProperties.getApiPath();
        if (ossClient == null) {
            CredentialsProvider credentialsProvider = new DefaultCredentialProvider(ACCESS_KEY_ID, ACCESS_KEY_SECRET);
            ClientConfiguration config = new ClientBuilderConfiguration();
            config.setMaxErrorRetry(1);
            config.setRequestTimeout(REQUEST_TIME_OUT);
            config.setSlowRequestsThreshold(SLOW_REQUESTS_THRESHOLD);
            ossClient = new OSSClient(ENDPOINT, credentialsProvider, config);
        }
        if (ENDPOINT_URL.startsWith("http:")) {
            ENDPOINT_URL = ENDPOINT_URL.replace("http:", "https:");
        }
        ENDPOINT_URL = ENDPOINT_URL.replace("https://", "https://" + BACKET_NAME + ".");
    }

    public static AliyunOssUtils getInstance() {
        return instance;
    }

    /**
     * 获取文件后缀并返回文件全路径
     *
     * @param url
     * @return
     */
    public static String getNewPicPathByUrl(String url, String basePath, String apiPath) {
        String suffix = url.substring(url.lastIndexOf("."), url.length());
        // 校验图片格式
        if (!PicTypeEnum.getAllPicTypeStr().contains((suffix + ".").toUpperCase())) {
            throw new RuntimeException("非法的图片格式" + url);
        }
        return getFilePath(basePath, apiPath, UUIDUtils.getUUID() + suffix);
    }

    /**
     * oss上传文件
     */
    public static void saveOSSBinaryFile(final byte[] content, String ossBucketName, String filePath) {
        try {
            ossClient.putObject(ossBucketName, filePath, new ByteArrayInputStream(content));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * oss上传图片文件
     */
    public static boolean saveOSSUrlFileByLocal(String url, String ossAccessId, String ossAccessKey, String ossEndpoint, String ossBucketName, String filePath) {

        OSSClient ossClient = null;
        try {
            CredentialsProvider credentialsProvider = new DefaultCredentialProvider(ossAccessId, ossAccessKey);
            ClientConfiguration config = new ClientBuilderConfiguration();
            config.setRequestTimeout(REQUEST_TIME_OUT);
            config.setSlowRequestsThreshold(SLOW_REQUESTS_THRESHOLD);
            ossClient = new OSSClient(ossEndpoint, credentialsProvider, config);

                // 上传网络流。
            InputStream inputStream = new URL(url).openStream();
            ossClient.putObject(ossBucketName, filePath, inputStream);
            ossClient.shutdown();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }

        return false;
    }

    /**
     * oss上传图片文件
     */
    public static boolean saveOSSUrlFile(String url, String ossBucketName, String filePath) {
        try {
            // 上传网络流。
            InputStream inputStream = new URL(url).openStream();
            ossClient.putObject(ossBucketName, filePath, inputStream);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * oss文件下载
     *
     * @return
     * @throws IOException
     */
    public static final byte[] getOSSInputStream(String ossEndpoint, String ossAccessId, String ossAccessKey,
                                                 String ossBucketName, String path) throws IOException {
        try {
            OSSObject ossObject = ossClient.getObject(ossBucketName, path);
            InputStream in = ossObject.getObjectContent();
            return IOUtils.toByteArray(in);
        } catch (Exception e) {
            e.printStackTrace();
        }

        throw new RuntimeException("oss文件下载失败");
    }

    /**
     * 根据网络图片上传阿里云oss服务器
     *
     * @param url
     * @param basePath
     * @param apiPath
     * @param ossEndpoint
     * @param ossAccessId
     * @param ossAccessKey
     * @param ossBucketName
     * @return
     */
    public static String uploadUrlToOss(String url, String basePath, String apiPath, String ossEndpoint, String ossAccessId, String ossAccessKey,
                                        String ossBucketName) {
        String picUrl = "";
        try {

            if (!url.startsWith("http")) {
                log.error("非网络图片 无法上传 {}", url);
                return null;
            }

            picUrl = getNewPicPathByUrl(url, basePath, apiPath);
            if (AliyunOssUtils.saveOSSUrlFileByLocal(url, ossAccessId,ossAccessKey,ossEndpoint, ossBucketName, picUrl)) {
                if (ossEndpoint.startsWith("http:")) {
                    ossEndpoint = ossEndpoint.replace("http:", "https:");
                }
                ossEndpoint = ossEndpoint.replace("https://", "https://" + ossBucketName + ".");
                return ossEndpoint + "/" + picUrl;
            }
        } catch (Exception e) {
            log.error("根据网络图片上传阿里云oss服务器异常 :" + picUrl, e);
        }

        return null;

    }

    public static String uploadUrlToOss(String url) {
        String picUrl = "";
        try {

            if (!url.startsWith("http")) {
                log.error("非网络图片 无法上传 {}", url);
                return null;
            }

            picUrl = getNewPicPathByUrl(url, BASE_PATH, API_PATH);
            if (AliyunOssUtils.saveOSSUrlFile(url, BACKET_NAME, picUrl)) {
                return ENDPOINT_URL + "/" + picUrl;
            }
        } catch (Exception e) {
            log.error("根据网络图片上传阿里云oss服务器异常 :" + picUrl, e);
        }

        return null;

    }

    /**
     * oss上传文件路径配置
     *
     * @param basePath oss存储图片的顶级目录名 images/
     * @param apiPath  图片oss存储目录名 如faceRecognitionPic/
     * @param fileName 文件名
     * @return
     */
    public static String getFilePath(String basePath, String apiPath, String fileName) {
        String filePath = null;
        try {
            StringBuffer sb = new StringBuffer();
            Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
            filePath = sb.append(basePath).append(apiPath).append(cal.get(Calendar.YEAR))
                    .append(cal.get(Calendar.MONTH) + 1).append(cal.get(Calendar.DAY_OF_MONTH)).append("/")
                    .append(fileName).toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filePath;
    }

    public static void main(String[] args) {
        String url = "http://103.72.165.29/1111.png";
//		String picUrl = getNewPicPathByUrl(url, "images/", "thjdq/");

//        System.out.println(uploadUrlToOss(url, "images/", "ticket/", "https://oss-cn-shanghai.aliyuncs.com", "LTAIgQD5iYItnMP4", "dReP74u0i3wQEycEq9LsNiDk7DYl5p", "sy-test-oss"));
        System.out.println(uploadUrlToOss(url));
        ;
    }

}
