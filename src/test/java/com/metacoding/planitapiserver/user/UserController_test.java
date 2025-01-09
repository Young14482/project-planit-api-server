package com.metacoding.planitapiserver.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserController_test {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserService userService;  // 서비스 Mock

    @InjectMocks
    private UserController userController;  // 컨트롤러 Mock

    @Autowired
    private ObjectMapper objectMapper;  // JSON 변환용

    @Test
    public void findIDByEmailDTO_test() throws Exception {
        // 테스트를 위한 가짜 데이터
        String email = "ssar@nate.com";
        UserRequest.FindIdByEmailDTO findIdByEmailDTO = new UserRequest.FindIdByEmailDTO();
        findIdByEmailDTO.setEmail(email);

        // Mock UserService의 findIDByEmailDTO 메서드
        new User();
        User user = User.builder().username("ssar").email(email).build();  // 실제 User 객체 생성
        UserResponse.FindIdDTO findIdDTO = new UserResponse.FindIdDTO(user);

        when(userService.findIDByEmailDTO(any(UserRequest.FindIdByEmailDTO.class))).thenReturn(findIdDTO);

        // MockMvc를 사용하여 POST 요청 보내기
        System.out.println("//////////////////////////////////////////////////////////////////////////////////////////////////////////");
        mockMvc.perform(post("/find-id")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(findIdByEmailDTO)))
                .andDo(print())
                .andExpect(status().isOk()) // 상태 코드가 200 OK인지 확인
                .andExpect(jsonPath("$.response.username").value("ssar"))
                .andExpect(jsonPath("$.success").value("true"));
    }

}
