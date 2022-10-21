package com.application.electronic_book.service.others.impl;

import com.application.electronic_book.entity.Group;
import com.application.electronic_book.entity.User;
import com.application.electronic_book.exception.EBookException;
import com.application.electronic_book.model.others.GroupModel;
import com.application.electronic_book.repository.GroupRepository;
import com.application.electronic_book.service.others.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;

    @Override
    public GroupModel create(GroupModel groupModel) {
        Group group = new Group();
        group.setName(groupModel.getName());

        groupModel.setId(group.getId());
        groupRepository.save(group);
        return groupModel;
    }

    @Override
    public String delete(Long id) {
        Group group = getEntityById(id);
        List<User> users = group.getReaders();

        if (users != null || !users.isEmpty()){
            for (User user : users){
                user.setGroup(null);
            }
        }

        groupRepository.save(group);
        groupRepository.delete(group);
        return "Group with id:" + id + " was deleted";
    }

    @Override
    public Group getEntityById(Long id) {
        return groupRepository.findById(id)
                .orElseThrow(() -> new EBookException("Group with id:" + id + " was not found"));
    }

    @Override
    public GroupModel getById(Long id) {
        return toModel(getEntityById(id));
    }

    @Override
    public List<GroupModel> getAll() {
        return groupRepository.findAll()
                .stream()
                .map(group -> toModel(group))
                .collect(Collectors.toList());
    }

    @Override
    public GroupModel toModel(Group group) {
        return new GroupModel(group.getId(), group.getName());
    }

    @Override
    public GroupModel update(GroupModel groupModel) {
        Group group = getEntityById(groupModel.getId());
        String name = groupModel.getName();
        if (name != null){
            group.setName(name);
        }

        groupRepository.save(group);
        return groupModel;
    }
}