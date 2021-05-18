package com.viku.userplatform.repository;

import com.viku.userplatform.dao.Developer;
import com.viku.userplatform.dao.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer,Long> {
    List<Developer> findByTeamId(Long id);
}
