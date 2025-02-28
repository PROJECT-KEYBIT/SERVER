package com.keybit.tag.framework.jpaadaptor;

import com.keybit.tag.domain.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, String> {
    Tag findByName(String name);
}