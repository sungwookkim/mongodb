package com.mongodb.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

/**
 * <pre>
 *     MongoDB Multi-Transaction 테스트를 위해 생성한 게시물 엔티티
 * </pre>
 */
@Document(collation = "posts")
public class Posts {
    private final String memberId;
    private final String text;
    private final LocalDateTime registrationDate;

    public Posts(String memberId, String text) {
        this.memberId = memberId;
        this.text = text;
        this.registrationDate = LocalDateTime.now();
    }

    public String getMemberId() {
        return memberId;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }
}
