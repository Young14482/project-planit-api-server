package com.metacoding.planitapiserver.category;

import com.metacoding.planitapiserver._core.error.exception.Exception404;
import com.metacoding.planitapiserver.todo.Todo;
import com.metacoding.planitapiserver.todo.TodoRepository;
import com.metacoding.planitapiserver.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final TodoRepository todoRepository;

    public CategoryResponse.DTOList find(Integer userId) {
        List<Category> allByUserId = categoryRepository.findAllByUserId(userId);
        CategoryResponse.DTOList dtoList = new CategoryResponse.DTOList(allByUserId);
        return dtoList;
    }

    @Transactional
    public CategoryResponse.DTO save(Integer userId, CategoryRequest.saveDTO requestDTO) {
        Category category = Category.builder().name(requestDTO.getName()).user(User.builder().id(userId).build()).build();
        categoryRepository.save(category);
        return new CategoryResponse.DTO(category);
    }

    @Transactional
    public CategoryResponse.DTO update(Integer categoryId, CategoryRequest.updateDTO requestDTO) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new Exception404("카테고리를 찾을 수 없습니다."));
        category.update(requestDTO);
        return new CategoryResponse.DTO(category);
    }

    @Transactional
    public void delete(Integer userId,Integer categoryId) {
        List<Todo> allByUserIdAndCategoryId = todoRepository.findAllByUserIdAndCategoryId(userId, categoryId);
        allByUserIdAndCategoryId.forEach(Todo::clearCategory);
        categoryRepository.deleteById(categoryId);
    }


    // TODO 카테고리 삭제시 기존에 있던 todo의 해당 카테고리 값을 null로 변경 해야함
}
