package com.application.electronic_book.service.others;

import com.application.electronic_book.entity.Book;
import com.application.electronic_book.entity.Favourite;
import com.application.electronic_book.entity.User;
import com.application.electronic_book.model.others.FavouriteModel;
import com.application.electronic_book.service.basic_interfaces.*;

public interface FavouriteService extends
        Create<FavouriteModel, FavouriteModel>,
        Read<FavouriteModel, Favourite>,
        Update<FavouriteModel>,
        ToModelConverter<FavouriteModel, User>
{
    String delete(Long userId, Long index);
}
