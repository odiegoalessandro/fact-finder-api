package com.fact.finder;

import com.fact.finder.dto.FactDto;
import com.fact.finder.model.Fact;
import com.fact.finder.repository.FactRepository;
import com.fact.finder.services.FactService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.then;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class FactServiceTests {
    @InjectMocks
    private FactService factService;

    @Mock
    private FactRepository factRepository;

    @Captor
    private ArgumentCaptor<Fact> factCaptor;

    @Test
    @DisplayName("Deve retornar um fato criado com sucesso sem informações nulas")
    void shouldReturnCreatedFact(){
        FactDto factDto = new FactDto("2+2=4", "2+2=4", "FactServiceTests");

        factService.save(factDto);

        then(factRepository).should().save(factCaptor.capture());
        Assertions.assertEquals(factDto.getTitle(), factCaptor.getValue().getTitle());
        Assertions.assertEquals(factDto.getBody(), factCaptor.getValue().getBody());
        Assertions.assertEquals(factDto.getSource(), factCaptor.getValue().getSource());
    }

    @Test
    @DisplayName("Deve retornar um fato aleatorio de uma lista com itens dentro dela")
    void shouldReturnRandomFact(){
        FactDto factDto = new FactDto("2+2=4", "2+2=4", "FactServiceTests");
        FactDto factDto2 = new FactDto("3+3=6", "3+3=", "FactServiceTests");

        Fact fact = new Fact(factDto);
        Fact fact2 = new Fact(factDto2);

        List<Fact> facts = List.of(fact, fact2);
        BDDMockito.given(factRepository.findAll()).willReturn(facts);

        Fact randomFact = factService.getRandomFact();

        Assertions.assertTrue(facts.contains(randomFact));
    }


    @Test
    @DisplayName("Deve retornar um erro ao tentar obter um fato de uma lista vazia")
    void shouldReturnAnErrorWhenPickingUpARandomFact(){
         BDDMockito.given(factRepository.findAll()).willReturn(new ArrayList<>());

        Assertions.assertThrows(Exception.class, () -> factService.getRandomFact());
    }

    @Test
    @DisplayName("Deve retornar toda a lista de fatos com um fato dentro")
    void shouldReturnListOfFactsWithFactWithin(){
        FactDto factDto = new FactDto("2+2=4", "2+2=4", "FactServiceTests");
        Fact fact = new Fact(factDto);

        List<Fact> facts = List.of(fact);

        BDDMockito.given(factRepository.findAll()).willReturn(facts);

        Assertions.assertEquals(factService.getAllFacts(), facts);
    }

    @Test
    @DisplayName("Deve retornar uma lista vazia tentar obter a lista de fatos sem nada dentro")
    void shouldReturnAEmptyList(){
        List<Fact> facts = new ArrayList<>();

        BDDMockito.given(factRepository.findAll()).willReturn(facts);

        Assertions.assertTrue(factService.getAllFacts().isEmpty());
    }
}
