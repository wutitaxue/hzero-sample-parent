package org.hzero.sample.domain.service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.hzero.core.util.FilenameUtils;
import org.hzero.starter.file.constant.FileConstant;
import org.hzero.starter.file.entity.FileInfo;
import org.hzero.starter.file.entity.StoreConfig;
import org.hzero.starter.file.service.AbstractFileService;
import org.hzero.starter.file.util.AesUtils;

import io.choerodon.core.exception.CommonException;
import io.minio.MinioClient;
import io.minio.http.Method;
import io.minio.policy.PolicyType;

/**
 * description
 *
 * @author shuangfei.zhu@hand-china.com 2020/01/17 9:39
 */
public class MinioFileServiceImpl extends AbstractFileService {

    /**
     * 对象存储客户端
     */
    private MinioClient client;
    /**
     * 桶的权限控制参数
     */
    private PolicyType policyType;

    /**
     * 初始化文件服务配置
     *
     * @param config 存储配置
     * @return AbstractFileService
     */
    @Override
    public AbstractFileService init(StoreConfig config) {
        this.config = config;
        try {
            // 初始化client
            client = new MinioClient(config.getEndPoint(), config.getAccessKeyId(), config.getAccessKeySecret());

        } catch (Exception e) {
            // 异常处理

        }
        return this;
    }

    /**
     * 上传本地文件(要用分片上传)
     *
     * @param file     文件信息
     * @param filePath 文件地址
     * @return 返回http地址
     */
    @Override
    public String upload(FileInfo file, String filePath) {
        // 获取桶名
        String realBucketName = getRealBucketName(file.getBucketName());
        // 获取对象KEY
        String fileKey = file.getFileKey();
        try {
            // 文件上传
            // client.putObject(xxxxx);
            // 返回http地址
            return getObjectPrefixUrl(realBucketName) + fileKey;
        } catch (Exception e) {
            // 异常处理

        }
        throw new CommonException("error");
    }

    /**
     * 文件上传
     *
     * @param file        文件信息
     * @param inputStream 字节流
     * @return 返回http地址
     */
    @Override
    public String upload(FileInfo file, InputStream inputStream) {
        String realBucketName = getRealBucketName(file.getBucketName());
        String fileKey = file.getFileKey();
        try {
            // 文件上传
            // client.putObject(xxxxx);
            // 返回http地址
            return getObjectPrefixUrl(realBucketName) + fileKey;
        } catch (Exception e) {
            // 异常处理
        }
        throw new CommonException("error");
    }

    /**
     * 文件复制
     *
     * @param file          原文件信息
     * @param oldFileKey    原文件的fileKey
     * @param oldBucketName 原文件的桶
     * @return 新文件的地址
     */
    @Override
    public String copyFile(FileInfo file, String oldFileKey, String oldBucketName) {
        String realBucketName = getRealBucketName(file.getBucketName());
        String fileKey = file.getFileKey();
        try {
            // 文件复制
            client.copyObject(getRealBucketName(oldBucketName), oldFileKey, realBucketName, fileKey);
            // 返回新文件地址
            return getObjectPrefixUrl(realBucketName) + fileKey;
        } catch (Exception e) {
            // 异常处理
        }
        throw new CommonException("error");
    }

    /**
     * 删除文件
     *
     * @param bucketName 桶
     * @param url        url
     * @param fileKey    文件key
     */
    @Override
    public void deleteFile(String bucketName, String url, String fileKey) {
        String realBucketName = getRealBucketName(bucketName);
        if (StringUtils.isBlank(fileKey)) {
            fileKey = getFileKey(realBucketName, url);
        }
        try {
            // 删除附件文档
            client.removeObject(realBucketName, fileKey);
        } catch (Exception e) {
            // 异常处理
        }
        throw new CommonException("error");
    }

    /**
     * 获取文件授权url
     *
     * @param servletRequest request
     * @param bucketName     桶名
     * @param url            url
     * @param fileName       文件名
     * @param fileKey        文件key
     * @param download       是否下载(是否将contentType设置为stream)
     * @param expires        有效时长
     * @return 下载地址
     */
    @Override
    public String getSignedUrl(HttpServletRequest servletRequest,
                               String bucketName,
                               String url,
                               String fileKey,
                               String fileName,
                               boolean download,
                               Long expires) {
        String realBucketName = getRealBucketName(bucketName);
        if (StringUtils.isBlank(fileKey)) {
            fileKey = getFileKey(realBucketName, url);
        }
        String signedUrl;
        // 路径有效期
        Long expiresTime = expires == null ? fileConfig.getDefaultExpires() : expires;
        try {
            if (download) {
                Map<String, String> reqParams = new HashMap<>(16);
                reqParams.put("response-content-type", FileConstant.DEFAULT_MULTI_TYPE);
                // 指定attachment，前端对文件做下载处理
                reqParams.put("response-content-disposition", "attachment;filename=" + FilenameUtils.encodeFileName(servletRequest, fileName));
                reqParams.put("response-cache-control", "must-revalidate, post-check=0, pre-check=0");
                reqParams.put("response-expires", String.valueOf(System.currentTimeMillis() + 1000));
                signedUrl = client.getPresignedObjectUrl(Method.GET, realBucketName, fileKey, expiresTime.intValue(), reqParams);
            } else {
                signedUrl = client.presignedGetObject(realBucketName, fileKey, expiresTime.intValue());
            }
            // 返回签名地址
            return signedUrl;
        } catch (Exception e) {
            // 异常处理
        }
        throw new CommonException("error");
    }

    /**
     * 下载文件，获取文件流
     *
     * @param request    HttpServletRequest
     * @param response   HttpServletResponse
     * @param bucketName 桶
     * @param url        url
     * @param fileKey    文件key
     */
    @Override
    public void download(HttpServletRequest request, HttpServletResponse response,
                         String bucketName, String url, String fileKey) {
        String realBucketName = getRealBucketName(bucketName);
        if (StringUtils.isBlank(fileKey)) {
            // 获取文件实际的ObjectKey
            fileKey = getFileKey(realBucketName, url);
        }
        try {
            // 获取文件InputStream
            InputStream is = client.getObject(realBucketName, fileKey);
            byte[] data = IOUtils.toByteArray(is);
            // 构建文件下载的response
            buildResponse(response, data, FilenameUtils.encodeFileName(request, FilenameUtils.getFileName(StringUtils.isBlank(url) ? fileKey : url)));
        } catch (Exception e) {
            // 异常处理
        }
    }

    /**
     * 下载文件并解密
     *
     * @param request    HttpServletRequest
     * @param response   HttpServletResponse
     * @param bucketName 桶
     * @param url        url
     * @param fileKey    文件key
     * @param password   密钥
     */
    @Override
    public void decryptDownload(HttpServletRequest request, HttpServletResponse response,
                                String bucketName, String url, String fileKey, String password) {
        String realBucketName = getRealBucketName(bucketName);
        if (StringUtils.isBlank(fileKey)) {
            // 获取文件实际的ObjectKey
            fileKey = getFileKey(realBucketName, url);
        }
        try {
            InputStream is = client.getObject(realBucketName, fileKey);
            byte[] data = IOUtils.toByteArray(is);
            if (StringUtils.isBlank(password)) {
                data = AesUtils.decrypt(data);
            } else {
                data = AesUtils.decrypt(data, password);
            }
            // 构建文件下载的response
            buildResponse(response, data, FilenameUtils.encodeFileName(request, FilenameUtils.getFileName(StringUtils.isBlank(url) ? fileKey : url)));
        } catch (Exception e) {
            // 异常处理
        }
    }

    /**
     * 获取对象前序URL
     *
     * @param bucketName 文件目录
     * @return 文件URL
     */
    @Override
    public String getObjectPrefixUrl(String bucketName) {
        return String.format("%s/%s/", config.getEndPoint(), bucketName);
    }
}