package com.rtalpha.pms.app.validator.impl;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.joda.time.DateTime;

import com.rtalpha.base.core.common.MultiCounter;
import com.rtalpha.pms.app.validator.api.ProductValidator;
import com.rtalpha.pms.kernel.dto.ProductDto;
import com.rtalpha.pms.kernel.dto.ProductDto.DiscountDto;
import com.rtalpha.pms.kernel.dto.ProductDto.PriceDto;

/**
 * @author Ricky
 * @since Jun 15, 2017
 */
public abstract class ProductValidatorImpl<P extends ProductDto> implements ProductValidator<P> {

	private static final int keywordLength = 20;

	@Override
	public void validateSave(P product) {
		validate((errors) -> {
			validateSave(errors, product);
		});
	}

	@Override
	public void validateUpdate(P product) {
		validate((errors) -> {
			validateUpdate(errors, product);
		});
	}

	protected void validatePrices(List<String> errors, List<PriceDto> prices) {
		if (CollectionUtils.isNotEmpty(prices)) {
			MultiCounter counter = new MultiCounter();

			prices.forEach((price) -> {
				counter.count(price.getName());
			});

			counter.getCounts().forEach((name, count) -> {
				if (count > 1) {
					errors.add("Duplicate price name of " + name);
				}
			});
		}
	}

	protected void validateSave(List<String> errors, P product) {
		checkNotNull(product, "product cannot be null");

		validateKeywords(errors, product.getKeywords());
		validateTime(errors, product);
		validatePrices(errors, product.getBasicPrices());
		validatePrices(errors, product.getExtraServices());
		validateDiscounts(errors, product.getDiscounts());
	}

	protected void validateUpdate(List<String> errors, P product) {
		checkNotNull(product, "product cannot be null");

		validateKeywords(errors, product.getKeywords());
		validatePrices(errors, product.getBasicPrices());
		validatePrices(errors, product.getExtraServices());
		validateDiscounts(errors, product.getDiscounts());
	}

	private void validateDiscounts(List<String> errors, List<DiscountDto> discounts) {
		if (CollectionUtils.isNotEmpty(discounts)) {

			MultiCounter counter = new MultiCounter();
			discounts.forEach((discount) -> {
				counter.count(discount.getName());
			});

			counter.getCounts().forEach((name, count) -> {
				if (count > 1) {
					errors.add("Duplicate discount name of " + name);
				}
			});
		}
	}

	private void validateKeywords(List<String> errors, List<String> keywords) {
		if (CollectionUtils.isNotEmpty(keywords)) {
			keywords.forEach((value) -> {
				if (value.length() > keywordLength) {
					errors.add("The keyword length of " + value + " exceeds " + keywordLength);
				}
			});
		}
	}

	private void validateTime(List<String> errors, P product) {
		DateTime now = new DateTime();

		if (product.getEffectiveTime().isBefore(now)) {
			errors.add("EffectiveTime cannot be before now");
		}

		if (product.getExpiryTime().isBefore(now)) {
			errors.add("ExpiryTime cannot be before now");
		}

		if (product.getEffectiveTime().isAfter(product.getExpiryTime())) {
			errors.add("EffectiveTime cannot be after ExpiryTime");
		}
	}
}
