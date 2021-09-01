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


    public RestClientConfig() throws IOException {
    }

    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient() {
        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo("localhost:9200")
                .build();

        return RestClients.create(clientConfiguration).rest();
    }

    public ElasticsearchOperations elasticsearchOperations() {
        return new ElasticsearchRestTemplate(elasticsearchClient());
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
