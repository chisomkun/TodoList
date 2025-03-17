package com.chiz.todo.todoList.todos;

import com.chiz.todo.todoList.model.ItemRequest;
import com.chiz.todo.todoList.response.TodoListItemResponse;
import com.chiz.todo.todoList.response.TodoListResponse;
import com.chiz.todo.todoList.todos.repository.ItemRepository;
import com.chiz.todo.todoList.util.RequestInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TodoListService {

    private final RequestInfo requestInfo;
    private final ItemRepository itemRepository;

    public TodoListItem addItem(ItemRequest request) {
        return itemRepository.save(
                new TodoListItem(requestInfo.getUserName(),
                request.title(),
                request.description()));
    }

    public TodoListItemResponse getItem(long id){
        TodoListItem item = itemRepository.getReferenceById(id);
        return new TodoListItemResponse(id,item.getTitle(),item.getDescription());
    }

    public TodoListItemResponse updateItem(long id, ItemRequest request){
        TodoListItem oldItem = itemRepository.getReferenceById(id);

        if(request.title() != null){
            oldItem.setTitle(request.title());
        }
        if(request.description() != null){
            oldItem.setDescription(request.description());
        }

        TodoListItem newItem = itemRepository.save(oldItem);
        return new TodoListItemResponse(id,newItem.getTitle(),newItem.getDescription());
    }

    public TodoListResponse getTodoList(Integer limitRq, Integer pageRq){
        int page = Objects.requireNonNullElse(pageRq, 0);
        int limit = Objects.requireNonNullElse(limitRq, 5); //default value

        Page<TodoListItem> paginatedRes = itemRepository.findAll(PageRequest.of(page,limit));
        List<TodoListItemResponse> todos = new ArrayList<>();
        for(TodoListItem s : paginatedRes.getContent()){
            todos.add(new TodoListItemResponse(s.getId(),
                    s.getTitle(),
                    s.getDescription()
            ));
        }
        return new TodoListResponse(todos,page,limit,paginatedRes.getTotalElements());
    }

    public String deleteTodoItem(long id){
        itemRepository.deleteById(id);
        return "Item deleted successfully! :)";
    }
}
