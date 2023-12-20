package com.miraclerun.petopia.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "upload")
public class Upload {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "upload_id")
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feed_id")
    private Feed feed;

    @NotNull
    private String fileName;

    @NotNull
    private String filePath;

    @NotNull
    private Long fileSize;

    public Upload() {
    }

    //==생성 메서드==//
    @Builder
    public Upload(Feed feed, String fileName, String filePath, Long fileSize) {
        this.feed = feed;
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }
}
