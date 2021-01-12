package com.fante.project.business.smsFlashPromotion.domain;

import com.fante.common.utils.bean.BeanUtils;

import java.util.List;

/**
 * @program: Fante
 * @Date: 2020/3/31 15:03
 * @Author: Mr.Z
 * @Description: 秒杀活动列表
 */
public class SmsFlashPromotionListDTO extends SmsFlashPromotion {

    private static final long serialVersionUID = 1L;

    private List<String> btns;


    public SmsFlashPromotionListDTO() {
    }

    public SmsFlashPromotionListDTO(SmsFlashPromotion promotion, List<String> btns) {
        BeanUtils.copyBeanProp(this, promotion);
        this.btns = btns;
    }

    public List<String> getBtns() {
        return btns;
    }

    public void setBtns(List<String> btns) {
        this.btns = btns;
    }
}
