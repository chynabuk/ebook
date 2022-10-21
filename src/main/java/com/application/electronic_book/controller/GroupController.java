package com.application.electronic_book.controller;

import com.application.electronic_book.model.others.GroupModel;
import com.application.electronic_book.model.response.Response;
import com.application.electronic_book.service.others.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/group")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;

    @PostMapping("/create")
    public Response<GroupModel> create(@RequestBody GroupModel groupModel){
        return new Response<>(groupService.create(groupModel));
    }

    @GetMapping
    public Response<List<GroupModel>> getAll(){
        return new Response<>(groupService.getAll());
    }

    @DeleteMapping("/{id}")
    public Response<GroupModel> deleteGroup(@PathVariable("id") Long id){
        return new Response<>(groupService.delete(id));
    }

}
