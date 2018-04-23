package com.rtalpha.pms.app.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;
import org.pojomatic.annotations.AutoProperty;

import com.rtalpha.base.web.model.FullModel;
import com.rtalpha.base.web.model.ImpermanentModel;

/**
 * @author Ricky
 * @since May 17, 2017
 */
@AutoProperty
public abstract class ProductFullModel extends ImpermanentModel {

	private static final long serialVersionUID = 1L;
	@Null
	private Long number;
	@NotEmpty
	@Size(min = 5, max = 50)
	private String subject;
	@NotEmpty
	@Size(min = 20, max = 400)
	private String summary;
	@Null
	private String agent;
	@Min(1)
	private Integer quantity;
	@Null
	private Integer numberOfDeal;
	@NotEmpty
	@Size(min = 1, max = 10)
	private List<String> keywords;
	@NotEmpty
	@Size(min = 1, max = 10)
	private List<String> previews;
	@Valid
	@NotEmpty
	@Size(min = 1, max = 5)
	private List<PriceFullModel> basicPrices;
	@Valid
	@Size(min = 1, max = 10)
	private List<PriceFullModel> extraServices;
	@Valid
	@Size(min = 1, max = 5)
	private List<DiscountFullModel> discounts;
	@NotEmpty
	@Size(min = 1, max = 10)
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

	public List<PriceFullModel> getBasicPrices() {
		return basicPrices;
	}

	public void setBasicPrices(List<PriceFullModel> basicPrices) {
		this.basicPrices = basicPrices;
	}

	public List<PriceFullModel> getExtraServices() {
		return extraServices;
	}

	public void setExtraServices(List<PriceFullModel> extraServices) {
		this.extraServices = extraServices;
	}

	public List<DiscountFullModel> getDiscounts() {
		return discounts;
	}

	public void setDiscounts(List<DiscountFullModel> discounts) {
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
	public static abstract class RatingFullModel extends FullModel {
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
	public static class PriceFullModel extends FullModel {
		private static final long serialVersionUID = 1L;
		@NotEmpty
		@Size(min = 1, max = 15)
		private String name;
		@NotEmpty
		@Size(min = 1, max = 50)
		private String description;
		@NotNull
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
	public static class DiscountFullModel extends FullModel {
		private static final long serialVersionUID = 1L;
		@NotNull
		private Type type;
		@NotEmpty
		@Size(min = 1, max = 15)
		private String name;
		@NotEmpty
		@Size(min = 1, max = 50)
		private String description;
		@NotNull
		private BigDecimal amount;
		@Valid
		private ThresholdFullModel threshold;

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

		public ThresholdFullModel getThreshold() {
			return threshold;
		}

		public void setThreshold(ThresholdFullModel threshold) {
			this.threshold = threshold;
		}

		public static enum Type {
			NUMBER, PERCENTAGE;
		}

		@AutoProperty
		public static class ThresholdFullModel extends FullModel {
			private static final long serialVersionUID = 1L;
			@Min(0)
			private Integer age;
			@Min(0)
			private Integer quantity;
			@Min(0)
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
