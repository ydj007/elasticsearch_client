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

@RestController @RequestMapping("/") @RequiredArgsConstructor
public class EsclientController {

    private ElasticsearchOperations elasticsearchOperations;

    public EsclientController(ElasticsearchOperations elasticsearchOperations){
        this.elasticsearchOperations = elasticsearchOperations;
    }

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

    @GetMapping("/person/{id}")
    public Person findById(@PathVariable("id") Long id){
        Criteria criteria = new Criteria("id").is(id);
        Query query = new CriteriaQuery(criteria);

        IndexCoordinates indexCoordinates = elasticsearchOperations.getIndexCoordinatesFor(Person.class);


        Person person = elasticsearchOperations.get(id.toString(),Person.class, indexCoordinates);
        return person;
    }
    @GetMapping("/person/{firstname}/{lastname}")
    public SearchHits<Person> findByName(@PathVariable("firstname") String firstname, @PathVariable("lastname") String lastname){
        Criteria criteria = new Criteria("lastname").is(lastname)
                .or("firstname").is(firstname);
        Query query = new CriteriaQuery(criteria);

        SearchHits<Person> searchHits = elasticsearchOperations.search(query,Person.class);
        return searchHits;
    }

}
