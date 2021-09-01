package com.example.esclient.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;

@Data
@Document(indexName = "person")
@RequiredArgsConstructor
public class Person {
    @Id
    @Field(type = FieldType.Long)
    String id;

    @Field(type = FieldType.Keyword)
    String firstname;

    @Field(type = FieldType.Keyword)
    String lastname;

    @Field(type = FieldType.Keyword)
    String email;

    @Field(type = FieldType.Date)
    String dateOfBirth;

}
