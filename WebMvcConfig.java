package cn.healthmall.sail.mgmt.config;

import cn.healthmall.sail.mgmt.interceptor.MgmtOperateLogInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	private static final Logger logger = LoggerFactory.getLogger(WebMvcConfig.class);

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		 registry.addResourceHandler("/**").addResourceLocations("classpath:/webStatic/");
	}

	/**
	 * 添加自定义实现的拦截器以及拦截的路径
	 * @param registry
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(getUserVerifyIntercptor()).addPathPatterns("/**")
//				.excludePathPatterns("/api/sailMgmt/bodyReportView/addByMaill");
//		super.addInterceptors(registry);
		registry.addInterceptor(getMgmtOptionalLogInterceptor()).addPathPatterns("/**");
	}

	/**
	 * 必须将次类交由Spring 管理
	 * @return
	 */
	@Bean
	public MgmtOperateLogInterceptor getMgmtOptionalLogInterceptor(){
		return new MgmtOperateLogInterceptor();
	}


}
