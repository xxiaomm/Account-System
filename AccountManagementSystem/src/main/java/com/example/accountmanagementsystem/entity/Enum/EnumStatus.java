package com.example.accountmanagementsystem.entity.Enum;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

/**reference
 * https://www.jdon.com/54272.html
 * https://www.youtube.com/watch?v=I2lXDt2Ss6A
 */

public enum EnumStatus {
    // status in database will be int, 0: ACTIVE, 1: DELETED, etc.
    ACTIVE, INACTIVE, DELETED, SUSPENDED

//    DEACTIVATED,

//    INACTIVE,
//    STATUS1("ACTIVE"),
//    STATUS2("DELETED"),
//    STATUS3("DEACTIVATED"),
//    STATUS4("SUSPENDED");
//
//    private String status;
//
//    EnumStatus(String status) {
//        this.status = status;
//    }
//
//    @JsonValue
//    public String getStatus() {
//        return status;
//    }
//
//    @JsonCreator
//    public static EnumStatus setStatus(String status) {
//        return Stream.of(EnumStatus.values()).filter(targetEnum -> targetEnum.status.equals(status)).findFirst().orElse(null);
//    }
}
