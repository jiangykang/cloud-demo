package cn.itcast.user.service;

import cn.itcast.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

//    @Autowired
//    private UserDao userDao;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient; //Eureka客户端，可以获取服务实例信息

    public List<User> queryUserByIds(List<Long> ids){
        List<User> users = new ArrayList<>();

        List<ServiceInstance> instances = discoveryClient.getInstances("user-service");
        ServiceInstance instance = instances.get(0);

        //获取ip和端口信息
        //String baseUrl = "http://"+instance.getHost() + ":" + instance.getPort()+"/user/";

        // 地址直接写服务名称即可
        String baseUrl = "http://user-service/user/";

        ids.forEach(id -> {
            //测试多次查询
            users.add(this.restTemplate.getForObject(baseUrl + id, User.class));
            //每次间隔500毫秒
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        return users;
    }
}