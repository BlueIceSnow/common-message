package com.tianqi.common.factory.serialize;

/**
 * 序列化
 * @author yuantianqi
 */
public interface Serialize {

    /**
     * 对象=》序列化为数组
     * @param data
     * @return
     */
    byte[] serializeRaw(Object data);

    /**
     * 对象=》序列化为字符串
     * @param data
     * @return
     */
    String serialize(Object data);

    /**
     * 字符串=》序列化成对象
     * @param content
     * @param <T>
     * @return
     */
    <T> T deserialize(String content);

    /**
     * 将字节数组=》序列化成对象
     * @param content
     * @param <T>
     * @return
     */
    <T> T deserialize(byte[] content);
}
