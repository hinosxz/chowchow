package com.centralesupelec.chowchow.show.domain;
import com.centralesupelec.chowchow.showRating.domain.ShowRatingEntity;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Shows")
public class ShowEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private int tmdb_id;

    @OneToMany(mappedBy = "show")
    Set<ShowRatingEntity> showRating;

    private String name;

    @Column(nullable = false)
    private int year;

    public Long getId() {
        return id;
    }

    public int getTMDBId() {
        return tmdb_id;
    }

    public void setTMDBId(int tmdbId) {
        this.tmdb_id = tmdbId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public Set<ShowRatingEntity> getShowRating() {return showRating;}

    public void setYear(int year) {
        this.year = year;
    }
}
