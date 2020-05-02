package by.andrus.riversappback.rest.admin;

import by.andrus.riversappback.dto.RiverDto;
import by.andrus.riversappback.model.River;
import by.andrus.riversappback.service.RiverService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/admin/rivers")
@Slf4j
public class AdminRiverRestControllerV1 {
    private final RiverService riverService;

    @Autowired
    public AdminRiverRestControllerV1(RiverService riverService) {
        this.riverService = riverService;
    }

    @PostMapping
    public ResponseEntity<RiverDto> createRiver(@RequestBody @Valid RiverDto riverDto) {
        River createdRiver;
        try {
            createdRiver = this.riverService.create(riverDto.toRiver());
        } catch (Throwable throwable) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(RiverDto.fromRiver(createdRiver), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RiverDto>> getAllRivers() {
        List<River> rivers = riverService.getAll();
        return rivers == null
                ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(
                rivers
                        .stream().map(RiverDto::fromRiver)
                        .collect(Collectors.toList())
                , HttpStatus.OK
        );
    }

    @PutMapping
    public ResponseEntity<RiverDto> updateRiver(@RequestBody @Valid RiverDto riverDto) {
        River updatedRiver;
        try {
            updatedRiver = this.riverService.update(riverDto.toRiver());
        } catch (Throwable throwable) {
            log.info(throwable.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(RiverDto.fromRiver(updatedRiver), HttpStatus.OK);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<HttpStatus> deleteRiverById(@PathVariable(name = "id") Long id) {
        try {
            riverService.deleteById(id);
        } catch (Throwable throwable) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
