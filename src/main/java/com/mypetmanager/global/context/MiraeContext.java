package com.mypetmanager.global.context;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MiraeContext {
    private String traceId;
    private String spanId;
    private Map<String,String> traceState;
    private String channelId;
    private String userId;


}
