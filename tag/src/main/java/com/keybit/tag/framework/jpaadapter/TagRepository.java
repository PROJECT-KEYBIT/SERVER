package com.keybit.tag.framework.jpaadapter;

import com.keybit.tag.domain.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface TagRepository extends JpaRepository<Tag, String> {
    Tag findByName(String name);
    List<Tag> findByNameIn(Set<String> names);
}