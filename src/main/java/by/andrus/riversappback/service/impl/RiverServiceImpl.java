package by.andrus.riversappback.service.impl;

import by.andrus.riversappback.model.River;
import by.andrus.riversappback.model.Status;
import by.andrus.riversappback.repository.RiverRepository;
import by.andrus.riversappback.service.RiverService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RiverServiceImpl implements RiverService {
    private final RiverRepository riverRepository;

    @Autowired
    public RiverServiceImpl(RiverRepository riverRepository) {
        this.riverRepository = riverRepository;
    }

    @Override
    public River create(River river) {
        river.setStatus(Status.ACTIVE);
        River createdRiver = riverRepository.save(river);
        log.info("IN RiverServiceImpl.create river: {} successfully created", createdRiver);
        return createdRiver;
    }

    @Override
    public List<River> getAll() {
        List<River> rivers = riverRepository.findAll();
        log.info("IN RiverServiceImpl.getAll: {} rivers found", rivers.size());
        return rivers;
    }

    @Override
    public River getById(Long id) {
        return null;
    }

    @Override
    public River update(River changedRiver) throws IllegalAccessException {
        River dbRiver = getById(changedRiver.getId());
        if (dbRiver == null)
            throw new IllegalArgumentException("There is NO such river in DB");
        changedRiver.addMissing(dbRiver);
        River updatedRiver = riverRepository.save(changedRiver);
        log.info("IN RiverServiceImpl.update river: {} successfully updated", updatedRiver);
        return updatedRiver;
    }

    @Override
    public void deleteById(Long id) {
        riverRepository.deleteById(id);
        log.info("IN riverServiceImpl.deleteById - river with id: {} successfully deleted", id);
    }
}
