package com.rtalpha.base.remote.crud;

import javax.annotation.Nonnull;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rtalpha.base.kernel.constant.RequestPath;
import com.rtalpha.base.kernel.dto.BaseDto;

/**
 * Defines the minimum methods for remote DTO creation. Extra methods should be
 * defined in the sub-interfaces if necessary
 * 
 * @author ricky.shi
 * @since 14 Mar 2018
 * @param <D>
 */
public interface RemoteCreateService<D extends BaseDto> extends RemoteCrudService<D> {

	/**
	 * Saves the underlying entity associated with the given dto. Use the returned
	 * instance for further operations as the operation might have changed the dto
	 * instance completely.
	 *
	 * @param dto
	 *            the associated entity will be saved
	 * @return the dto with new status
	 */
	@Nonnull
	@RequestMapping(method = RequestMethod.POST, path = RequestPath.METHOD_CREATE_ONE)
	D createOne(@Nonnull @RequestBody D dto);
}
