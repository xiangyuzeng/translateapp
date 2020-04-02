package cn.zengxiangyu.translate.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
//import com.sun.org.apache.bcel.internal.generic.DSTORE;
//import org.apache.commons.lang.StringEscapeUtils;
import org.bson.Document;

import javax.swing.text.StyledEditorKit;
import java.util.*;

/**
 * @author 11636
 * @date 2020-03-18 16:00
 * @Description: mongodb Util
 */
public class MongoDBUtil {

    public static void insert(MongoDatabase mongoDatabase, String collectionName, String Key, String value){
        MongoCollection<Document> conn = mongoDatabase.getCollection(collectionName);
        Document document = new Document(Key, value);
        conn.insertOne(document);
    }

    public static JSONArray getAll(MongoDatabase mongoDatabase, String collectionName) {
        MongoCollection<Document> conn = mongoDatabase.getCollection(collectionName);
        FindIterable findIterable = conn.find();
        MongoCursor<Document> cursor = findIterable.iterator();

        JSONArray jsonArray = new JSONArray();
        while (cursor.hasNext()) {
            Document document = cursor.next();
            Set<Map.Entry<String, Object>> entries = document.entrySet();
            Iterator<Map.Entry<String, Object>> iterator = entries.iterator();
            for (Map.Entry<String, Object> entry : entries) {
                if (!entry.getKey().equals("_id")) {
                    JSONObject paramsJson = new JSONObject();
                    String key = entry.getKey();
                    JSONObject valueJson = JSONObject.parseObject((String) entry.getValue());
                    JSONObject trans_result = valueJson.getJSONArray("trans_result").getJSONObject(0);
                    paramsJson.put("date", key);
                    paramsJson.put("app_id", valueJson.getString("APP_ID"));
                    paramsJson.put("from", valueJson.getString("from"));
                    paramsJson.put("to", valueJson.getString("to"));
                    paramsJson.put("dst", trans_result.getString("dst"));
                    paramsJson.put("src", valueJson.getString("src"));
                    jsonArray.add(paramsJson);
                }
            }
        }
        return jsonArray;
    }
}
