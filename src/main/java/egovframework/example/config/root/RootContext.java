package egovframework.example.config.root;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ContextAspect.class, ContextCommon.class, ContextDataSource.class
	, ContextIdgen.class, ContextMapper.class, ContextSqlMap.class, ContextProperties.class
	, ContextTransaction.class, ContextValidator.class
})
public class RootContext {

}
