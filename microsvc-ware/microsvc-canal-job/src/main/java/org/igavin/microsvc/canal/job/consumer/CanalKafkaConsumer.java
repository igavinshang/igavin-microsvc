package org.igavin.microsvc.canal.job.consumer;

import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.igavin.microsvc.elasticsearch.contants.ElasticsearchConstant;
import org.igavin.microsvc.elasticsearch.model.Person;
import org.igavin.microsvc.elasticsearch.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CanalKafkaConsumer {

  @Autowired
  private PersonService personService;

  @KafkaListener(topics = "${spring.kafka.igavin.topic}", groupId = "${spring.kafka.igavin.groupid}")
  public void sinkToEs(ConsumerRecord<?, ?> record, Consumer consumer, Acknowledgment ack) {

    System.out.println(
            "topic名称:"
                    + record.topic()
                    + ",key:"
                    + record.key()
                    + ",分区位置:"
                    + record.partition()
                    + ",下标"
                    + record.offset()
                    + ",值"
                    + record.value());

    String json = (String) record.value();
    JSONObject jsonObject = JSONObject.parseObject(json);
    String type = jsonObject.getString("type");
    String pkNames = jsonObject.getJSONArray("pkNames").getString(0);
    String table = jsonObject.getString("table");
    String dataJsonString = jsonObject.getString("data");

    if("person".equals(table)){
      List<Person> personList = JSONObject.parseArray(dataJsonString, Person.class);
      // 我这里为了方便,检测类型为insert或者update,都统一为insert,实际到es则直接覆盖掉了
      switch (type) {
        case "UPDATE":{
          personList.forEach(
                  person -> personService.insert(ElasticsearchConstant.INDEX_NAME,person)
          );
        }break;
        case "INSERT": {
          personList.forEach(
                  person -> personService.insert(ElasticsearchConstant.INDEX_NAME,person)
          );
        }break;
        case "DELETE":{
          personList.forEach(
                  person -> personService.delete(ElasticsearchConstant.INDEX_NAME,person)
          );
        }break;
      }
    }


  }

//
//  @KafkaListener(topics = "${spring.kafka.igavin.topic}", groupId = "${spring.kafka.igavin.groupid}")
//  public void receive(ConsumerRecord<?, ?> record, Consumer consumer, Acknowledgment ack) {
//
//    System.out.println(
//            "topic名称:"
//                    + record.topic()
//                    + ",key:"
//                    + record.key()
//                    + ",分区位置:"
//                    + record.partition()
//                    + ",下标"
//                    + record.offset()
//                    + ",值"
//                    + record.value());
//    String json = (String) record.value();
//    JSONObject jsonObject = JSONObject.parseObject(json);
//    String type = jsonObject.getString("type");
//    String pkNames = jsonObject.getJSONArray("pkNames").getString(0);
//    JSONArray data = jsonObject.getJSONArray("data");
//    for (int i = 0; i < data.size(); i++) {
//      JSONObject dataObject = data.getJSONObject(i);
//      // 我这里为了方便,检测类型为insert或者update,都统一为save,实际到es则直接覆盖掉了
//      switch (type) {
//        case "UPDATE":
//        case "INSERT":
//          break;
//        case "DELETE":
//          break;
//      }
//    }
//  }

//
//  @KafkaListener(topics = "${spring.kafka.xkt.topic}", groupId = "${spring.kafka.xkt.groupid}")
//  public void receive1(ConsumerRecord<?, ?> record, Consumer consumer, Acknowledgment ack) {
//    System.out.println(
//        "topic名称:"
//            + record.topic()
//            + ",key:"
//            + record.key()
//            + ",分区位置:"
//            + record.partition()
//            + ",下标"
//            + record.offset()
//            + ",值"
//            + record.value());
//    String json = (String) record.value();
//    JSONObject jsonObject = JSONObject.parseObject(json);
//    String type = jsonObject.getString("type");
//    String pkNames = jsonObject.getJSONArray("pkNames").getString(0);
//    JSONArray data = jsonObject.getJSONArray("data");
//    for (int i = 0; i < data.size(); i++) {
//      JSONObject dataObject = data.getJSONObject(i);
//      // 我这里为了方便,检测类型为insert或者update,都统一为save,实际到es则直接覆盖掉了
//      switch (type) {
//        case "UPDATE":
//        case "INSERT":
//          break;
//        case "DELETE":
//          break;
//      }
//    }
//  }
//
//  @KafkaListener(
//      topics = "${spring.kafka.material.topic}",
//      groupId = "${spring.kafka.material.groupid}")
//  public void receive2(ConsumerRecord<?, ?> record, Consumer consumer, Acknowledgment ack) {
//    System.out.println(
//        "topic名称:"
//            + record.topic()
//            + ",key:"
//            + record.key()
//            + ",分区位置:"
//            + record.partition()
//            + ",下标"
//            + record.offset()
//            + ",值"
//            + record.value());
//    String json = (String) record.value();
//    JSONObject jsonObject = JSONObject.parseObject(json);
//    String type = jsonObject.getString("type");
//    JSONArray data = jsonObject.getJSONArray("data");
//    Boolean isDdl = jsonObject.getBoolean("isDdl");
//
//    if (!isDdl && data != null) {
//      for (int i = 0; i < data.size(); i++) {
//        JSONObject dataObject = data.getJSONObject(i);
//        // 我这里为了方便,检测类型为insert或者update,都统一为save,实际到es则直接覆盖掉了
//        switch (type) {
//          case "UPDATE":
//          case "INSERT":
//            break;
//          case "DELETE":
//            break;
//        }
//      }
//    }
//
//    if (isDdl) {
//      String sql = jsonObject.getString("sql");
//      System.out.println("DDL-->" + type + ":" + sql);
//    }
//  }


}
