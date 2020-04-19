package com.viku.userplatform.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.viku.userplatform.dao.FoundItem;
import com.viku.userplatform.dao.LostItem;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
@Getter
@Setter
public class LostItemDetails {
    LostItem lostItem;
    FoundItem foundItem;
    User user;
}
