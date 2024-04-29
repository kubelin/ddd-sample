package com.mypetmanager.global.context;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MyContext {
	private String traceId;
	private String spanId;
	private Map<String, String> traceState;
	private String channelId;
	private String userId;

}
