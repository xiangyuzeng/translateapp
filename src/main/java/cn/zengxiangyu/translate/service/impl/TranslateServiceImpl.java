package cn.zengxiangyu.translate.service.impl;

import cn.zengxiangyu.translate.service.TranslateService;
import cn.zengxiangyu.translate.utils.DateUtils;
import cn.zengxiangyu.translate.utils.MongoDBUtil;
import cn.zengxiangyu.translate.utils.TransApi;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 11636
 * @date 2020-03-19 9:49
 * @Description: [一句话描述该类的功能]
 */
@Service
public class TranslateServiceImpl implements TranslateService {

    private static final String APP_ID = "20200312000397122";
    private static final String SECURITY_KEY = "6vb_Hm9068YlXpxzkBWZ";

    @Autowired
    private MongoDatabase mongoDatabase;

    @Override
    public String translateFromInput(String inputValue) {
        TransApi api = new TransApi(APP_ID, SECURITY_KEY);
        String query = inputValue;
        String transResult = api.getTransResult(query, "auto", "en");

        JSONObject jsonResult = JSONObject.parseObject(transResult);
        jsonResult.put("APP_ID", APP_ID);
        MongoDBUtil.insert(mongoDatabase, "demo_db", DateUtils.currDay(), jsonResult.toString());

        JSONObject trans_result = jsonResult.getJSONArray("trans_result").getJSONObject(0);
        return trans_result.getString("dst");
    }

    @Override
    public JSONArray getAllRecords() {
        return MongoDBUtil.getAll(mongoDatabase, "demo_db");
    }
}
