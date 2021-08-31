package org.hzero.sample.app.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.hzero.boot.file.FileClient;
import org.hzero.boot.file.dto.FileDTO;
import org.hzero.core.util.FilenameUtils;
import org.hzero.sample.app.service.FileService;
import org.hzero.sample.infra.util.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * description
 *
 * @author shuangfei.zhu@hand-china.com 2020/01/13 11:16
 */
@Service
public class FileServiceImpl implements FileService {

    private final FileClient fileClient;

    @Autowired
    public FileServiceImpl(FileClient fileClient) {
        this.fileClient = fileClient;
    }

    @Override
    public String uploadMultipartFile(Long tenantId, String bucketName, String directory, MultipartFile multipartFile) {
        return fileClient.uploadFile(tenantId, bucketName, directory, multipartFile);
    }

    @Override
    public String uploadByteFile(Long tenantId, String bucketName, String directory, MultipartFile multipartFile) throws IOException {
        return fileClient.uploadFile(tenantId, bucketName, directory, multipartFile.getOriginalFilename(), multipartFile.getContentType(), multipartFile.getBytes());
    }

    @Override
    public String uploadMultipartAttachment(Long tenantId, String bucketName, String directory, MultipartFile multipartFile) {
        String attachmentId = fileClient.getAttachmentUUID(tenantId);
        fileClient.uploadAttachment(tenantId, bucketName, directory, attachmentId, multipartFile);
        return attachmentId;
    }

    @Override
    public String uploadByteAttachment(Long tenantId, String bucketName, String directory, MultipartFile multipartFile) throws IOException {
        String attachmentId = fileClient.getAttachmentUUID(tenantId);
        fileClient.uploadAttachment(tenantId, bucketName, directory, attachmentId, multipartFile.getOriginalFilename(), multipartFile.getContentType(), multipartFile.getBytes());
        return attachmentId;
    }

    @Override
    public void downloadFile(HttpServletRequest request, HttpServletResponse response, Long tenantId, String bucketName, String url) throws IOException {
        InputStream inputStream = fileClient.downloadFile(tenantId, bucketName, url);
        byte[] data = IOUtils.toByteArray(inputStream);
        // 针对不同浏览器做处理，避免下载的文件名乱码
        String filename = FilenameUtils.encodeFileName(request, FilenameUtils.getFileName(url));
        ResponseUtils.buildResponse(response, filename, data);
    }

    @Override
    public String updateMultipartFile(Long tenantId, String bucketName, String url, MultipartFile multipartFile) {
        return fileClient.updateFile(tenantId, bucketName, url, multipartFile);
    }

    @Override
    public String updateByteFile(Long tenantId, String bucketName, String url, MultipartFile multipartFile) throws IOException {
        return fileClient.updateFile(tenantId, bucketName, url, multipartFile.getBytes());
    }

    @Override
    public void deleteFile(Long tenantId, String bucketName, String url) {
        fileClient.deleteFileByUrl(tenantId, bucketName, Collections.singletonList(url));
    }

    @Override
    public void deleteAttachment(Long tenantId, String bucketName, String attachmentId) {
        fileClient.deleteFile(tenantId, bucketName, Collections.singletonList(attachmentId));
    }

    @Override
    public List<FileDTO> getAttachmentFiles(Long tenantId, String bucketName, String attachmentId) {
        return fileClient.getAttachmentFiles(tenantId, bucketName, attachmentId);
    }

    @Override
    public String getSignUrl(Long tenantId, String bucketName, String url) {
        return fileClient.getSignedUrl(tenantId, bucketName, url);
    }
}
