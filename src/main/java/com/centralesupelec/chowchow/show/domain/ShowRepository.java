package com.centralesupelec.chowchow.show.domain;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShowRepository extends JpaRepository<ShowEntity, String> {

  ShowEntity findById(final Long id);

  @Override
  List<ShowEntity> findAll();
}
