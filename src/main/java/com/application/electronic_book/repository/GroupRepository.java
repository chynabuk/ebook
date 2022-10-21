package com.application.electronic_book.repository;

import com.application.electronic_book.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
}
