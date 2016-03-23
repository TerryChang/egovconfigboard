package egovframework.example.config.root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springmodules.validation.commons.DefaultBeanValidator;
import org.springmodules.validation.commons.DefaultValidatorFactory;
import org.springmodules.validation.commons.ValidatorFactory;

@Configuration
public class ContextValidator {
	
	@Autowired
	ApplicationContext ac;
	
	@Bean
	public DefaultBeanValidator beanValidator(ValidatorFactory validatorFactory){
		DefaultBeanValidator defaultBeanValidator = new DefaultBeanValidator();
		defaultBeanValidator.setValidatorFactory(validatorFactory);
		return defaultBeanValidator;
	}
	
	@Bean
	public DefaultValidatorFactory validatorFactory(){
		DefaultValidatorFactory defaultValidatorFactory = new DefaultValidatorFactory();
		
		defaultValidatorFactory.setValidationConfigLocations(new Resource[]{
				ac.getResource("/WEB-INF/config/egovframework/validator/validator-rules.xml")
				, ac.getResource("/WEB-INF/config/egovframework/validator/validator.xml")
			});
		
		return defaultValidatorFactory;
	}
}