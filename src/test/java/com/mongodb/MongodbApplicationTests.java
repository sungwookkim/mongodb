package com.mongodb;

import com.mongodb.entity.Posts;
import com.mongodb.service.PostsServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MongodbApplicationTests {

	@Autowired
	PostsServiceImpl postsServiceImpl;

	@Test
	void mongodb_게시물_정상_케이스() {
		Posts posts = new Posts("success_id", "mongodb Insert!");

		this.postsServiceImpl.savePosts(posts, false);
	}

	@Test
	void mongodb_게시물_임의_예외_발생_롤백_케이스() {
		Posts posts = new Posts("fail_id", "mongodb Rollback!");

		this.postsServiceImpl.savePosts(posts, true);
	}
}
