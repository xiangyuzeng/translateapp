package cn.zengxiangyu.translate.service;


import com.alibaba.fastjson.JSONArray;

/**
 * @author 11636
 * @date 2020-03-19 9:47
 * @Description: [一句话描述该类的功能]
 */
public interface TranslateService {

    public String translateFromInput(String inputValue);

    JSONArray getAllRecords();
}
