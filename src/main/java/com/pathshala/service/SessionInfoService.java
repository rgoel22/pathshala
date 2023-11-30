package com.pathshala.service;

import com.pathshala.dao.SessionInfoEntity;
import com.pathshala.exception.NotFoundException;
import com.pathshala.repository.SessionInfoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@AllArgsConstructor
public class SessionInfoService {
    private SessionInfoRepository sessionInfoRepository;

    public SessionInfoEntity findByUserIdAndIsActive(Long userId){
        Optional<SessionInfoEntity> sessionInfo = sessionInfoRepository.findByUserIdAndIsActiveTrue(userId);
        if(!sessionInfo.isPresent()){
            throw new NotFoundException("", "");
        }
        return sessionInfo.get();
    }

    public Boolean createSession(Long userId, String token) {
        SessionInfoEntity sessionInfo = SessionInfoEntity.builder().userId(userId).sessionToken(token).isActive(true).build();
        SessionInfoEntity savedSession = sessionInfoRepository.save(sessionInfo);
        return savedSession != null;
    }

    @Transactional
    public void expireToken(){
        sessionInfoRepository.expireToken();
    }

    @Transactional
    public Boolean expireSessionForUserId(Long userId) {
        return sessionInfoRepository.expireSessionByUserId(userId) != 0;
    }
}
