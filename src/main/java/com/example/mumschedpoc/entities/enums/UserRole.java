package com.example.mumschedpoc.entities.enums;

public enum UserRole {
    ADMIN(1, "ROLE_ADMIN"),
    FACULTY(2, "ROLE_FACULTY"),
    STUDENT(3, "ROLE_STUDENT");

    private final int code;
    private final String description;

    UserRole(int code, String description)
    {
        this.code = code;
        this.description = description;
    }

    public int getCode()
    {
        return code;
    }
    public String getDescription()
    {
        return description;
    }

    public static UserRole toEnum(Integer code)
    {
        if (code == null) return null;

        for (UserRole value : UserRole.values())
        {
            if (code.equals(value.getCode()))
            {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid UserRole code: " + code);
    }

    public static Integer getCodeFromDescription(String description)
    {
        if (description.equals(null)) return null;

        for (UserRole role : UserRole.values())
        {
            if (description.equals(role.getDescription()))
            {
                return role.code;
            }
        }
        throw new IllegalArgumentException("Invalid UserRole with description: " + description);
    }
}
