package cn.healthmall.sail.mgmt.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * Created By User: RXK
 * Date: 2017/10/11
 * Time: 21:41
 * Version: V1.0
 * Description: 从请求中获取 数据
 */
public class HttpHelper
{
	private static final Logger LOGGER = LoggerFactory.getLogger(HttpHelper.class);

	public static String getBodyString(ServletRequest request)
	{
		LOGGER.info("inputStream ----> 获取中");
		StringBuilder sb = new StringBuilder();
		InputStream inputStream = null;
		BufferedReader reader = null;
		try
		{
			inputStream = request.getInputStream();
			reader = new BufferedReader(new InputStreamReader(inputStream, Charset.forName("UTF-8")));
			String line;
			while ((line = reader.readLine()) != null)
			{
				sb.append(line);
			}
		} catch (IOException e)
		{
			LOGGER.info("获取数据失败：{}", e.getMessage());
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		LOGGER.info("获取参数完成：{}");
		return sb.toString();
	}
}
