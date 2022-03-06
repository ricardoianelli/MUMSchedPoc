package com.example.mumschedpoc.entities.enums;

public enum UserRole {
    ADMIN(1),
    FACULTY(2),
    STUDENT(3);

    private final int code;

    UserRole(int code)
    {
        this.code = code;
    }

    public int getCode()
    {
        return code;
    }

    public static UserRole valueOf(int code)
    {
        for (UserRole value : UserRole.values())
        {
            if (value.getCode() == code)
            {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid UserRole code");
    }
}
