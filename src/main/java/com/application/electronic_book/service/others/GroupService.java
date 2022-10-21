package com.application.electronic_book.service.others;

import com.application.electronic_book.entity.Group;
import com.application.electronic_book.model.others.GroupModel;
import com.application.electronic_book.service.basic_interfaces.*;

public interface GroupService extends
        Create<GroupModel, GroupModel>, Read<GroupModel, Group>, Delete, Update<GroupModel>, ToModelConverter<GroupModel, Group> {
}
