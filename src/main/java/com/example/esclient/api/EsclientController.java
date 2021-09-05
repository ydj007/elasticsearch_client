package com.example.esclient.api;

import com.example.esclient.model.Person;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.web.bind.annotation.*;

import static org.elasticsearch.search.aggregations.AggregationBuilders.terms;

@RestController @RequestMapping("/")
public class EsclientController {
    // @RequiredArgsConstructor 어노테이션을 처음에 붙이니까 ElasticsearchOperations 주입이 안되었다.
    // RequiredArgsConstructor 어노테이션은 초기화되지 않은 final 필드나 @Nonnull이 붙은 필드에 생성자를 생성해준다.
    private ElasticsearchOperations elasticsearchOperations;

    public EsclientController(ElasticsearchOperations elasticsearchOperations){
        this.elasticsearchOperations = elasticsearchOperations;
    }

    // ES index에 document 저장하기
    @PostMapping("/person")
    public String save(@RequestBody Person person)
    {
        IndexCoordinates indexCoordinates = elasticsearchOperations.getIndexCoordinatesFor(Person.class);

        IndexQuery indexQuery = new IndexQueryBuilder()
                .withId(person.getId().toString())
                .withObject(person)
                .build();
        String documentId = elasticsearchOperations.index(indexQuery,indexCoordinates);
        return documentId;
    }

    // id 기준으로 document 가져오기
    @GetMapping("/person/{id}")
    public Person findById(@PathVariable("id") Long id){
        Criteria criteria = new Criteria("id").is(id);
        Query query = new CriteriaQuery(criteria);

        IndexCoordinates indexCoordinates = elasticsearchOperations.getIndexCoordinatesFor(Person.class);


        Person person = elasticsearchOperations.get(id.toString(),Person.class, indexCoordinates);
        return person;
    }

    // firstname와 lastname으로 검색 쿼리하고 검색 결과 가져오기
    @GetMapping("/person/{firstname}/{lastname}")
    public SearchHits<Person> findByName(@PathVariable("firstname") String firstname, @PathVariable("lastname") String lastname){
        Criteria criteria = new Criteria("lastname").is(lastname)
                .or("firstname").is(firstname);
        Query query = new CriteriaQuery(criteria);

        SearchHits<Person> searchHits = elasticsearchOperations.search(query,Person.class);
        return searchHits;
    }

}
