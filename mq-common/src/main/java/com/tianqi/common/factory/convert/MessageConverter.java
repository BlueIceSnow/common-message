package com.tianqi.common.factory.convert;

import com.tianqi.common.factory.serialize.Serialize;

/**
 * 消息转换抽象类
 *
 * @author yuantianqi
 */
public abstract class MessageConverter {

    protected Serialize serialize;

    public MessageConverter(Serialize serialize) {
        this.serialize = serialize;
    }
}
