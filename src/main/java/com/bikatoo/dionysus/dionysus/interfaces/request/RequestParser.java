package com.bikatoo.dionysus.dionysus.interfaces.request;

import com.bikatoo.dionysus.dionysus.exception.GlobalException;
import com.bikatoo.dionysus.dionysus.interfaces.terminal.Terminals;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static com.bikatoo.dionysus.dionysus.utils.PreconditionUtils.checkConditionAndThrow;

@Slf4j
public final class RequestParser {

    private static final String[] default_required_arr = new String[] {"terminal", "uuid"};

    // 默认的请求必选入参
    private static final Set<String> default_required = new HashSet<>(Arrays.asList(default_required_arr));

    public static RequestModel parse(@NonNull HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = request.getReader()) {
            String str;
            while ((str = br.readLine()) != null) {
                sb.append(str);
            }
        } catch (IOException e) {
            log.error("request body read fail", e);
        }
        ObjectMapper mp = new ObjectMapper();
        try {
            JsonNode reqData = mp.readTree(sb.toString());
            checkRequired(reqData);

            return doParse(reqData);
        } catch (JsonProcessingException e) {
            log.error("request body parse fail ", e);
        }
        throw new GlobalException("request body parse fail");
    }

    /**
     * 校验必选参数
     * @param data 请求参数
     */
    private static void checkRequired(JsonNode data) {

        // 校验必填参数存在
        default_required.forEach(v -> {
            checkConditionAndThrow(data.has(v), new GlobalException("Required parameter " + v + " is not present"));;
        });

        // 校验 terminal 合法性
        checkConditionAndThrow(Terminals.verifyTerminal(data.get("terminal").asText()), new GlobalException("terminal Illegal"));
    }

    private static RequestModel doParse(JsonNode data) {

        ObjectMapper mapper = new ObjectMapper();
        RequestModel model = mapper.convertValue(data, RequestModel.class);
        JsonNode extra = data.deepCopy();
        model.setExtra(extra);
        return model;
    }
}
