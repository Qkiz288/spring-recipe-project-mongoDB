package com.kkukielka.springrecipeproject.services;

import com.kkukielka.springrecipeproject.commands.UnitOfMeasureCommand;
import com.kkukielka.springrecipeproject.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.kkukielka.springrecipeproject.domain.UnitOfMeasure;
import com.kkukielka.springrecipeproject.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository, UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;

        this.unitOfMeasureToUnitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand;
    }

    @Override
    public Set<UnitOfMeasureCommand> listAllUoms() {
        Iterator<UnitOfMeasure> iterator = unitOfMeasureRepository.findAll().iterator();
        Set<UnitOfMeasureCommand> unitOfMeasureCommands = new HashSet<>();

        while (iterator.hasNext()) {
            UnitOfMeasureCommand unitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand.convert(iterator.next());
            unitOfMeasureCommands.add(unitOfMeasureCommand);
        }

        return unitOfMeasureCommands;
    }
}
