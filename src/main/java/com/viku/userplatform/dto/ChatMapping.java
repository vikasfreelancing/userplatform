package com.viku.userplatform.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
@Getter
@Setter
@Document
public class ChatMapping {
    @Id
    private String id;
    private String firstEmail;
    private String secondEmail;
}
