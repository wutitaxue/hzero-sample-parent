package org.hzero.sample.app.service.impl;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.hzero.boot.file.FileClient;
import org.hzero.core.util.FilenameUtils;
import org.hzero.sample.app.service.FileKeyService;
import org.hzero.sample.infra.util.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * description
 *
 * @author shuangfei.zhu@hand-china.com 2020/01/13 14:32
 */
@Component
public class FileKeyServiceImpl implements FileKeyService {

    private final FileClient fileClient;

    @Autowired
    public FileKeyServiceImpl(FileClient fileClient) {
        this.fileClient = fileClient;
    }

    @Override
    public String uploadMultipartFile(Long tenantId, String bucketName, String directory, MultipartFile multipartFile) {
        return fileClient.uploadFileWithMD5(tenantId, bucketName, directory, multipartFile).getFileKey();
    }

    @Override
    public String uploadByteFile(Long tenantId, String bucketName, String directory, MultipartFile multipartFile) throws IOException {
        return fileClient.uploadFileWithMD5(tenantId, bucketName, directory, multipartFile.getOriginalFilename(), multipartFile.getContentType(), multipartFile.getBytes()).getFileKey();
    }

    @Override
    public void downloadFile(HttpServletRequest request, HttpServletResponse response, Long tenantId, String fileKey) throws IOException {
        InputStream inputStream = fileClient.downloadFile(tenantId, fileKey);
        byte[] data = IOUtils.toByteArray(inputStream);
        // 针对不同浏览器做处理，避免下载的文件名乱码
        String filename = FilenameUtils.encodeFileName(request, FilenameUtils.getFileName(fileKey));
        ResponseUtils.buildResponse(response, filename, data);
    }

    @Override
    public String updateMultipartFile(Long tenantId, String fileKey, MultipartFile multipartFile) {
        return fileClient.updateFile(tenantId, fileKey, multipartFile);
    }

    @Override
    public String updateByteFile(Long tenantId, String fileKey, MultipartFile multipartFile) throws IOException {
        return fileClient.updateFile(tenantId, fileKey, multipartFile.getBytes());
    }

    @Override
    public void deleteFile(Long tenantId, String fileKey) {
        fileClient.deleteFileByKey(tenantId, fileKey);
    }

    @Override
    public String getSignUrl(Long tenantId, String fileKey) {
        return fileClient.getSignedUrl(tenantId, fileKey).getFileTokenUrl();
    }
}
