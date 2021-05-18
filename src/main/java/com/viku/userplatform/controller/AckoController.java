package com.viku.userplatform.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.viku.userplatform.dto.CreateTeamRequest;
import com.viku.userplatform.service.AckoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


//TODO: instead of sending string response we can have common response object and add controller advice
@Slf4j
@RestController
@RequestMapping("/api/v1/acko")
@CrossOrigin(maxAge = 3600)
public class AckoController {
    @Autowired
    private AckoService ackoService;
    @Autowired
    private ObjectMapper objectMapper;


    @PostMapping(value = "/create", produces = "application/json")
    public String createTeam(@RequestBody String request) {
        try {
            CreateTeamRequest createTeamRequest = objectMapper.readValue(request, CreateTeamRequest.class);
            ackoService.createTeam(createTeamRequest);
            return "SUCCESS";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @GetMapping("/alert/{teamId}")
    public String sendAlerts(@PathVariable String teamId) {
        try {
            Long id = Long.parseLong(teamId);
            return ackoService.sendAlert(id);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
