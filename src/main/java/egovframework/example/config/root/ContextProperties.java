package egovframework.example.config.root;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import egovframework.rte.fdl.cmmn.exception.FdlException;
import egovframework.rte.fdl.property.impl.EgovPropertyServiceImpl;

@Configuration
public class ContextProperties {

	@Bean(destroyMethod="destroy")
	public EgovPropertyServiceImpl propertiesService() throws FdlException{
		Map<String, String> properties = new HashMap<String, String>();
		properties.put("pageUnit", "10");
		properties.put("pageSize", "10");
		
		EgovPropertyServiceImpl egovPropertyServiceImpl = new EgovPropertyServiceImpl();
		egovPropertyServiceImpl.setProperties(properties);
		
		return egovPropertyServiceImpl;
		
	}
}
