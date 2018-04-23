package com.rtalpha.pms.app.mapper;

import javax.annotation.Nullable;

import com.rtalpha.base.web.mapper.DtoModelMapper;
import com.rtalpha.base.web.model.BaseModel;
import com.rtalpha.pms.app.model.ProductFullModel;
import com.rtalpha.pms.kernel.dto.ProductDto;

/**
 * @author Ricky Shi
 * @since Aug 11, 2017
 */
public interface ProductDtoModelMapper<D extends ProductDto, M extends BaseModel> extends DtoModelMapper<D, M> {

	@Nullable
	ProductDto.DiscountDto toDtoDiscount(@Nullable ProductFullModel.DiscountFullModel modelDiscount);

	@Nullable
	ProductDto.PriceDto toDtoPrice(@Nullable ProductFullModel.PriceFullModel modelPrice);

	@Nullable
	ProductDto.DiscountDto.ThresholdDto toDtoThreshold(
			@Nullable ProductFullModel.DiscountFullModel.ThresholdFullModel modelThreshold);

	@Nullable
	ProductDto.DiscountDto.Type toDtoType(@Nullable ProductFullModel.DiscountFullModel.Type modelType);

	@Nullable
	ProductFullModel.DiscountFullModel toModelDiscount(@Nullable ProductDto.DiscountDto dtoDiscount);

	@Nullable
	ProductFullModel.PriceFullModel toModelPrice(@Nullable ProductDto.PriceDto dtoPrice);

	@Nullable
	ProductFullModel.DiscountFullModel.ThresholdFullModel toModelThreshold(
			@Nullable ProductDto.DiscountDto.ThresholdDto dtoThreshold);

	@Nullable
	ProductFullModel.DiscountFullModel.Type toModelType(@Nullable ProductDto.DiscountDto.Type dtoType);
}
