package com.rtalpha.pms.app.rest.external;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.annotation.Nonnull;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rtalpha.base.kernel.constant.RequestPath;
import com.rtalpha.base.web.exception.RestException;
import com.rtalpha.base.web.rest.controller.external.ExternalController;
import com.rtalpha.base.web.rest.response.external.ExternalSingleResponse;
import com.rtalpha.pms.app.mapper.MultipartDtoModelMapper;
import com.rtalpha.pms.app.model.MultipartFullModel;
import com.rtalpha.pms.app.service.api.MultipartService;
import com.rtalpha.pms.kernel.dto.MultipartDto;

/**
 * @author Ricky
 * @since May 17, 2017
 */
@RestController
@RequestMapping(RequestPath.API_EXTERNAL_PMS_VERSION_1 + "/multiparts")
public class MultipartController implements ExternalController {

	private static final Logger logger = LoggerFactory.getLogger(MultipartController.class);
	private final MultipartService service;
	private final MultipartDtoModelMapper mapper;

	@Autowired
	public MultipartController(@Nonnull MultipartService service, @Nonnull MultipartDtoModelMapper mapper) {
		checkNotNull(service, "service cannot be null");
		checkNotNull(mapper, "mapper cannot be null");

		this.service = service;
		this.mapper = mapper;
	}

	@PostMapping(path = RequestPath.METHOD_CREATE_ONE)
	public ExternalSingleResponse<MultipartFullModel> createOne(@RequestParam("file") final MultipartFile file) {
		logger.debug("Received a request for creating multipart file");

		return new ExternalSingleResponse<>(() -> {
			if (file.isEmpty()) {
				throw new RestException(HttpStatus.BAD_REQUEST, "Empty file not allowed");
			}

			String[] names = StringUtils.split(file.getOriginalFilename(), ".");
			String[] types = StringUtils.split(file.getContentType(), "/");
			String fileType = null;

			if (names.length > 1) {
				fileType = names[1];
			} else {
				fileType = types[1];
			}

			MultipartDto multipart = service.save(file.getBytes(), fileType);
			return mapper.toModel(multipart);
		});
	}
}
