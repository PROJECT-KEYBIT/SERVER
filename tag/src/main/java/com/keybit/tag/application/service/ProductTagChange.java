package com.keybit.tag.application.service;

import com.keybit.tag.application.port.in.ProductTagChangeUsecase;
import com.keybit.tag.application.port.out.TagOutputPort;
import com.keybit.tag.domain.event.TagChanged;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductTagChange implements ProductTagChangeUsecase {

    private final TagOutputPort outputPort;

    @Override
    public void productTagChanged(TagChanged tagChanged) {
        //TODO:
    }
}
