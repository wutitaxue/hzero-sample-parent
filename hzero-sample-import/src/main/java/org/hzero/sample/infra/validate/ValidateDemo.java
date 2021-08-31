package org.hzero.sample.infra.validate;

import org.hzero.boot.imported.app.service.ValidatorHandler;
import org.hzero.boot.imported.infra.validator.annotation.ImportValidator;
import org.hzero.boot.imported.infra.validator.annotation.ImportValidators;

/**
 * 自定义数据校验逻辑
 *
 * @author xiaoyu.zhao@hand-china.com 2020/01/14
 */
@ImportValidators({
        @ImportValidator(templateCode = "SIMPLE_USER_IMPORT")
})
public class ValidateDemo extends ValidatorHandler {

    @Override
    public boolean validate(String data) {
        System.out.println("自定义数据校验逻辑......");
        return true;
    }
}
