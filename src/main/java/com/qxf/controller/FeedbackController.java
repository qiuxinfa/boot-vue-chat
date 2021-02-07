package com.qxf.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.qxf.dto.FeedbackDto;
import com.qxf.entity.SysUser;
import com.qxf.service.SysUserService;
import com.qxf.util.MailUtil;
import com.qxf.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName FeedbackController
 * @Description TODO
 * @Author qiuxinfa
 * @Date 2021/2/6 18:16
 **/
@RestController
@RequestMapping("/feedback")
public class FeedbackController {
    @Autowired
    private MailUtil mailUtil;

    @Autowired
    private SysUserService userService;

    // 提交反馈内容
    @PostMapping("/send")
    public ResultUtil sendFeedback(@RequestBody FeedbackDto dto){
        SysUser user = userService.getById(dto.getUserId());
        if (user == null || StringUtils.isEmpty(user.getEmail())){
            return ResultUtil.error("发送失败");
        }
        mailUtil.sendFeedback(user.getEmail(),user.getUsername()+"，反馈："+dto.getContent());
        return ResultUtil.error("发送成功");
    }
}
