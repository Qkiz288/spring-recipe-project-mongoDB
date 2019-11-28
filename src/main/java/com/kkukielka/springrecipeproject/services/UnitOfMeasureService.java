package com.kkukielka.springrecipeproject.services;

import com.kkukielka.springrecipeproject.commands.UnitOfMeasureCommand;

import java.util.Set;

public interface UnitOfMeasureService {
    Set<UnitOfMeasureCommand> listAllUoms();
}
