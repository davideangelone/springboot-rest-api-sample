package it.test.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

/**
 * The Class DozerConfig.
 */
@Configuration
public class DozerConfig {
	
	/**
	 * Mapper.
	 *
	 * @param resourceArray the resource array
	 * @return the mapper
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Bean	
	public Mapper mapper(@Value(value = "classpath*:mappings/*.xml") Resource[] resources) throws IOException {
	    DozerBeanMapper dozerBeanMapper = new DozerBeanMapper();
	    List<String> mappingFiles = new ArrayList<>();
	    for (Resource r : resources) {
	    	mappingFiles.add(r.getURL().toString());
	    }
	    dozerBeanMapper.setMappingFiles(mappingFiles);
	    return dozerBeanMapper;
	}
	
	

}
