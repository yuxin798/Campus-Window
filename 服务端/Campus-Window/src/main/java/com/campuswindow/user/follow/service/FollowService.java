package com.campuswindow.user.follow.service;

import com.campuswindow.user.follow.entity.Follow;
import com.campuswindow.user.follow.repository.FollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class FollowService {
    private final FollowRepository followRepository;

    @Autowired
    public FollowService(FollowRepository followRepository) {
        this.followRepository = followRepository;
    }


    public void followOtherUser(String userId, String toUserId) {
        String followId = UUID.randomUUID().toString().replaceAll("-", "");
        Follow follow = new Follow(followId, userId, toUserId);
        followRepository.save(follow);
    }

    public void cancelFollowOtherUser(String userId, String toUserId) {
        followRepository.deleteByUserIdAndToUserId(userId, toUserId);
    }

    public int findCountByUserIdAndToUserId(String userId, String toUserId) {
        return followRepository.findCountByUserIdAndToUserId(userId, toUserId);
    }

    public boolean mutualFollowIsOrNot(String userId, String toUserId) {
        int count = findCountByUserIdAndToUserId(userId, toUserId);
        if (count == 2){
            return true;
        }
        return false;
    }

    public boolean findFollowByUserIdAndToUserId(String userId, String toUserId) {
        int count = followRepository.findFollowByUserIdAndToUserId(userId, toUserId);
        if (count == 1){
            return true;
        }
        return false;
    }
}
