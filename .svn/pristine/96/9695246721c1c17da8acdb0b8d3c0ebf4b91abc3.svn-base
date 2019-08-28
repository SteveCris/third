package com.shangyong.thryt.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;

import com.shangyong.thryt.enums.RytResultEnum;
import com.shangyong.thryt.exception.CalfException;

public class FileUtil {

	public static String readeJsonWithNet(String url) {
		try {
			return readeJsonString(new URL(url).openStream());
		} catch (IOException e) {
			throw new CalfException("IOException", e, RytResultEnum.IO_EXCEPTION);
		}
	}

	private static String readeJsonString(InputStream inputStream) throws IOException {
		try (LineIterator lineIterator = IOUtils.lineIterator(inputStream, Charset.forName("UTF-8"));) {
			StringBuilder sb = new StringBuilder();
			while (lineIterator.hasNext()) {
				sb.append(lineIterator.next());
			}
			return sb.toString();
		}
	}

}
