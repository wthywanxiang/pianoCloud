package com.example.demogateway.filter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.democommon.util.AdminTokenUtil;
import com.example.democommon.util.UserTokenUtil;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import org.springframework.http.HttpMethod;
@Component
public class GatewayGlobalFilter implements GlobalFilter, Ordered {

    public static final String START_VISIT_TIME = "startVisitTime";
    private static final String AUTHORIZATION_HEADER = "Authorization";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        HttpMethod method = exchange.getRequest().getMethod();
        String path = exchange.getRequest().getURI().getPath();
        // 处理OPTIONS请求与登录请求，直接放行
        if (method == HttpMethod.OPTIONS
                || path.startsWith("/api/user/teacherLogin")
                || path.startsWith("/api/user/studentLogin")
                || path.startsWith("/api/login")
        ) {
            return chain.filter(exchange);
        }
        if (path.startsWith("/api/user")) {
            String authorizationHeader = exchange.getRequest().getHeaders().getFirst(AUTHORIZATION_HEADER);
            try {
                UserTokenUtil.analysisToken(authorizationHeader);
            } catch (Exception e){
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
        } else if (path.startsWith("/api/admin")) {
            String authorizationHeader = exchange.getRequest().getHeaders().getFirst(AUTHORIZATION_HEADER);
            try {
                AdminTokenUtil.analysisToken(authorizationHeader);
            } catch (Exception e){
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
        }

        exchange.getAttributes().put(START_VISIT_TIME, System.currentTimeMillis());
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            Long startVisitTime = exchange.getAttribute(START_VISIT_TIME);
            if (startVisitTime != null) {
                //TODO 此处可引入slf4j打印日志信息
            }
        }));
    }

    // 数字越小优先级越高
    @Override
    public int getOrder() {
        return -1;
    }
}
