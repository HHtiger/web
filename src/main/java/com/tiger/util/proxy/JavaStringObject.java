package com.tiger.util.proxy;

import javax.tools.SimpleJavaFileObject;
import java.net.URI;

/**
 * Package: com.tiger.core.util.proxy
 * ClassName: aa
 * Author: he_hu@founder.com.cn
 * Description:
 * CreateDate: 2016/4/7
 * Version: 1.0
 */

public class JavaStringObject extends SimpleJavaFileObject {
    /**
     * The source code of this "file".
     */
    final String code;

    /**
     * Constructs a new JavaSourceFromString.
     * @param name the name of the compilation unit represented by this file object
     * @param code the source code for the compilation unit represented by this file object
     */
    JavaStringObject(String name, String code) {
        super(URI.create("string:///" + name.replace('.','/') + Kind.SOURCE.extension),
                Kind.SOURCE);
        this.code = code;
    }

    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors) {
        return code;
    }
}