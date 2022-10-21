package com.application.electronic_book.controller;

import com.application.electronic_book.model.others.GroupModel;
import com.application.electronic_book.model.response.Response;
import com.application.electronic_book.service.others.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/group")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;

    @PostMapping("/create")
    public Response<GroupModel> create(@RequestBody GroupModel groupModel){
        return new Response<>(groupService.create(groupModel));
    }
}
