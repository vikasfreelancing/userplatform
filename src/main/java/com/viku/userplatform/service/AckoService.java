package com.viku.userplatform.service;

import com.viku.userplatform.dao.Developer;
import com.viku.userplatform.dao.Team;
import com.viku.userplatform.dto.CreateTeamRequest;
import com.viku.userplatform.dto.AlterRequest;
import com.viku.userplatform.repository.DeveloperRepository;
import com.viku.userplatform.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional(rollbackOn = Exception.class)
public class AckoService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    DeveloperRepository developerRepository;
    public void createTeam(CreateTeamRequest createTeamRequest){
        createTeamRequest.validateRequest();
        //TODO : check if team exist with same name
        Team team = new Team();
        team.setName(createTeamRequest.getTeam().getName());
        Team savedTeam = teamRepository.save(team);
        List<Developer> developers = createDevelopers(createTeamRequest.getDevelopers(),savedTeam.getId());
        //TODO: validations if dev exist with same mobile number
        developerRepository.saveAll(developers);
    }

    private List<Developer> createDevelopers(List<CreateTeamRequest.Developers> developers, Long id) {
        List<Developer> developerList = new ArrayList<>();
        developers.forEach(dev->{
            Developer developer = new Developer();
            developer.setTeamId(id);
            developer.setName(dev.getName());
            developer.setPhoneNumber(dev.getPhoneNumber());
            developerList.add(developer);
        });
        return developerList;
    }

    public String sendAlert(Long id) {
        List<Developer> developers = developerRepository.findByTeamId(id);
        AlterRequest sendAlterRequest = new AlterRequest();

        sendAlterRequest.setMessage("5XX increased");
        sendAlterRequest.setPhoneNumber(CollectionUtils.isEmpty(developers)?"9999999999":developers.get(0).getPhoneNumber());
        return callNotificationService(sendAlterRequest);
    }
    //TODO: we should autowire this doing this because of lack of time
    public String callNotificationService(AlterRequest sendAlterRequest){
        //NotificationTable - S,P,F
        String url = "https://run.mocky.io/v3/fd99c100-f88a-4d70-aaf7-393dbbd5d99f";
        RequestEntity<AlterRequest> requestRequestEntity = new RequestEntity(sendAlterRequest, HttpMethod.POST, URI.create(url));
        ResponseEntity<String> response = restTemplate.exchange(requestRequestEntity,String.class);
        if(response.getStatusCode()== HttpStatus.OK){
            return response.getBody();
        }
        else {
            //TODO: we can send kibana alerts for 5xx or 4xx
            return "FAIL TO SEND ALERT";
        }
    }
}
