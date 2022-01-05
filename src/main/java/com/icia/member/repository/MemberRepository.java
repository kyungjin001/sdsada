package com.icia.member.repository;

import com.icia.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

//JpaRepository<해당 Entity클리스이름, pk타입>
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
}
