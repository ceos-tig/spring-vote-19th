package ceos.vote_be.member.repository;

import ceos.vote_be.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface MemberRepository extends JpaRepository<Member,Long> {
    public Optional<Member> findByLoginId(String loginId);

    public Optional<Member> findByEmail(String email);
}
