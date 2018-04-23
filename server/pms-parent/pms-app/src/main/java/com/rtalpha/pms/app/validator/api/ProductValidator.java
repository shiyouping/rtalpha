package com.rtalpha.pms.app.validator.api;

import com.rtalpha.base.core.validation.SaveValidator;
import com.rtalpha.base.core.validation.UpdateValidator;
import com.rtalpha.pms.kernel.dto.ProductDto;

/**
 * Performs logical validation check to products
 * 
 * @author Ricky
 * @since Jun 15, 2017
 */
public interface ProductValidator<P extends ProductDto> extends SaveValidator<P>, UpdateValidator<P> {
}
