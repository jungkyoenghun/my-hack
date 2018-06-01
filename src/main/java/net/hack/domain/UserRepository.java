package net.hack.domain;

import org.springframework.data.jpa.repository.JpaRepository;


//User class에 대한 Repository이고 id의 타입은 Long 이다.
// 이렇게 구현하면 데이터를 입력하고 꺼내오는 것이 가능하다
public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByUserId(String userId);

}
