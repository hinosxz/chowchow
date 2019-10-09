package com.centralesupelec.chowchow.show.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShowRepository extends JpaRepository<ShowEntity,String> {


    ShowEntity findById(final Long id);

    List<ShowEntity> findAll();

}
