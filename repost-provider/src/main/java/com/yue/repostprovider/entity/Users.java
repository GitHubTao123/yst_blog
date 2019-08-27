package com.yue.repostprovider.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Data
@EqualsAndHashCode
@Getter
@Setter
public class Users implements Serializable {
    private int userId;
    private String userName;
    private String userPw;
    private String userAddr;
}
