package egovframework.example.config.servlet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import egovframework.example.cmmn.web.EgovBindingInitializer;
import egovframework.example.cmmn.web.EgovImgPaginationRenderer;
import egovframework.rte.ptl.mvc.tags.ui.pagination.DefaultPaginationManager;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationRenderer;

@Configuration
@ComponentScan(
		basePackages="egovframework",
		includeFilters={
			@ComponentScan.Filter(type=FilterType.ANNOTATION, value=Controller.class)	
		},
		excludeFilters={
			@ComponentScan.Filter(type=FilterType.ANNOTATION, value=Service.class)
			, @ComponentScan.Filter(type=FilterType.ANNOTATION, value=Repository.class)
			, @ComponentScan.Filter(type=FilterType.ANNOTATION, value=Configuration.class)
		}
)
public class ServletContext extends WebMvcConfigurationSupport {
	
	@Bean
	@Override
	public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
		// TODO Auto-generated method stub
		RequestMappingHandlerAdapter rmha = super.requestMappingHandlerAdapter();
		rmha.setWebBindingInitializer(new EgovBindingInitializer());
		return rmha;
	}

	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}

	@Bean
	public SessionLocaleResolver localeResolver(){
		SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
		return sessionLocaleResolver;
	}
	
	/*
	// 쿠키를 이용한 Locale 이용시 이 부분을 주석처리를 풀어서 사용하고 위에 있는 SessionLocaleResolver 클래스를 사용하는
	// @Bean 메소드는 주석처리 한다
	@Bean
	public CookieLocaleResolver localeResolver(){
		CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
		return cookieLocaleResolver;
	} 
	 */
    
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor(){
    	LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
    	localeChangeInterceptor.setParamName("language");
    	return localeChangeInterceptor;
    }
    
    
    /**
     * WebMvcConfigurationSupport 클래스의 handlerExceptionResolver 메소드(@Bean 어노테이션이 선언되어 있기 때문에
     * Spring Bean을 등록하는 메소드이다)에서 파라미터로 넘긴 exceptionResolvers에 들어있는
     * ExceptionResolver들을 모두 관리하는 HandlerExceptionResolverComposite 클래스를 Spring Bean으로 등록한다
     * 그렇기땜에 특정 exceptionResolvers에 자신이 사용하고자 하는 ExceptionResolver들을 넣으면 된다
     * 만약 이 작업을 하지 않으면 WebMvcConfigurationSupport 클래스의 handlerExceptionResolver 메소드는
     * ExceptionHandlerExceptionResolver 클래스 객체와 ResponseStatusExceptionResolver 클래스 객체만을 
     * ExceptionResolver로 사용한다
     */
    @Override
	protected void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
		// TODO Auto-generated method stub
    	SimpleMappingExceptionResolver smer = new SimpleMappingExceptionResolver();
    	smer.setDefaultErrorView("cmmn/egovError");
    	Properties mappings = new Properties();
    	mappings.setProperty("org.springframework.dao.DataAccessException", "cmmn/dataAccessFailure");
    	mappings.setProperty("org.springframework.transaction.TransactionException", "cmmn/transactionFailure");
    	mappings.setProperty("egovframework.rte.fdl.cmmn.exception.EgovBizException", "cmmn/egovError");
    	mappings.setProperty("org.springframework.security.AccessDeniedException", "cmmn/egovError");
    	smer.setExceptionMappings(mappings);
    	exceptionResolvers.add(smer);
	}
    
    @Bean
    public UrlBasedViewResolver urlBasedViewResolver(){
    	UrlBasedViewResolver urlBasedViewResolver = new UrlBasedViewResolver();
    	urlBasedViewResolver.setOrder(1);
    	urlBasedViewResolver.setViewClass(JstlView.class);
    	urlBasedViewResolver.setPrefix("/WEB-INF/jsp/egovframework/example/");
    	urlBasedViewResolver.setSuffix(".jsp");
    	return urlBasedViewResolver;
    }

    @Bean
    public EgovImgPaginationRenderer imageRenderer(){
    	EgovImgPaginationRenderer egovImgPaginationRenderer = new EgovImgPaginationRenderer();
    	return egovImgPaginationRenderer;
    }
    
    @Bean
    public DefaultPaginationManager paginationManager(EgovImgPaginationRenderer imageRenderer){
    	DefaultPaginationManager defaultPaginationManager = new DefaultPaginationManager();
    	Map<String, PaginationRenderer> rendererType = new HashMap<String, PaginationRenderer>();
    	rendererType.put("image", imageRenderer);
    	defaultPaginationManager.setRendererType(rendererType);
    	return defaultPaginationManager;
    }
	
    @Override
	protected void addViewControllers(ViewControllerRegistry registry) {
		// TODO Auto-generated method stub
		registry.addViewController("/cmmn/validator.do").setViewName("cmmn/validator");
	}

}
