package org.hzero.sample.infra.util;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

/**
 * description
 *
 * @author shuangfei.zhu@hand-china.com 2020/01/13 14:57
 */
public class ResponseUtils {

    private ResponseUtils(){}

    public static void buildResponse(HttpServletResponse response, String filename, byte[] data) throws IOException {
        response.reset();
        response.setHeader("Content-Disposition", "attachment;filename=" + filename);
        response.setContentType("application/octet-stream");
        response.addHeader("Content-Length", "" + data.length);
        response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
        response.setHeader("Pragma", "public");
        response.setDateHeader("Expires", (System.currentTimeMillis() + 1000));
        IOUtils.write(data, response.getOutputStream());
    }
}
