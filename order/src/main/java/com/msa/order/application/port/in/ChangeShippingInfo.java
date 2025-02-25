package com.msa.order.application.port.in;

import com.msa.order.framework.web.dto.request.ChangeShippingInfoRequest;
import com.msa.order.framework.web.dto.response.ChangeShippingInfoResponse;

public interface ChangeShippingInfo {

    ChangeShippingInfoResponse changeShippingInfo(String orderNo, ChangeShippingInfoRequest changeShippingInfoRequest);
}
