package com.paydi.service;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paydi.entity.MMSAppAccessEntity;
import com.paydi.repository.MMSUtilRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import io.sentry.Sentry;

@Service
public class AuthenApiService {

    @Autowired
    private StringRedisTemplate template;
    @Autowired
    private MMSUtilRepository ultilRepository;

    public MMSAppAccessEntity checkApiKey(String apiKey) {

        MMSAppAccessEntity appAccessEntity = null;
        try {
            // In ra màn hình Giá trị của key "loda" trong Redis
            String listString = (String) template.opsForValue().get("accessInfo");

            ObjectMapper mapper = new ObjectMapper();
            List<MMSAppAccessEntity> list = null;
            if (listString != null) {
                list = mapper.readValue(listString, new TypeReference<List<MMSAppAccessEntity>>() {

                });
            }
            if (list == null) {
                list = new ArrayList<MMSAppAccessEntity>();
            } else {
                for (int i = 0; i < list.size(); i++) {
                    MMSAppAccessEntity mmsAppAccessEntity = list.get(i);

                    if (mmsAppAccessEntity.getApiKey().equals(apiKey)) {
                        appAccessEntity = mmsAppAccessEntity;
                    }
                }

            }

            if (appAccessEntity == null) {
                appAccessEntity = ultilRepository.checkApiKey(apiKey);
                if (appAccessEntity != null) {

                    list.add(appAccessEntity);
                    String jsonInString = mapper.writeValueAsString(list);
                    template.opsForValue().set("accessInfo", jsonInString);
                }

            }

            return appAccessEntity;
        } catch (Exception e) {
            e.printStackTrace();
            Sentry.captureException(e);
            return null;
        }
    }
}
