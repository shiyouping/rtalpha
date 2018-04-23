package com.rtalpha.pms.app.rest.internal;

import javax.annotation.Nonnull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rtalpha.base.kernel.constant.RequestPath;
import com.rtalpha.base.web.rest.response.internal.InternalBulkResponse;
import com.rtalpha.pms.app.document.PackageTour;
import com.rtalpha.pms.app.service.api.PackageTourService;
import com.rtalpha.pms.kernel.dto.PackageTourDto;

/**
 * @author Ricky Shi
 * @since Aug 18, 2017
 */
@RestController("internalPackageTourController")
@RequestMapping(RequestPath.API_INTERNAL_PMS_VERSION_1 + "/packageTours")
public class PackageTourController extends ProductController<PackageTourDto, PackageTour> {

	private final PackageTourService service;

	@Autowired
	public PackageTourController(@Nonnull PackageTourService service) {
		super(service);
		this.service = service;
	}

	@GetMapping(path = RequestPath.METHOD_FIND_ALL + "ByDeparture")
	public InternalBulkResponse<PackageTourDto> findAllByDeparture(@RequestParam String departure) {
		return new InternalBulkResponse<>(() -> {
			return service.findAllByDeparture(departure);
		});
	}

	@GetMapping(path = RequestPath.METHOD_FIND_ALL + "ByDestination")
	public InternalBulkResponse<PackageTourDto> findAllByDestination(@RequestParam String destination) {
		return new InternalBulkResponse<>(() -> {
			return service.findAllByDestination(destination);
		});
	}
}
