package org.hzero.sample.infra.repository.impl;

import org.hzero.sample.domain.entity.User;
import org.hzero.sample.domain.repository.UserRepository;
import org.hzero.mybatis.base.impl.BaseRepositoryImpl;
import org.springframework.stereotype.Component;

/**
 * description
 *
 * @author shuangfei.zhu@hand-china.com 2020/01/06 10:32
 */
@Component
public class UserRepositoryImpl extends BaseRepositoryImpl<User> implements UserRepository {

}
