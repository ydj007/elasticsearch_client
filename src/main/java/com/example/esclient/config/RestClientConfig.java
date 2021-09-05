package com.example.esclient.config;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

import javax.persistence.Index;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.util.Collections;

import static java.util.Collections.*;
import static org.elasticsearch.action.support.WriteRequest.RefreshPolicy.IMMEDIATE;

@Configuration
public class RestClientConfig extends AbstractElasticsearchConfiguration {
    // 추상클래스에서 ElasticsearchOperations Bean 주입을 시켜준다.
    // ElasticsearchOperations 는 id 기반으로 엔티티를 저장하고 업데이트하고 가져오는 역할 및 검색 쿼리를 하고 갖고오는 역할을 수행한다.
    // RestHighLevelClient 를 주입시켜서 이 어플리케이션이 Elasticsearch Client 역할을 하게 된다.
    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient() {
        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo("localhost:9200")
                .build();

        return RestClients.create(clientConfiguration).rest();
    }


/*
    @Autowired
    RestHighLevelClient highLevelClient;

    RestClient lowLevelClient = highLevelClient.getLowLevelClient();

    IndexRequest request = new IndexRequest("spring-data")
            .id("id001")
            .source(singletonMap("feature","high-level-rest-client"))
            .setRefreshPolicy(IMMEDIATE);

    IndexResponse response = highLevelClient.index(request, RequestOptions.DEFAULT);

    SearchResponse searchResponse = highLevelClient.search();
*/


}
