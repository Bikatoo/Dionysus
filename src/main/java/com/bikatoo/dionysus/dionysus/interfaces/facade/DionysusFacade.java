package com.bikatoo.dionysus.dionysus.interfaces.facade;

import com.bikatoo.dionysus.dionysus.interfaces.DTO.CommonResult;
import com.bikatoo.dionysus.dionysus.interfaces.request.RequestModel;
import com.bikatoo.dionysus.dionysus.interfaces.request.RequestParser;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class DionysusFacade {

    @PostMapping("/config")
    public CommonResult<JsonNode> config(HttpServletRequest request) {
        RequestModel model = RequestParser.parse(request);
        return CommonResult.ok(null);
    }
}
