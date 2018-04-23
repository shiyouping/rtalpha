package com.rtalpha.base.web.config;

import static com.google.common.base.Preconditions.checkNotNull;

import java.lang.reflect.Modifier;
import java.util.Set;

import javax.annotation.Nonnull;

import org.apache.commons.collections4.CollectionUtils;
import org.reflections.Reflections;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.rtalpha.base.kernel.utility.ConfigLogger;
import com.rtalpha.base.web.model.BaseModel;

/**
 * Configure the type information for json deserialization
 * 
 * @author Ricky Shi
 * @since Sep 12, 2017
 */
@Configuration
public class JacksonConfig {

	@Autowired
	public void registerSubtypes(@Nonnull ObjectMapper mapper) {
		checkNotNull(mapper, "mapper cannot be null");

		Set<Class<? extends BaseModel>> classes = getModelClasses();

		if (CollectionUtils.isNotEmpty(classes)) {
			for (Class<? extends BaseModel> clazz : classes) {
				if (!Modifier.isAbstract(clazz.getModifiers())) {
					mapper.registerSubtypes(new NamedType(clazz, clazz.getSimpleName()));
					ConfigLogger.info("Jackson subtype registered. {} short for {}", clazz.getSimpleName(),
							clazz.getCanonicalName());
				}
			}
		}
	}

	private Set<Class<? extends BaseModel>> getModelClasses() {
		FilterBuilder filterBuilder = new FilterBuilder();
		// Filter macOS files to avoid exceptions
		filterBuilder.include(".DS_Store");
		filterBuilder.exclude("com.rtalpha.*.model");

		ConfigurationBuilder configurationBuilder = new ConfigurationBuilder();
		configurationBuilder.setUrls(ClasspathHelper.forPackage("com.rtalpha")).filterInputsBy(filterBuilder);

		Reflections reflections = new Reflections(configurationBuilder);
		return reflections.getSubTypesOf(BaseModel.class);
	}
}
