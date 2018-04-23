package com.rtalpha.base.web.config.properties;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

/**
 * @author Ricky Shi
 * @since Jul 18, 2017
 */
@Validated
@ConfigurationProperties("baseWeb")
public class BaseWebProperties {

	@Valid
	private Http http = new Http();

	public Http getHttp() {
		return http;
	}

	public void setHttp(Http http) {
		this.http = http;
	}

	public static class Http {
		@Valid
		private TaskExecutor taskExecutor = new TaskExecutor();
		@Valid
		private Multipart multipart = new Multipart();

		public Multipart getMultipart() {
			return multipart;
		}

		public void setMultipart(Multipart multipart) {
			this.multipart = multipart;
		}

		public TaskExecutor getTaskExecutor() {
			return taskExecutor;
		}

		public void setTaskExecutor(TaskExecutor taskExecutor) {
			this.taskExecutor = taskExecutor;
		}
	}

	public static class Multipart {
		private String baseLocation;

		public String getBaseLocation() {
			return baseLocation;
		}

		public void setBaseLocation(String baseLocation) {
			this.baseLocation = baseLocation;
		}
	}

	public static class TaskExecutor {
		@Min(10)
		private int corePoolSize = 50;
		@Min(50)
		private int maxPoolSize = 200;
		@Min(50)
		private int queueCapacity = 500;

		public int getCorePoolSize() {
			return corePoolSize;
		}

		public int getMaxPoolSize() {
			return maxPoolSize;
		}

		public int getQueueCapacity() {
			return queueCapacity;
		}

		public void setCorePoolSize(int corePoolSize) {
			this.corePoolSize = corePoolSize;
		}

		public void setMaxPoolSize(int maxPoolSize) {
			this.maxPoolSize = maxPoolSize;
		}

		public void setQueueCapacity(int queueCapacity) {
			this.queueCapacity = queueCapacity;
		}
	}
}
