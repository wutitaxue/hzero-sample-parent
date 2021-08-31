package org.hzero.sample.app.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hzero.boot.file.dto.FileDTO;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件服务(url)
 *
 * @author shuangfei.zhu@hand-china.com 2020/01/13 11:13
 */
public interface FileService {

    /**
     * 上传Multipart单文件
     *
     * @param tenantId      租户Id
     * @param bucketName    桶名
     * @param directory     目录
     * @param multipartFile 文件
     * @return 文件url
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
     * 上传Multipart附件
     *
     * @param tenantId      租户Id
     * @param bucketName    桶名
     * @param directory     目录
     * @param multipartFile 文件
     * @return 附件ID
     */
    String uploadMultipartAttachment(Long tenantId, String bucketName, String directory, MultipartFile multipartFile);

    /**
     * 上传byte附件
     *
     * @param tenantId      租户Id
     * @param bucketName    桶名
     * @param directory     目录
     * @param multipartFile 文件
     * @return 附件ID
     * @throws IOException 读取文件失败
     */
    String uploadByteAttachment(Long tenantId, String bucketName, String directory, MultipartFile multipartFile) throws IOException;

    /**
     * 下载文件
     *
     * @param request    request
     * @param response   response
     * @param tenantId   租户
     * @param bucketName 桶名
     * @param url        文件url
     * @throws IOException 读取文件失败
     */
    void downloadFile(HttpServletRequest request, HttpServletResponse response, Long tenantId, String bucketName, String url) throws IOException;

    /**
     * 更新Multipart单文件
     *
     * @param tenantId      租户Id
     * @param bucketName    桶名
     * @param url           文件url
     * @param multipartFile 文件
     * @return 文件url
     */
    String updateMultipartFile(Long tenantId, String bucketName, String url, MultipartFile multipartFile);

    /**
     * 更新Byte单文件
     *
     * @param tenantId      租户Id
     * @param bucketName    桶名
     * @param url           文件url
     * @param multipartFile 文件
     * @return 文件url
     * @throws IOException 读取文件失败
     */
    String updateByteFile(Long tenantId, String bucketName, String url, MultipartFile multipartFile) throws IOException;

    /**
     * 删除单文件
     *
     * @param tenantId   租户Id
     * @param bucketName 桶名
     * @param url        文件url
     */
    void deleteFile(Long tenantId, String bucketName, String url);

    /**
     * 删除附件
     *
     * @param tenantId     租户Id
     * @param bucketName   桶名
     * @param attachmentId 附件Id
     */
    void deleteAttachment(Long tenantId, String bucketName, String attachmentId);

    /**
     * 获取附件的文件列表
     *
     * @param tenantId     租户Id
     * @param bucketName   桶名
     * @param attachmentId 附件Id
     * @return 附件列表
     */
    List<FileDTO> getAttachmentFiles(Long tenantId, String bucketName, String attachmentId);

    /**
     * 获取带签名的url
     *
     * @param tenantId   租户Id
     * @param bucketName 桶名
     * @param url        文件url
     * @return 签名url
     */
    String getSignUrl(Long tenantId, String bucketName, String url);
}
