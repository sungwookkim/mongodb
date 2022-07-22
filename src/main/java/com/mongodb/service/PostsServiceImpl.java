package com.mongodb.service;

import com.mongodb.entity.Posts;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * <pre>
 *     MongoDB Multi-Transaction 테스트를 위한 구현체
 * </pre>
 */
@Service
public class PostsServiceImpl {
    private final MongoTemplate mongoTemplate;

    public PostsServiceImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * <pre>
     *     테스트 목적을 위한 게시물 저장 메서드
     * </pre>
     * @param posts
     * @param isTransactionsTest 예외 발생 시 롤백이 수행되는지 확인 하기 위한 변수
     */
    @Transactional(rollbackFor = Exception.class)
    public void savePosts(Posts posts, boolean isTransactionsTest) {
        this.mongoTemplate.insert(Objects.requireNonNull(posts), "posts");
        this.mongoTemplate.insert(Objects.requireNonNull(posts), "posts_transactions");

        if(isTransactionsTest) {
            throw new IllegalStateException("트랜잭션 테스트를 위한 임시 예외 발생");
        }
    }
}
