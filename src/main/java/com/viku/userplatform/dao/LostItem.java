package com.viku.userplatform.dao;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.Date;

@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
@Getter
@Setter
@Document
public class LostItem {
    @Id
    private String id;
    private String images;
    private String userId;
    private String type;
    private boolean isFound;
    private String foundId;
    @CreatedDate
    @JsonFormat(pattern="EEEE dd-MM-yyyy HH:mm:ss")
    private Date createdAt;
    @JsonFormat(pattern="EEEE dd-MM-yyyy HH:mm:ss")
    @LastModifiedDate
    private Date modifiedAt;
}
