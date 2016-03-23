package egovframework.example.config.root;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.lookup.DataSourceLookupFailureException;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.jndi.JndiTemplate;

@Configuration
public class ContextDataSource {

	@Bean
	public DataSource dataSource(){
		return new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.HSQL)
				.addScript("classpath:db/sampledb.sql")
				.build();
	}
	
	/*
	@Bean(destroyMethod="close")
	public DataSource dataSource(){
		
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		basicDataSource.setUrl("jdbc:oracle:thin:@127.0.0.1:1521:example");
		basicDataSource.setUsername("user");
		basicDataSource.setPassword("password");
		return basicDataSource;
		
	}
	
	@Bean
	public DataSource dataSource() throws DataSourceLookupFailureException {
		
		JndiDataSourceLookup jdsl = new JndiDataSourceLookup();
		jdsl.setResourceRef(true);
		DataSource dataSource = jdsl.getDataSource("jdbc/oracle");
		return dataSource;
		
	}
	
	@Bean
	public DataSource dataSource() throws NamingException {
		
		JndiTemplate jndiTemplate = new JndiTemplate();
		DataSource dataSource = jndiTemplate.lookup("java:comp/env/jdbc/oracle", DataSource.class);
		return dataSource;
		
	}
	*/
	
}
