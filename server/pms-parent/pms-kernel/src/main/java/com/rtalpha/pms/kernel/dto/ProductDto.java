package com.rtalpha.pms.kernel.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.pojomatic.annotations.AutoProperty;

import com.rtalpha.base.kernel.dto.BaseDto;
import com.rtalpha.base.kernel.dto.ImpermanentDto;

/**
 * @author Ricky
 * @since May 17, 2017
 */
@AutoProperty
public abstract class ProductDto extends ImpermanentDto {

	private static final long serialVersionUID = 1L;
	private Long number;
	private String subject;
	private String summary;
	private List<String> keywords;
	private String agent;
	private Integer quantity;
	private List<PriceDto> basicPrices;
	private List<PriceDto> extraServices;
	private List<DiscountDto> discounts;
	private Integer numberOfDeal;
	private List<String> previews;
	private Map<String, String> details;

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public List<String> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public List<PriceDto> getBasicPrices() {
		return basicPrices;
	}

	public void setBasicPrices(List<PriceDto> basicPrices) {
		this.basicPrices = basicPrices;
	}

	public List<PriceDto> getExtraServices() {
		return extraServices;
	}

	public void setExtraServices(List<PriceDto> extraServices) {
		this.extraServices = extraServices;
	}

	public List<DiscountDto> getDiscounts() {
		return discounts;
	}

	public void setDiscounts(List<DiscountDto> discounts) {
		this.discounts = discounts;
	}

	public Integer getNumberOfDeal() {
		return numberOfDeal;
	}

	public void setNumberOfDeal(Integer numberOfDeal) {
		this.numberOfDeal = numberOfDeal;
	}

	public List<String> getPreviews() {
		return previews;
	}

	public void setPreviews(List<String> previews) {
		this.previews = previews;
	}

	public Map<String, String> getDetails() {
		return details;
	}

	public void setDetails(Map<String, String> details) {
		this.details = details;
	}

	@AutoProperty
	public static abstract class RatingDto extends BaseDto {
		private static final long serialVersionUID = 1L;
		private Float price;
		private Float service;
		private Float description;

		public Float getPrice() {
			return price;
		}

		public void setPrice(Float price) {
			this.price = price;
		}

		public Float getService() {
			return service;
		}

		public void setService(Float service) {
			this.service = service;
		}

		public Float getDescription() {
			return description;
		}

		public void setDescription(Float description) {
			this.description = description;
		}
	}

	@AutoProperty
	public static class PriceDto extends BaseDto {
		private static final long serialVersionUID = 1L;
		private String name;
		private String description;
		private BigDecimal amount;
		private BigDecimal tax;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public BigDecimal getAmount() {
			return amount;
		}

		public void setAmount(BigDecimal amount) {
			this.amount = amount;
		}

		public BigDecimal getTax() {
			return tax;
		}

		public void setTax(BigDecimal tax) {
			this.tax = tax;
		}
	}

	@AutoProperty
	public static class DiscountDto extends BaseDto {
		private static final long serialVersionUID = 1L;
		private Type type;
		private String name;
		private String description;
		private BigDecimal amount;
		private ThresholdDto threshold;

		public Type getType() {
			return type;
		}

		public void setType(Type type) {
			this.type = type;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public BigDecimal getAmount() {
			return amount;
		}

		public void setAmount(BigDecimal amount) {
			this.amount = amount;
		}

		public ThresholdDto getThreshold() {
			return threshold;
		}

		public void setThreshold(ThresholdDto threshold) {
			this.threshold = threshold;
		}

		public static enum Type {
			NUMBER, PERCENTAGE;
		}

		@AutoProperty
		public static class ThresholdDto extends BaseDto {
			private static final long serialVersionUID = 1L;
			private Integer age;
			private Integer quantity;
			private BigDecimal amount;
			private DateTime effectiveTime;
			private DateTime expiryTime;

			public Integer getAge() {
				return age;
			}

			public void setAge(Integer age) {
				this.age = age;
			}

			public Integer getQuantity() {
				return quantity;
			}

			public void setQuantity(Integer quantity) {
				this.quantity = quantity;
			}

			public BigDecimal getAmount() {
				return amount;
			}

			public void setAmount(BigDecimal amount) {
				this.amount = amount;
			}

			public DateTime getEffectiveTime() {
				return effectiveTime;
			}

			public void setEffectiveTime(DateTime effectiveTime) {
				this.effectiveTime = effectiveTime;
			}

			public DateTime getExpiryTime() {
				return expiryTime;
			}

			public void setExpiryTime(DateTime expiryTime) {
				this.expiryTime = expiryTime;
			}
		}
	}
}
