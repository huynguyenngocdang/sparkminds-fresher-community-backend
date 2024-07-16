package com.example.sparkminds_community.entity;

        import com.fasterxml.jackson.annotation.JsonBackReference;
        import jakarta.persistence.Entity;
        import jakarta.persistence.FetchType;
        import jakarta.persistence.GeneratedValue;
        import jakarta.persistence.GenerationType;
        import jakarta.persistence.Id;
        import jakarta.persistence.JoinColumn;
        import jakarta.persistence.ManyToOne;
        import jakarta.persistence.OneToMany;
        import lombok.AllArgsConstructor;
        import lombok.Builder;
        import lombok.Getter;
        import lombok.NoArgsConstructor;
        import lombok.Setter;

        import java.util.ArrayList;
        import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private String imageUrl;
    private String postType;
    private Long totalLikes;
    private String createdDate;
    private boolean isDelete;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FK_USER_ID")
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<PostLike> postLikes = new ArrayList<>();

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<PostDislike> postDislikes = new ArrayList<>();

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<PostComment> postComments = new ArrayList<>();
}
