package com.application.electronic_book.service.others.impl;

import com.application.electronic_book.entity.Publisher;
import com.application.electronic_book.exception.EBookException;
import com.application.electronic_book.model.others.PublisherModel;
import com.application.electronic_book.repository.PublisherRepository;
import com.application.electronic_book.service.others.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PublisherServiceImpl implements PublisherService {
    private final PublisherRepository publisherRepository;

    @Override
    public PublisherModel create(PublisherModel publisherModel) {
        Publisher publisher = new Publisher(publisherModel.getName());
        publisherRepository.save(publisher);

        publisherModel.setId(publisher.getId());
        return publisherModel;
    }

    @Override
    public String delete(Long id) {
        Publisher publisher = getEntityById(id);
        publisherRepository.delete(publisher);
        return "Publisher with id:" + id + " was deleted";
    }

    @Override
    public Publisher getEntityById(Long id) {
        return publisherRepository.findById(id)
                .orElseThrow(() -> new EBookException("Publisher with id:" + id + " was not found"));
    }

    @Override
    public PublisherModel getById(Long id) {
        return toModel(getEntityById(id));
    }

    @Override
    public List<PublisherModel> getAll() {
        return publisherRepository.findAll()
                .stream()
                .map(publisher -> toModel(publisher))
                .collect(Collectors.toList());
    }

    @Override
    public PublisherModel toModel(Publisher publisher) {
        return new PublisherModel(publisher.getId(), publisher.getName());
    }

    @Override
    public PublisherModel update(PublisherModel publisherModel) {
        Publisher publisher = getEntityById(publisherModel.getId());
        String name = publisherModel.getName();
        if (name != null){
            publisher.setName(name);
        }

        publisherRepository.save(publisher);
        return publisherModel;
    }
}
