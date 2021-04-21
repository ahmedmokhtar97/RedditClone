package com.mokhtar.redditclone.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@ToString
@NoArgsConstructor
public class Comment extends Auditable {

    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private String body;


    @ManyToOne
    @NonNull
    private Link link;


}
