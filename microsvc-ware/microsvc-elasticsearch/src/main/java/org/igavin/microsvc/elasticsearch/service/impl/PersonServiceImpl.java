package org.igavin.microsvc.elasticsearch.service.impl;

import cn.hutool.core.bean.BeanUtil;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.igavin.microsvc.elasticsearch.model.Person;
import org.igavin.microsvc.elasticsearch.service.PersonService;
import org.igavin.microsvc.elasticsearch.service.base.BaseElasticsearchService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Service
public class PersonServiceImpl extends BaseElasticsearchService implements PersonService {

    @Override
    public boolean isExistIndex(String index){
        return super.isExistIndex(index);
    }

    @Override
    public void createIndex(String index) {
        createIndexRequest(index);
    }

    @Override
    public void deleteIndex(String index) {
        deleteIndexRequest(index);
    }

    @Override
    public void insert(String index, Person person) {

        IndexRequest request = buildIndexRequest(index, String.valueOf(person.getId()), person);
        try {
            client.index(request, COMMON_OPTIONS);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(String index, Person person) {
        updateRequest(index, String.valueOf(person.getId()), person);
    }

    @Override
    public void delete(String index, Person person) {
        if (ObjectUtils.isEmpty(person)) {
            // 如果person 对象为空，则删除全量
            searchList(index).forEach(p -> {
                deleteRequest(index, String.valueOf(p.getId()));
            });
        }
        deleteRequest(index, String.valueOf(person.getId()));
    }

    @Override
    public List<Person> searchList(String index) {
        SearchResponse searchResponse = search(index);
        SearchHit[] hits = searchResponse.getHits().getHits();
        List<Person> personList = new ArrayList<>();
        Arrays.stream(hits).forEach(hit -> {
            Map<String, Object> sourceAsMap = hit.getSourceAsMap();
            Person person = BeanUtil.mapToBean(sourceAsMap, Person.class, true);
            personList.add(person);
        });
        return personList;
    }
}
