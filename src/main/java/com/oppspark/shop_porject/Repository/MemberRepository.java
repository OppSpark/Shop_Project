package com.oppspark.shop_porject.Repository;

import com.oppspark.shop_porject.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
}
