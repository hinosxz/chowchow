package com.centralesupelec.chowchow.show.controllers;
import com.centralesupelec.chowchow.showRating.domain.ShowRatingEntity;
import com.centralesupelec.chowchow.trakt.controllers.TraktShowDTO;
import com.centralesupelec.chowchow.show.domain.ShowEntity;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Set;

@JsonInclude(JsonInclude.Include.NON_NULL) // Ignore the null values when parsing into Json
public class ShowDTO {
    private final Long id;
    private final int traktId;
    private final String title;
    private final int year;
    private final String overview;
    private final String trailer;
    private final String homepage;
    private final List<String> genres;
    private final Integer airedEpisodes;
    private final Set<ShowRatingEntity> likedShows;

    @JsonCreator
    ShowDTO(
            @JsonProperty("id") Long id,
            @JsonProperty("traktId") Integer traktId,
            @JsonProperty("title") String title,
            @JsonProperty("year") Integer year,
            @JsonProperty("overview") String overview,
            @JsonProperty("trailer") String trailer,
            @JsonProperty("homepage") String homepage,
            @JsonProperty("genres") List<String> genres,
            @JsonProperty("airedEpisodes") Integer airedEpisodes,
            @JsonProperty("likedShows") Set<ShowRatingEntity> likedShows
            ) {
        this.id = id;
        this.traktId = traktId;
        this.title = title;
        this.year = year;
        this.overview = overview;
        this.trailer = trailer;
        this.homepage = homepage;
        this.genres = genres;
        this.airedEpisodes = airedEpisodes;
        this.likedShows = likedShows;
    }

    public static ShowDTO fromEntity(ShowEntity showEntity) {
        return new ShowDTO(
            showEntity.getId(),
            showEntity.getTraktId(),
            showEntity.getName(),
            showEntity.getYear(),
            null,
            null,
            null,
            null,
            null,
                showEntity.getShowRating()
        );
    }

    public static ShowEntity toEntity(ShowDTO showDTO) {
        ShowEntity showEntity = new ShowEntity();
        showEntity.setTraktId(showDTO.traktId);
        showEntity.setName(showDTO.title);
        showEntity.setYear(showDTO.year);
        return showEntity;
    }

    public static ShowDTO fromTraktShowDTO(TraktShowDTO traktShowDTO) {
        return new ShowDTO(
            null,
                traktShowDTO.getIdsDTO().getTrakt(),
                traktShowDTO.getTitle(),
                traktShowDTO.getYear(),
                traktShowDTO.getOverview(),
                traktShowDTO.getTrailer(),
                traktShowDTO.getHomepage(),
                traktShowDTO.getGenres(),
                traktShowDTO.getAiredEpisodes(),
                null
        );
    }

    public Long getId() {
        return id;
    }

    public int getTraktId() {
        return traktId;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public String getOverview() {
        return overview;
    }

    public String getTrailer() {
        return trailer;
    }

    public String getHomepage() {
        return homepage;
    }

    public List<String> getGenres() {
        return genres;
    }

    public Integer getAiredEpisodes() {
        return airedEpisodes;
    }

    public Set<ShowRatingEntity> getLikedShows() {return likedShows;}
}
