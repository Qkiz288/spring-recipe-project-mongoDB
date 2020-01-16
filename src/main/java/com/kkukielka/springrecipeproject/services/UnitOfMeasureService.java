package com.kkukielka.springrecipeproject.services;

import com.kkukielka.springrecipeproject.commands.UnitOfMeasureCommand;
import reactor.core.publisher.Flux;

public interface UnitOfMeasureService {
    Flux<UnitOfMeasureCommand> listAllUoms();
}
