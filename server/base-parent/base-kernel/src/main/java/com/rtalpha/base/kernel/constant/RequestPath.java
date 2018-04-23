package com.rtalpha.base.kernel.constant;

/**
 * @author Ricky
 * @since Jun 10, 2017
 *
 */
public interface RequestPath {
	String API_EXTERNAL_UMS_VERSION_1 = "/api/ums/v1";
	String API_EXTERNAL_PMS_VERSION_1 = "/api/pms/v1";
	String API_EXTERNAL_EMS_VERSION_1 = "/api/ems/v1";

	String API_INTERNAL_UMS_VERSION_1 = "/api/internal/ums/v1";
	String API_INTERNAL_PMS_VERSION_1 = "/api/internal/pms/v1";
	String API_INTERNAL_EMS_VERSION_1 = "/api/internal/ems/v1";

	String METHOD_FIND_ONE = "/findOne";
	String METHOD_FIND_PAGE = "/findPage";
	String METHOD_FIND_ALL = "/findAll";
	String METHOD_CREATE_ONE = "/createOne";
	String METHOD_UPDATE_ONE = "/updateOne";
	String METHOD_DELETE_ONE = "/deleteOne";
}