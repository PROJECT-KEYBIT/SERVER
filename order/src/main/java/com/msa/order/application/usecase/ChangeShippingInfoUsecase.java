package com.msa.order.application.usecase;

import com.msa.order.framework.web.dto.request.ChangeShippingInfoRequest;
import com.msa.order.framework.web.dto.response.ChangeShippingInfoResponse;

public interface ChangeShippingInfoUsecase {

    ChangeShippingInfoResponse changeShippingInfo(ChangeShippingInfoRequest changeShippingInfoRequest);
}
