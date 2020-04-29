package by.andrus.riversappback.service;

import by.andrus.riversappback.model.River;

import java.util.List;

public interface RiverService {
    River create(River river);

    List<River> getAll();

    River getById(Long id);

    River update(River river) throws InstantiationException, IllegalAccessException;

    void deleteById(Long id);
}
