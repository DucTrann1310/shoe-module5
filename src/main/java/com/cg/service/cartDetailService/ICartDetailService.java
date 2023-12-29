package com.cg.service.cartDetailService;

import com.cg.model.CartDetail;
import com.cg.model.dto.AddToCartCreReqDTO;
import com.cg.model.dto.CustomerReqDTO;
import com.cg.service.IGeneralService;

public interface ICartDetailService extends IGeneralService<CartDetail, Long> {

    void create(AddToCartCreReqDTO addToCartCreReqDTO);

    void increQuantityProduct(Long cardDetailId);
    void decreQuantityProduct(Long cardDetailId);
    void removeCartDetail(Long cardDetailId);

    void order(CustomerReqDTO customerReqDTO);

}
