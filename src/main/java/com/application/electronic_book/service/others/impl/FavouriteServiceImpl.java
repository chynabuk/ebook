package com.application.electronic_book.service.others.impl;

import com.application.electronic_book.entity.Book;
import com.application.electronic_book.entity.Favourite;
import com.application.electronic_book.entity.User;
import com.application.electronic_book.model.others.BookModel;
import com.application.electronic_book.model.others.FavouriteModel;
import com.application.electronic_book.repository.FavouriteRepository;
import com.application.electronic_book.repository.UserRepository;
import com.application.electronic_book.service.others.BookService;
import com.application.electronic_book.service.others.FavouriteService;
import com.application.electronic_book.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class FavouriteServiceImpl implements FavouriteService {
    private final UserRepository userRepository;
    private final UserService userService;
    private final BookService bookService;

    @Override
    public FavouriteModel create(FavouriteModel favouriteModel) {
        User user = userService.getEntityById(favouriteModel.getUserId());

        for (BookModel book : favouriteModel.getBookModels()){
            user.getFavourites().add(bookService.toEntity(book));
        }

        userRepository.save(user);

        return favouriteModel;
    }

    @Override
    public String delete(Long userId, Long index) {
        User user = userService.getEntityById(userId);

        List<Book> favourites = user.getFavourites();
        for (int i = 0; i < favourites.size(); i++){
            if (i == index){
                favourites.remove(favourites.get(i));
            }
        }

        userRepository.save(user);
        return "Book is deleted from favourites";
    }

    @Override
    public Favourite getEntityById(Long id) {
        return null;
    }

    @Override
    public FavouriteModel getById(Long id) {
        return null;
    }

    @Override
    public List<FavouriteModel> getAll() {

        return null;
    }

    @Override
    public FavouriteModel toModel(User user) {
        List<Book> favourites = user.getFavourites();
        return new FavouriteModel(
                user.getId(), favourites.stream().map(book -> bookService.toModel(book)).collect(Collectors.toList()));
    }

    @Override
    public FavouriteModel update(FavouriteModel favouriteModel) {

        return null;

    }
}
