package com.scott.neptune.authclient.client;

import com.scott.neptune.authclient.hystric.AuthClientFallbackFactory;
import com.scott.neptune.common.config.FeignConfig;
import com.scott.neptune.userclient.dto.AuthUserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * User服务的远程调用接口
 *
 * @author scott
 */
@FeignClient(name = "auth-server", configuration = FeignConfig.class, fallbackFactory = AuthClientFallbackFactory.class)
public interface AuthClient {

    @RequestMapping(path = "/auth/username/{username}", method = RequestMethod.GET)
    AuthUserDto loadUserByUsername(@PathVariable("username") String username);
}