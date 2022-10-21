package com.application.electronic_book.service.others;

import com.application.electronic_book.entity.Author;
import com.application.electronic_book.model.others.AuthorModel;
import com.application.electronic_book.service.basic_interfaces.*;

public interface AuthorService extends
        Create<AuthorModel, AuthorModel>,
        Read<AuthorModel, Author>,
        Delete,
        Update<AuthorModel>,
        ToModelConverter<AuthorModel, Author>
{
}
