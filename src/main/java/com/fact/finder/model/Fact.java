package com.fact.finder.model;

import com.fact.finder.dto.FactDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Entity
@Table(name = "FACT_TABLE")
public class Fact implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Builder.Default
    private String title = "N/A";

    @Builder.Default
    private String body = "N/A";

    @Builder.Default
    private String source = "N/A";

    public Fact(FactDto factDto) {
        this.title = factDto.getTitle();
        this.body = factDto.getBody();
        this.source = factDto.getSource();
    }
}
