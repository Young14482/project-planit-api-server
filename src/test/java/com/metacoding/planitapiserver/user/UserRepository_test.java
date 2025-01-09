package com.metacoding.planitapiserver.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserRepository_test {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("findByEmail 메서드가 이메일로 사용자 찾기")
    public void findByEmail_test() {
        // given: 테스트 데이터를 저장
        User user = new User().builder()
                .email("test@test.com")
                .username("Test")
                .password("$2a$10$DDJOwWzVI3VE4jtDgc.OcOQymy1sbksVfA0uJ9tVF.p/WZiP1X3qy").build();
        userRepository.save(user);

        // when: findByEmail 메서드 호출
        Optional<User> result = userRepository.findByEmail("test@test.com");

        // then: 반환값 검증
        assertThat(result).isPresent(); // 결과가 존재해야 함
        assertThat(result.get().getEmail()).isEqualTo("test@test.com");
        assertThat(result.get().getUsername()).isEqualTo("Test");
    }

    @Test
    @DisplayName("이메일로 사용자가 존재하지 않으면 빈 Optional 반환")
    public void findByEmail_userNotFound_test() {
        // when: 존재하지 않는 이메일로 호출
        Optional<User> result = userRepository.findByEmail("nonexistent@test.com");

        // then: 반환값이 비어 있어야 함
        assertThat(result).isNotPresent(); // 결과가 존재하지 않아야 함
    }
    @Test
    @DisplayName("이메일과 사용자명으로 찾기")
    public void findByUsernameAndEmail_test() {
        // given: 테스트 데이터를 저장
        User user = new User().builder()
                .email("asdf@asdf.com")
                .username("asdf")
                .password("$2a$10$DDJOwWzVI3VE4jtDgc.OcOQymy1sbksVfA0uJ9tVF.p/WZiP1X3qy") // 암호화된 비밀번호
                .build();
        userRepository.save(user); // 실제로 DB에 저장됨을 확인

        // when: 데이터 조회
        Optional<User> result = userRepository.findByUsernameAndEmail("asdf@asdf.com", "asdf");

        // then: 값이 존재해야 한다
        assertThat(result).isPresent();  // 결과가 존재하는지 확인

        // 결과값을 추가로 검증
        User foundUser = result.get();
        assertThat(foundUser.getEmail()).isEqualTo("asdf@asdf.com");
        assertThat(foundUser.getUsername()).isEqualTo("asdf");
    }

}
