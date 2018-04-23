package com.rtalpha.base.remote.crud;

import javax.annotation.Nonnull;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.rtalpha.base.kernel.constant.RequestPath;
import com.rtalpha.base.kernel.dto.BaseDto;

/**
 * Defines the minimum methods for remote DTO deletion. Extra methods should be
 * defined in the sub-interfaces if necessary
 * 
 * @author Ricky Shi
 * @since Jul 12, 2017
 * @param <D>
 */
public interface RemoteDeleteService<D extends BaseDto> extends RemoteCrudService<D> {

	/**
	 * Deletes the underlying entity with the given id.
	 *
	 * @param id
	 *            the underlying entity with this id
	 */
	@RequestMapping(method = RequestMethod.DELETE, path = RequestPath.METHOD_DELETE_ONE + "ById")
	void deleteOneById(@Nonnull @RequestParam(name = "id") String id);

	/**
	 * Deletes the underlying entity associated with the given dto
	 * 
	 * @param dto
	 */
	@RequestMapping(method = RequestMethod.DELETE, path = RequestPath.METHOD_DELETE_ONE)
	void deleteOne(@Nonnull @RequestBody D dto);
}
