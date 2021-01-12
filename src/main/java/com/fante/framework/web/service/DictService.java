package com.fante.framework.web.service;

import com.fante.common.utils.StringUtils;
import com.fante.common.utils.text.Convert;
import com.fante.project.system.dict.domain.DictData;
import com.fante.project.system.dict.service.IDictDataService;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * RuoYi首创 html调用 thymeleaf 实现字典读取
 *
 * @author fante
 */
@Service("dict")
public class DictService {
    @Autowired
    private IDictDataService dictDataService;

    /**
     * 根据字典类型查询字典数据信息
     *
     * @param dictType 字典类型
     * @return 参数键值
     */
    public List<DictData> getType(String dictType) {
        return dictDataService.selectDictDataByType(dictType);
    }

    /**
     * 根据字典类型和字典键值查询字典数据信息
     *
     * @param dictType  字典类型
     * @param dictValue 字典键值
     * @return 字典标签
     */
    public String getLabel(String dictType, String dictValue) {
        return dictDataService.selectDictLabel(dictType, dictValue);
    }


    /**
     * 判断是否选中
     * @param value
     * @param arrStr
     * @return
     */
    public boolean isCheck(String value, String arrStr) {
        if (StringUtils.isBlank(arrStr) || StringUtils.isBlank(value)) {
            return false;
        }
        return isCheck(value, Convert.toStrArray(arrStr));
    }

    /**
     * 判断是否选中
     * @param value
     * @param arr
     * @return
     */
    public boolean isCheck(String value, String[] arr) {
        if (StringUtils.isBlank(value)) {
            return false;
        }
        return ArrayUtils.contains(arr, value);
    }
}
