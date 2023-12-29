package com.imooc.springcloud.irule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.Server;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class ConsistenceHashRule extends AbstractLoadBalancerRule {

    public ConsistenceHashRule(){

    }

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {



    }

    @Override
    public Server choose(Object key) {
        HttpServletRequest request =((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String uri = request.getServletPath() + "?" + request.getQueryString();
        return route(uri.hashCode(),getLoadBalancer().getAllServers());
    }


    public Server route(int hashId, List<Server> addressList){
        if (CollectionUtils.isEmpty(addressList)) return null;
        TreeMap<Long,Server> address = new TreeMap<>();
        addressList.forEach(row -> {
            for (int i = 0; i < 8; i++) {
                long hash = hash(row.getId() + i);
                address.put(hash,row);
            }
        });
        SortedMap<Long, Server> longServerSortedMap = address.tailMap(hash(Long.toString(hashId)));
        if (longServerSortedMap.isEmpty()){
            return address.firstEntry().getValue();
        }
        return address.get(longServerSortedMap.firstKey());

    }

    public Long hash(String key){
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = key.getBytes(StandardCharsets.UTF_8);
            md5.update(bytes);
            byte[] digest = md5.digest();
            long hashCode = ((long)(digest[2] & 0xFF << 16)) | ((long)(digest[1] & 0xFF << 8)) | ((long)(digest[0] & 0xFF));
            return hashCode & 0xffffffffL;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
}
