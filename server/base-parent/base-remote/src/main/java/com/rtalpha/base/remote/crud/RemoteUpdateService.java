package com.rtalpha.base.remote.crud;

import javax.annotation.Nonnull;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.rtalpha.base.kernel.constant.RequestPath;
import com.rtalpha.base.kernel.dto.BaseDto;

/**
 * Defines the minimum methods for remote DTO update. Extra methods should be
 * defined in the sub-interfaces if necessary
 * 
 * @author Ricky Shi
 * @since Jul 12, 2017
 * @param <D>
 */
public interface RemoteUpdateService<D extends BaseDto> extends RemoteCrudService<D> {
	/**
	 * Updates the underlying entity associated with the given dto. Use the returned
	 * instance for further operations as the operation might have changed the dto
	 * instance completely.
	 *
	 * @param dto
	 *            the associated entity will be updated
	 * @return the dto with updated states
	 */
	@Nonnull
	@RequestMapping(method = RequestMethod.PUT, path = RequestPath.METHOD_UPDATE_ONE)
	D updateOne(@Nonnull @RequestBody D dto);
}
