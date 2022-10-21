package com.application.electronic_book.service.others;

import com.application.electronic_book.entity.Publisher;
import com.application.electronic_book.model.others.PublisherModel;
import com.application.electronic_book.service.basic_interfaces.*;

public interface PublisherService extends
        Create<PublisherModel, PublisherModel>,
        Read<PublisherModel, Publisher>,
        Delete,
        Update<PublisherModel>,
        ToModelConverter<PublisherModel, Publisher>
{
}
