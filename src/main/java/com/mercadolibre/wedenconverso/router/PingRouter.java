package com.mercadolibre.wedenconverso.router;

import com.newrelic.api.agent.NewRelic;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PingRouter {

    /**
     * @return "pong" String.
     */
    @GetMapping("/ping")
    public String ping() {
        NewRelic.ignoreTransaction();
        return "pong";
    }
}
