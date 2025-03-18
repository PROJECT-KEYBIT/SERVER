package com.keybit.tag.framework.jpaadapter;

import com.keybit.tag.domain.tag.model.Tag;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface TagRepository extends MongoRepository<Tag, String> {
    Tag findByName(String name);
    List<Tag> findByNameIn(Set<String> names);
    Set<Tag> findByNameInAndProductIdsNotContaining(Set<String> names, String productId);
}