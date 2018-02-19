package com.example.demo;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource(collectionResourceRel = "people", path = "people")
public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {

    @RestResource(exported = false)
    @Override
    <S extends Person> S save(S s);

    @RestResource(exported = false)
    @Override
    <S extends Person> Iterable<S> save(Iterable<S> iterable);
}
