package com.back;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Rq {
    private final String actionName;
    private final Map<String,String> paramsMap;

    public Rq(String cmd) {

        String[] cmdBits = cmd.split("\\?",2);
        String queryString = cmdBits.length > 1 ? cmdBits[1] : "";
        actionName = cmdBits[0];

        paramsMap = Arrays.stream(queryString.split("&"))
                .map(part -> part.split("=",2))
                .filter(bits -> bits.length == 2 && !bits[0].trim().isEmpty() && !bits[1].trim().isEmpty())
                .collect(Collectors.toMap(
                        bits -> bits[0].trim(),
                        bits -> bits[1].trim()
                ));
    }

    public String getParam(String paramName, String defaultVale) {
        // 만약 해쉬맵 키가 서치키워드타입이면 해당 value반환 아니면 ""
        if (paramsMap.containsKey(paramName)) {
            return paramsMap.get(paramName);
        }
        return defaultVale;
    }

    public String getActionName() {
        return actionName;
    }

    public int getParamAsInt(String paramName, int defaultValue) {
        String value = getParam(paramName,"");
        if (value.isEmpty()) return defaultValue;
        try {
            return Integer.parseInt(value);
        }catch (NumberFormatException e){
            return defaultValue;
        }
    }
}
