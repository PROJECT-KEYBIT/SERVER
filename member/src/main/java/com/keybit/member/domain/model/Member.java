package com.keybit.member.domain.model;

import com.keybit.member.domain.model.vo.Email;
import com.keybit.member.domain.model.vo.IDName;
import com.keybit.member.domain.model.vo.Password;
import com.keybit.member.domain.model.vo.UserRole;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Member {

    @Id @GeneratedValue
    private Long memberNo;

    @Embedded
    private IDName idName;

    @Embedded
    private Password password;

    @Embedded
    private Email email;

    @Enumerated(EnumType.STRING)
    @ElementCollection
    private Set<UserRole> roles = new HashSet<>();

    public static Member registerMember(String id, String name, String password, String email) {
        IDName newIDName = IDName.create(id, name);
        Password newPassword = Password.create(password);
        Email newEmail = Email.create(email);
        return new Member(newIDName, newPassword, newEmail);
    }

    public Member login(IDName idName, Password password) {
        return this;
    }

    public void logout(IDName idName) {

    }

    public Long getMemberNo() {
        return memberNo;
    }

    public String getId() {
        return getIdName().getId();
    }

    public String getName() {
        return getIdName().getName();
    }

    public String getEmail() {
        return email.getAddress();
    }

    private IDName getIdName() {
        return idName;
    }

    private Member(IDName idName, Password password, Email email) {
        this.idName = idName;
        this.password = password;
        this.email = email;
        this.roles.add(UserRole.USER);
    }

    protected Member() {}
}
