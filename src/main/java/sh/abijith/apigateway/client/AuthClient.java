package sh.abijith.apigateway.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "auth-service")
public interface AuthClient {

    @PostMapping("/api/auth/validate")
    ResponseEntity<Void> validateToken(@RequestHeader("Authorization") String token);
}