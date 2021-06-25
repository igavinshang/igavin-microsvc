package org.igavin.microsvc.canal.job.consumer;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class CanalKafkaConsumer {

    @KafkaListener(topics = "${spring.kafka.xkt.topic}",groupId = "${spring.kafka.xkt.groupid}")
    public void receive(ConsumerRecord<?, ?> record, Consumer consumer, Acknowledgment ack) {
        System.out.println(
                "topic名称:" + record.topic() +
                        ",key:" + record.key() +
                        ",分区位置:" + record.partition() +
                        ",下标" + record.offset() +
                        ",值" + record.value()
        );
        String json = (String) record.value();
        JSONObject jsonObject = JSONObject.parseObject(json);
        String type = jsonObject.getString("type");
        String pkNames = jsonObject.getJSONArray("pkNames").getString(0);
        JSONArray data = jsonObject.getJSONArray("data");
        for (int i = 0; i < data.size(); i++) {
            JSONObject dataObject = data.getJSONObject(i);
            //我这里为了方便,检测类型为insert或者update,都统一为save,实际到es则直接覆盖掉了
            switch (type) {
                case "UPDATE":
                case "INSERT":
                    break;
                case "DELETE":
                    break;
            }
        }


    }

    @KafkaListener(topics = "${spring.kafka.material.topic}",groupId = "${spring.kafka.material.groupid}")
    public void receive2(ConsumerRecord<?, ?> record, Consumer consumer, Acknowledgment ack) {
        System.out.println(
                "topic名称:" + record.topic() +
                        ",key:" + record.key() +
                        ",分区位置:" + record.partition() +
                        ",下标" + record.offset() +
                        ",值" + record.value()
        );
        String json = (String) record.value();
        JSONObject jsonObject = JSONObject.parseObject(json);
        String type = jsonObject.getString("type");
        String pkNames = jsonObject.getJSONArray("pkNames").getString(0);
        JSONArray data = jsonObject.getJSONArray("data");
        for (int i = 0; i < data.size(); i++) {
            JSONObject dataObject = data.getJSONObject(i);
            //我这里为了方便,检测类型为insert或者update,都统一为save,实际到es则直接覆盖掉了
            switch (type) {
                case "UPDATE":
                case "INSERT":
                    break;
                case "DELETE":
                    break;
            }
        }


    }

}
