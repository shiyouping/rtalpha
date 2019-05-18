package com.rtalpha.ums.app.validator.api;

import com.rtalpha.base.core.validation.SaveValidator;
import com.rtalpha.base.core.validation.UpdateValidator;
import com.rtalpha.ums.kenel.dto.CustomerDto;

/**
 * @author Ricky
 * @since Jun 17, 2017
 *
 */
public interface CustomerValidator extends SaveValidator<CustomerDto>, UpdateValidator<CustomerDto> {

}
