package com.rtalpha.pms.app.validator.impl;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.rtalpha.pms.app.validator.api.PackageTourValidator;
import com.rtalpha.pms.kernel.dto.PackageTourDto;
import com.rtalpha.pms.kernel.dto.PackageTourDto.ItineraryDto;

/**
 * @author Ricky Shi
 * @since Aug 16, 2017
 */
@Component
public class PackageTourValidatorImpl extends ProductValidatorImpl<PackageTourDto> implements PackageTourValidator {

	@Override
	protected void validateSave(List<String> errors, PackageTourDto packageTour) {
		super.validateSave(errors, packageTour);
		validatePrices(errors, packageTour.getRooms());
		validatePrices(errors, packageTour.getInsurances());
		validateItineraries(errors, packageTour.getItineraries());
	}

	@Override
	protected void validateUpdate(List<String> errors, PackageTourDto packageTour) {
		super.validateUpdate(errors, packageTour);
		validatePrices(errors, packageTour.getRooms());
		validatePrices(errors, packageTour.getInsurances());
		validateItineraries(errors, packageTour.getItineraries());
	}

	private void validateItineraries(List<String> errors, List<ItineraryDto> itineraries) {
		if (CollectionUtils.isNotEmpty(itineraries)) {
			List<Integer> days = Lists.newArrayListWithExpectedSize(itineraries.size());

			itineraries.forEach((itinerary) -> {
				days.add(itinerary.getDay());
			});

			long distinctDays = itineraries.stream().distinct().count();

			if (distinctDays != itineraries.size()) {
				errors.add("Duplicate day of itinerary");
			}
		}
	}
}
