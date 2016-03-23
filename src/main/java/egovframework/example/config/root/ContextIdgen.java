package egovframework.example.config.root;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import egovframework.rte.fdl.idgnr.impl.EgovTableIdGnrServiceImpl;
import egovframework.rte.fdl.idgnr.impl.strategy.EgovIdGnrStrategyImpl;

@Configuration
public class ContextIdgen {

	@Bean(destroyMethod="destroy")
	public EgovTableIdGnrServiceImpl egovIdGnrService(DataSource dataSource, EgovIdGnrStrategyImpl egovIdGnrStrategyImpl){
		EgovTableIdGnrServiceImpl egovTableIdGnrServiceImpl = new EgovTableIdGnrServiceImpl();
		egovTableIdGnrServiceImpl.setDataSource(dataSource);
		egovTableIdGnrServiceImpl.setStrategy(egovIdGnrStrategyImpl);
		egovTableIdGnrServiceImpl.setBlockSize(10);
		egovTableIdGnrServiceImpl.setTable("IDS");
		egovTableIdGnrServiceImpl.setTableName("SAMPLE");
		return egovTableIdGnrServiceImpl;
	}
	
	@Bean
	public EgovIdGnrStrategyImpl mixPrefixSample(){
		EgovIdGnrStrategyImpl egovIdGnrStrategyImpl = new EgovIdGnrStrategyImpl();
		egovIdGnrStrategyImpl.setPrefix("SAMPLE-");
		egovIdGnrStrategyImpl.setCipers(5);
		egovIdGnrStrategyImpl.setFillChar('0');
		return egovIdGnrStrategyImpl;
	}
}
