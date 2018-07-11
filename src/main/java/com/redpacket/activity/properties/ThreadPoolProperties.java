package com.redpacket.activity.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "my.threadPool")
public class ThreadPoolProperties {

    private int corePoolSize;
    private int maximumPoolSize;
    private long keepAliveTime;
    private int queueLength;

    private int receiveCorePoolSize;
    private int receiveMaximumPoolSize;
    private int receiveKeepAliveTime;
    private int receiveQueueLength;

    private int sendCorePoolSize;
    private int sendMaximumPoolSize;
    private int sendKeepAliveTime;
    private int sendQueueLength;

    public int getCorePoolSize() {
        return corePoolSize;
    }

    public void setCorePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
    }

    public int getMaximumPoolSize() {
        return maximumPoolSize;
    }

    public void setMaximumPoolSize(int maximumPoolSize) {
        this.maximumPoolSize = maximumPoolSize;
    }

    public long getKeepAliveTime() {
        return keepAliveTime;
    }

    public void setKeepAliveTime(long keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
    }

    public int getQueueLength() {
        return queueLength;
    }

    public void setQueueLength(int queueLength) {
        this.queueLength = queueLength;
    }

    public int getReceiveCorePoolSize() {
        return receiveCorePoolSize;
    }

    public void setReceiveCorePoolSize(int receiveCorePoolSize) {
        this.receiveCorePoolSize = receiveCorePoolSize;
    }

    public int getReceiveMaximumPoolSize() {
        return receiveMaximumPoolSize;
    }

    public void setReceiveMaximumPoolSize(int receiveMaximumPoolSize) {
        this.receiveMaximumPoolSize = receiveMaximumPoolSize;
    }

    public int getReceiveKeepAliveTime() {
        return receiveKeepAliveTime;
    }

    public void setReceiveKeepAliveTime(int receiveKeepAliveTime) {
        this.receiveKeepAliveTime = receiveKeepAliveTime;
    }

    public int getReceiveQueueLength() {
        return receiveQueueLength;
    }

    public void setReceiveQueueLength(int receiveQueueLength) {
        this.receiveQueueLength = receiveQueueLength;
    }

    public int getSendCorePoolSize() {
        return sendCorePoolSize;
    }

    public void setSendCorePoolSize(int sendCorePoolSize) {
        this.sendCorePoolSize = sendCorePoolSize;
    }

    public int getSendMaximumPoolSize() {
        return sendMaximumPoolSize;
    }

    public void setSendMaximumPoolSize(int sendMaximumPoolSize) {
        this.sendMaximumPoolSize = sendMaximumPoolSize;
    }

    public int getSendKeepAliveTime() {
        return sendKeepAliveTime;
    }

    public void setSendKeepAliveTime(int sendKeepAliveTime) {
        this.sendKeepAliveTime = sendKeepAliveTime;
    }

    public int getSendQueueLength() {
        return sendQueueLength;
    }

    public void setSendQueueLength(int sendQueueLength) {
        this.sendQueueLength = sendQueueLength;
    }

    @Override
    public String toString() {
        return "ThreadPoolProperties{" +
                "corePoolSize=" + corePoolSize +
                ", maximumPoolSize=" + maximumPoolSize +
                ", keepAliveTime=" + keepAliveTime +
                ", queueLength=" + queueLength +
                '}';
    }
}
