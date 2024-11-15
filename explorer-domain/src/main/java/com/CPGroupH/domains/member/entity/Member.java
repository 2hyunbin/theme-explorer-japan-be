package com.CPGroupH.domains.member.entity;

import com.CPGroupH.common.enums.MemberRole;
import com.CPGroupH.domains.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PreRemove;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "members")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;

    @Column(nullable = false, length = 255)
    private String email;

    private String profileImage;

    private Boolean allowance;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private MemberRole role;

    @Column
    private LocalDateTime deletedAt;

    @Builder
    public Member(String nickname, String email, String profileImage, Boolean allowance, MemberRole role) {
        this.nickname = nickname;
        this.email = email;
        this.profileImage = profileImage;
        this.allowance = allowance;
        this.role = role;
    }

    @PreRemove
    public void onDelete() {
        this.deletedAt = LocalDateTime.now();
    }

    public void updateAllowance() {
        this.allowance = true;
    }
}

