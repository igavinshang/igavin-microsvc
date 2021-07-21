package org.igavin.microsvc.elasticsearch.service;

import org.igavin.microsvc.elasticsearch.model.Person;
import org.springframework.lang.Nullable;

import java.util.List;


public interface PersonService {


    boolean isExistIndex(String index);

    /**
     * create Index
     *
     * @param index elasticsearch index name
     * @author fxbin
     */
    void createIndex(String index);

    /**
     * delete Index
     *
     * @param index elasticsearch index name
     * @author fxbin
     */
    void deleteIndex(String index);

    /**
     * insert document source
     *
     * @param index elasticsearch index name
     * @param person  data source
     * @author fxbin
     */
    void insert(String index, Person person);

    /**
     * update document source
     *
     * @param index elasticsearch index name
     * @param person  data source
     * @author fxbin
     */
    void update(String index, Person person);

    /**
     * delete document source
     *
     * @param person delete data source and allow null object
     * @author fxbin
     */
    void delete(String index, @Nullable Person person);

    /**
     * search all doc records
     *
     * @param index elasticsearch index name
     * @return person list
     * @author fxbin
     */
    List<Person> searchList(String index);

}
