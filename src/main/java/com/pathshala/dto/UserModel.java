package com.pathshala.dto;

import com.pathshala.dao.MetaData;
import com.pathshala.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserModel extends MetaData {
    private int id;

    private String name;

    private String email;

    private String phoneNumber;

    private UserType userType;

    private String userId;

    private String password;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserModel userModel = (UserModel) o;
        return id == userModel.id && Objects.equals(name, userModel.name) && Objects.equals(email, userModel.email) && Objects.equals(phoneNumber, userModel.phoneNumber) && userType == userModel.userType && Objects.equals(userId, userModel.userId) && Objects.equals(password, userModel.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, phoneNumber, userType, userId, password);
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", userType=" + userType +
                ", userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
