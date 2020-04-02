package cn.zengxiangyu.translate.controller;

import cn.zengxiangyu.translate.service.TranslateService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 11636
 * @date 2020-03-15 18:12
 * @Description: [一句话描述该类的功能]
 */
@RestController
public class TranslateController {

    private static final String APP_ID = "20200312000397122";
    private static final String SECURITY_KEY = "6vb_Hm9068YlXpxzkBWZ";

    @Autowired
    private MongoDatabase mongoDatabase;

    @Autowired
    private TranslateService translateService;

    @RequestMapping(value = "/translats")
    public String translateFromInput(@RequestParam("inputValue") String inputValue) {
        return translateService.translateFromInput(inputValue);
    }

    @RequestMapping(value = "/getAllRecords")
    @ResponseBody
    public String getAllRecords() {
        JSONArray allRecords = translateService.getAllRecords();

        JSONObject result_params = new JSONObject();
        result_params.put("code", 0);
        result_params.put("count", allRecords.size());
        result_params.put("msg" , "");
        result_params.put("data", allRecords);

        return result_params.toString();
    }

}
