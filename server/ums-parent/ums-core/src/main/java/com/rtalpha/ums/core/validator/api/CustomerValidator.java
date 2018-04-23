package com.rtalpha.ums.core.validator.api;

import com.rtalpha.framework.core.validation.SaveValidator;
import com.rtalpha.framework.core.validation.UpdateValidator;
import com.rtalpha.ums.remote.dto.CustomerDto;

/**
 * @author Ricky
 * @since Jun 17, 2017
 *
 */
public interface CustomerValidator extends SaveValidator<CustomerDto>, UpdateValidator<CustomerDto> {

}
