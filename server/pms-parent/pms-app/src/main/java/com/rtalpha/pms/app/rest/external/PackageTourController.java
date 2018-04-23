package com.rtalpha.pms.app.rest.external;

import javax.annotation.Nonnull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rtalpha.base.kernel.constant.RequestPath;
import com.rtalpha.base.web.rest.response.external.ExternalPageResponse;
import com.rtalpha.pms.app.document.PackageTour;
import com.rtalpha.pms.app.mapper.PackageTourDtoModelMapper;
import com.rtalpha.pms.app.model.PackageTourFullModel;
import com.rtalpha.pms.app.model.PackageTourUpdateModel;
import com.rtalpha.pms.app.service.api.PackageTourService;
import com.rtalpha.pms.kernel.dto.PackageTourDto;

/**
 * @author Ricky
 * @since May 17, 2017
 */
@RestController
@RequestMapping(RequestPath.API_EXTERNAL_PMS_VERSION_1 + "/packageTours")
public class PackageTourController
		extends ProductController<PackageTourDto, PackageTour, PackageTourFullModel, PackageTourUpdateModel> {

	private static final Logger logger = LoggerFactory.getLogger(PackageTourController.class);
	private final PackageTourService service;
	private final PackageTourDtoModelMapper mapper;

	@Autowired
	public PackageTourController(@Nonnull PackageTourService service, @Nonnull PackageTourDtoModelMapper mapper) {
		super(service, mapper);
		this.mapper = mapper;
		this.service = service;
	}

	@GetMapping(path = RequestPath.METHOD_FIND_PAGE + "ByDeparture")
	public ExternalPageResponse<PackageTourFullModel> findPageByDeparture(@RequestParam("departure") String departure,
			Pageable pageable) {
		logger.debug("Received a request for finding page by departure={}, pageable={}", departure, pageable);

		return new ExternalPageResponse<>(() -> {
			Page<PackageTourDto> page = service.findPageByDeparture(departure, pageable);
			return mapper.toModelPage(page, pageable);
		});
	}

	@GetMapping(path = RequestPath.METHOD_FIND_PAGE + "ByDestination")
	public ExternalPageResponse<PackageTourFullModel> findPageByDestination(
			@RequestParam("destination") String destination, Pageable pageable) {
		logger.debug("Received a request for finding page by destination={}, pageable={}", destination, pageable);

		return new ExternalPageResponse<>(() -> {
			Page<PackageTourDto> page = service.findPageByDestination(destination, pageable);
			return mapper.toModelPage(page, pageable);
		});
	}
}