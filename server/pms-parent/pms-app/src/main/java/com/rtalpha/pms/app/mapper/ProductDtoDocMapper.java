package com.rtalpha.pms.app.mapper;

import javax.annotation.Nullable;

import com.rtalpha.base.kernel.dto.BaseDto;
import com.rtalpha.base.mongo.document.BaseDocument;
import com.rtalpha.base.mongo.mapper.DtoDocumentMapper;
import com.rtalpha.pms.app.document.Product;
import com.rtalpha.pms.kernel.dto.ProductDto;

/**
 * @author Ricky Shi
 * @since Aug 11, 2017
 */
public interface ProductDtoDocMapper<Dto extends BaseDto, Doc extends BaseDocument>
		extends DtoDocumentMapper<Dto, Doc> {

	@Nullable
	Product.Discount toDocumentDiscountDto(@Nullable ProductDto.DiscountDto dtoDiscountDto);

	@Nullable
	Product.Price toDocumentPrice(@Nullable ProductDto.PriceDto dtoPrice);

	@Nullable
	Product.Discount.Threshold toDocumentThreshold(@Nullable ProductDto.DiscountDto.ThresholdDto dtoThreshold);

	@Nullable
	Product.Discount.Type toDocumentType(@Nullable ProductDto.DiscountDto.Type dtoType);

	@Nullable
	ProductDto.DiscountDto toDtoDiscountDto(@Nullable Product.Discount documentDiscountDto);

	@Nullable
	ProductDto.PriceDto toDtoPrice(@Nullable Product.Price documentPrice);

	@Nullable
	ProductDto.DiscountDto.ThresholdDto toDtoThreshold(@Nullable Product.Discount.Threshold documentThreshold);

	@Nullable
	ProductDto.DiscountDto.Type toDtoType(@Nullable Product.Discount.Type documentType);
}
