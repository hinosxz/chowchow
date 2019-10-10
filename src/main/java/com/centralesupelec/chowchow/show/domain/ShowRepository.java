package com.centralesupelec.chowchow.show.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepository  extends JpaRepository<ShowEntity,String> {

    ShowEntity findById(final Long id);
}
