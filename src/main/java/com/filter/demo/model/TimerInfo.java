package com.filter.demo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TimerInfo {
 private int totalFireCount;
 private boolean runForever;
 private long repeatInterval;
 private long initialOffSet;
 private  String callBack;

}
