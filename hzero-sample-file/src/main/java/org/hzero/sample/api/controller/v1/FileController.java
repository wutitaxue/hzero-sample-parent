package org.hzero.sample.api.controller.v1;

import org.hzero.core.util.Results;
import org.hzero.sample.app.service.FileService;
import org.hzero.sample.config.FileSwaggerApiConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.choerodon.swagger.annotation.Permission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 文件controller
 *
 * @author shuangfei.zhu@hand-china.com 2020/01/13 11:19
 */
@Api(tags = FileSwaggerApiConfig.FILE)
@RestController("fileController.v1")
@RequestMapping("/v1/file")
public class FileController {

    private final FileService fileService;

    @Autowired
    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @Permission(permissionLogin = true)
    @ApiOperation(value = "Multipart上传文件")
    @PostMapping("/multipart")
    public ResponseEntity<String> uploadFile(@RequestParam Long tenantId, @RequestParam String bucketName,
                                             @RequestParam(value = "directory", required = false) String directory,
                                             @RequestParam MultipartFile multipartFile) {
        return Results.success(fileService.updateMultipartFile(tenantId, bucketName, directory, multipartFile));
    }
}
