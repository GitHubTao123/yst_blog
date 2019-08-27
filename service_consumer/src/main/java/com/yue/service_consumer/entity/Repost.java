package com.yue.service_consumer.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Data
@EqualsAndHashCode
@Getter
@Setter
public class Repost implements Serializable {
    private int id;
    private String repCont;
    private Long repTime;
}
