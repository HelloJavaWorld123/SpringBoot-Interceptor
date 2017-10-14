package cn.healthmall.sail.mgmt.config;

import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created By User: RXK
 * Date: 2017/10/11
 * Time: 21:17
 * Version: V1.0
 * Description:用户request的再次包装
 */
@Order(1)
@WebFilter(displayName = "RequestHttpFilter", urlPatterns = "/*")
public class RequestHttpFilter implements Filter
{
	@Override
	public void init(FilterConfig filterConfig)
			throws ServletException
	{
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException
	{
		RequestWrapper requestWrapper = null;
		if (request instanceof HttpServletRequest)
		{
			requestWrapper = new RequestWrapper((HttpServletRequest) request);
			if (null != requestWrapper)
			{
				chain.doFilter(requestWrapper, response);
			} else
			{
				chain.doFilter(request, response);
			}
		}
	}

	@Override
	public void destroy()
	{
	}
}
