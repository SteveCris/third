package com.shangyong.thjdq.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * GzipTool
 *
 * @author hejiangshan on 2018年12月19日
 * @version 1.0
 */
public class GzipTool {

    /**
     * gzip compress
     *
     * @param bytes origin bytes
     *
     * @return bytes compressed
     */
    public static byte[] compress(byte[] bytes) throws IOException {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            try (GZIPOutputStream gzip = new GZIPOutputStream(bos)) {
                gzip.write(bytes);
                gzip.finish();
            }
            return bos.toByteArray();
        }
    }

    /**
     * gzip uncompress
     *
     * @param bytes origin bytes
     *
     * @return bytes uncompressed
     */
    public static byte[] unCompress(byte[] bytes) throws IOException {
        try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes)) {
            try (GZIPInputStream gzip = new GZIPInputStream(bis)) {
                byte[] buf = new byte[1024];
                int num = -1;
                try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
                    while ((num = gzip.read(buf, 0, buf.length)) != -1) {
                        bos.write(buf, 0, num);
                    }
                    bos.flush();
                    return bos.toByteArray();
                }
            }
        }
    }

}
