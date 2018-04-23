package com.rtalpha.pms.app.mapper;

import javax.annotation.Nullable;

import org.mapstruct.Mapper;

import com.rtalpha.pms.app.document.PackageTour;
import com.rtalpha.pms.kernel.dto.PackageTourDto;

/**
 * @author Ricky Shi
 * @since Aug 11, 2017
 */
@Mapper
public interface PackageTourDtoDocMapper extends ProductDtoDocMapper<PackageTourDto, PackageTour> {

	@Nullable
	PackageTourDto.ItineraryDto toDtoItinerary(@Nullable PackageTour.Itinerary documentItinerary);

	@Nullable
	PackageTour.Itinerary toDocumentItinerary(@Nullable PackageTourDto.ItineraryDto dtoItinerary);

	@Nullable
	PackageTourDto.RatingDto toDtoRating(@Nullable PackageTour.Rating documentRating);

	@Nullable
	PackageTour.Rating toDocumentRating(@Nullable PackageTourDto.RatingDto dtoRating);
}
