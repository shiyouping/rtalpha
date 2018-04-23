package com.rtalpha.base.core.cache;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;

import javax.annotation.Nonnull;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.interceptor.CacheOperationInvocationContext;
import org.springframework.cache.interceptor.SimpleCacheResolver;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

/**
 * This cache resolver serves three purposes: <br>
 * <br>
 * 
 * 1. Resolve the cache names from {@linkplain CacheConfig} in the subclasses
 * rather than in their parents. Usually an abstract service implementing
 * CreateService, UpdateService or DeleteService needs to specify this resolver
 * in {@linkplain CacheConfig}. <br>
 * For example: @CacheConfig(cacheResolver = AutoNamingCacheResolver.NAME)<br>
 * <br>
 * 
 * 2. Automatically add a customized cache name for the method annotated by
 * {@linkplain Cacheable}, e.g. packageTour.findAllByDeparture . This feature is
 * based on {@linkplain CacheConfig} on class level <br>
 * <br>
 * 
 * 3. Automatically add all customized cache names to the method annotated by
 * {@linkplain CacheEvict} . This feature is based on {@linkplain CacheConfig}
 * on class level
 * 
 * @author Ricky Shi
 * @since Sep 14, 2017
 */
@Component
public class AutoNamingCacheResolver extends SimpleCacheResolver {

	public static final String NAME = "autoNamingCacheResolver";
	private static final Logger logger = LoggerFactory.getLogger(AutoNamingCacheResolver.class);

	@Autowired
	public AutoNamingCacheResolver(@Nonnull CacheManager cacheManager) {
		super(cacheManager);
	}

	@Override
	protected Collection<String> getCacheNames(CacheOperationInvocationContext<?> context) {
		Class<? extends Object> targetClass = context.getTarget().getClass();
		Annotation[] classAnnotations = targetClass.getDeclaredAnnotations();

		if (classAnnotations == null || classAnnotations.length == 0) {
			return getSuperCacheNames(context);
		}

		for (Annotation classAnnotation : classAnnotations) {
			Class<? extends Annotation> cacheConfigClass = classAnnotation.annotationType();
			if (cacheConfigClass == CacheConfig.class) {
				try {
					String methodName = context.getMethod().getName();
					String targetClassName = targetClass.getCanonicalName();
					Method cacheNamesMethod = cacheConfigClass.getMethod("cacheNames");
					String[] classCacheNames = (String[]) cacheNamesMethod.invoke(classAnnotation);

					if (classCacheNames == null || classCacheNames.length == 0) {
						logger.warn("No cache names found on class level for class {}", targetClassName);
						return getSuperCacheNames(context);
					}

					if (isQualified(targetClass, CacheEvict.class, methodName)) {
						return getCacheNamesForCacheEvict(targetClass, context, classCacheNames);
					} else if (isQualified(targetClass, Cacheable.class, methodName)) {
						return getCacheNamesForCacheable(classCacheNames, methodName);
					}
				} catch (Exception e) {
					logger.debug("Failed to get the cacheNames from a concrete class", e);
				}
			}
		}

		return getSuperCacheNames(context);
	}

	private List<String> getCacheNamesForCacheable(String[] classCacheNames, String methodName) {
		List<String> cacheNames = Lists.newArrayListWithExpectedSize(classCacheNames.length);
		for (String classCacheName : classCacheNames) {
			cacheNames.add(classCacheName + "." + methodName);
		}

		return cacheNames;
	}

	private List<String> getCacheNamesForCacheEvict(Class<?> targetClass, CacheOperationInvocationContext<?> context,
			String[] classCacheNames) {
		Method[] methods = targetClass.getMethods();
		if (methods == null || methods.length == 0) {
			return Lists.newArrayList(getSuperCacheNames(context));
		}

		List<String> cacheNames = Lists.newArrayListWithCapacity(methods.length);
		for (Method method : methods) {
			Annotation[] annotations = method.getAnnotations();
			if (annotations != null && annotations.length > 0) {
				for (Annotation annotation : annotations) {
					if (annotation.annotationType() == Cacheable.class) {
						for (String classCacheName : classCacheNames) {
							String methodCacheName = classCacheName + "." + method.getName();
							if (!cacheNames.contains(methodCacheName)) {
								cacheNames.add(methodCacheName);
							}
						}
					}
				}
			}
		}

		if (CollectionUtils.isEmpty(cacheNames)) {
			return Lists.newArrayList(getSuperCacheNames(context));
		}

		return cacheNames;
	}

	private Collection<String> getSuperCacheNames(CacheOperationInvocationContext<?> context) {
		logger.debug("Getting the cacheNames from super class...");
		return super.getCacheNames(context);
	}

	private boolean isQualified(Class<?> targetClass, Class<?> annotationType, String methodName) {
		Method[] methods = targetClass.getMethods();
		if (methods == null || methods.length == 0) {
			return false;
		}

		for (Method method : methods) {
			if (StringUtils.equals(methodName, method.getName())) {
				Annotation[] annotations = method.getAnnotations();
				if (annotations != null && annotations.length > 0) {
					for (Annotation annotation : annotations) {
						if (annotation.annotationType() == annotationType) {
							return true;
						}
					}
				}
			}
		}

		return false;
	}
}