package com.fact.finder.model;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "FACT_TABLE")
public class FunFact implements Serializable {
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
}
