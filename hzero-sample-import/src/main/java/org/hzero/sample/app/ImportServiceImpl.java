package org.hzero.sample.app;

import org.hzero.boot.imported.app.service.IDoImportService;
import org.hzero.boot.imported.infra.validator.annotation.ImportService;
import org.hzero.sample.domain.entity.User;
import org.hzero.sample.domain.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 逐条导入示例
 *
 * @author shuangfei.zhu@hand-china.com 2020/01/06 10:00
 */
@ImportService(templateCode = "SIMPLE_USER_IMPORT")
public class ImportServiceImpl implements IDoImportService {

    private static Logger logger = LoggerFactory.getLogger(ImportServiceImpl.class);

    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;

    @Autowired
    public ImportServiceImpl(ObjectMapper objectMapper,
                             UserRepository userRepository) {
        this.objectMapper = objectMapper;
        this.userRepository = userRepository;
    }

    @Override
    public Boolean doImport(String data) {
        User user;
        try {
            user = objectMapper.readValue(data, User.class);
        } catch (Exception e) {
            logger.error("data error");
            return false;
        }
        userRepository.insertSelective(user);
        return true;
    }

    @Override
    public void onStart() {
        logger.info("import start");
    }

    @Override
    public void onFinish() {
        logger.info("import finish");
    }
}
