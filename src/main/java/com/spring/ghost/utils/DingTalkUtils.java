package com.spring.ghost.utils;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.taobao.api.ApiException;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DingTalkUtils {

    private static String envToken;
    private static final String baseUrl = "https://oapi.dingtalk.com/robot/send?access_token=%s";

    static {
        Map<String, String> env = System.getenv();
        String defaultToken = "9d6da20b7e3e596c660b5b6379a2e10f962b823d076c11bbfea3f393bfdcb1cd";
        envToken = env.getOrDefault("DING_TOKEN", defaultToken);
    }

    public static TypeBuilder with(String token) {
        return new TypeBuilder(token);
    }

    public static TypeBuilder with() {
        return with(envToken);
    }

    public static class TypeBuilder {

        private String serviceUrl;
        private OapiRobotSendRequest.Link link;
        private OapiRobotSendRequest.Text text;
        private OapiRobotSendRequest.Markdown markdown;
        private OapiRobotSendRequest.Actioncard actionCard;
        private OapiRobotSendRequest.Feedcard feedCard;

        TypeBuilder(String token) {
            this.serviceUrl = String.format(baseUrl, token);
        }

        public Builder setLink(Link link) {
            this.link = ClassMergeUtils.copyProperties(OapiRobotSendRequest.Link.class, link);
            return new Builder();
        }

        public Builder setText(Text text) {
            this.text = ClassMergeUtils.copyProperties(OapiRobotSendRequest.Text.class, text);
            return new Builder();
        }

        public Builder setMarkdown(Markdown markdown) {
            this.markdown = ClassMergeUtils.copyProperties(OapiRobotSendRequest.Markdown.class, markdown);
            return new Builder();
        }

        public Builder setActionCard(ActionCard actionCard) {
            this.actionCard = ClassMergeUtils.copyProperties(OapiRobotSendRequest.Actioncard.class, actionCard);
            return new Builder();
        }

        public Builder setFeedCard(FeedCard feedCard) {
            this.feedCard = ClassMergeUtils.copyProperties(OapiRobotSendRequest.Feedcard.class, feedCard);
            return new Builder();
        }

        public class Builder {

            private OapiRobotSendRequest.At at;

            Builder() {
                at = new OapiRobotSendRequest.At();
            }

            public Builder setAtMobiles(List<String> atMobiles) {
                this.at.setAtMobiles(atMobiles);
                return this;
            }

            public DingResponse build() {
                DingTalkClient client = new DefaultDingTalkClient(serviceUrl);
                OapiRobotSendRequest request = new OapiRobotSendRequest();
                if (link != null) {
                    request.setLink(link);
                    request.setMsgtype(Type.link.getName());
                } else if (text != null) {
                    request.setText(text);
                    request.setMsgtype(Type.text.getName());
                } else if (markdown != null) {
                    request.setMarkdown(markdown);
                    request.setMsgtype(Type.markdown.getName());
                } else if (actionCard != null) {
                    request.setActionCard(actionCard);
                    request.setMsgtype(Type.actionCard.getName());
                } else if (feedCard != null) {
                    request.setFeedCard(feedCard);
                    request.setMsgtype(Type.feedCard.getName());
                } else {
                    throw new NullPointerException("ding talk message type error");
                }
                try {
                    OapiRobotSendResponse resp = client.execute(request);
                    return ClassMergeUtils.copyProperties(DingResponse.class, resp);
                } catch (ApiException e) {
                    e.printStackTrace();
                }
                return new DingResponse();
            }
        }
    }


    public static class Text {
        private String content;

        public Text() {
        }

        public Text(String content) {
            this.content = content;
        }

        public Text setContent(String content) {
            this.content = content;
            return this;
        }
    }

    public static class Link {

        private String messageUrl;
        private String picUrl;
        private String text;
        private String title;

        public Link setMessageUrl(String messageUrl) {
            this.messageUrl = messageUrl;
            return this;
        }

        public Link setPicUrl(String picUrl) {
            this.picUrl = picUrl;
            return this;
        }


        public Link setText(String text) {
            this.text = text;
            return this;
        }

        public Link setTitle(String title) {
            this.title = title;
            return this;
        }
    }

    public static class Markdown {

        private String text;
        private String title;

        public Markdown setText(String text) {
            this.text = text;
            return this;
        }

        public Markdown setTitle(String title) {
            this.title = title;
            return this;
        }
    }

    public static class Btns {

        private String actionURL;
        private String title;

        public Btns setActionURL(String actionURL) {
            this.actionURL = actionURL;
            return this;
        }


        public Btns setTitle(String title) {
            this.title = title;
            return this;
        }
    }

    public static class ActionCard {

        private String btnOrientation;
        private List<OapiRobotSendRequest.Btns> btns;
        private String hideAvatar;
        private String singleTitle;
        private String singleURL;
        private String title;
        private String text;

        public ActionCard setBtnOrientation(String btnOrientation) {
            this.btnOrientation = btnOrientation;
            return this;
        }


        public ActionCard setBtns(List<Btns> btns) {
            this.btns = new ArrayList<>();
            for (Btns btn : btns) {
                OapiRobotSendRequest.Btns tempBtn = new OapiRobotSendRequest.Btns();
                BeanUtils.copyProperties(btn, tempBtn);
                this.btns.add(tempBtn);
            }
            return this;
        }


        public ActionCard setHideAvatar(String hideAvatar) {
            this.hideAvatar = hideAvatar;
            return this;
        }


        public ActionCard setSingleTitle(String singleTitle) {
            this.singleTitle = singleTitle;
            return this;
        }


        public ActionCard setSingleURL(String singleURL) {
            this.singleURL = singleURL;
            return this;
        }


        public ActionCard setText(String text) {
            this.text = text;
            return this;
        }

        public ActionCard setTitle(String title) {
            this.title = title;
            return this;
        }
    }

    public static class FeedCard {
        private List<OapiRobotSendRequest.Links> links;

        public FeedCard setLinks(List<Links> links) {
            this.links = new ArrayList<>();
            for (Links link : links) {
                OapiRobotSendRequest.Links tempLinks = new OapiRobotSendRequest.Links();
                BeanUtils.copyProperties(link, tempLinks);
                this.links.add(tempLinks);
            }
            return this;
        }
    }

    public static class Links {
        private String messageURL;
        private String picURL;
        private String title;

        public Links setMessageURL(String messageURL) {
            this.messageURL = messageURL;
            return this;
        }

        public Links setPicURL(String picURL) {
            this.picURL = picURL;
            return this;
        }

        public Links setTitle(String title) {
            this.title = title;
            return this;
        }
    }

    public static class DingResponse {

        private Long errcode = -1L;
        private String errmsg;

        public Long getErrcode() {
            return this.errcode;
        }

        public String getErrmsg() {
            return this.errmsg;
        }

        public boolean isSuccess() {
            return this.getErrcode() == null || this.getErrcode().equals(0L);
        }
    }

    enum Type {
        text, link, markdown, actionCard, feedCard;

        public String getName() {
            return this.name().toLowerCase();
        }
    }
}
