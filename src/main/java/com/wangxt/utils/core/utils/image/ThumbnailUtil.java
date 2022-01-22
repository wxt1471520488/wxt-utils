package com.wangxt.utils.core.utils.image;

import net.coobird.thumbnailator.Thumbnails;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/*
 * @author wangxt
 * @description 
 * @date 2022/1/22 16:10
 **/
public class ThumbnailUtil {

    /**
     * @param bytes 源文件流
     * @return byte[]
     * @author wangxt
     * @description 压缩图片到指定宽高
     * @date 2022/1/22 16:14
     * @throws IOException
     **/
    public static byte[] resize(byte[] bytes, int width, int height) throws IOException {
        try (ByteArrayInputStream is = new ByteArrayInputStream(bytes);
             ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            Thumbnails.of(is).size(width, height).toOutputStream(os);
            return os.toByteArray();
        }
    }

}
