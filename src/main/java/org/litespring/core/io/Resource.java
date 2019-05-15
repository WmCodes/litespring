package org.litespring.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author wangmeng
 * @date 2019/5/14
 * @desciption
 */
public interface Resource {

    public InputStream getInputStream() throws IOException;
    public String getDescription();

}
