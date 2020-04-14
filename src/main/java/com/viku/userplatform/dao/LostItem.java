package com.viku.userplatform.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
@Getter
@Setter
public class LostItem {
    @Id
    private Long id;
    private String images;
    private String userId;
    private String type;
    private boolean isFound;
    private Long foundId;
}
