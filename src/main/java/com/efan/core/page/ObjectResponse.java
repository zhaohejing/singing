package com.efan.core.page;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


public class ObjectResponse implements Serializable {
    public Integer code;
    public String message;
    public Object respBody;
}