package org.yuanhong.li.wealth.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.yuanhong.li.wealth.filter.UserContextFilter;


/**n
 * 此处配置filter、servlet、listener
 * @author yuanhong.li
 *
 */
@Configuration
public class BaseConfig {
	
	
	/**=================filter配置开始=================**/
	/**
	 * 只有继承javax.servlet.Filter的Filter需要在这里注册
	 */
	/**
	 * 配置characterEncodingFilter
	 */
	@Bean
	public FilterRegistrationBean characterEncodingFilter() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		registrationBean.setFilter(characterEncodingFilter);
		registrationBean.setOrder(0);
		registrationBean.setName("Set Character Encoding");
		registrationBean.addUrlPatterns("/*");
		return registrationBean;
	}
	
	/**
	 * 配置AuthorityFilter
	 * @return
	 * yuanhong.li
	 */
//	@Bean
//	public FilterRegistrationBean authorityFilter() {
//		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//		AuthorityFilter authorityFilter = new AuthorityFilter();
//		registrationBean.setFilter(authorityFilter);
//		registrationBean.setOrder(1);
//		registrationBean.addUrlPatterns("/*");
//		return registrationBean;
//	}
	
	/**
	 * 配置UserContextFilter
	 * @return
	 * yuanhong.li
	 */
	@Bean
	public FilterRegistrationBean userContextFilter() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		UserContextFilter userContextFilter = new UserContextFilter();
		registrationBean.setFilter(userContextFilter);
		registrationBean.setOrder(1);
		registrationBean.addUrlPatterns("/*");
		return registrationBean;
	}

}
