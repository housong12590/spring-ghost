package com.spring.ghost.bean;


import javax.validation.constraints.NotEmpty;

public class DockerImage {

    @NotEmpty(message = "镜像名不能为空")
    private String imageName;
    @NotEmpty(message = "镜像地址不能为空")
    private String pullAddress;
    @NotEmpty(message = "镜像TAG不能为空")
    private String imageTag;
    @NotEmpty(message = "git分支不能为空")
    private String gitBranch;
    private String gitMessage;
    private String codeRegistry;
    private String host;
    private String port;
    private String command;

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getPullAddress() {
        return pullAddress;
    }

    public void setPullAddress(String pullAddress) {
        this.pullAddress = pullAddress;
    }

    public String getImageTag() {
        return imageTag;
    }

    public void setImageTag(String imageTag) {
        this.imageTag = imageTag;
    }

    public String getGitBranch() {
        return gitBranch;
    }

    public void setGitBranch(String gitBranch) {
        this.gitBranch = gitBranch;
    }

    public String getGitMessage() {
        return gitMessage;
    }

    public void setGitMessage(String gitMessage) {
        this.gitMessage = gitMessage;
    }

    public String getCodeRegistry() {
        return codeRegistry;
    }

    public void setCodeRegistry(String codeRegistry) {
        this.codeRegistry = codeRegistry;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    @Override
    public String toString() {
        return "DockerImage{" +
                "imageName='" + imageName + '\'' +
                ", pullAddress='" + pullAddress + '\'' +
                ", imageTag='" + imageTag + '\'' +
                ", gitBranch='" + gitBranch + '\'' +
                ", gitMessage='" + gitMessage + '\'' +
                ", codeRegistry='" + codeRegistry + '\'' +
                ", host='" + host + '\'' +
                ", port='" + port + '\'' +
                ", command='" + command + '\'' +
                '}';
    }
}
