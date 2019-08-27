package com.yue.repostprovider.mapper;

import com.yue.repostprovider.entity.Repost;

import java.util.Map;

public interface RepostMapper {

    void addRepostCont(Repost repost);

    void addRepostAndComm(Map<String,Integer> map);

    void addRepostAndUser(Map<String,Integer> map);
}
