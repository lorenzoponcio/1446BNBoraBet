package com.example.auth_service.domain.user.vo;

import lombok.Getter;

@Getter
public enum RoleType {
    USER(1),
    SUPPORT(2),
    RISK_MANAGER(3),
    ADMIN(4),
    COMPLIANCE(5);

    @Getter
    private final int level;

    RoleType(int level) {
        this.level = level;
    }

    public boolean covers(RoleType other) {
        return this.level >= other.level;
    }
}
