package org.hzero.sample.app.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件服务(key)
 *
 * @author shuangfei.zhu@hand-china.com 2020/01/13 14:31
 */
public interface FileKeyService {

    /**
     * 上传Multipart单文件
     *
     * @param tenantId      租户Id
     * @param bucketName    桶名
     * @param directory     目录
     * @param multipartFile 文件
     * @return 文件key
     */
    String uploadMultipartFile(Long tenantId, String bucketName, String directory, MultipartFile multipartFile);

    /**
     * 上传byte单文件
     *
     * @param tenantId      租户Id
     * @param bucketName    桶名
     * @param directory     目录
     * @param multipartFile 文件
     * @return 文件url
     * @throws IOException 获取文件失败
     */
    String uploadByteFile(Long tenantId, String bucketName, String directory, MultipartFile multipartFile) throws IOException;

    /**
     * 下载文件
     *
     * @param request  request
     * @param response response
     * @param tenantId 租户
     * @param fileKey  文件key
     * @throws IOException 读取文件失败
     */
    void downloadFile(HttpServletRequest request, HttpServletResponse response, Long tenantId, String fileKey) throws IOException;

    /**
     * 更新Multipart单文件
     *
     * @param tenantId      租户Id
     * @param fileKey       文件key
     * @param multipartFile 文件
     * @return 文件key
     */
    String updateMultipartFile(Long tenantId, String fileKey, MultipartFile multipartFile);

    /**
     * 更新Byte单文件
     *
     * @param tenantId      租户Id
     * @param fileKey       文件url
     * @param multipartFile 文件
     * @return 文件url
     * @throws IOException 读取文件失败
     */
    String updateByteFile(Long tenantId, String fileKey, MultipartFile multipartFile) throws IOException;

    /**
     * 删除单文件
     *
     * @param tenantId 租户Id
     * @param fileKey  文件key
     */
    void deleteFile(Long tenantId, String fileKey);

    /**
     * 获取带签名的url
     *
     * @param tenantId 租户Id
     * @param fileKey  文件key
     * @return 签名url
     */
    String getSignUrl(Long tenantId, String fileKey);
}
