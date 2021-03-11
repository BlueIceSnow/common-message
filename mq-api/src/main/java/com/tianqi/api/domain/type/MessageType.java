package com.tianqi.api.domain.type;

/**
 * 消息类型
 *
 * @author yuantianqi
 */
public enum MessageType {
    /**
     * 简单消息
     */
    SIMPLE,
    /**
     * 确认消息
     */
    CONFIRM,
    /**
     * 可靠消息
     */
    RELIABLE,
    /**
     * 延迟消息
     */
    DELAY
}