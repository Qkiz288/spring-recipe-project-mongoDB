package com.kkukielka.springrecipeproject.services;

import com.kkukielka.springrecipeproject.commands.UnitOfMeasureCommand;
import com.kkukielka.springrecipeproject.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.kkukielka.springrecipeproject.domain.UnitOfMeasure;
import com.kkukielka.springrecipeproject.repositories.UnitOfMeasureRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;
import static org.junit.Assert.assertEquals;

public class UnitOfMeasureServiceImplTest {

    UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand = new UnitOfMeasureToUnitOfMeasureCommand();
    UnitOfMeasureService service;

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        service = new UnitOfMeasureServiceImpl(unitOfMeasureRepository, unitOfMeasureToUnitOfMeasureCommand);
    }

    @Test
    public void listAllUoms() {
        // given
        Set<UnitOfMeasure> unitOfMeasures = new HashSet();
        UnitOfMeasure uom1 = new UnitOfMeasure();
        uom1.setId("1");
        unitOfMeasures.add(uom1);

        UnitOfMeasure uom2 = new UnitOfMeasure();
        uom2.setId("2");
        unitOfMeasures.add(uom2);

        when(unitOfMeasureRepository.findAll()).thenReturn(unitOfMeasures);

        // when
        Set<UnitOfMeasureCommand> commands = service.listAllUoms();

        // then
        assertEquals(2, commands.size());
        verify(unitOfMeasureRepository, times(1)).findAll();

    }
}