package com.metacoding.planitapiserver.todo;

import com.metacoding.planitapiserver._core.error.exception.Exception403;
import com.metacoding.planitapiserver._core.error.exception.Exception404;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class TodoService {
    private final TodoRepository postRepository;

//    @Transactional
//    public PostResponse.DTO 게시글쓰기(PostRequest.SaveDTO requestDTO, User sessionUser){
//        ToDo postPS = postRepository.save(requestDTO.toEntity(sessionUser));
//        return new PostResponse.DTO(postPS);
//    }

    public TodoResponse.PageDTO 게시글목록보기(int page, LocalDate date){
        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "id"));
        Page<ToDo> postPG = postRepository.mFindAll(pageable, date);
        return new TodoResponse.PageDTO(postPG);
    }

    public TodoResponse.DetailDTO 게시글상세보기(int id, int sessionUserId){
        // Post+User 조회
        ToDo postPS = postRepository.mFindById(id)
                .orElseThrow(() -> new Exception404("해당 id를 찾을 수 없습니다 : "+id));

        return null;
    }

//    @Transactional
//    public PostResponse.DTO 게시글수정하기(int id, PostRequest.UpdateDTO requestDTO, int sessionUserId){
//        ToDo postPS = postRepository.findById(id)
//                .orElseThrow(() -> new Exception404("해당 id를 찾을 수 없습니다 : "+id));
//
//        if(postPS.getUser().getId() != sessionUserId) throw new Exception403("게시글을 수정할 권한이 없습니다");
//
//        postPS.update(requestDTO.getTitle(), requestDTO.getContent());
//
//        return new PostResponse.DTO(postPS);
//    }

    @Transactional
    public void 게시글삭제하기(int id, int sessionUserId){
        ToDo postPS = postRepository.findById(id)
                .orElseThrow(() -> new Exception404("해당 id를 찾을 수 없습니다 : "+id));
        if(postPS.getUser().getId() != sessionUserId) throw new Exception403("게시글을 삭제할 권한이 없습니다");
        postRepository.deleteById(id);
    }
}
