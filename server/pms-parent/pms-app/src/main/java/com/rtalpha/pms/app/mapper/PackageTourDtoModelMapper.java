package com.rtalpha.pms.app.mapper;

import javax.annotation.Nullable;

import org.mapstruct.Mapper;

import com.rtalpha.pms.app.model.PackageTourFullModel;
import com.rtalpha.pms.kernel.dto.PackageTourDto;

/**
 * @author Ricky Shi
 * @since Aug 16, 2017
 */
@Mapper
public interface PackageTourDtoModelMapper extends ProductDtoModelMapper<PackageTourDto, PackageTourFullModel> {

	@Nullable
	PackageTourDto.ItineraryDto toDtoItinerary(@Nullable PackageTourFullModel.ItineraryFullModel modelItinerary);

	@Nullable
	PackageTourFullModel.ItineraryFullModel toModelItinerary(@Nullable PackageTourDto.ItineraryDto dtoItinerary);

	@Nullable
	PackageTourDto.RatingDto toDtoRating(@Nullable PackageTourFullModel.RatingFullModel modelRating);

	@Nullable
	PackageTourFullModel.RatingFullModel toModelRating(@Nullable PackageTourDto.RatingDto dtoRating);

}
