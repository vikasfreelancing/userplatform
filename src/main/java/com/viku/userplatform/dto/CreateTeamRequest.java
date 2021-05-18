package com.viku.userplatform.dto;

import com.sun.crypto.provider.Preconditions;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
public class CreateTeamRequest {
    Team team;
    List<Developers> developers;

    @Getter
    @Setter
    @ToString
    public static class Team{
        String name;
    }

    @Getter
    @Setter
    @ToString
    public static class Developers{
        String name;
        String phoneNumber;
    }

    public void validateRequest(){
        if(Objects.isNull(team) || CollectionUtils.isEmpty(developers) || invalidDeveloper())
        {
            throw new IllegalArgumentException("Invalid request");
        }
    }

    private boolean invalidDeveloper() {
        for(Developers dev : developers)
        {
            if(StringUtils.isEmpty(dev.getName()) || StringUtils.isEmpty(dev.getPhoneNumber()))
                return true;
        }
        return false;
    }
}


//{"team": {"name": "claims"}, "developers": [{"name": "someone", "phone_number":
//        "9999999999"}, {"name": "somebody", "phone_number": "9111111111"}]}