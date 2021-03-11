package com.tianqi.api.domain.callback;

/**
 * 消息发送回调
 *
 * @author yuantianqi
 */
public interface SendCallback {
    /**
     * 消息发送成功回调
     */
    void onSuccess(String messageId, String cause);

    /**
     * 消息发送失败
     */
    void onFail(String messageId, String cause);
}
