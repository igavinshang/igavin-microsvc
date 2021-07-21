package cn.igavin.microsvc.elasticsearch;

import org.igavin.microsvc.elasticsearch.EsApplication;
import org.igavin.microsvc.elasticsearch.contants.ElasticsearchConstant;
import org.igavin.microsvc.elasticsearch.model.Person;
import org.igavin.microsvc.elasticsearch.service.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EsApplication.class)
public class EsTest {

    @Autowired
    private PersonService personService;


    /**
     * 测试创建索引
     */
    @Test
    public void createIndexTest() {
        personService.createIndex(ElasticsearchConstant.INDEX_NAME);
    }

    /**
     * 测试新增
     */
    @Test
    public void insertTest() {
        List<Person> list = new ArrayList<>();
        list.add(Person.builder().age(11).birthday(new Date()).country("CN").id(1L).name("哈哈").remark("test1").build());
        list.add(Person.builder().age(22).birthday(new Date()).country("US").id(2L).name("hiahia").remark("test2").build());
        list.add(Person.builder().age(33).birthday(new Date()).country("ID").id(3L).name("呵呵").remark("test3").build());

        list.forEach(person -> personService.insert(ElasticsearchConstant.INDEX_NAME, person));

    }

    /**
     * 测试更新
     */
    @Test
    public void updateTest() {
        Person person = Person.builder().age(33).birthday(new Date()).country("ID_update").id(3L).name("呵呵update").remark("test3_update").build();
        List<Person> list = new ArrayList<>();
        list.add(person);

        list.forEach(p -> personService.update(ElasticsearchConstant.INDEX_NAME,p));

    }



    /**
     * 测试查询
     */
    @Test
    public void searchListTest() {
        List<Person> personList = personService.searchList(ElasticsearchConstant.INDEX_NAME);
        System.out.println(personList);
    }

    @Test
    public void existTest(){
        boolean result = personService.isExistIndex(ElasticsearchConstant.INDEX_NAME);
        System.out.println(result);
    }

}