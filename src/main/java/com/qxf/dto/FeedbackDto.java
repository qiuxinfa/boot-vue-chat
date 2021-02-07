package com.qxf.dto;

/**
 * @ClassName FeedbackDto
 * @Description 用户反馈
 * @Author qiuxinfa
 * @Date 2021/2/6 18:14
 **/
public class FeedbackDto {
    // 反馈用户id
    private String userId;
    // 反馈内容
    private String content;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
