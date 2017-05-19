package com.efan.controller;

import com.efan.utils.HttpUtils;
import com.qiniu.util.Auth;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@RestController
@RequestMapping("/api/qiniu")
@EnableSwagger2
public class QiniuController {
    @ApiOperation(value="获取气牛token", notes="七牛接口")
    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public ModelMap token(){
        String accessKey = "o_qxXubM6dRAw_VHd5UqDoaRsAZpB0kGeJeg9AQe";
        String secretKey = "XAQWoZ_tCokwcUobnP9ntJN2bjju5xlCl9vxicne";
        String bucket = "resource";
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);

        ModelMap result = new ModelMap();

        if(upToken != null){
            result.put("result","1");
            result.put("errorMsg","获取成功");
            result.put("data",upToken);
        }else {
            result.put("result", "0");
            result.put("errorMsg", "获取失败");
        }
        return result;
    }
    @ApiOperation(value="获取微信token", notes="微信接口")
    @RequestMapping(value = "/getWxToken", method = RequestMethod.POST)
    public String getWxToken(@RequestParam String code){
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx734728844b17a945&secret=b21df0dbd7639790820b545e584e82db&code="+code+"&grant_type=authorization_code";
        String result = HttpUtils.sendPost(url,"");
        return result;
    }
    @ApiOperation(value="获取用户基本信息", notes="微信接口")
    @RequestMapping(value = "/getuserinfo", method = RequestMethod.POST)
    public String getUserInfo(@RequestParam String token, @RequestParam String openId){
        String url = "https://api.weixin.qq.com/sns/userinfo?access_token="+token+"&openid="+openId+"&lang=zh_CN";
        String result = HttpUtils.sendPost(url,"");
        return result;
    }


}
