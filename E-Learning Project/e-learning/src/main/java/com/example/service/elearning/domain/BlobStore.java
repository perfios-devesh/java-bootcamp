package com.example.service.elearning.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import javax.persistence.*;

@Entity
@Data
public class BlobStore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long blobId;
    private String blobType;
    @Lob
    private byte[] data;
    private String section;
    private String itemHeading;
    private Long itemDuration;
    private String itemType;            //is it video or reading document.

    @ManyToOne
    @JoinColumn(name="course")
    @JsonBackReference
    private Course course;
}
