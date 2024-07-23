package searchengine.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "pages",
        indexes = @Index(name = "path_index", columnList = "path"),
        uniqueConstraints = { @UniqueConstraint(columnNames = { "path", "site_id" }) }
)
public class PageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, columnDefinition = "varchar(765)")
    private String path;

    @Column(nullable = false)
    private int code;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "site_id", referencedColumnName = "id", nullable = false)
    private SiteEntity site;

    @OneToMany(mappedBy = "page", cascade = CascadeType.ALL)
    private List<IndexEntity> indexEntities;

    public PageEntity(String pathToSave, int httpStatusCode, String html, SiteEntity siteEntity) {
        this.path = pathToSave;
        this.code = httpStatusCode;
        this.content = html;
        this.site = siteEntity;
    }
}
