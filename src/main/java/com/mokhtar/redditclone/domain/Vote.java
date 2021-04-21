package com.mokhtar.redditclone.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@NoArgsConstructor
public class Vote {

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private int vote;

}
