package com.rtalpha.pms.app.rest.internal;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.IOException;

import javax.annotation.Nonnull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rtalpha.base.kernel.constant.RequestPath;
import com.rtalpha.base.mongo.crud.CrudService;
import com.rtalpha.base.web.exception.RestException;
import com.rtalpha.base.web.rest.controller.internal.InternalCreateController;
import com.rtalpha.base.web.rest.controller.internal.InternalUpdateController;
import com.rtalpha.base.web.rest.response.internal.InternalSingleResponse;
import com.rtalpha.pms.app.document.Multipart;
import com.rtalpha.pms.app.service.api.MultipartService;
import com.rtalpha.pms.kernel.dto.MultipartDto;

/**
 * @author Ricky Shi
 * @since Jul 31, 2017
 */
@RestController("internalMultipartController")
@RequestMapping(RequestPath.API_INTERNAL_PMS_VERSION_1 + "/multiparts")
public class MultipartController implements InternalCreateController<MultipartDto, Multipart>,
		InternalUpdateController<MultipartDto, Multipart> {

	private final MultipartService service;

	@Autowired
	public MultipartController(@Nonnull MultipartService service) {
		checkNotNull(service, "service cannot be null");
		this.service = service;
	}

	@PostMapping(path = RequestPath.METHOD_CREATE_ONE + "WithFileType")
	public InternalSingleResponse<MultipartDto> createOne(@RequestBody byte[] data,
			@PathVariable("fileType") String fileType) {

		return new InternalSingleResponse<>(() -> {
			try {
				return service.save(data, fileType);
			} catch (IOException e) {
				throw new RestException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to save the specified file");
			}
		});
	}

	@GetMapping(path = RequestPath.METHOD_FIND_ONE)
	public InternalSingleResponse<MultipartDto> findOne(@RequestParam("md5") String md5,
			@RequestParam("crc32") String crc32, @RequestParam("sha256") String sha256) {

		return new InternalSingleResponse<>(() -> {
			return service.findOne(md5, crc32, sha256);
		});
	}

	@Override
	public CrudService<MultipartDto, Multipart> getCrudService() {
		return service;
	}
}
