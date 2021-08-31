package org.hzero.sample.domain.service;

import org.hzero.starter.file.service.AbstractFileService;
import org.hzero.starter.file.service.StoreCreator;

/**
 * description
 *
 * @author shuangfei.zhu@hand-china.com 2020/01/17 9:25
 */
public class MinioStoreCreator implements StoreCreator {

    @Override
    public Integer storeType() {
        return 3;
    }

    @Override
    public AbstractFileService getFileService() {
        return new MinioFileServiceImpl();
    }
}
