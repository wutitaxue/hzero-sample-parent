package org.hzero.samples.core.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;

import io.choerodon.core.exception.ExceptionResponse;

import org.hzero.core.util.Results;

/**
 * Controller 方法中封装返回结果
 *
 * @author bojiangzhou 2020/01/20
 */
public class ResultsDemo {

    public ResponseEntity<Void> demo() {

        // 返回空，状态码204
        ResponseEntity<Void> result1 = Results.success();

        // 返回非空，状态码200
        ResponseEntity<List> result2 = Results.success(new ArrayList<>());

        // 返回失败，状态码500
        ResponseEntity<Void> result3 = Results.error();

        // 返回失败，并返回失败信息，状态码500
        ResponseEntity<ExceptionResponse> result4 = Results.error(new ExceptionResponse());

        // 返回前端错误，状态码400
        ResponseEntity<Void> result5 = Results.invalid();

        return result1;
    }

}
