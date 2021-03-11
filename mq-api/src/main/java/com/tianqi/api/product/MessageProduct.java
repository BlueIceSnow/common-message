package com.tianqi.api.product;

import com.tianqi.api.domain.callback.SendCallback;
import com.tianqi.api.domain.message.Message;

/**
 * 消息生产者接口
 *
 * @author yuantianqi
 */
public interface MessageProduct {

    /**
     * 发送简单消息
     *
     * @param message 消息体
     */
    void sendMessage(Message message);

    /**
     * 发送确认消息
     *
     * @param message  消息体
     * @param callback 消息回调
     */
    void sendConfirmMessage(Message message, SendCallback callback);

    /**
     * 发送可靠消息
     *
     * @param message 消息体
     */
    void sendReliableMessage(Message message);

    /**
     * 发送延迟消息
     * @param message
     */
    void sendDelayMessage(Message message);

}
