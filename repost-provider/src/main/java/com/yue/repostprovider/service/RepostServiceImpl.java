package com.yue.repostprovider.service;

import com.yue.repostprovider.entity.Repost;
import com.yue.repostprovider.mapper.RepostMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@Service("repostService")
public class RepostServiceImpl implements RepostService{

    @Autowired
    private RepostMapper repostMapper;

    @Override
    public void repost(int commId,String repCont,int loginUserId) {
        long timeMillis = System.currentTimeMillis();
        Repost repost = new Repost();
        repost.setRepCont(repCont);
        repost.setRepTime(timeMillis);
        repostMapper.addRepostCont(repost);
        Map<String,Integer> map = new HashMap<>();
        map.put("id",repost.getId());
        map.put("commId",commId);
        map.put("loginUserId",loginUserId);
        repostMapper.addRepostAndComm(map);
        repostMapper.addRepostAndUser(map);
    }
}
