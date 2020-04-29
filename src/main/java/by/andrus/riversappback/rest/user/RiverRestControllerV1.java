package by.andrus.riversappback.rest.user;

import by.andrus.riversappback.dto.RiverDto;
import by.andrus.riversappback.model.River;
import by.andrus.riversappback.service.RiverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/rivers/")
public class RiverRestControllerV1 {
    private final RiverService riverService;

    @Autowired
    public RiverRestControllerV1(RiverService riverService) {
        this.riverService = riverService;
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
}
