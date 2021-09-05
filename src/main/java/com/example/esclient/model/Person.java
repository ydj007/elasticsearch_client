package com.example.esclient.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;

// ES에 저장할 document 객체를 정의하기 위해 @Document 어노테이션을 사용한다.
// 각 필드에 @Field 어노테이션을 사용해서 document에 저장되는 필드의 유형을 정의한다.
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
