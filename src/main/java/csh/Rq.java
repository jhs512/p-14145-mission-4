package csh;

import java.util.HashMap;
import java.util.Map;

public class Rq {
    private Map<String,String> map;

    public Rq(String input) {
        map = new HashMap<>();
        String[] adf = input.split("\\?");
        String actionName = adf.length > 0 ? adf[0] : "";
        String param = adf.length > 1 ? adf[1] : "";
        map.put("actionname", actionName);

        if("".equals(param)) return;

        String[] bbb = param.split("&");
        for (String s : bbb) {
            String[] kv = s.split("=");
            String val = kv.length > 1 ? kv[1] : "";
            if(val == "") continue;
            String key = kv[0].toLowerCase();
            System.out.println(key + "=" + val);
            map.put(key, val);
        }
    }

    public String getValue(String key, String defaultValue) {
        String v = map.get(key.toLowerCase());
        return v == null || v == "" ? defaultValue : v;
    }

    public int getIntValue(String key, int defaultValue) {
        return Integer.valueOf(getValue(key, String.valueOf(defaultValue)));
    }
}
