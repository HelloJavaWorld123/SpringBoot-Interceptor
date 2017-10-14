package cn.healthmall.sail.mgmt.config;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;
import java.nio.charset.Charset;

/**
 * Created By User: RXK
 * Date: 2017/10/11
 * Time: 11:21
 * Version: V1.0
 * Description:
 */
public class RequestWrapper extends HttpServletRequestWrapper
{
	private final byte[] body;

	public RequestWrapper(HttpServletRequest request) throws IOException {
		super(request);
		body = HttpHelper.getBodyString(request).getBytes(Charset.forName("UTF-8"));
	}

	@Override
	public BufferedReader getReader() throws IOException {
		return new BufferedReader(new InputStreamReader(getInputStream()));
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		final ByteArrayInputStream bais = new ByteArrayInputStream(body);
		return new ServletInputStream() {

			@Override
			public boolean isFinished()
			{
				return false;
			}

			@Override
			public boolean isReady()
			{
				return false;
			}

			@Override
			public void setReadListener(ReadListener readListener)
			{

			}

			@Override
			public int read() throws IOException {
				return bais.read();
			}
		};
	}

}
