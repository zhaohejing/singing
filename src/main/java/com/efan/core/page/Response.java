package com.efan.core.page;

import java.io.Serializable;
import java.security.PublicKey;
import java.util.List;
import java.util.Map;

/**
 * Created by 45425 on 2017/5/12.
 */
public class Response implements Serializable {
    public Integer code;
    public String message;
    public List<Map<String,Object>> respBody;
}
