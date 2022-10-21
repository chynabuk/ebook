package com.application.electronic_book.service.others.impl;

import com.application.electronic_book.entity.Author;
import com.application.electronic_book.exception.EBookException;
import com.application.electronic_book.model.others.AuthorModel;
import com.application.electronic_book.repository.AuthorRepository;
import com.application.electronic_book.service.others.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Override
    public AuthorModel create(AuthorModel authorModel) {
        Author author = new Author(authorModel.getFullName());
        authorRepository.save(author);

        authorModel.setId(author.getId());
        return authorModel;
    }

    @Override
    public String delete(Long id) {
        Author author = getEntityById(id);

        authorRepository.delete(author);

        return "Author with id:" + id + " was deleted";
    }

    @Override
    public Author getEntityById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(()->new EBookException("Author with id:" + id + " was not found"));
    }

    @Override
    public AuthorModel getById(Long id) {
        return toModel(getEntityById(id));
    }

    @Override
    public List<AuthorModel> getAll() {
        return null;
    }

    @Override
    public AuthorModel toModel(Author author) {
        return new AuthorModel(author.getId(), author.getFullName());
    }

    @Override
    public AuthorModel update(AuthorModel authorModel) {
        return null;
    }
}
